/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.module.cams.dao.ojb;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.apache.ojb.broker.query.QueryFactory;
import org.apache.ojb.broker.query.ReportQueryByCriteria;
import org.kuali.core.bo.DocumentHeader;
import org.kuali.core.dao.ojb.PlatformAwareDaoBaseOjb;
import org.kuali.kfs.KFSConstants;
import org.kuali.kfs.KFSPropertyConstants;
import org.kuali.kfs.bo.GeneralLedgerPendingEntry;
import org.kuali.kfs.context.SpringContext;
import org.kuali.kfs.dao.ojb.GeneralLedgerPendingEntryDaoOjb;
import org.kuali.kfs.service.ParameterService;
import org.kuali.kfs.service.impl.ParameterConstants;
import org.kuali.module.cams.bo.Asset;
import org.kuali.module.cams.bo.AssetObjectCode;
import org.kuali.module.cams.bo.AssetPayment;
import org.kuali.module.cams.bo.AssetType;
import org.kuali.module.cams.dao.DepreciableAssetsDao;
import org.kuali.module.chart.bo.Account;
import org.kuali.module.chart.bo.ObjectCode;
import org.kuali.module.chart.bo.Org;
import org.kuali.module.financial.document.DisbursementVoucherDocument;
import org.kuali.module.financial.document.GeneralErrorCorrectionDocument;
import org.kuali.module.gl.bo.Balance;
import org.kuali.module.gl.bo.Entry;
import org.kuali.core.service.DateTimeService;
import org.kuali.core.util.KualiDecimal;
import org.kuali.core.util.ObjectUtils;
import org.kuali.module.cams.bo.DepreciableAssets;
import org.kuali.module.cams.CamsConstants;
import org.kuali.module.cams.CamsPropertyConstants;
import org.springmodules.orm.ojb.PersistenceBrokerTemplate;

public class DepreciableAssetsDaoOjb extends PlatformAwareDaoBaseOjb implements DepreciableAssetsDao {
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(DepreciableAssetsDaoOjb.class);
    private Integer fiscalYear = new Integer(0);
    private Integer fiscalMonth= new Integer(0);

    Collection<DepreciableAssets> depreciableAssetsCollection = new ArrayList<DepreciableAssets>();       
    private Criteria assetCriteria      = new Criteria();
    private List<String[]>reportLine    = new ArrayList<String[]>();
    private String errorMsg             = new String();
    private List<String>depreExpObjCodes= new ArrayList<String>();
    private List<String>accumulatedDepreciationObjCodes= new ArrayList<String>();
    private Collection<AssetObjectCode> objectCodes;


    /**
     * 
     * @see org.kuali.module.cams.dao.CamsDepreciableAssetsDao#initDepreciation(java.lang.Integer, java.lang.Integer)
     */
    public void initDepreciation(Integer fiscalYear, Integer fiscalMonth) {
        LOG.debug("CamsDepreciableAssetsDaoOjb.initDepreciation(Integer fiscalYear, Integer fiscalMonth) -  started");
        this.fiscalYear = fiscalYear;
        this.fiscalMonth= fiscalMonth;
        this.objectCodes = this.getObjectCodes();            
        this.createDepreciationCriteria();
        this.checkSum(true);
        LOG.debug("CamsDepreciableAssetsDaoOjb.initDepreciation(Integer fiscalYear, Integer fiscalMonth) -  ended");
    }

