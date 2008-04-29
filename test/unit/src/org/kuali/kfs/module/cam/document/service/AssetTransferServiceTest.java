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
package org.kuali.module.cams.service;

import static org.kuali.test.fixtures.UserNameFixture.KHUNTLEY;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.util.KualiDecimal;
import org.kuali.kfs.bo.GeneralLedgerPendingEntrySourceDetail;
import org.kuali.kfs.context.KualiTestBase;
import org.kuali.kfs.context.SpringContext;
import org.kuali.module.cams.CamsConstants;
import org.kuali.module.cams.CamsPropertyConstants;
import org.kuali.module.cams.bo.Asset;
import org.kuali.module.cams.bo.AssetHeader;
import org.kuali.module.cams.bo.AssetPayment;
import org.kuali.module.cams.bo.AssetRetirementReason;
import org.kuali.module.cams.document.AssetTransferDocument;
import org.kuali.module.cams.fixture.AssetTransferFixture;
import org.kuali.test.ConfigureContext;
import org.kuali.test.fixtures.UserNameFixture;

public class AssetTransferServiceTest extends KualiTestBase {

    private AssetTransferService assetTransferService;

    @Override
    @ConfigureContext(session = UserNameFixture.KHUNTLEY, shouldCommitTransactions = false)
    protected void setUp() throws Exception {
        super.setUp();
        assetTransferService = SpringContext.getBean(AssetTransferService.class);
    }

    @SuppressWarnings("deprecation")
    public void testIsTransferable_RetiredAsset() throws Exception {
        assertNotNull(assetTransferService);
        AssetTransferDocument document = new AssetTransferDocument();
        Asset newAsset = AssetTransferFixture.RETIRED_ASSET.newAsset();
        AssetRetirementReason reason = new AssetRetirementReason();
        reason.setRetirementReasonCode(newAsset.getRetirementReasonCode());
        reason.setRetirementReasonName("Test Retired");
        newAsset.setRetirementReason(reason);
        document.setAsset(newAsset);
        assertFalse(this.assetTransferService.isTransferable(document));
    }

    public void testIsTransferable_Success() throws Exception {
        assertNotNull(assetTransferService);
        AssetTransferDocument document = AssetTransferFixture.ASSET_TRANSFER.newAssetTransferDocument();
        document.setAsset(AssetTransferFixture.ACTIVE_CAPITAL_ASSET.newAsset());
        assertTrue(this.assetTransferService.isTransferable(document));
    }

    public void testIsTransferable_LockedAsset() throws Exception {
        // TODO this is pending implementation
    }

