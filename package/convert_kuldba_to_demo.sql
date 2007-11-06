/* KULDBA to Demo */
/* Disable constraints for the duration of this script */
DECLARE 
   CURSOR constraint_cursor IS 
      SELECT table_name, constraint_name 
         FROM user_constraints 
         WHERE constraint_type = 'R'
           AND status = 'ENABLED';
BEGIN 
   FOR r IN constraint_cursor LOOP
      execute immediate 'ALTER TABLE '||r.table_name||' DISABLE CONSTRAINT '||r.constraint_name; 
   END LOOP; 
END;
/

-- clear all locks

TRUNCATE TABLE fp_maint_lock_t
/

-- check parm table for IU hosts/emails

-- INVALID_FILE_TO_ADDRESSES
-- HARD_EDIT_TO_EMAIL_ADDRESSES
-- SOFT_EDIT_TO_EMAIL_ADDRESSES
-- TAX_CANCEL_TO_EMAIL_ADDRESSES
-- TAX_GROUP_TO_EMAIL_ADDRESSES
-- NO_PAYMENT_FILE_TO_EMAIL_ADDRESSES
UPDATE sh_parm_t
	SET sh_parm_txt = 'fixme@sample.edu'
  WHERE sh_parm_typ_cd <> 'HELP'
    AND sh_parm_nm LIKE '%ADDRESSES'
/

-- clean out non release 2.0 documents and associated entries

DELETE FROM fp_doc_type_t
  WHERE fdoc_grp_cd IN ( 'AR', 'AN', 'CM', 'CR', 'MO', 'SF' )
     OR FDOC_TYP_ACTIVE_CD = 'N'
/

DELETE FROM fp_doc_group_t
  WHERE fdoc_grp_cd NOT IN ( SELECT DISTINCT fdoc_grp_cd FROM fp_doc_type_t )
/

DELETE FROM ca_acct_delegate_t
    WHERE fdoc_typ_cd NOT IN ( SELECT fdoc_typ_cd FROM fp_doc_type_t )
/

DELETE FROM gl_offset_defn_t
    WHERE fdoc_typ_cd NOT IN ( SELECT fdoc_typ_cd FROM fp_doc_type_t )
/    

-- NOTE: this will leave the balance table out of sync with the GL entries
DELETE FROM gl_entry_t
    WHERE fdoc_typ_cd NOT IN ( SELECT fdoc_typ_cd FROM fp_doc_type_t )
/    

DELETE FROM gl_encumbrance_t
    WHERE fdoc_typ_cd NOT IN ( SELECT fdoc_typ_cd FROM fp_doc_type_t )
/    

DELETE FROM ld_ldgr_entr_t
    WHERE fdoc_typ_cd NOT IN ( SELECT fdoc_typ_cd FROM fp_doc_type_t )
/    


-- TODO: Clean up workflow document types and versions

DELETE
--    SELECT *
  FROM en_rte_node_lnk_t a
  WHERE from_rte_node_id IN ( SELECT rte_node_id FROM en_rte_node_t
  WHERE doc_typ_id IN (SELECT doc_typ_id FROM en_doc_typ_t
    WHERE doc_typ_nm LIKE 'Fake%'
       OR doc_typ_nm LIKE 'Test%'
       OR doc_typ_nm LIKE 'MyTest%'
       OR doc_typ_nm LIKE '%TEST%'
       OR doc_typ_nm LIKE 'aintenance%'
		OR doc_typ_cur_ind = 0
		OR doc_typ_nm IN ( 
  'KualiA21LaborDistributionHelpTextMaintenanceDocument'
, 'KualiA21LaborDistributionReportTypeMaintenanceDocument'
, 'KualiA21ReportPeriodMaintenanceDocument'
, 'KualiA21SubAccountMaintenanceDocument'
, 'KualiBudgetAdjustmentFringeBenefitMoveMaintenanceDocument'
, 'KualiBudgetConstructionAccountReportsMaintenanceDocument'
, 'KualiBudgetConstructionAppointmentFundingReasonCodeMaintenanceDocument'
, 'KualiBudgetConstructionDocument'
, 'KualiBudgetConstructionDurationMaintenanceDocument'
, 'KualiBudgetConstructionIntendedIncumbentMaintenanceDocument'
, 'KualiBudgetConstructionOrganizationReportsMaintenanceDocument'
, 'KualiBudgetConstructionPositionMaintenanceDocument'
, 'KualiCalculatedSalaryFoundationTrackerOverrideMaintenanceDocument'
, 'ProgramMaintenanceDocument'
)
)
)
/