    /**
     * 
     * This method...
     */
    private void createDepreciationCriteria() {
        LOG.debug("CamsDepreciableAssetsDaoOjb.createDepreciationCriteria() -  started");

        ParameterService parameterService = SpringContext.getBean(ParameterService.class);

        List<String> assetTransferCode              = new ArrayList<String>(); 
        List<String> depreciationMethodList         = new ArrayList<String>();
        List<String> federallyOwnedObjectSybTypes   = new ArrayList<String>();       
        List<String> notAcceptedAssetStatus         = new ArrayList<String>();

        assetTransferCode.add("N");
        assetTransferCode.add(" ");

        depreciationMethodList.add(CamsConstants.DEPRECIATION_METHOD_SALVAGE_VALUE_CODE);
        depreciationMethodList.add(CamsConstants.DEPRECIATION_METHOD_STRAIGHT_LINE_CODE);

        if (parameterService.parameterExists(ParameterConstants.CAPITAL_ASSETS_BATCH.class,CamsConstants.Parameters.NON_DEPRECIABLE_FEDERALLY_OWNED_OBJECT_SUB_TYPES)){
            federallyOwnedObjectSybTypes = parameterService.getParameterValues(ParameterConstants.CAPITAL_ASSETS_BATCH.class,CamsConstants.Parameters.NON_DEPRECIABLE_FEDERALLY_OWNED_OBJECT_SUB_TYPES);
        }
        if (parameterService.parameterExists(ParameterConstants.CAPITAL_ASSETS_BATCH.class,CamsConstants.Parameters.NON_DEPRECIABLE_NON_CAPITAL_ASSETS_STATUS_CODES)){
            notAcceptedAssetStatus = parameterService.getParameterValues(ParameterConstants.CAPITAL_ASSETS_BATCH.class,CamsConstants.Parameters.NON_DEPRECIABLE_NON_CAPITAL_ASSETS_STATUS_CODES);
        }
        DateTimeService dateTimeService = SpringContext.getBean(DateTimeService.class);
        Date depreciationDate = dateTimeService.getCurrentSqlDateMidnight();        

        Criteria criteria = new Criteria();
        Criteria criteriaB = new Criteria();
        Criteria criteriaC = new Criteria();


        criteria.addEqualTo(CamsPropertyConstants.AssetPayment.ORIGINATION_CODE,"01"); //01 - Transaction Processing

        // Begin ************
        criteriaB = new Criteria();
        criteriaB.addNotNull(CamsPropertyConstants.AssetPayment.PRIMARY_DEPRECIATION_BASE_AMOUNT);

        criteriaC = new Criteria();
        criteriaC.addNotEqualTo(CamsPropertyConstants.AssetPayment.PRIMARY_DEPRECIATION_BASE_AMOUNT,0);   

        criteriaB.addOrCriteria(criteriaC);
        criteria.addAndCriteria(criteriaB);
        // End ***************

        //Begin *******************************************************************
        criteriaB = new Criteria();        
        criteriaB.addIn(CamsPropertyConstants.AssetPayment.TRANSFER_PAYMENT_CODE, assetTransferCode);

        criteriaC = new Criteria();
        criteriaC.addIsNull(CamsPropertyConstants.AssetPayment.TRANSFER_PAYMENT_CODE);

        criteriaB.addOrCriteria(criteriaC);
        criteria.addAndCriteria(criteriaB);
        //End *********************************************************************        

        // Begin  **********
        criteria.addIn("asset."+CamsPropertyConstants.Asset.PRIMARY_DEPRECIATION_METHOD, depreciationMethodList);        
        // End    **********

        criteria.addNotNull        ("asset."+CamsPropertyConstants.Asset.ASSET_DATE_OF_SERVICE);                      
        criteria.addLessOrEqualThan("asset."+CamsPropertyConstants.Asset.ASSET_DATE_OF_SERVICE,dateTimeService.toString(depreciationDate,"yyyy/MM/dd"));
        criteria.addGreaterThan    ("asset."+CamsPropertyConstants.Asset.ASSET_DATE_OF_SERVICE,"1900/01/01");

        //Begin *******************************************************************
        criteriaB = new Criteria();        
        criteriaB.addGreaterThan("asset."+CamsPropertyConstants.Asset.ASSET_RETIREMENT_FISCAL_YEAR, fiscalYear);

        criteriaC = new Criteria();
        criteriaC.addIsNull("asset."+CamsPropertyConstants.Asset.ASSET_RETIREMENT_FISCAL_YEAR);

        Criteria criteriaD = new Criteria();
        criteriaD.addNotNull("asset."+CamsPropertyConstants.Asset.ASSET_RETIREMENT_FISCAL_MONTH);

        criteriaB.addOrCriteria(criteriaC);
        criteriaB.addOrCriteria(criteriaD);                

        criteriaC = new Criteria();
        criteriaC.addEqualTo    ("asset."+CamsPropertyConstants.Asset.ASSET_RETIREMENT_FISCAL_YEAR,fiscalYear);
        criteriaC.addGreaterThan("asset."+CamsPropertyConstants.Asset.ASSET_RETIREMENT_FISCAL_MONTH,fiscalMonth);

        criteriaB.addOrCriteria(criteriaC);
        criteria.addAndCriteria(criteriaB);
        //End *********************************************************************            

        if (!notAcceptedAssetStatus.isEmpty())
            criteria.addNotIn("asset."+CamsPropertyConstants.Asset.ASSET_INVENTORY_STATUS,notAcceptedAssetStatus);

        criteria.addGreaterThan ("asset.capitalAssetType."+CamsPropertyConstants.AssetType.ASSET_DEPRECIATION_LIFE_LIMIT,0);

        //Excluding federally owned assets.
        if (!federallyOwnedObjectSybTypes.isEmpty())
            criteria.addNotIn("financialObject."+KFSPropertyConstants.FINANCIAL_OBJECT_SUB_TYPE_CODE,federallyOwnedObjectSybTypes);

        // DELETE THIS LINE ****************************************** 
        criteria.addEqualTo("capitalAssetNumber", "389220");
        //************************************************************

        this.assetCriteria = criteria;
        LOG.debug("CamsDepreciableAssetsDaoOjb.createDepreciationCriteria() -  ended");
    }

