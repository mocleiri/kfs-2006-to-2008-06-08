/* Run in KULDBA */

/* 1a) */
truncate table ER_BDGT_DOC_T;

/* 1b) */
-- remove orphan members
DELETE FROM en_wrkgrp_mbr_t
    WHERE NOT EXISTS ( SELECT 'x' FROM en_wrkgrp_t 
        WHERE wrkgrp_id = en_wrkgrp_mbr_t.wrkgrp_id
          AND wrkgrp_ver_nbr = en_wrkgrp_mbr_t.wrkgrp_ver_nbr 
    )
/  

/* 1d) */
UPDATE ca_obj_sub_type_t
    SET fin_obj_sub_typ_nm = 'TRANSFERS - NONMANDATORY'
    WHERE fin_obj_sub_typ_cd = 'TN'
/

/* 1e) */
-- rename the "STATE OF INDIANA" sub fund groups            
UPDATE ca_sub_fund_grp_t 
SET sub_fund_grp_desc = 'STATE FUNDS'
WHERE sub_fund_grp_desc = 'STATE OF INDIANA'
/

/* 1h) */
-- remove references to "IU" and specific object codes
UPDATE fp_dv_pmt_reas_t 
    SET dv_pmt_reas_desc = REPLACE( dv_pmt_reas_desc, ' IU ', ' University ' )
/

/* 1i) */
-- Tax Control Name referenced "IU"       
UPDATE fp_dv_tax_ctrl_t
   SET dv_tax_ctrl_nm = 'Approved for Employee'
   WHERE dv_payee_txctrl_cd = 'A'
/

-- TODO: cleanup unused workgroups

-- cleanup unused documents and old versions
DELETE
  FROM en_rte_node_lnk_t a
  WHERE from_rte_node_id IN ( SELECT rte_node_id FROM en_rte_node_t
  WHERE doc_typ_id IN (SELECT doc_typ_id FROM en_doc_typ_t
    WHERE doc_typ_nm IN (
  'AccountingPeriodMainanceDocument'
, 'BusinessRuleMaintenanceDocument'
, 'KualiAccountGuideline'
, 'KualiApplicationConstantMaintenanceDocument'
, 'KualiAutomatedReportPeriodMaintenanceDocument'
, 'KualiAutomatedReportPeriodMaintenanceDocument'
, 'KualiBaseTestDocument'
, 'KualiBudgetBaseCodeMaintenanceDocument'
, 'KualiBudgetPurposeCodeMaintenanceDocument'
, 'KualiBusinessRuleGroupMaintenanceDocument'
, 'KualiBusinessRuleMaintenanceDocument'
, 'KualiCatalogOfFederalDomesticAssistanceReferenceMaintenanceDocument'
, 'KualiCloseDocument'
, 'KualiCollectorFileMaintenanceDocument'
, 'KualiControlAttributeTypeMaintenanceDocument'
, 'KualiDisbursementVoucherPaymentReasonMaintenanceDocument'
, 'KualiErrorCertificationReportPeriodStatusMaintenanceDocument'
, 'KualiFederalFundedMaintenanceDocument'
, 'KualiFinancialSystemParameterSecurityMaintenanceDocument'
, 'KualiGeneralErrorCorrectionYearEndDocument'
, 'KualiGlobalAccountMaintenanceDocument'
, 'KualiGrantDescriptionMaintenanceDocument'
, 'KualiGroupMaintenanceDocument'
, 'KualiHomeOriginationMaintenanceDocument'
, 'KualiIndirectCostLookupMaintenanceDocument'
, 'KualiIndirectCostRecoveryThresholdMaintenanceDocument'
, 'KualiKeywordMaintenanceDocument'
, 'KualiLaborObjectBenefitMaintenanceDocument'
, 'KualiLaborObjectGroupMaintenanceDocument'
, 'KualiLedgerErrorCorrectionDocument'
, 'KualiLetterOfCreditFundGroupMaintenanceDocument'
, 'KualiObjectCodeRestrictionsMaintenanceDocument'
, 'KualiOrganizationReversionChangeMaintenanceDocument'
, 'KualiOrgOptionsMaintenanceDocument'
, 'KualiProjectDirectorMaintenanceDocument'
, 'KualiPurchaseOrderPrintDocument'
, 'KualiReportingFinancialDocument'
, 'KualiReportingTransferOfFundsDocument'
, 'KualiResearchAdminPreAwartMaintenanceDocument'
, 'KualiRoutingFormResearchRiskTypeDocument'
, 'KualiSharedStateMaintenanceDocument'
, 'KualiSharedZipCodeMaintenanceDocument'
, 'KualiSubmissionTypeMaintenanceDocument'
, 'KualiUserMaintenanceDocument'
, 'KualiVendorStatusMaintenanceDocument'
, 'KualiYearEndDistIncExpDocument'
, 'KualiYETESTDistributionOfIncomeAndExpenseDocument'
, 'KualiZipCodeMaintenanceDocument'
, 'MyTestDocumentType'
, 'ProgramMaintenanceDocument'
, 'Test Disbursement Voucher Document'
, 'TestWorkflowDocument'
, 'TestWorkflowKualiInternalBillingDocument'
)
		OR doc_typ_cur_ind = 0
)
)
/