    /**
     * Test capital asset with active payments
     * 
     * @throws Exception
     */
    @ConfigureContext(session = KHUNTLEY, shouldCommitTransactions = false)
    public void testCreateGLPostables_Success() throws Exception {
        // set up the data
        AssetTransferDocument document = buildTransferDocument(AssetTransferFixture.ACTIVE_CAPITAL_ASSET.newAsset(), true);
        this.assetTransferService.createGLPostables(document);
        // assert gl postables
        List<GeneralLedgerPendingEntrySourceDetail> generalLedgerPostables = document.getGeneralLedgerPostables();
        assertFalse(generalLedgerPostables.isEmpty());
        assertEquals(12, generalLedgerPostables.size());
        Asset asset = document.getAsset();
        // assert source gl postable for first payment
        AssetPayment payment1 = asset.getAssetPayments().get(0);
        assertGLPostable(generalLedgerPostables.get(0), asset.getOrganizationOwnerChartOfAccountsCode(), payment1.getAccountChargeAmount(), "9520004", "Reverse asset cost", "8610");
        assertGLPostable(generalLedgerPostables.get(1), asset.getOrganizationOwnerChartOfAccountsCode(), payment1.getAccumulatedPrimaryDepreciationAmount(), "9520004", "Reverse accumulated depreciation", "8910");
        assertGLPostable(generalLedgerPostables.get(2), asset.getOrganizationOwnerChartOfAccountsCode(), payment1.getAccountChargeAmount().subtract(payment1.getAccumulatedPrimaryDepreciationAmount()), "9520004", "Reverse offset amount", "9899");

        // assert source gl postable for second payment
        AssetPayment payment2 = asset.getAssetPayments().get(1);
        assertGLPostable(generalLedgerPostables.get(3), asset.getOrganizationOwnerChartOfAccountsCode(), payment2.getAccountChargeAmount(), "9520004", "Reverse asset cost", "8610");
        assertGLPostable(generalLedgerPostables.get(4), asset.getOrganizationOwnerChartOfAccountsCode(), payment2.getAccumulatedPrimaryDepreciationAmount(), "9520004", "Reverse accumulated depreciation", "8910");
        assertGLPostable(generalLedgerPostables.get(5), asset.getOrganizationOwnerChartOfAccountsCode(), payment2.getAccountChargeAmount().subtract(payment2.getAccumulatedPrimaryDepreciationAmount()), "9520004", "Reverse offset amount", "9899");

        // assert target gl postable for first payment
        assertGLPostable(generalLedgerPostables.get(6), document.getOrganizationOwnerChartOfAccountsCode(), payment1.getAccountChargeAmount(), "9567077", "Transfer asset cost", "8610");
        assertGLPostable(generalLedgerPostables.get(7), document.getOrganizationOwnerChartOfAccountsCode(), payment1.getAccumulatedPrimaryDepreciationAmount(), "9567077", "Transfer accumulated depreciation", "8910");
        assertGLPostable(generalLedgerPostables.get(8), document.getOrganizationOwnerChartOfAccountsCode(), payment1.getAccountChargeAmount().subtract(payment1.getAccumulatedPrimaryDepreciationAmount()), "9567077", "Transfer offset amount", "9899");

        // assert target gl postable for second payment
        assertGLPostable(generalLedgerPostables.get(9), document.getOrganizationOwnerChartOfAccountsCode(), payment2.getAccountChargeAmount(), "9567077", "Transfer asset cost", "8610");
        assertGLPostable(generalLedgerPostables.get(10), document.getOrganizationOwnerChartOfAccountsCode(), payment2.getAccumulatedPrimaryDepreciationAmount(), "9567077", "Transfer accumulated depreciation", "8910");
        assertGLPostable(generalLedgerPostables.get(11), document.getOrganizationOwnerChartOfAccountsCode(), payment2.getAccountChargeAmount().subtract(payment2.getAccumulatedPrimaryDepreciationAmount()), "9567077", "Transfer offset amount", "9899");


    }

    /**
     * Test capital asset with active payments
     * 
     * @throws Exception
     */
    @ConfigureContext(session = KHUNTLEY, shouldCommitTransactions = false)
    public void testCreateGLPostables_No_Offset() throws Exception {
        // set up the data
        AssetTransferDocument document = buildTransferDocumentWithoutOffset(AssetTransferFixture.ACTIVE_CAPITAL_ASSET.newAsset(), true);
        this.assetTransferService.createGLPostables(document);
        // assert gl postables
        List<GeneralLedgerPendingEntrySourceDetail> generalLedgerPostables = document.getGeneralLedgerPostables();
        assertFalse(generalLedgerPostables.isEmpty());
        assertEquals(8, generalLedgerPostables.size());
        Asset asset = document.getAsset();
        // assert source gl postable for first payment
        AssetPayment payment1 = asset.getAssetPayments().get(0);
        assertGLPostable(generalLedgerPostables.get(0), asset.getOrganizationOwnerChartOfAccountsCode(), payment1.getAccountChargeAmount(), "9520004", "Reverse asset cost", "8610");
        assertGLPostable(generalLedgerPostables.get(1), asset.getOrganizationOwnerChartOfAccountsCode(), payment1.getAccumulatedPrimaryDepreciationAmount(), "9520004", "Reverse accumulated depreciation", "8910");

        // assert source gl postable for second payment
        AssetPayment payment2 = asset.getAssetPayments().get(1);
        assertGLPostable(generalLedgerPostables.get(2), asset.getOrganizationOwnerChartOfAccountsCode(), payment2.getAccountChargeAmount(), "9520004", "Reverse asset cost", "8610");
        assertGLPostable(generalLedgerPostables.get(3), asset.getOrganizationOwnerChartOfAccountsCode(), payment2.getAccumulatedPrimaryDepreciationAmount(), "9520004", "Reverse accumulated depreciation", "8910");

        // assert target gl postable for first payment
        assertGLPostable(generalLedgerPostables.get(4), document.getOrganizationOwnerChartOfAccountsCode(), payment1.getAccountChargeAmount(), "9567077", "Transfer asset cost", "8610");
        assertGLPostable(generalLedgerPostables.get(5), document.getOrganizationOwnerChartOfAccountsCode(), payment1.getAccumulatedPrimaryDepreciationAmount(), "9567077", "Transfer accumulated depreciation", "8910");

        // assert target gl postable for second payment
        assertGLPostable(generalLedgerPostables.get(6), document.getOrganizationOwnerChartOfAccountsCode(), payment2.getAccountChargeAmount(), "9567077", "Transfer asset cost", "8610");
        assertGLPostable(generalLedgerPostables.get(7), document.getOrganizationOwnerChartOfAccountsCode(), payment2.getAccumulatedPrimaryDepreciationAmount(), "9567077", "Transfer accumulated depreciation", "8910");


    }