    /**
     * 
     * @see org.kuali.module.cams.dao.CamsDepreciableAssetsDao#getListOfDepreciableAssets()
     */
    public Collection<DepreciableAssets> getListOfDepreciableAssets() {        
        LOG.debug("CamsDepreciableAssetsDaoOjb.getListOfDepreciableAssets() -  started");

        QueryByCriteria q = QueryFactory.newQuery(AssetPayment.class, this.assetCriteria); //, this.assetCriteria);     
        q.addOrderByAscending(CamsPropertyConstants.AssetPayment.CAPITAL_ASSET_NUMBER);  
        q.addOrderByAscending(CamsPropertyConstants.AssetPayment.ORIGINATION_CODE);
        q.addOrderByAscending(CamsPropertyConstants.AssetPayment.ACCOUNT_NUMBER);
        q.addOrderByAscending(CamsPropertyConstants.AssetPayment.SUB_ACCOUNT_NUMBER);
        q.addOrderByAscending(CamsPropertyConstants.AssetPayment.OBJECT_CODE);
        q.addOrderByAscending(CamsPropertyConstants.AssetPayment.SUB_OBJECT_CODE);
        q.addOrderByAscending(CamsPropertyConstants.AssetPayment.OBJECT_TYPE_CODE);
        q.addOrderByAscending(CamsPropertyConstants.AssetPayment.PROJECT_CODE);
        Collection<AssetPayment> iter = getPersistenceBrokerTemplate().getCollectionByQuery(q);

        DepreciableAssets depreciableAssets;

        //int a=0;
        for (AssetPayment assetPayment : iter) {
            //   a++;
            Asset asset = assetPayment.getAsset();

            AssetType assetType = asset.getCapitalAssetType();

            //LOG.debug("************* READING PAYMENT ************* "+a);
            Account account = assetPayment.getAccount();
            //LOG.debug("We have an account... " + account.toString());
            Org org = account.getOrganization();
            //LOG.debug("We have an org... " + org.toString());
            ObjectCode financialObject = assetPayment.getFinancialObject();
            //LOG.debug("We have an obj... " + financialObject.toString());
            //LOG.debug("******************************************");

            depreciableAssets = new DepreciableAssets(); 

            depreciableAssets.setInventoryStatusCode            (asset.getInventoryStatusCode());                
            depreciableAssets.setCapitalAssetNumber             (asset.getCapitalAssetNumber());
            depreciableAssets.setCapitalAssetTypeCode           (asset.getCapitalAssetTypeCode());

            depreciableAssets.setPrimaryDepreciationMethodCode  (asset.getPrimaryDepreciationMethodCode());
            depreciableAssets.setCapitalAssetInServiceDate      (asset.getCapitalAssetInServiceDate());
            depreciableAssets.setSalvageAmount                  (asset.getSalvageAmount());
            depreciableAssets.setDepreciableLifeLimit           (assetType.getDepreciableLifeLimit());

            depreciableAssets.setTransferPaymentCode            (assetPayment.getTransferPaymentCode());
            depreciableAssets.setFinancialSystemOriginationCode (assetPayment.getFinancialSystemOriginationCode());

            depreciableAssets.setPaymentSequenceNumber          (assetPayment.getPaymentSequenceNumber());
            depreciableAssets.setChartOfAccountsCode            (assetPayment.getChartOfAccountsCode());
            depreciableAssets.setAccountNumber                  (assetPayment.getAccountNumber());
            depreciableAssets.setSubAccountNumber               (assetPayment.getSubAccountNumber());
            depreciableAssets.setFinancialObjectCode            (assetPayment.getFinancialObjectCode());
            depreciableAssets.setFinancialSubObjectCode         (assetPayment.getFinancialSubObjectCode());           
            depreciableAssets.setPrimaryDepreciationBaseAmount  (assetPayment.getPrimaryDepreciationBaseAmount());

            depreciableAssets.setAccumulatedPrimaryDepreciationAmount (assetPayment.getAccumulatedPrimaryDepreciationAmount());
            depreciableAssets.setPreviousYearPrimaryDepreciationAmount(assetPayment.getPreviousYearPrimaryDepreciationAmount());

            depreciableAssets.setPeriod1Depreciation1Amount (assetPayment.getPeriod1Depreciation1Amount());
            depreciableAssets.setPeriod2Depreciation1Amount (assetPayment.getPeriod2Depreciation1Amount());
            depreciableAssets.setPeriod3Depreciation1Amount (assetPayment.getPeriod3Depreciation1Amount());
            depreciableAssets.setPeriod4Depreciation1Amount (assetPayment.getPeriod4Depreciation1Amount());
            depreciableAssets.setPeriod5Depreciation1Amount (assetPayment.getPeriod5Depreciation1Amount());
            depreciableAssets.setPeriod6Depreciation1Amount (assetPayment.getPeriod6Depreciation1Amount());
            depreciableAssets.setPeriod7Depreciation1Amount (assetPayment.getPeriod7Depreciation1Amount());
            depreciableAssets.setPeriod8Depreciation1Amount (assetPayment.getPeriod8Depreciation1Amount());
            depreciableAssets.setPeriod9Depreciation1Amount (assetPayment.getPeriod9Depreciation1Amount());
            depreciableAssets.setPeriod10Depreciation1Amount(assetPayment.getPeriod10Depreciation1Amount());
            depreciableAssets.setPeriod11Depreciation1Amount(assetPayment.getPeriod11Depreciation1Amount());
            depreciableAssets.setPeriod12Depreciation1Amount(assetPayment.getPeriod12Depreciation1Amount());
            depreciableAssets.setProjectCode                (assetPayment.getProjectCode());
            depreciableAssets.setDocumentNumber             (assetPayment.getDocumentNumber());

            depreciableAssets.setFinancialObjectSubTypeCode (financialObject.getFinancialObjectSubTypeCode());
            depreciableAssets.setFinancialObjectTypeCode    (financialObject.getFinancialObjectTypeCode());

            depreciableAssets.setOrganizationPlantChartCode                 (org.getOrganizationPlantChartCode());                    
            depreciableAssets.setOrganizationPlantAccountNumber             (org.getOrganizationPlantAccountNumber());
            depreciableAssets.setCampusPlantAccountNumber                   (org.getCampusPlantAccountNumber());
            depreciableAssets.setCampusPlantChartCode                       (org.getCampusPlantChartCode());

            //LOG.debug("************* OBJECT CODES *************");
            for (AssetObjectCode assetObjectCodes : objectCodes) {
                //LOG.debug("****AssetObject:"+assetObjectCodes.toString());
                boolean found=false;
                List<ObjectCode> objectCodesList = assetObjectCodes.getObjectCode();                     
                for (ObjectCode oc : objectCodesList) {
                    //LOG.debug("*****Object Code:"+oc.getFinancialObjectCode()+ " - Payment object code:"+assetPayment.getFinancialObjectCode());
                    if (oc.getFinancialObjectCode().equals(assetPayment.getFinancialObjectCode())) {
                        //LOG.debug("****Payment Object Code Found!!!"+assetPayment.getFinancialObjectCode()+" "+assetObjectCodes.getCapitalizationFinancialObjectCode()+" "+assetObjectCodes.getAccumulatedDepreciationFinancialObjectCode()+" "+assetObjectCodes.getDepreciationExpenseFinancialObjectCode());
                        depreciableAssets.setCapitalizationFinancialObjectCode          (assetObjectCodes.getCapitalizationFinancialObjectCode());
                        depreciableAssets.setAccumulatedDepreciationFinancialObjectCode (assetObjectCodes.getAccumulatedDepreciationFinancialObjectCode());
                        depreciableAssets.setDepreciationExpenseFinancialObjectCode     (assetObjectCodes.getDepreciationExpenseFinancialObjectCode());
                        found=true;
                        break;
                    }
                }
                if (found)
                    break;
            }                
            depreciableAssetsCollection.add(depreciableAssets);
        }
        LOG.debug("CamsDepreciableAssetsDaoOjb.getListOfDepreciableAssets() -  ended");
        return depreciableAssetsCollection;
    }
    /**
     * 
     * @see org.kuali.module.cams.dao.CamsDepreciableAssetsDao#updateAssetPayments(java.util.List)
     */
    public void updateAssetPayments(List<DepreciableAssets> depreciatedAssets) {
        LOG.debug("CamsDepreciableAssetsDaoOjb.updateAssetPayments(List<CamsDepreciableAssets> depreciatedAssets) -  started");
        
        Criteria criteria = new Criteria();
        Collection<AssetPayment> assetPayments;

        // If we are in the first month of the fiscal year, then add the previous year depreciation amounts into previous year depreciation field
        // This for all the assets in the payment table.
        //LOG.debug("**** Fiscal Month:"+this.fiscalMonth);
        if (this.fiscalMonth == 1) {
            criteria.addNotNull(CamsPropertyConstants.AssetPayment.CAPITAL_ASSET_NUMBER);
            QueryByCriteria q = QueryFactory.newQuery(AssetPayment.class,criteria);     
            assetPayments = getPersistenceBrokerTemplate().getCollectionByQuery(q);

            for (AssetPayment assetPayment : assetPayments) {
                if (assetPayment != null) {
                    //LOG.debug("**** Asset#: "+assetPayment.getCapitalAssetNumber()+" - Seq#: "+assetPayment.getPaymentSequenceNumber());

                    assetPayment.setPreviousYearPrimaryDepreciationAmount(assetPayment.getPeriod1Depreciation1Amount().add(
                    assetPayment.getPeriod2Depreciation1Amount()).add(assetPayment.getPeriod3Depreciation1Amount()).add(
                    assetPayment.getPeriod4Depreciation1Amount()).add(assetPayment.getPeriod5Depreciation1Amount()).add(
                    assetPayment.getPeriod6Depreciation1Amount()).add(
                    assetPayment.getPeriod7Depreciation1Amount()).add(assetPayment.getPeriod8Depreciation1Amount()).add(
                    assetPayment.getPeriod9Depreciation1Amount()).add(assetPayment.getPeriod10Depreciation1Amount()).add(
                    assetPayment.getPeriod11Depreciation1Amount()).add(assetPayment.getPeriod12Depreciation1Amount()));

                    assetPayment.setPeriod1Depreciation1Amount(new KualiDecimal(0));                
                    assetPayment.setPeriod2Depreciation1Amount(new KualiDecimal(0));
                    assetPayment.setPeriod3Depreciation1Amount(new KualiDecimal(0));
                    assetPayment.setPeriod4Depreciation1Amount(new KualiDecimal(0));
                    assetPayment.setPeriod5Depreciation1Amount(new KualiDecimal(0));
                    assetPayment.setPeriod6Depreciation1Amount(new KualiDecimal(0));
                    assetPayment.setPeriod7Depreciation1Amount(new KualiDecimal(0));
                    assetPayment.setPeriod8Depreciation1Amount(new KualiDecimal(0));
                    assetPayment.setPeriod9Depreciation1Amount(new KualiDecimal(0));
                    assetPayment.setPeriod10Depreciation1Amount(new KualiDecimal(0));
                    assetPayment.setPeriod11Depreciation1Amount(new KualiDecimal(0));
                    assetPayment.setPeriod12Depreciation1Amount(new KualiDecimal(0));

                    getPersistenceBrokerTemplate().store(assetPayment);
                }
            }
        }

        // Storing depreciation amounts
        for(DepreciableAssets d : depreciatedAssets) {
            criteria = new Criteria();
            criteria.addEqualTo(CamsPropertyConstants.AssetPayment.CAPITAL_ASSET_NUMBER ,d.getCapitalAssetNumber() );
            criteria.addEqualTo(CamsPropertyConstants.AssetPayment.PAYMENT_SEQ_NUMBER   ,d.getPaymentSequenceNumber() );
            //criteria.addEqualTo("financialSystemOriginationCode",   d.getFinancialSystemOriginationCode() );

            AssetPayment assetPayment = (AssetPayment)getPersistenceBrokerTemplate().getObjectByQuery(new QueryByCriteria(AssetPayment.class,criteria));

            assetPayment.setAccumulatedPrimaryDepreciationAmount(d.getAccumulatedDepreciation());
            if (this.fiscalMonth == 1)
                assetPayment.setPeriod1Depreciation1Amount(d.getTransactionAmount());
            else if (this.fiscalMonth == 2)
                assetPayment.setPeriod2Depreciation1Amount(d.getTransactionAmount());
            else if (this.fiscalMonth == 3)
                assetPayment.setPeriod3Depreciation1Amount(d.getTransactionAmount());
            else if (this.fiscalMonth == 4)
                assetPayment.setPeriod4Depreciation1Amount(d.getTransactionAmount());
            else if (this.fiscalMonth == 5)
                assetPayment.setPeriod5Depreciation1Amount(d.getTransactionAmount());
            else if (this.fiscalMonth == 6)
                assetPayment.setPeriod6Depreciation1Amount(d.getTransactionAmount());
            else if (this.fiscalMonth == 7)
                assetPayment.setPeriod7Depreciation1Amount(d.getTransactionAmount());
            else if (this.fiscalMonth == 8)
                assetPayment.setPeriod8Depreciation1Amount(d.getTransactionAmount());
            else if (this.fiscalMonth == 9)
                assetPayment.setPeriod9Depreciation1Amount(d.getTransactionAmount());
            else if (this.fiscalMonth == 10)
                assetPayment.setPeriod10Depreciation1Amount(d.getTransactionAmount());
            else if (this.fiscalMonth == 11)
                assetPayment.setPeriod11Depreciation1Amount(d.getTransactionAmount());
            else if (this.fiscalMonth == 12)
                assetPayment.setPeriod12Depreciation1Amount(d.getTransactionAmount());

            //LOG.debug("*****Updating asset#: " +d.getCapitalAssetNumber()+" - Seq#: "+d.getPaymentSequenceNumber());
            getPersistenceBrokerTemplate().store(assetPayment);
        }

        LOG.debug("CamsDepreciableAssetsDaoOjb.updateAssetPayments(List<CamsDepreciableAssets> depreciatedAssets) -  ended");
    }    