DELETE
  FROM en_rte_node_lnk_t a
  WHERE to_rte_node_id IN ( SELECT rte_node_id FROM en_rte_node_t
  WHERE doc_typ_id IN (SELECT doc_typ_id FROM en_doc_typ_t
    WHERE doc_typ_nm IN (
  'AccountingPeriodMainanceDocument'
, 'BusinessRuleMaintenanceDocument'
, 'KualiAccountGuideline'
, 'KualiApplicationConstantMaintenanceDocument'
, 'KualiAutomatedReportPeriodMaintenanceDocument'
, 'KualiAutomatedReportPeriodMaintenanceDocument'
, 'KualiBaseTestDocument'
, 'KualiBudgetBaseCodeMaintenanceDocument'
, 'KualiBudgetPurposeCodeMaintenanceDocument'
, 'KualiBusinessRuleGroupMaintenanceDocument'
, 'KualiBusinessRuleMaintenanceDocument'
, 'KualiCatalogOfFederalDomesticAssistanceReferenceMaintenanceDocument'
, 'KualiCloseDocument'
, 'KualiCollectorFileMaintenanceDocument'
, 'KualiControlAttributeTypeMaintenanceDocument'
, 'KualiDisbursementVoucherPaymentReasonMaintenanceDocument'
, 'KualiErrorCertificationReportPeriodStatusMaintenanceDocument'
, 'KualiFederalFundedMaintenanceDocument'
, 'KualiFinancialSystemParameterSecurityMaintenanceDocument'
, 'KualiGeneralErrorCorrectionYearEndDocument'
, 'KualiGlobalAccountMaintenanceDocument'
, 'KualiGrantDescriptionMaintenanceDocument'
, 'KualiGroupMaintenanceDocument'
, 'KualiHomeOriginationMaintenanceDocument'
, 'KualiIndirectCostLookupMaintenanceDocument'
, 'KualiIndirectCostRecoveryThresholdMaintenanceDocument'
, 'KualiKeywordMaintenanceDocument'
, 'KualiLaborObjectBenefitMaintenanceDocument'
, 'KualiLaborObjectGroupMaintenanceDocument'
, 'KualiLedgerErrorCorrectionDocument'
, 'KualiLetterOfCreditFundGroupMaintenanceDocument'
, 'KualiObjectCodeRestrictionsMaintenanceDocument'
, 'KualiOrganizationReversionChangeMaintenanceDocument'
, 'KualiOrgOptionsMaintenanceDocument'
, 'KualiProjectDirectorMaintenanceDocument'
, 'KualiPurchaseOrderPrintDocument'
, 'KualiReportingFinancialDocument'
, 'KualiReportingTransferOfFundsDocument'
, 'KualiResearchAdminPreAwartMaintenanceDocument'
, 'KualiRoutingFormResearchRiskTypeDocument'
, 'KualiSharedStateMaintenanceDocument'
, 'KualiSharedZipCodeMaintenanceDocument'
, 'KualiSubmissionTypeMaintenanceDocument'
, 'KualiUserMaintenanceDocument'
, 'KualiVendorStatusMaintenanceDocument'
, 'KualiYearEndDistIncExpDocument'
, 'KualiYETESTDistributionOfIncomeAndExpenseDocument'
, 'KualiZipCodeMaintenanceDocument'
, 'MyTestDocumentType'
, 'ProgramMaintenanceDocument'
, 'Test Disbursement Voucher Document'
, 'TestWorkflowDocument'
, 'TestWorkflowKualiInternalBillingDocument'
)
		OR doc_typ_cur_ind = 0
)
)
/