DELETE
  FROM en_rte_node_lnk_t a
  WHERE to_rte_node_id IN ( SELECT rte_node_id FROM en_rte_node_t
  WHERE doc_typ_id IN (SELECT doc_typ_id FROM en_doc_typ_t
    WHERE doc_typ_nm LIKE 'Fake%'
       OR doc_typ_nm LIKE 'Test%'
       OR doc_typ_nm LIKE 'MyTest%'
       OR doc_typ_nm LIKE '%TEST%'
       OR doc_typ_nm LIKE 'aintenance%'
		OR doc_typ_cur_ind = 0
		OR doc_typ_nm IN ( 
  'KualiA21LaborDistributionHelpTextMaintenanceDocument'
, 'KualiA21LaborDistributionReportTypeMaintenanceDocument'
, 'KualiA21ReportPeriodMaintenanceDocument'
, 'KualiA21SubAccountMaintenanceDocument'
, 'KualiBudgetAdjustmentFringeBenefitMoveMaintenanceDocument'
, 'KualiBudgetConstructionAccountReportsMaintenanceDocument'
, 'KualiBudgetConstructionAppointmentFundingReasonCodeMaintenanceDocument'
, 'KualiBudgetConstructionDocument'
, 'KualiBudgetConstructionDurationMaintenanceDocument'
, 'KualiBudgetConstructionIntendedIncumbentMaintenanceDocument'
, 'KualiBudgetConstructionOrganizationReportsMaintenanceDocument'
, 'KualiBudgetConstructionPositionMaintenanceDocument'
, 'KualiCalculatedSalaryFoundationTrackerOverrideMaintenanceDocument'
, 'ProgramMaintenanceDocument'
)
)
)
/

DELETE
  FROM en_rte_node_t a
  WHERE doc_typ_id IN (SELECT doc_typ_id FROM en_doc_typ_t
    WHERE doc_typ_nm LIKE 'Fake%'
       OR doc_typ_nm LIKE 'Test%'
       OR doc_typ_nm LIKE 'MyTest%'
       OR doc_typ_nm LIKE '%TEST%'
       OR doc_typ_nm LIKE 'aintenance%'
		OR doc_typ_cur_ind = 0
		OR doc_typ_nm IN ( 
  'KualiA21LaborDistributionHelpTextMaintenanceDocument'
, 'KualiA21LaborDistributionReportTypeMaintenanceDocument'
, 'KualiA21ReportPeriodMaintenanceDocument'
, 'KualiA21SubAccountMaintenanceDocument'
, 'KualiBudgetAdjustmentFringeBenefitMoveMaintenanceDocument'
, 'KualiBudgetConstructionAccountReportsMaintenanceDocument'
, 'KualiBudgetConstructionAppointmentFundingReasonCodeMaintenanceDocument'
, 'KualiBudgetConstructionDocument'
, 'KualiBudgetConstructionDurationMaintenanceDocument'
, 'KualiBudgetConstructionIntendedIncumbentMaintenanceDocument'
, 'KualiBudgetConstructionOrganizationReportsMaintenanceDocument'
, 'KualiBudgetConstructionPositionMaintenanceDocument'
, 'KualiCalculatedSalaryFoundationTrackerOverrideMaintenanceDocument'
, 'ProgramMaintenanceDocument'
)
)
/