    /**
     * 
     * @see org.kuali.module.cams.dao.CamsDepreciableAssetsDao#getObjectCodes()
     */
    public Collection<AssetObjectCode> getObjectCodes() {
        LOG.debug("CamsDepreciableAssetsDaoOjb.getObjectCodes() -  started");
        Collection<AssetObjectCode> col;        
        Criteria criteria = new Criteria();
        criteria.addEqualTo(CamsPropertyConstants.AssetObject.UNIVERSITY_FISCAL_YEAR,this.fiscalYear);
        QueryByCriteria q = QueryFactory.newQuery(AssetObjectCode.class, criteria); 

        col = getPersistenceBrokerTemplate().getCollectionByQuery(q);

        //Creating a list of depreciation expense object codes.
        for (Iterator<AssetObjectCode> iterator = col.iterator(); iterator.hasNext();) {
            AssetObjectCode assetObjectCode =  iterator.next();

            String objCode=assetObjectCode.getDepreciationExpenseFinancialObjectCode();
            if (objCode != null && !objCode.equals("") && !this.depreExpObjCodes.contains(objCode)) {
                this.depreExpObjCodes.add(objCode);
            }

            objCode=assetObjectCode.getAccumulatedDepreciationFinancialObjectCode();
            if (objCode != null && !objCode.equals("") && !this.accumulatedDepreciationObjCodes.contains(objCode)) {
                this.accumulatedDepreciationObjCodes.add(objCode);
            }

        }
        LOG.debug("CamsDepreciableAssetsDaoOjb.getObjectCodes() -  ended");        
        return col;
    }  