DELETE
  FROM en_rte_node_t a
  WHERE doc_typ_id IN (SELECT doc_typ_id FROM en_doc_typ_t
    WHERE doc_typ_nm IN (
  'AccountingPeriodMainanceDocument'
, 'BusinessRuleMaintenanceDocument'
, 'KualiAccountGuideline'
, 'KualiApplicationConstantMaintenanceDocument'
, 'KualiAutomatedReportPeriodMaintenanceDocument'
, 'KualiAutomatedReportPeriodMaintenanceDocument'
, 'KualiBaseTestDocument'
, 'KualiBudgetBaseCodeMaintenanceDocument'
, 'KualiBudgetPurposeCodeMaintenanceDocument'
, 'KualiBusinessRuleGroupMaintenanceDocument'
, 'KualiBusinessRuleMaintenanceDocument'
, 'KualiCatalogOfFederalDomesticAssistanceReferenceMaintenanceDocument'
, 'KualiCloseDocument'
, 'KualiCollectorFileMaintenanceDocument'
, 'KualiControlAttributeTypeMaintenanceDocument'
, 'KualiDisbursementVoucherPaymentReasonMaintenanceDocument'
, 'KualiErrorCertificationReportPeriodStatusMaintenanceDocument'
, 'KualiFederalFundedMaintenanceDocument'
, 'KualiFinancialSystemParameterSecurityMaintenanceDocument'
, 'KualiGeneralErrorCorrectionYearEndDocument'
, 'KualiGlobalAccountMaintenanceDocument'
, 'KualiGrantDescriptionMaintenanceDocument'
, 'KualiGroupMaintenanceDocument'
, 'KualiHomeOriginationMaintenanceDocument'
, 'KualiIndirectCostLookupMaintenanceDocument'
, 'KualiIndirectCostRecoveryThresholdMaintenanceDocument'
, 'KualiKeywordMaintenanceDocument'
, 'KualiLaborObjectBenefitMaintenanceDocument'
, 'KualiLaborObjectGroupMaintenanceDocument'
, 'KualiLedgerErrorCorrectionDocument'
, 'KualiLetterOfCreditFundGroupMaintenanceDocument'
, 'KualiObjectCodeRestrictionsMaintenanceDocument'
, 'KualiOrganizationReversionChangeMaintenanceDocument'
, 'KualiOrgOptionsMaintenanceDocument'
, 'KualiProjectDirectorMaintenanceDocument'
, 'KualiPurchaseOrderPrintDocument'
, 'KualiReportingFinancialDocument'
, 'KualiReportingTransferOfFundsDocument'
, 'KualiResearchAdminPreAwartMaintenanceDocument'
, 'KualiRoutingFormResearchRiskTypeDocument'
, 'KualiSharedStateMaintenanceDocument'
, 'KualiSharedZipCodeMaintenanceDocument'
, 'KualiSubmissionTypeMaintenanceDocument'
, 'KualiUserMaintenanceDocument'
, 'KualiVendorStatusMaintenanceDocument'
, 'KualiYearEndDistIncExpDocument'
, 'KualiYETESTDistributionOfIncomeAndExpenseDocument'
, 'KualiZipCodeMaintenanceDocument'
, 'MyTestDocumentType'
, 'ProgramMaintenanceDocument'
, 'Test Disbursement Voucher Document'
, 'TestWorkflowDocument'
, 'TestWorkflowKualiInternalBillingDocument'
)
		OR doc_typ_cur_ind = 0
)
/

