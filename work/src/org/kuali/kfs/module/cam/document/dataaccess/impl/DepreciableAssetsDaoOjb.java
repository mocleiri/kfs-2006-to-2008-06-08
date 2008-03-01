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
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.apache.ojb.broker.query.QueryFactory;
import org.apache.ojb.broker.query.ReportQueryByCriteria;
import org.kuali.core.bo.DocumentHeader;
import org.kuali.core.dao.ojb.PlatformAwareDaoBaseOjb;
import org.kuali.core.util.KualiDecimal;
import org.kuali.kfs.KFSConstants;
import org.kuali.kfs.KFSPropertyConstants;
import org.kuali.kfs.bo.GeneralLedgerPendingEntry;
import org.kuali.kfs.context.SpringContext;
import org.kuali.kfs.service.OptionsService;
import org.kuali.kfs.service.ParameterService;
import org.kuali.kfs.service.impl.ParameterConstants;
import org.kuali.module.cams.CamsConstants;
import org.kuali.module.cams.CamsPropertyConstants;
import org.kuali.module.cams.bo.Asset;
import org.kuali.module.cams.bo.AssetHeader;
import org.kuali.module.cams.bo.AssetObjectCode;
import org.kuali.module.cams.bo.AssetPayment;
import org.kuali.module.cams.bo.AssetRetirementDocument;
import org.kuali.module.cams.bo.AssetTransferDocument;
import org.kuali.module.cams.bo.AssetType;
import org.kuali.module.cams.bo.DepreciableAssets;
import org.kuali.module.cams.dao.DepreciableAssetsDao;
import org.kuali.module.chart.bo.Account;
import org.kuali.module.chart.bo.ObjectCode;
import org.kuali.module.chart.bo.Org;

public class DepreciableAssetsDaoOjb extends PlatformAwareDaoBaseOjb implements DepreciableAssetsDao {
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(DepreciableAssetsDaoOjb.class);

    Collection<DepreciableAssets> depreciableAssetsCollection = new ArrayList<DepreciableAssets>();

    private Criteria        assetCriteria   = new Criteria();
    private String          errorMsg        = new String();

    private final static String PAYMENT_TO_ASSET_REFERENCE_DESCRIPTOR    = "asset.";
    private final static String PAYMENT_TO_OBJECT_REFERENCE_DESCRIPTOR   = "financialObject.";
    private final static String ASSET_TO_ASSET_TYPE_REFERENCE_DESCRIPTOR = "asset.capitalAssetType.";
    private final static String[] REPORT_GROUP = {"*** BEFORE RUNNING DEPRECIATION PROCESS ****","*** AFTER RUNNING DEPRECIATION PROCESS ****"};
    private final static String DEPRECIATION_ALREADY_RAN_MSG="Depreciation batch process already ran for the current depreciation date.";
    