    /**
     * 
     * @see org.kuali.module.cams.dao.CamsDepreciableAssetsDao#checkSum(boolean)
     */
    public void checkSum(boolean beforeDepreciationReport) {     
        LOG.debug("CamsDepreciableAssetsDaoOjb.checkSum(boolean beforeDepreciationReport) -  started");        

        NumberFormat usdFormat = NumberFormat.getCurrencyInstance(Locale.US);


        KualiDecimal amount = new KualiDecimal(0);
        String[] columns = new String[2];

        columns[1]="******************";
        if (beforeDepreciationReport)
            columns[0]="*** BEFORE RUNNING DEPRECIATION PROCESS ****";
        else
            columns[0]="*** AFTER RUNNING DEPRECIATION PROCESS ****";

        reportLine.add(columns.clone());                

        Criteria criteria = new Criteria(); 
        ReportQueryByCriteria q = QueryFactory.newReportQuery(DocumentHeader.class, new Criteria());
        q.setAttributes(new String[] {"count(*)" });
        Iterator<Object> i = getPersistenceBrokerTemplate().getReportQueryIteratorByQuery(q);
        Object[] data = (Object[])i.next();        
        columns[0]="Document Count";
        columns[1]=((Long)data[0]).toString();
        reportLine.add(columns.clone());

        q = QueryFactory.newReportQuery(GeneralLedgerPendingEntry.class, new Criteria());
        q.setAttributes(new String[] {"count(*)" });
        i = getPersistenceBrokerTemplate().getReportQueryIteratorByQuery(q);
        data = (Object[])i.next();        
        columns[0]="General ledger pending entry record count";
        columns[1]=((Long)data[0]).toString();
        reportLine.add(columns.clone());

        if (beforeDepreciationReport) {        
            q = QueryFactory.newReportQuery(Asset.class, new Criteria());
            q.setAttributes(new String[] {"count(*)" });
            i = getPersistenceBrokerTemplate().getReportQueryIteratorByQuery(q);
            data = (Object[])i.next();       
            columns[0]="Asset Count";
            columns[1]=((Long)data[0]).toString();
            reportLine.add(columns.clone());
        }        

        q = QueryFactory.newReportQuery(AssetPayment.class, new Criteria());
        q.setAttributes(new String[] {
                "count(*)",
                "SUM(ast_depr1_base_amt)" ,  
                "SUM(ast_acum_depr1_amt)" ,  
                "SUM(ast_prvyrdepr1_amt)" ,
                "SUM(ast_prd1_depr1_amt)" ,
                "SUM(ast_prd2_depr1_amt)" ,
                "SUM(ast_prd3_depr1_amt)" ,
                "SUM(ast_prd4_depr1_amt)" ,
                "SUM(ast_prd5_depr1_amt)" ,
                "SUM(ast_prd6_depr1_amt)" ,
                "SUM(ast_prd7_depr1_amt)" ,
                "SUM(ast_prd8_depr1_amt)" ,
                "SUM(ast_prd9_depr1_amt)" ,
                "SUM(ast_prd10depr1_amt)" ,
                "SUM(ast_prd11depr1_amt)" ,
        "SUM(ast_prd12depr1_amt)"});

        i = getPersistenceBrokerTemplate().getReportQueryIteratorByQuery(q);
        data = (Object[])i.next();

        if (beforeDepreciationReport) {        
            columns[0]="Asset Payment record count";
            columns[1]=((Long)data[0]).toString();
            reportLine.add(columns.clone());
        }

        columns[0]="Depreciation base amount";
        columns[1]=(usdFormat.format((BigDecimal)data[1]));
        reportLine.add(columns.clone());

        columns[0]="Current year - accumulated depreciation";
        columns[1]=(usdFormat.format((BigDecimal)data[2]));
        reportLine.add(columns.clone());

        columns[0]="Previous year - accumulated depreciation";
        columns[1]=(usdFormat.format((BigDecimal)data[3]));
        reportLine.add(columns.clone());

        columns[0]="July Depreciation amount";
        columns[1]=(usdFormat.format((BigDecimal)data[4]));
        reportLine.add(columns.clone());

        columns[0]="August Depreciation amount";
        columns[1]=(usdFormat.format((BigDecimal)data[5]));
        reportLine.add(columns.clone());

        columns[0]="September Depreciation amount";
        columns[1]=(usdFormat.format((BigDecimal)data[6]));
        reportLine.add(columns.clone());

        columns[0]="October Depreciation amount";
        columns[1]=(usdFormat.format((BigDecimal)data[7]));
        reportLine.add(columns.clone());

        columns[0]="November Depreciation amount";
        columns[1]=(usdFormat.format((BigDecimal)data[8]));
        reportLine.add(columns.clone());

        columns[0]="December Depreciation amount";
        columns[1]=(usdFormat.format((BigDecimal)data[9]));
        reportLine.add(columns.clone());

        columns[0]="January Depreciation amount";
        columns[1]=(usdFormat.format((BigDecimal)data[10]));
        reportLine.add(columns.clone());

        columns[0]="February Depreciation amount";
        columns[1]=(usdFormat.format((BigDecimal)data[11]));
        reportLine.add(columns.clone());

        columns[0]="March Depreciation amount";
        columns[1]=(usdFormat.format((BigDecimal)data[12]));
        reportLine.add(columns.clone());

        columns[0]="April Depreciation amount";
        columns[1]=(usdFormat.format((BigDecimal)data[13]));
        reportLine.add(columns.clone());

        columns[0]="May Depreciation amount";
        columns[1]=(usdFormat.format((BigDecimal)data[14]));
        reportLine.add(columns.clone());

        columns[0]="June Depreciation amount";
        columns[1]=(usdFormat.format((BigDecimal)data[15]));
        reportLine.add(columns.clone());

        if (beforeDepreciationReport) {
            q = QueryFactory.newReportQuery(AssetPayment.class, this.assetCriteria);
            q.setAttributes(new String[] {"count(distinct capitalAssetNumber)","count(*)"});
            i = getPersistenceBrokerTemplate().getReportQueryIteratorByQuery(q);
            data = (Object[])i.next();
            columns[0]="Assets eligible for depreciation";
            columns[1]=((Long)data[0]).toString();
            reportLine.add(columns.clone());

            columns[0]="Asset Payments eligible for depreciation";
            columns[1]=((Long)data[1]).toString();
            reportLine.add(columns.clone());

            //Count the assets being retired or transferred!!!.
            //payments eligible after deleting pending AR and AT documents.!! 
        }

        if (!beforeDepreciationReport) {
            KualiDecimal debits = new KualiDecimal(0);
            KualiDecimal credits= new KualiDecimal(0);

            //Expense Debit
            criteria = new Criteria();
            criteria.addIn      (KFSPropertyConstants.FINANCIAL_OBJECT_CODE        , this.depreExpObjCodes);
            criteria.addEqualTo (KFSPropertyConstants.TRANSACTION_DEBIT_CREDIT_CODE, KFSConstants.GL_DEBIT_CODE);
            //criteria.addEqualTo(KFSPropertyConstants.DOCUMENT_NUMBER, "????");

            q = QueryFactory.newReportQuery(GeneralLedgerPendingEntry.class, criteria);
            q.setAttributes(new String[] {"SUM(transactionLedgerEntryAmount)"});
            i = getPersistenceBrokerTemplate().getReportQueryIteratorByQuery(q);
            data = (Object[])i.next();

            amount = (data[0] == null ? new KualiDecimal(0) : (KualiDecimal)data[0]);                 
            columns[0]="Debit - Depreciation Expense object codes";
            columns[1]=(usdFormat.format(amount));
            reportLine.add(columns.clone());
            debits.add(amount);

            //Accumulated Depreciation credit
            criteria = new Criteria();
            criteria.addIn      (KFSPropertyConstants.FINANCIAL_OBJECT_CODE         , this.accumulatedDepreciationObjCodes);
            criteria.addEqualTo (KFSPropertyConstants.TRANSACTION_DEBIT_CREDIT_CODE , KFSConstants.GL_CREDIT_CODE);


            //criteria.addEqualTo(KFSPropertyConstants.DOCUMENT_NUMBER, "????");

            q = QueryFactory.newReportQuery(GeneralLedgerPendingEntry.class, criteria);
            q.setAttributes(new String[] {"SUM(transactionLedgerEntryAmount)"});
            i = getPersistenceBrokerTemplate().getReportQueryIteratorByQuery(q);
            data = (Object[])i.next();        
            amount = (data[0] == null ? new KualiDecimal(0) : (KualiDecimal)data[0]); 
            columns[0]="Credit - Accumulated depreciation object codes";
            columns[1]=(usdFormat.format(amount));
            reportLine.add(columns.clone());
            credits.add(amount);
            //***********************************************************************************************

            //Accumulated Depreciation debit
            criteria = new Criteria();
            criteria.addIn      (KFSPropertyConstants.FINANCIAL_OBJECT_CODE        , this.accumulatedDepreciationObjCodes);
            criteria.addEqualTo (KFSPropertyConstants.TRANSACTION_DEBIT_CREDIT_CODE, KFSConstants.GL_DEBIT_CODE);
            //criteria.addEqualTo (KFSPropertyConstants.DOCUMENT_NUMBER, "?");

            q = QueryFactory.newReportQuery(GeneralLedgerPendingEntry.class, criteria);
            q.setAttributes(new String[] {"SUM(transactionLedgerEntryAmount)"});
            i = getPersistenceBrokerTemplate().getReportQueryIteratorByQuery(q);
            data = (Object[])i.next();        
            amount = (data[0] == null ? new KualiDecimal(0) : (KualiDecimal)data[0]);             
            columns[0]="Debit - Accumulated depreciation object codes";
            columns[1]=(usdFormat.format(amount));
            reportLine.add(columns.clone());
            debits.add(amount);

            //Expense credit
            criteria = new Criteria();
            criteria.addIn      (KFSPropertyConstants.FINANCIAL_OBJECT_CODE         , this.depreExpObjCodes);
            criteria.addEqualTo (KFSPropertyConstants.TRANSACTION_DEBIT_CREDIT_CODE , KFSConstants.GL_CREDIT_CODE);
            //criteria.addEqualTo(KFSPropertyConstants.DOCUMENT_NUMBER, "?");

            q = QueryFactory.newReportQuery(GeneralLedgerPendingEntry.class, criteria);
            q.setAttributes(new String[] {"SUM(transactionLedgerEntryAmount)"});
            i = getPersistenceBrokerTemplate().getReportQueryIteratorByQuery(q);
            data = (Object[])i.next();        
            amount = (data[0] == null ? new KualiDecimal(0) : (KualiDecimal)data[0]);             
            columns[0]="Credit - Depreciation Expense object codes";
            columns[1]=(usdFormat.format(amount));
            reportLine.add(columns.clone());
            credits.add(amount);


            columns[0]="Total Debits";
            columns[1]=usdFormat.format(debits);
            reportLine.add(columns.clone());

            columns[0]="Total Credits";
            columns[1]=usdFormat.format(credits);
            reportLine.add(columns.clone());

            columns[0]="Total Debits - Total Credits";
            columns[1]=usdFormat.format(debits.subtract(credits));
            reportLine.add(columns.clone());

            //Document #
        }
/*        for (Iterator<String[]> iterator = reportLine.iterator(); iterator.hasNext();) {
            String[] strings = iterator.next();
            LOG.info("***Report Line: "+strings[0]+" "+strings[1]);
        }  
*/        LOG.debug("CamsDepreciableAssetsDaoOjb.checkSum(boolean beforeDepreciationReport) -  ended");        
    }