    /**
     * test non-capital assets
     * 
     * @throws Exception
     */
    @ConfigureContext(session = KHUNTLEY, shouldCommitTransactions = false)
    public void testCreateGLPostables_Non_CapitalAsset() throws Exception {
        // set up the data
        AssetTransferDocument document = buildTransferDocument(AssetTransferFixture.ACTIVE_NON_CAPITAL_ASSET.newAsset(), true);
        this.assetTransferService.createGLPostables(document);
        // assert gl postables
        List<GeneralLedgerPendingEntrySourceDetail> generalLedgerPostables = document.getGeneralLedgerPostables();
        assertTrue(generalLedgerPostables.isEmpty());
    }

    /**
     * Test active asset with no payments
     * 
     * @throws Exception
     */
    @ConfigureContext(session = KHUNTLEY, shouldCommitTransactions = false)
    public void testCreateGLPostables_No_Payments() throws Exception {
        // set up the data
        AssetTransferDocument document = buildTransferDocument(AssetTransferFixture.ACTIVE_CAPITAL_ASSET.newAsset(), false);
        this.assetTransferService.createGLPostables(document);
        // assert gl postables
        List<GeneralLedgerPendingEntrySourceDetail> generalLedgerPostables = document.getGeneralLedgerPostables();
        assertTrue(generalLedgerPostables.isEmpty());
    }

    /**
     * Assert GL postable entry
     * 
     * @param glPostable
     * @param chartOfAccountsCode
     * @param amount
     * @param plantAccount
     * @param financialLineDesc
     * @param financialObjectCode
     */
    private void assertGLPostable(GeneralLedgerPendingEntrySourceDetail glPostable, String chartOfAccountsCode, KualiDecimal amount, String plantAccount, String financialLineDesc, String financialObjectCode) {
        assertEquals(plantAccount, glPostable.getAccountNumber());
        assertEquals(amount, glPostable.getAmount());
        assertEquals(CamsConstants.GL_BALANCE_TYPE_CDE_AC, glPostable.getBalanceTypeCode());
        assertEquals(chartOfAccountsCode, glPostable.getChartOfAccountsCode());
        assertEquals(financialLineDesc, glPostable.getFinancialDocumentLineDescription());
        assertEquals(financialObjectCode, glPostable.getFinancialObjectCode());
        assertEquals(Integer.valueOf(2008), glPostable.getPostingYear());
        assertNull(glPostable.getOrganizationReferenceId());
        assertNull(glPostable.getProjectCode());
        assertNull(glPostable.getReferenceNumber());
        assertNull(glPostable.getReferenceOriginCode());
        assertNull(glPostable.getReferenceTypeCode());
    }