    /**
     * 
     * @see org.kuali.module.cams.dao.CamsDepreciableAssetsDao#getListOfDepreciableAssets()
     */
    public Collection<DepreciableAssets> getListOfDepreciableAssets(Integer fiscalYear, Integer fiscalMonth, Calendar depreciationDate) {
        LOG.debug("CamsDepreciableAssetsDaoOjb.getListOfDepreciableAssets() -  started");

        Collection<AssetObjectCode> objectCodes = SpringContext.getBean(AssetObjectCodeDaoOjb.class).getAssetObjectCodes(fiscalYear);

        QueryByCriteria q = QueryFactory.newQuery(AssetPayment.class, this.getDepreciationCriteria(fiscalYear, fiscalMonth, depreciationDate)); 
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

        for (AssetPayment assetPayment : iter) {
            Asset asset = assetPayment.getAsset();

            AssetType   assetType        = asset.getCapitalAssetType();
            Account     account          = assetPayment.getAccount();
            Org         org              = account.getOrganization();
            ObjectCode  financialObject  = assetPayment.getFinancialObject();

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

            depreciableAssets.setAccumulatedPrimaryDepreciationAmount   (assetPayment.getAccumulatedPrimaryDepreciationAmount());
            depreciableAssets.setPreviousYearPrimaryDepreciationAmount  (assetPayment.getPreviousYearPrimaryDepreciationAmount());
            
            depreciableAssets.setProjectCode   (assetPayment.getProjectCode());
            depreciableAssets.setDocumentNumber(assetPayment.getDocumentNumber());

            depreciableAssets.setFinancialObjectSubTypeCode (financialObject.getFinancialObjectSubTypeCode());
            depreciableAssets.setFinancialObjectTypeCode    (financialObject.getFinancialObjectTypeCode());

            depreciableAssets.setOrganizationPlantChartCode     (org.getOrganizationPlantChartCode());
            depreciableAssets.setOrganizationPlantAccountNumber (org.getOrganizationPlantAccountNumber());
            depreciableAssets.setCampusPlantAccountNumber       (org.getCampusPlantAccountNumber());
            depreciableAssets.setCampusPlantChartCode           (org.getCampusPlantChartCode());

            for (AssetObjectCode assetObjectCodes : objectCodes) {
                boolean found = false;
                List<ObjectCode> objectCodesList = assetObjectCodes.getObjectCode();
                for (ObjectCode oc : objectCodesList) {
                    if (oc.getFinancialObjectCode().equals(assetPayment.getFinancialObjectCode())) {
                        depreciableAssets.setCapitalizationFinancialObjectCode          (assetObjectCodes.getCapitalizationFinancialObjectCode());
                        depreciableAssets.setAccumulatedDepreciationFinancialObjectCode (assetObjectCodes.getAccumulatedDepreciationFinancialObjectCode());
                        depreciableAssets.setDepreciationExpenseFinancialObjectCode     (assetObjectCodes.getDepreciationExpenseFinancialObjectCode());
                        found = true;
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
    public void updateAssetPayments(List<DepreciableAssets> depreciatedAssets, Integer fiscalYear, Integer fiscalMonth) {
        LOG.debug("CamsDepreciableAssetsDaoOjb.updateAssetPayments(List<CamsDepreciableAssets> depreciatedAssets) -  started");

        Criteria criteria = new Criteria();
        Collection<AssetPayment> assetPayments;

        // If we are in the first month of the fiscal year, then add the previous year depreciation amounts into previous year
        // depreciation field
        // This for all the assets in the payment table.
        if (fiscalMonth == 1) {
            criteria.addNotNull(CamsPropertyConstants.AssetPayment.CAPITAL_ASSET_NUMBER);
            QueryByCriteria q = QueryFactory.newQuery(AssetPayment.class, criteria);
            assetPayments = getPersistenceBrokerTemplate().getCollectionByQuery(q);

            for (AssetPayment assetPayment : assetPayments) {
                if (assetPayment != null) {
                    assetPayment.setPreviousYearPrimaryDepreciationAmount(assetPayment.getPeriod1Depreciation1Amount().add(assetPayment.getPeriod2Depreciation1Amount()).add(assetPayment.getPeriod3Depreciation1Amount()).add(assetPayment.getPeriod4Depreciation1Amount()).add(assetPayment.getPeriod5Depreciation1Amount()).add(assetPayment.getPeriod6Depreciation1Amount()).add(assetPayment.getPeriod7Depreciation1Amount()).add(assetPayment.getPeriod8Depreciation1Amount()).add(assetPayment.getPeriod9Depreciation1Amount()).add(assetPayment.getPeriod10Depreciation1Amount()).add(assetPayment.getPeriod11Depreciation1Amount()).add(assetPayment.getPeriod12Depreciation1Amount()));

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
        for (DepreciableAssets d : depreciatedAssets) {
            criteria = new Criteria();
            criteria.addEqualTo(CamsPropertyConstants.AssetPayment.CAPITAL_ASSET_NUMBER, d.getCapitalAssetNumber());
            criteria.addEqualTo(CamsPropertyConstants.AssetPayment.PAYMENT_SEQ_NUMBER, d.getPaymentSequenceNumber());

            AssetPayment assetPayment = (AssetPayment) getPersistenceBrokerTemplate().getObjectByQuery(new QueryByCriteria(AssetPayment.class, criteria));

            assetPayment.setAccumulatedPrimaryDepreciationAmount(d.getAccumulatedDepreciation());

            if (fiscalMonth == 1)
                assetPayment.setPeriod1Depreciation1Amount(d.getTransactionAmount());
            else if (fiscalMonth == 2)
                assetPayment.setPeriod2Depreciation1Amount(d.getTransactionAmount());
            else if (fiscalMonth == 3)
                assetPayment.setPeriod3Depreciation1Amount(d.getTransactionAmount());
            else if (fiscalMonth == 4)
                assetPayment.setPeriod4Depreciation1Amount(d.getTransactionAmount());
            else if (fiscalMonth == 5)
                assetPayment.setPeriod5Depreciation1Amount(d.getTransactionAmount());
            else if (fiscalMonth == 6)
                assetPayment.setPeriod6Depreciation1Amount(d.getTransactionAmount());
            else if (fiscalMonth == 7)
                assetPayment.setPeriod7Depreciation1Amount(d.getTransactionAmount());
            else if (fiscalMonth == 8)
                assetPayment.setPeriod8Depreciation1Amount(d.getTransactionAmount());
            else if (fiscalMonth == 9)
                assetPayment.setPeriod9Depreciation1Amount(d.getTransactionAmount());
            else if (fiscalMonth == 10)
                assetPayment.setPeriod10Depreciation1Amount(d.getTransactionAmount());
            else if (fiscalMonth == 11)
                assetPayment.setPeriod11Depreciation1Amount(d.getTransactionAmount());
            else if (fiscalMonth == 12)
                assetPayment.setPeriod12Depreciation1Amount(d.getTransactionAmount());

            getPersistenceBrokerTemplate().store(assetPayment);
        }

        LOG.debug("CamsDepreciableAssetsDaoOjb.updateAssetPayments(List<CamsDepreciableAssets> depreciatedAssets) -  ended");
    }


    /**
     * 
     * @see org.kuali.module.cams.dao.CamsDepreciableAssetsDao#checkSum(boolean)
     */
    public List<String[]> checkSum(boolean beforeDepreciationReport, String documentNumber, Integer fiscalYear, Integer fiscalMonth, Calendar depreciationDate) {
        LOG.debug("CamsDepreciableAssetsDaoOjb.checkSum(boolean beforeDepreciationReport) -  started");

        List<String[]>  reportLine = new ArrayList<String[]>();
        boolean processAlreadyRan=false;

        NumberFormat usdFormat = NumberFormat.getCurrencyInstance(Locale.US);
        KualiDecimal amount = new KualiDecimal(0);
        String[] columns = new String[2];


        columns[1] = "******************";
        if (beforeDepreciationReport)
            columns[0] = REPORT_GROUP[0];
        else
            columns[0] = REPORT_GROUP[1];

        reportLine.add(columns.clone());

        Criteria criteria = new Criteria();
        ReportQueryByCriteria q = QueryFactory.newReportQuery(DocumentHeader.class, new Criteria());
        q.setAttributes(new String[] { "count(*)" });
        Iterator<Object> i = getPersistenceBrokerTemplate().getReportQueryIteratorByQuery(q);

        Object[] data = (Object[]) i.next();
        columns[0] = "Document Count";
        columns[1] = (convertCountValueToString(data[0]));
        reportLine.add(columns.clone());

        q = QueryFactory.newReportQuery(GeneralLedgerPendingEntry.class, new Criteria());
        q.setAttributes(new String[] { "count(*)" });
        i = getPersistenceBrokerTemplate().getReportQueryIteratorByQuery(q);
        data = (Object[]) i.next();
        columns[0] = "General ledger pending entry record count";
        columns[1] = (convertCountValueToString(data[0]));
        reportLine.add(columns.clone());

        if (beforeDepreciationReport) {
            q = QueryFactory.newReportQuery(Asset.class, new Criteria());
            q.setAttributes(new String[] { "count(*)" });
            i = getPersistenceBrokerTemplate().getReportQueryIteratorByQuery(q);
            data = (Object[]) i.next();
            columns[0] = "Asset Count";
            columns[1] = (convertCountValueToString(data[0]));
            reportLine.add(columns.clone());
        }

        q = QueryFactory.newReportQuery(AssetPayment.class, new Criteria());
        q.setAttributes(new String[] { "count(*)", "SUM(ast_depr1_base_amt)", "SUM(ast_acum_depr1_amt)", "SUM(ast_prvyrdepr1_amt)", "SUM(ast_prd1_depr1_amt)", "SUM(ast_prd2_depr1_amt)", "SUM(ast_prd3_depr1_amt)", "SUM(ast_prd4_depr1_amt)", "SUM(ast_prd5_depr1_amt)", "SUM(ast_prd6_depr1_amt)", "SUM(ast_prd7_depr1_amt)", "SUM(ast_prd8_depr1_amt)", "SUM(ast_prd9_depr1_amt)", "SUM(ast_prd10depr1_amt)", "SUM(ast_prd11depr1_amt)", "SUM(ast_prd12depr1_amt)" });

        i = getPersistenceBrokerTemplate().getReportQueryIteratorByQuery(q);
        data = (Object[]) i.next();

        if (beforeDepreciationReport) {
            columns[0] = "Asset Payment record count";
            columns[1] = (convertCountValueToString(data[0]));
            reportLine.add(columns.clone());
        }

        columns[0] = "Depreciation base amount";
        columns[1] = (usdFormat.format((BigDecimal) data[1]));
        reportLine.add(columns.clone());

        columns[0] = "Current year - accumulated depreciation";
        columns[1] = (usdFormat.format((BigDecimal) data[2]));
        reportLine.add(columns.clone());

        columns[0] = "Previous year - accumulated depreciation";
        columns[1] = (usdFormat.format((BigDecimal) data[3]));
        reportLine.add(columns.clone());

        /*
         * Here I'm getting the column of total depreciation for the current fiscal month. The idea here is to prevent the process from
         * running a second time for the same fiscal month.
         * 3 + fiscalMonth (variable)  => current fiscal month depreciation column in the array.
         * So if the current fiscal month depreciation column > 0 then depreciation was already ran. Therefore, it should be stop but,
         * not until part of the pdf report List is populated so it can be written.
         */
        processAlreadyRan = false;
        if ( ((BigDecimal)data[ 3 + fiscalMonth]).compareTo(new BigDecimal(0)) != 0)
            processAlreadyRan = true;
        //*******************************************************************************************************************************    

        // Adding monthly depreciation amounts
        int fiscalStartMonth = Integer.parseInt(SpringContext.getBean(OptionsService.class).getCurrentYearOptions().getUniversityFiscalYearStartMo());
        boolean isJanuaryTheFirstFiscalMonth = (fiscalStartMonth == 1);
        int col = 4;
        int currentMonth = fiscalStartMonth - 1;
        for (int monthCounter = 1; monthCounter <= 12; monthCounter++, currentMonth++) {
            columns[0] = CamsConstants.MONTHS[currentMonth] + " Depreciation amount";
            columns[1] = (usdFormat.format((BigDecimal) data[col]));
            reportLine.add(columns.clone());

            col++;

            if (!isJanuaryTheFirstFiscalMonth) {
                if (currentMonth == 11)
                    currentMonth = -1;
            }
        }

        if (beforeDepreciationReport) {
            q = QueryFactory.newReportQuery(AssetPayment.class, this.getDepreciationCriteria(fiscalYear, fiscalMonth, depreciationDate));
            q.setAttributes(new String[] { "count(distinct capitalAssetNumber)", "count(*)" });

            i = getPersistenceBrokerTemplate().getReportQueryIteratorByQuery(q);

            data = (Object[]) i.next();
            columns[0] = "Assets eligible for depreciation";
            columns[1] = (convertCountValueToString(data[0]));
            reportLine.add(columns.clone());

            columns[0] = "Asset Payments eligible for depreciation";
            columns[1] = (convertCountValueToString(data[1]));
            reportLine.add(columns.clone());

            // Count the assets being retired or transferred!!!.
            // payments eligible after deleting pending AR and AT documents.!!
        }

        if (!beforeDepreciationReport) {
            // Generating a list of depreciation expense object codes.
            List<String> depreExpObjCodes = SpringContext.getBean(AssetObjectCodeDaoOjb.class).getExpenseObjectCodes(fiscalYear);

            //Generating a list of accumulated depreciation object codes.
            List<String> accumulatedDepreciationObjCodes = SpringContext.getBean(AssetObjectCodeDaoOjb.class).getAccumulatedDepreciationObjectCodes(fiscalYear);

            KualiDecimal debits = new KualiDecimal(0);
            KualiDecimal credits = new KualiDecimal(0);

            // Document Number created
            columns[0] = "Document#";
            columns[1] = documentNumber;
            reportLine.add(columns.clone());


            // Expense Debit
            criteria = new Criteria();
            criteria.addIn      (KFSPropertyConstants.FINANCIAL_OBJECT_CODE         , depreExpObjCodes);
            criteria.addEqualTo (KFSPropertyConstants.TRANSACTION_DEBIT_CREDIT_CODE , KFSConstants.GL_DEBIT_CODE);
            criteria.addEqualTo (KFSPropertyConstants.DOCUMENT_NUMBER               , documentNumber);

            q = QueryFactory.newReportQuery(GeneralLedgerPendingEntry.class, criteria);
            q.setAttributes(new String[] { "SUM(transactionLedgerEntryAmount)" });
            i = getPersistenceBrokerTemplate().getReportQueryIteratorByQuery(q);
            data = (Object[]) i.next();

            amount = (data[0] == null ? new KualiDecimal(0) : (KualiDecimal) data[0]);
            columns[0] = "Debit - Depreciation Expense object codes";
            columns[1] = (usdFormat.format(amount));
            reportLine.add(columns.clone());
            debits = debits.add(amount);

            // Accumulated Depreciation credit
            criteria = new Criteria();
            criteria.addIn      (KFSPropertyConstants.FINANCIAL_OBJECT_CODE         , accumulatedDepreciationObjCodes);
            criteria.addEqualTo (KFSPropertyConstants.TRANSACTION_DEBIT_CREDIT_CODE , KFSConstants.GL_CREDIT_CODE);
            criteria.addEqualTo (KFSPropertyConstants.DOCUMENT_NUMBER               , documentNumber);

            q = QueryFactory.newReportQuery(GeneralLedgerPendingEntry.class, criteria);
            q.setAttributes(new String[] { "SUM(transactionLedgerEntryAmount)" });
            i = getPersistenceBrokerTemplate().getReportQueryIteratorByQuery(q);
            data = (Object[]) i.next();
            amount = (data[0] == null ? new KualiDecimal(0) : (KualiDecimal) data[0]);
            columns[0] = "Credit - Accumulated depreciation object codes";
            columns[1] = (usdFormat.format(amount));
            reportLine.add(columns.clone());
            credits = credits.add(amount);
            // ***********************************************************************************************

            // Accumulated Depreciation debit
            criteria = new Criteria();
            criteria.addIn      (KFSPropertyConstants.FINANCIAL_OBJECT_CODE         , accumulatedDepreciationObjCodes);
            criteria.addEqualTo (KFSPropertyConstants.TRANSACTION_DEBIT_CREDIT_CODE , KFSConstants.GL_DEBIT_CODE);
            criteria.addEqualTo (KFSPropertyConstants.DOCUMENT_NUMBER               , documentNumber);

            q = QueryFactory.newReportQuery(GeneralLedgerPendingEntry.class, criteria);
            q.setAttributes(new String[] { "SUM(transactionLedgerEntryAmount)" });
            i = getPersistenceBrokerTemplate().getReportQueryIteratorByQuery(q);
            data = (Object[]) i.next();
            amount = (data[0] == null ? new KualiDecimal(0) : (KualiDecimal) data[0]);
            columns[0] = "Debit - Accumulated depreciation object codes";
            columns[1] = (usdFormat.format(amount));
            reportLine.add(columns.clone());
            debits = debits.add(amount);

            // Expense credit
            criteria = new Criteria();
            criteria.addIn      (KFSPropertyConstants.FINANCIAL_OBJECT_CODE         , depreExpObjCodes);
            criteria.addEqualTo (KFSPropertyConstants.TRANSACTION_DEBIT_CREDIT_CODE , KFSConstants.GL_CREDIT_CODE);
            criteria.addEqualTo (KFSPropertyConstants.DOCUMENT_NUMBER               , documentNumber);

            q = QueryFactory.newReportQuery(GeneralLedgerPendingEntry.class, criteria);
            q.setAttributes(new String[] { "SUM(transactionLedgerEntryAmount)" });
            i = getPersistenceBrokerTemplate().getReportQueryIteratorByQuery(q);
            data = (Object[]) i.next();
            amount = (data[0] == null ? new KualiDecimal(0) : (KualiDecimal) data[0]);
            columns[0] = "Credit - Depreciation Expense object codes";
            columns[1] = (usdFormat.format(amount));
            reportLine.add(columns.clone());
            credits = credits.add(amount);


            columns[0] = "Total Debits";
            columns[1] = usdFormat.format(debits);
            reportLine.add(columns.clone());

            columns[0] = "Total Credits";
            columns[1] = usdFormat.format(credits);
            reportLine.add(columns.clone());

            columns[0] = "Total Debits - Total Credits";
            columns[1] = usdFormat.format(debits.subtract(credits));
            reportLine.add(columns.clone());
        }        
        LOG.debug("CamsDepreciableAssetsDaoOjb.checkSum(boolean beforeDepreciationReport) -  ended");

        if (processAlreadyRan && beforeDepreciationReport)
            throw new RuntimeException(DEPRECIATION_ALREADY_RAN_MSG);
        
        return reportLine;
    }


    /**
     * 
     * This method...
     * 
     * @param fieldValue
     * @return
     */
    private String convertCountValueToString(Object fieldValue) {
        if (fieldValue instanceof BigDecimal) {
            return ((BigDecimal) fieldValue).toString();
        }
        else {
            return ((Long) fieldValue).toString();
        }
    }

    /**
     * 
     * This method generates a subquery that will retrieve all the assets with pending transfers and pending retriments
     * 
     * @return
     */
    private ReportQueryByCriteria getPendingAssetDocumentSubquery() {
        List<String> notPendingDocStatuses = new ArrayList<String>();
        notPendingDocStatuses.add(CamsConstants.NotPendingDocumentStatuses.APPROVED);
        notPendingDocStatuses.add(CamsConstants.NotPendingDocumentStatuses.CANCELED);

        List<String> excludedAssets = new ArrayList<String>();

        Criteria arCriteria = new Criteria();
        Criteria atCriteria = new Criteria();

        // Retired assets subquery
        ReportQueryByCriteria arSubQuery;
        arCriteria.addNotIn(KFSPropertyConstants.DOCUMENT_HEADER + "." + KFSPropertyConstants.FINANCIAL_DOCUMENT_STATUS_CODE, notPendingDocStatuses);

        arSubQuery = QueryFactory.newReportQuery(AssetRetirementDocument.class, arCriteria);
        arSubQuery.setAttributes(new String[] { KFSPropertyConstants.DOCUMENT_HEADER + "." + KFSPropertyConstants.DOCUMENT_NUMBER });

        // transferred assets subquery
        ReportQueryByCriteria atSubQuery;
        atCriteria.addNotIn(KFSPropertyConstants.DOCUMENT_HEADER + "." + KFSPropertyConstants.FINANCIAL_DOCUMENT_STATUS_CODE, notPendingDocStatuses);

        atSubQuery = QueryFactory.newReportQuery(AssetTransferDocument.class, atCriteria);
        atSubQuery.setAttributes(new String[] { KFSPropertyConstants.DOCUMENT_HEADER + "." + KFSPropertyConstants.DOCUMENT_NUMBER });

        // Assets header query
        Criteria criteria = new Criteria();
        criteria.addNotIn(CamsPropertyConstants.AssetHeader.DOCUMENT_NUMBER, atSubQuery);
        criteria.addNotIn(CamsPropertyConstants.AssetHeader.DOCUMENT_NUMBER, arSubQuery);

        ReportQueryByCriteria q = QueryFactory.newReportQuery(AssetHeader.class, criteria);
        q.setAttributes(new String[] { CamsPropertyConstants.AssetHeader.CAPITAL_ASSET_NUMBER });
        return q;
    }

    /**
     * 
     * This method creates the criteria for the assets that will be depreciated
     * @param fiscalYear
     * @param fiscalMonth
     * @return Criteria
     */
    private Criteria getDepreciationCriteria(Integer fiscalYear, Integer fiscalMonth, Calendar depreciationDate) {
        LOG.debug("CamsDepreciableAssetsDaoOjb.createDepreciationCriteria() -  started");

        ParameterService parameterService = SpringContext.getBean(ParameterService.class);

        List<String> assetTransferCode = new ArrayList<String>();
        List<String> depreciationMethodList = new ArrayList<String>();
        List<String> federallyOwnedObjectSybTypes = new ArrayList<String>();
        List<String> notAcceptedAssetStatus = new ArrayList<String>();

        assetTransferCode.add(CamsConstants.TRANSFER_PAYMENT_CODE_N);
        assetTransferCode.add(" ");

        depreciationMethodList.add(CamsConstants.DEPRECIATION_METHOD_SALVAGE_VALUE_CODE);
        depreciationMethodList.add(CamsConstants.DEPRECIATION_METHOD_STRAIGHT_LINE_CODE);

        if (parameterService.parameterExists(ParameterConstants.CAPITAL_ASSETS_BATCH.class, CamsConstants.Parameters.NON_DEPRECIABLE_FEDERALLY_OWNED_OBJECT_SUB_TYPES)) {
            federallyOwnedObjectSybTypes = parameterService.getParameterValues(ParameterConstants.CAPITAL_ASSETS_BATCH.class, CamsConstants.Parameters.NON_DEPRECIABLE_FEDERALLY_OWNED_OBJECT_SUB_TYPES);
        }
        if (parameterService.parameterExists(ParameterConstants.CAPITAL_ASSETS_BATCH.class, CamsConstants.Parameters.NON_DEPRECIABLE_NON_CAPITAL_ASSETS_STATUS_CODES)) {
            notAcceptedAssetStatus = parameterService.getParameterValues(ParameterConstants.CAPITAL_ASSETS_BATCH.class, CamsConstants.Parameters.NON_DEPRECIABLE_NON_CAPITAL_ASSETS_STATUS_CODES);
        }

        Criteria criteria = new Criteria();
        Criteria criteriaB = new Criteria();
        Criteria criteriaC = new Criteria();


        criteria.addEqualTo(CamsPropertyConstants.AssetPayment.ORIGINATION_CODE, CamsConstants.Depreciation.DEPRECIATION_ORIGINATION_CODE);         

        // Begin ************
        criteriaB = new Criteria();
        criteriaB.addNotNull(CamsPropertyConstants.AssetPayment.PRIMARY_DEPRECIATION_BASE_AMOUNT);

        criteriaC = new Criteria();
        criteriaC.addNotEqualTo(CamsPropertyConstants.AssetPayment.PRIMARY_DEPRECIATION_BASE_AMOUNT, 0);

        criteriaB.addOrCriteria(criteriaC);
        criteria.addAndCriteria(criteriaB);
        // End ***************

        // Begin *******************************************************************
        criteriaB = new Criteria();
        criteriaB.addIn(CamsPropertyConstants.AssetPayment.TRANSFER_PAYMENT_CODE, assetTransferCode);

        criteriaC = new Criteria();
        criteriaC.addIsNull(CamsPropertyConstants.AssetPayment.TRANSFER_PAYMENT_CODE);

        criteriaB.addOrCriteria(criteriaC);
        criteria.addAndCriteria(criteriaB);
        // End *********************************************************************

        // Begin **********
        criteria.addIn(PAYMENT_TO_ASSET_REFERENCE_DESCRIPTOR + CamsPropertyConstants.Asset.PRIMARY_DEPRECIATION_METHOD, depreciationMethodList);
        // End **********

        Calendar DateOf1900 = Calendar.getInstance();
        DateOf1900.set(1900, 0, 1);

        criteria.addNotNull         (PAYMENT_TO_ASSET_REFERENCE_DESCRIPTOR + CamsPropertyConstants.Asset.ASSET_DATE_OF_SERVICE);
        criteria.addLessOrEqualThan (PAYMENT_TO_ASSET_REFERENCE_DESCRIPTOR + CamsPropertyConstants.Asset.ASSET_DATE_OF_SERVICE, new java.sql.Date(depreciationDate.getTimeInMillis()));
        criteria.addGreaterThan     (PAYMENT_TO_ASSET_REFERENCE_DESCRIPTOR + CamsPropertyConstants.Asset.ASSET_DATE_OF_SERVICE, new java.sql.Date(DateOf1900.getTimeInMillis()));

        // Begin *******************************************************************
        criteriaB = new Criteria();
        criteriaB.addGreaterThan(PAYMENT_TO_ASSET_REFERENCE_DESCRIPTOR + CamsPropertyConstants.Asset.ASSET_RETIREMENT_FISCAL_YEAR, fiscalYear);

        criteriaC = new Criteria();
        criteriaC.addIsNull(PAYMENT_TO_ASSET_REFERENCE_DESCRIPTOR + CamsPropertyConstants.Asset.ASSET_RETIREMENT_FISCAL_YEAR);

        Criteria criteriaD = new Criteria();
        criteriaD.addNotNull(PAYMENT_TO_ASSET_REFERENCE_DESCRIPTOR + CamsPropertyConstants.Asset.ASSET_RETIREMENT_FISCAL_MONTH);

        criteriaB.addOrCriteria(criteriaC);
        criteriaB.addOrCriteria(criteriaD);

        criteriaC = new Criteria();
        criteriaC.addEqualTo    (PAYMENT_TO_ASSET_REFERENCE_DESCRIPTOR + CamsPropertyConstants.Asset.ASSET_RETIREMENT_FISCAL_YEAR, fiscalYear);
        criteriaC.addGreaterThan(PAYMENT_TO_ASSET_REFERENCE_DESCRIPTOR + CamsPropertyConstants.Asset.ASSET_RETIREMENT_FISCAL_MONTH, fiscalMonth);

        criteriaB.addOrCriteria(criteriaC);
        criteria.addAndCriteria(criteriaB);
        // End *********************************************************************

        if (!notAcceptedAssetStatus.isEmpty())
            criteria.addNotIn(PAYMENT_TO_ASSET_REFERENCE_DESCRIPTOR + CamsPropertyConstants.Asset.ASSET_INVENTORY_STATUS, notAcceptedAssetStatus);

        criteria.addGreaterThan(ASSET_TO_ASSET_TYPE_REFERENCE_DESCRIPTOR + CamsPropertyConstants.AssetType.ASSET_DEPRECIATION_LIFE_LIMIT, 0);

        // Excluding federally owned assets.
        if (!federallyOwnedObjectSybTypes.isEmpty())
            criteria.addNotIn(PAYMENT_TO_OBJECT_REFERENCE_DESCRIPTOR + KFSPropertyConstants.FINANCIAL_OBJECT_SUB_TYPE_CODE, federallyOwnedObjectSybTypes);

        // DELETE THIS LINE ******************************************
        // criteria.addEqualTo("capitalAssetNumber", "389220");
        // ************************************************************

        // Getting a list of assets being transferred or retired which documents are pending of approval.
        criteria.addNotIn(CamsPropertyConstants.AssetPayment.CAPITAL_ASSET_NUMBER, this.getPendingAssetDocumentSubquery());

        LOG.debug("CamsDepreciableAssetsDaoOjb.createDepreciationCriteria() -  ended");
        return criteria;
    }
} // end of class

/*
 * A.cptlast_nbr = P.cptlast_nbr // foreign key AND A.cptlast_origin_cd = P.cptlast_origin_cd // ??
 * 
 * AND P.cptlast_origin_cd = '01' // done AND ( P.ast_depr1_base_amt != 0.00 OR P.ast_depr1_base_amt IS NOT NULL ) //done AND (
 * P.ast_trnfr_pmt_cd IN ( 'N', ' ') OR P.ast_trnfr_pmt_cd IS NULL) //done AND ( ( A.ast_depr_mthd1_cd = 'SL'OR A.ast_depr_mthd1_cd
 * IS NULL ) OR ( A.ast_depr_mthd1_cd = 'SV' ) ) //Done
 * 
 * AND A.cptl_ast_int_srvc_dt IS NOT NULL // done AND A.cptl_ast_crt_dt != '01/01/1900' // done AND A.cptl_ast_crt_dt <= v_depr_date //
 * done
 * 
 * AND ( ( A.ast_retir_fscl_yr > v_fiscal_yr ) OR ( A.ast_retir_fscl_yr = v_fiscal_yr AND TO_NUMBER( A.ast_retir_prd_cd ) >
 * v_fiscal_prd ) OR A.ast_retir_fscl_yr IS NULL OR A.ast_retir_prd_cd IS NULL )
 * 
 * AND A.ast_invn_stat_cd NOT IN ( 'N', 'O' ) // done AND A.cptlast_typ_cd = T.cptlast_typ_cd //done AND T.cptlast_deprlf_lmt > 0
 * //done
 * 
 * and p.fin_object_cd not in ( -- Exclusing all the federally-owned object sub type codes. Select ca_object_code_t From
 * ca_object_code_t o, cm_cptlast_obj_t a Where o.univ_fiscal_yr = a.univ_fiscal_yr and a.univ_fiscal_yr = v_fiscal_year And
 * o.fin_coa_cd = a.fin_coa_cd And o.fin_obj_sub_typ_cd = a.fin_obj_sub_typ_cd And fin_obj_sub_typ_cd IN ('CO', 'CP', 'UO', 'AM',
 * 'AF', 'LA', 'BY' ) ) and
 * 
 * **** Excluding retired and transferd assets ************* P.cptlast_nbr + cptlast_origin_cd not in ( Select c.cptlast_nbr +
 * c.cptlast_origin_cd FROM fp_doc_header_t, H cm_cptlast_hdr_t T WHERE H.fs_origin_cd = C.fs_origin_cd and H.fdoc_nbr = C.fdoc_nbr
 * and H.fdoc_typ_cd IN ('AR', 'AT') AND H.fdoc_status_cd NOT IN ('A', 'C') GROUP BY P.cptlast_nbr, P.cptlast_origin_cd )*/