    public List<String[]> getReportLine() {
        return reportLine;
    }



} // end of class

/*
                A.cptlast_nbr       = P.cptlast_nbr                                         // foreign key
        AND     A.cptlast_origin_cd = P.cptlast_origin_cd                                   // ??

        AND     P.cptlast_origin_cd = '01'                                                  // done
        AND     ( P.ast_depr1_base_amt != 0.00 OR P.ast_depr1_base_amt IS NOT NULL )        //done
        AND     ( P.ast_trnfr_pmt_cd IN ( 'N', ' ') OR P.ast_trnfr_pmt_cd IS NULL)          //done   
        AND     ( ( A.ast_depr_mthd1_cd = 'SL'OR A.ast_depr_mthd1_cd IS NULL ) OR ( A.ast_depr_mthd1_cd = 'SV' ) ) //Done

        AND     A.cptl_ast_int_srvc_dt IS NOT NULL // done
        AND     A.cptl_ast_crt_dt != '01/01/1900'  // done
        AND     A.cptl_ast_crt_dt <= v_depr_date  // done

        AND     ( ( A.ast_retir_fscl_yr > v_fiscal_yr ) OR 
                ( A.ast_retir_fscl_yr = v_fiscal_yr AND TO_NUMBER( A.ast_retir_prd_cd ) > v_fiscal_prd )
                 OR A.ast_retir_fscl_yr IS NULL OR A.ast_retir_prd_cd IS NULL )

        AND     A.ast_invn_stat_cd NOT IN ( 'N', 'O' )   // done
        AND     A.cptlast_typ_cd = T.cptlast_typ_cd      //done
        AND     T.cptlast_deprlf_lmt > 0                 //done

and p.fin_object_cd not in (
-- Exclusing all the federally-owned object sub type codes.
Select ca_object_code_t From ca_object_code_t o, cm_cptlast_obj_t a
Where o.univ_fiscal_yr = a.univ_fiscal_yr  and  a.univ_fiscal_yr = v_fiscal_year 
And o.fin_coa_cd = a.fin_coa_cd
And o.fin_obj_sub_typ_cd = a.fin_obj_sub_typ_cd
And fin_obj_sub_typ_cd IN ('CO', 'CP', 'UO', 'AM', 'AF', 'LA', 'BY' ) ) and

 ***** Excluding retired and transferd assets *************
P.cptlast_nbr + cptlast_origin_cd not in (
Select  c.cptlast_nbr + c.cptlast_origin_cd
 FROM fp_doc_header_t,   H  cm_cptlast_hdr_t  T 
WHERE H.fs_origin_cd = C.fs_origin_cd   and H.fdoc_nbr = C.fdoc_nbr and 
H.fdoc_typ_cd IN ('AR', 'AT') AND     H.fdoc_status_cd NOT IN ('A', 'C') 
    GROUP BY     P.cptlast_nbr,  P.cptlast_origin_cd )




SELECT  C.CPTLAST_NBR
 FROM FP_DOC_HEADER_T H,  CM_AST_PAYMENT_T  C
WHERE H.FDOC_NBR = C.FDOC_NBR AND
C.FDOC_TYP_CD IN ('AR', 'AT') AND     H.FDOC_STATUS_CD NOT IN ('A', 'C')



    private ReportQueryByCriteria getObjectCodeSubquery() {
        List federallyOwnedObjectSybTypes = new ArrayList();
        federallyOwnedObjectSybTypes.add("CO");
        federallyOwnedObjectSybTypes.add("CP");
        federallyOwnedObjectSybTypes.add("UO");
        federallyOwnedObjectSybTypes.add("AM");
        federallyOwnedObjectSybTypes.add("AF");
        federallyOwnedObjectSybTypes.add("LA");
        federallyOwnedObjectSybTypes.add("BY");

        Criteria criteria = new Criteria();
        criteria.addEqualTo("ObjectCodes.universityFiscalYear",this.fiscalYear);
        criteria.addIn     ("ObjectCodes.financialObjectSubTypeCode",federallyOwnedObjectSybTypes);
        ReportQueryByCriteria q = QueryFactory.newReportQuery(AssetObjectCode.class, criteria);

        q.setAttributes(new String[] {"ObjectCodes.financialObjectCode"}); 
        //Iterator i = getPersistenceBrokerTemplate().getReportQueryIteratorByQuery(q);
        //System.out.println("** SubQuery:"+q.toString());        
        return q;
    }
 */