    private AssetTransferDocument buildTransferDocument(Asset asset, boolean addPayments) {
        BusinessObjectService boService = SpringContext.getBean(BusinessObjectService.class);
        AssetTransferDocument document = AssetTransferFixture.ASSET_TRANSFER.newAssetTransferDocument();
        asset.setCapitalAssetNumber(null);
        asset.setCapitalAssetTypeCode("665");
        boService.save(asset);
        if (addPayments) {
            AssetPayment payment1 = AssetTransferFixture.PAYMENT1_WITH_OFFSET.newAssetPayment();
            AssetPayment payment2 = AssetTransferFixture.PAYMENT2_WITH_OFFSET.newAssetPayment();
            payment1.setCapitalAssetNumber(asset.getCapitalAssetNumber());
            payment1.setPaymentSequenceNumber(1);
            payment2.setCapitalAssetNumber(asset.getCapitalAssetNumber());
            payment2.setPaymentSequenceNumber(2);
            boService.save(payment1);
            boService.save(payment2);
            payment1.refresh();
            payment2.refresh();
            asset.getAssetPayments().add(payment1);
            asset.getAssetPayments().add(payment2);
        }
        AssetHeader assetHeader = new AssetHeader();
        assetHeader.setCapitalAssetNumber(asset.getCapitalAssetNumber());
        assetHeader.setDocumentNumber(document.getDocumentNumber());
        document.setAsset(asset);
        document.setAssetHeader(assetHeader);
        return document;
    }

    private AssetTransferDocument buildTransferDocumentWithoutOffset(Asset asset, boolean addPayments) {
        BusinessObjectService boService = SpringContext.getBean(BusinessObjectService.class);
        AssetTransferDocument document = AssetTransferFixture.ASSET_TRANSFER.newAssetTransferDocument();
        asset.setCapitalAssetNumber(null);
        asset.setCapitalAssetTypeCode("665");
        boService.save(asset);
        if (addPayments) {
            AssetPayment payment1 = AssetTransferFixture.PAYMENT3_WITHOUT_OFFSET.newAssetPayment();
            AssetPayment payment2 = AssetTransferFixture.PAYMENT4_WITHOUT_OFFSET.newAssetPayment();
            payment1.setCapitalAssetNumber(asset.getCapitalAssetNumber());
            payment1.setPaymentSequenceNumber(1);
            payment2.setCapitalAssetNumber(asset.getCapitalAssetNumber());
            payment2.setPaymentSequenceNumber(2);
            boService.save(payment1);
            boService.save(payment2);
            payment1.refresh();
            payment2.refresh();
            asset.getAssetPayments().add(payment1);
            asset.getAssetPayments().add(payment2);
        }
        document.setAsset(asset);
        return document;
    }