-- clear out bad document types
DELETE 
--SELECT *
FROM en_doc_typ_t
    WHERE doc_typ_nm LIKE 'Fake%'
       OR doc_typ_nm LIKE 'Test%'
       OR doc_typ_nm LIKE 'MyTest%'
       OR doc_typ_nm LIKE '%TEST%'
       OR doc_typ_nm LIKE 'aintenance%'
		OR doc_typ_cur_ind = 0
		OR doc_typ_nm IN ( 
  'KualiA21LaborDistributionHelpTextMaintenanceDocument'
, 'KualiA21LaborDistributionReportTypeMaintenanceDocument'
, 'KualiA21ReportPeriodMaintenanceDocument'
, 'KualiA21SubAccountMaintenanceDocument'
, 'KualiBudgetAdjustmentFringeBenefitMoveMaintenanceDocument'
, 'KualiBudgetConstructionAccountReportsMaintenanceDocument'
, 'KualiBudgetConstructionAppointmentFundingReasonCodeMaintenanceDocument'
, 'KualiBudgetConstructionDocument'
, 'KualiBudgetConstructionDurationMaintenanceDocument'
, 'KualiBudgetConstructionIntendedIncumbentMaintenanceDocument'
, 'KualiBudgetConstructionOrganizationReportsMaintenanceDocument'
, 'KualiBudgetConstructionPositionMaintenanceDocument'
, 'KualiCalculatedSalaryFoundationTrackerOverrideMaintenanceDocument'
, 'ProgramMaintenanceDocument'
)
/

-- delete old version document types
DELETE FROM en_doc_typ_t
    WHERE doc_typ_cur_ind = 0
/

-- remove some old rule attributes
DELETE FROM en_rule_attrib_t WHERE 
--rule_attrib_lbl_txt = 'foo'
 --OR
  rule_attrib_nm LIKE 'KualiReporting%'
/

-- delete non-phase 1 rule templates
DELETE
  FROM en_rule_tmpl_attrib_t
 WHERE rule_tmpl_id IN (SELECT a.rule_tmpl_id
                          FROM en_rule_tmpl_t a
                         WHERE rule_tmpl_nm LIKE 'Test%' OR rule_tmpl_nm LIKE 'KualiReporting%')
/  

DELETE
  FROM en_rule_tmpl_attrib_t
  WHERE NOT EXISTS ( SELECT rule_attrib_id FROM en_rule_attrib_t )
/

DELETE
  FROM en_rule_tmpl_optn_t
 WHERE rule_tmpl_id IN (SELECT a.rule_tmpl_id
                          FROM en_rule_tmpl_t a
                         WHERE rule_tmpl_nm LIKE 'Test%' OR rule_tmpl_nm LIKE 'KualiReporting%')
/

DELETE
  FROM en_rule_tmpl_t
  WHERE rule_tmpl_nm LIKE 'Test%' OR rule_tmpl_nm LIKE 'KualiReporting%'
/ 

-- remove document numbers from workgroup table
UPDATE en_wrkgrp_t SET doc_hdr_id = -1 WHERE doc_hdr_id <> -1
/

-- remove old version workgroups 
DELETE FROM en_wrkgrp_t WHERE wrkgrp_cur_ind = 0
/

UPDATE en_appl_cnst_t
    SET appl_cnst_val_txt = '127.0.0.1'
    WHERE appl_cnst_nm = 'Config.Mailer.IPAddress'
/

UPDATE en_appl_cnst_t
    SET appl_cnst_val_txt = 'KualiEnterpriseWorkflow@yourinstitution.edu'
    WHERE appl_cnst_nm = 'Config.Mailer.FromAddress'
/

-- TODO: cleanup unused workgroups


-- based on above deletions, also update en_wrkgrp_ext_t and en_wrkgrp_ext_dta_t
DELETE FROM en_wrkgrp_mbr_t where (WRKGRP_ID, WRKGRP_VER_NBR) not in (select WRKGRP_ID, WRKGRP_VER_NBR from en_wrkgrp_t)
/
DELETE FROM en_wrkgrp_ext_t where (WRKGRP_ID, WRKGRP_VER_NBR) not in (select WRKGRP_ID, WRKGRP_VER_NBR from en_wrkgrp_t)
/
DELETE FROM en_wrkgrp_ext_dta_t where WRKGRP_EXT_ID not in (select WRKGRP_EXT_ID from en_wrkgrp_ext_t)
/
COMMIT
/

/* Re-enable constraints */
DECLARE 
   CURSOR constraint_cursor IS 
      SELECT table_name, constraint_name 
         FROM user_constraints 
         WHERE constraint_type = 'R'
           AND status <> 'ENABLED';
BEGIN 
   FOR r IN constraint_cursor LOOP
      execute immediate 'ALTER TABLE '||r.table_name||' ENABLE CONSTRAINT '||r.constraint_name; 
   END LOOP; 
END;
/