-- clear out bad document types
DELETE 
FROM en_doc_typ_t
    WHERE doc_typ_nm IN (
  'AccountingPeriodMainanceDocument'
, 'BusinessRuleMaintenanceDocument'
, 'KualiAccountGuideline'
, 'KualiApplicationConstantMaintenanceDocument'
, 'KualiAutomatedReportPeriodMaintenanceDocument'
, 'KualiAutomatedReportPeriodMaintenanceDocument'
, 'KualiBaseTestDocument'
, 'KualiBudgetBaseCodeMaintenanceDocument'
, 'KualiBudgetPurposeCodeMaintenanceDocument'
, 'KualiBusinessRuleGroupMaintenanceDocument'
, 'KualiBusinessRuleMaintenanceDocument'
, 'KualiCatalogOfFederalDomesticAssistanceReferenceMaintenanceDocument'
, 'KualiCloseDocument'
, 'KualiCollectorFileMaintenanceDocument'
, 'KualiControlAttributeTypeMaintenanceDocument'
, 'KualiDisbursementVoucherPaymentReasonMaintenanceDocument'
, 'KualiErrorCertificationReportPeriodStatusMaintenanceDocument'
, 'KualiFederalFundedMaintenanceDocument'
, 'KualiFinancialSystemParameterSecurityMaintenanceDocument'
, 'KualiGeneralErrorCorrectionYearEndDocument'
, 'KualiGlobalAccountMaintenanceDocument'
, 'KualiGrantDescriptionMaintenanceDocument'
, 'KualiGroupMaintenanceDocument'
, 'KualiHomeOriginationMaintenanceDocument'
, 'KualiIndirectCostLookupMaintenanceDocument'
, 'KualiIndirectCostRecoveryThresholdMaintenanceDocument'
, 'KualiKeywordMaintenanceDocument'
, 'KualiLaborObjectBenefitMaintenanceDocument'
, 'KualiLaborObjectGroupMaintenanceDocument'
, 'KualiLedgerErrorCorrectionDocument'
, 'KualiLetterOfCreditFundGroupMaintenanceDocument'
, 'KualiObjectCodeRestrictionsMaintenanceDocument'
, 'KualiOrganizationReversionChangeMaintenanceDocument'
, 'KualiOrgOptionsMaintenanceDocument'
, 'KualiProjectDirectorMaintenanceDocument'
, 'KualiPurchaseOrderPrintDocument'
, 'KualiReportingFinancialDocument'
, 'KualiReportingTransferOfFundsDocument'
, 'KualiResearchAdminPreAwartMaintenanceDocument'
, 'KualiRoutingFormResearchRiskTypeDocument'
, 'KualiSharedStateMaintenanceDocument'
, 'KualiSharedZipCodeMaintenanceDocument'
, 'KualiSubmissionTypeMaintenanceDocument'
, 'KualiUserMaintenanceDocument'
, 'KualiVendorStatusMaintenanceDocument'
, 'KualiYearEndDistIncExpDocument'
, 'KualiYETESTDistributionOfIncomeAndExpenseDocument'
, 'KualiZipCodeMaintenanceDocument'
, 'MyTestDocumentType'
, 'ProgramMaintenanceDocument'
, 'Test Disbursement Voucher Document'
, 'TestWorkflowDocument'
, 'TestWorkflowKualiInternalBillingDocument'
)
		OR doc_typ_cur_ind = 0
/