    @ConfigureContext(session = KHUNTLEY, shouldCommitTransactions = false)
    public void testSaveApprovedChanges() throws Exception {
        AssetTransferDocument document = buildTransferDocument(AssetTransferFixture.ACTIVE_CAPITAL_ASSET.newAsset(), true);
        this.assetTransferService.saveApprovedChanges(document);
        BusinessObjectService boService = SpringContext.getBean(BusinessObjectService.class);
        Asset asset = document.getAsset();
        asset = (Asset) boService.retrieve(asset);
        asset.refreshReferenceObject(CamsPropertyConstants.Asset.ASSET_PAYMENTS);
        assertNotNull(asset);
        assertEquals(document.getOrganizationOwnerChartOfAccountsCode(), asset.getOrganizationOwnerChartOfAccountsCode());
        assertEquals(document.getOrganizationOwnerAccountNumber(), asset.getOrganizationOwnerAccountNumber());
        assertEquals(document.getCampusCode(), asset.getCampusCode());
        assertEquals(document.getBuildingCode(), asset.getBuildingCode());
        assertEquals(document.getBuildingRoomNumber(), asset.getBuildingRoomNumber());
        List<AssetPayment> assetPayments = asset.getAssetPayments();
        // sort the payment
        Collections.sort(assetPayments, new Comparator<AssetPayment>() {
            public int compare(AssetPayment o1, AssetPayment o2) {
                return o1.getPaymentSequenceNumber().compareTo(o2.getPaymentSequenceNumber());
            }
        });
        assertEquals(6, assetPayments.size());
        // Asset original payment 1
        AssetPayment testDataPayment = AssetTransferFixture.PAYMENT1_WITH_OFFSET.newAssetPayment();
        AssetPayment assetPayment1 = assetPayments.get(0);
        assertOriginalPayment(testDataPayment, assetPayment1);

        // Assert original payment 2
        testDataPayment = AssetTransferFixture.PAYMENT2_WITH_OFFSET.newAssetPayment();
        AssetPayment assetPayment2 = assetPayments.get(1);
        assertOriginalPayment(testDataPayment, assetPayment2);

        // Assert offset payment 1
        testDataPayment = AssetTransferFixture.PAYMENT1_WITH_OFFSET.newAssetPayment();
        AssetPayment offsetPayment1 = assetPayments.get(2);
        assertOffsetPayment(testDataPayment, offsetPayment1);

        // Assert offset payment 2
        testDataPayment = AssetTransferFixture.PAYMENT2_WITH_OFFSET.newAssetPayment();
        AssetPayment offsetPayment2 = assetPayments.get(3);
        assertOffsetPayment(testDataPayment, offsetPayment2);

        // Assert new payment 1
        testDataPayment = AssetTransferFixture.PAYMENT1_WITH_OFFSET.newAssetPayment();
        AssetPayment newPayment1 = assetPayments.get(4);
        assertNewPayment(document, testDataPayment, newPayment1);

        // Assert new payment 2
        testDataPayment = AssetTransferFixture.PAYMENT2_WITH_OFFSET.newAssetPayment();
        AssetPayment newPayment2 = assetPayments.get(5);
        assertNewPayment(document, testDataPayment, newPayment2);
    }

    private void assertNewPayment(AssetTransferDocument document, AssetPayment referencePayment, AssetPayment newPayment) {
        assertEquals(document.getOrganizationOwnerAccountNumber(), newPayment.getAccountNumber());
        assertEquals(referencePayment.getAccountChargeAmount(), newPayment.getAccountChargeAmount());
        assertEquals(referencePayment.getAccumulatedPrimaryDepreciationAmount(), newPayment.getAccumulatedPrimaryDepreciationAmount());
        assertEquals(null, newPayment.getPeriod1Depreciation1Amount());
        assertEquals(null, newPayment.getTransferPaymentCode());
    }

    private void assertOriginalPayment(AssetPayment referencePayment, AssetPayment assetOriginalPayment) {
        assertEquals(referencePayment.getAccountNumber(), assetOriginalPayment.getAccountNumber());
        assertEquals(referencePayment.getAccountChargeAmount(), assetOriginalPayment.getAccountChargeAmount());
        assertEquals(referencePayment.getAccumulatedPrimaryDepreciationAmount(), assetOriginalPayment.getAccumulatedPrimaryDepreciationAmount());
        assertEquals(referencePayment.getPeriod1Depreciation1Amount(), assetOriginalPayment.getPeriod1Depreciation1Amount());
        assertEquals(CamsConstants.TRANSFER_PAYMENT_CODE_Y, assetOriginalPayment.getTransferPaymentCode());
    }

    private void assertOffsetPayment(AssetPayment referencePayment, AssetPayment assetOffsetPayment) {
        assertEquals(referencePayment.getAccountNumber(), assetOffsetPayment.getAccountNumber());
        KualiDecimal negative = new KualiDecimal(-1);
        assertEquals(referencePayment.getAccountChargeAmount().multiply(negative), assetOffsetPayment.getAccountChargeAmount());
        assertEquals(referencePayment.getAccumulatedPrimaryDepreciationAmount().multiply(negative), assetOffsetPayment.getAccumulatedPrimaryDepreciationAmount());
        assertEquals(null, assetOffsetPayment.getPeriod1Depreciation1Amount());
        assertEquals(CamsConstants.TRANSFER_PAYMENT_CODE_Y, assetOffsetPayment.getTransferPaymentCode());
    }
}
