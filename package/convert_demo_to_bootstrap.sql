/* Disable constraints for the duration of this script */
DECLARE 
   CURSOR constraint_cursor IS 
      SELECT table_name, constraint_name 
         FROM user_constraints 
         WHERE constraint_type = 'R';
BEGIN 
   FOR r IN constraint_cursor LOOP
      execute immediate 'ALTER TABLE '||r.table_name||' DISABLE CONSTRAINT '||r.constraint_name; 
   END LOOP; 
END;
/

/* ** Clear out tables we don't need rows in ** */

DECLARE
  CURSOR tables_to_empty IS
    SELECT table_name
      FROM user_tables
      WHERE table_name NOT IN ( 
	  'CA_ACCOUNT_TYPE_T'
	, 'CA_ACCTG_CTGRY_T'
	, 'AP_ELCTRNC_INV_RJT_TYP_CD_T'
	, 'AP_CRDT_MEMO_STAT_T'
	, 'AP_ELCTRNC_INV_RJT_REAS_T'
	, 'CA_ACCT_SF_T'
	, 'CA_AICPA_FUNC_T'
	, 'CA_BALANCE_TYPE_T'
	, 'CA_BDGT_AGGR_T'
	, 'CA_BDGT_REC_LVL_T'
	, 'CA_FED_FND_T'
	, 'CA_FEDERAL_FUNC_T'
	, 'CA_FUND_GRP_T'
	, 'CA_HIGHR_ED_FUNC_T'
	, 'CA_ICR_TYPE_T'
	, 'CA_MNXFR_ELIM_T'
	, 'CA_OBJ_SUB_TYPE_T'
	, 'CA_OBJ_TYPE_T'
	, 'CA_ORG_RVRSN_CTGRY_T'
	, 'CA_ORG_TYPE_T'
	, 'CA_RC_T'
	, 'CA_RESTRICT_STAT_T'
	, 'CA_SUB_FND_GRP_TYP_T'
	, 'CA_SUB_FUND_GRP_T'
	, 'CA_UBO_FUNC_T'
	, 'CG_AGENCY_TYP_T'
	, 'CG_AWD_STAT_T'
	, 'CG_GRANT_DESC_T'
	, 'CG_LTRCR_FNDGRP_T'
	, 'CG_PRPSL_AWD_TYP_T'
	, 'CG_PRPSL_STAT_T'
	, 'EN_APPL_CNST_T'
	, 'EN_DLGN_RSP_T'
	, 'EN_DOC_RTE_TYP_T'
	, 'EN_DOC_TYP_ATTRIB_T'
	, 'EN_DOC_TYP_PLCY_RELN_T'
	, 'EN_DOC_TYP_PROC_T'
	, 'EN_DOC_TYP_T'
	, 'EN_EDOCLT_ASSOC_T'
	, 'EN_EDOCLT_DEF_T'
	, 'EN_EDOCLT_STYLE_T'
	, 'EN_HLP_T'
	, 'EN_RTE_BRCH_PROTO_T'
	, 'EN_RTE_BRCH_ST_T'
	, 'EN_RTE_BRCH_T'
	, 'EN_RTE_NODE_LNK_T'
	, 'EN_RTE_NODE_T'
	, 'EN_RULE_ATTRIB_KEY_VAL_T'
	, 'EN_RULE_ATTRIB_T'
	, 'EN_RULE_ATTRIB_VLD_VAL_T'
	, 'EN_RULE_BASE_VAL_T'
	, 'EN_RULE_EXT_T'
	, 'EN_RULE_EXT_VAL_T'
	, 'EN_RULE_RSP_T'
	, 'EN_RULE_TMPL_ATTRIB_T'
	, 'EN_RULE_TMPL_OPTN_T'
	, 'EN_RULE_TMPL_T'
	, 'EN_WRKGRP_T'
	, 'EN_WRKGRP_TYP_ATTRIB_T'
	, 'EN_WRKGRP_TYP_T'
	, 'ER_APPT_TYP_T'
	, 'ER_BDGT_BASE_CD_T'
	, 'ER_BDGT_TYP_CD_T'
	, 'ER_CTRL_ATTRIB_TYP_T'
	, 'ER_DUE_DT_TYP_T'
	, 'ER_IDC_LU_T'
	, 'ER_NPRS_CTGRY_CD_T'
	, 'ER_NPRS_SUB_CTGRY_CD_T'
	, 'ER_PROJ_TYP_T'
	, 'ER_PRPS_T'
	, 'ER_PRSN_ROLE_T'
	, 'ER_QSTN_TYP_T'
	, 'ER_RSRCH_RSK_TYP_T'
	, 'ER_RSRCH_TYP_CD_T'
	, 'ER_SUBM_TYP_T'
	, 'FP_CR_CARD_TYP_T'
	, 'FP_DOC_GROUP_T'
	, 'FP_DOC_STATUS_T'
	, 'FP_DOC_TYPE_T'
	, 'FP_DV_DIEM_T'
	, 'FP_DV_EXP_TYP_T'
	, 'FP_DV_MLG_T'
	, 'FP_DV_OWNR_TYP_T'
	, 'FP_DV_PMT_REAS_T'
	, 'FP_DV_TAX_CTRL_T'
	, 'FP_DV_TRVL_CO_NM_T'
	, 'FP_FSCL_YR_CTRL_T'
	, 'FP_FUNC_CTRL_CD_T'
	, 'FP_INC_CLS_T'
	, 'FP_NRA_TAX_PCT_T'
	, 'FS_HOME_ORIGIN_T'
	, 'LD_BCN_AF_RSN_CD_T'
	, 'LD_BCN_DURATION_T'
	, 'LD_BENEFITS_TYPE_T'
	, 'LD_CSF_ACTION_T'
	, 'LD_CSF_COMPANY_T'
	, 'LD_CSF_ERNCD_T'
	, 'LD_POS_OBJ_GRP_T'
	, 'PDP_ACCTG_CHG_CD_T'
	, 'PDP_DISB_TYP_CD_T'
	, 'PDP_PMT_CHG_CD_T'
	, 'PDP_PMT_STAT_CD_T'
	, 'PUR_ADDR_TYP_T'
	, 'PUR_AP_CPTL_AST_TRN_TYP_T'
	, 'PUR_AP_ITM_TYP_T'
	, 'PUR_AP_RECUR_PMT_FREQ_T'
	, 'PUR_AP_RECUR_PMT_TYP_T'
	, 'PUR_AP_UOM_T'
	, 'PUR_CARI_T'
	, 'PUR_CNTCT_TYP_T'
	, 'PUR_DLVY_REQ_DT_REAS_T'
	, 'PUR_FND_SRC_T'
	, 'PUR_OWNR_CTGRY_T'
	, 'PUR_OWNR_TYP_T'
	, 'PUR_PHN_TYP_T'
	, 'PUR_PMT_TERM_TYP_T'
	, 'PUR_PO_CST_SRC_T'
	, 'PUR_PO_CST_SRC_T'
	, 'PUR_PO_STAT_T'
	, 'PUR_PO_TRNS_MTHD_T'
	, 'PUR_PO_VNDR_CHC_T'
	, 'PUR_REQS_STAT_T'
	, 'PUR_SHP_PMT_TERM_T'
	, 'PUR_SHP_SPCL_COND_T'
	, 'PUR_SHP_TTL_T'
	, 'PUR_SUPP_DVRST_T'
	, 'PUR_VNDR_INACTV_REAS_T'
	, 'PUR_VNDR_TYP_T'
	, 'SH_ACCT_PERIOD_T'
	, 'SH_CMP_TYP_T'
	, 'SH_COUNTRY_T'
	, 'SH_EMP_STAT_T'
	, 'SH_EMP_TYP_T'
	, 'SH_LOCK_TYP_DESC_T'
	, 'SH_NTE_TYP_T'
	, 'SH_PARM_DTL_TYP_T'
	, 'SH_PARM_NMSPC_T'
	, 'SH_PARM_T'
	, 'SH_PARM_TYP_T'
	, 'SH_STATE_T'
	, 'SH_UNIV_DATE_T'
	);
BEGIN
  FOR rec IN tables_to_empty LOOP
    dbms_output.put_line( 'Truncated Table: '||rec.table_name );
    EXECUTE IMMEDIATE 'TRUNCATE TABLE '||rec.table_name;
  END LOOP;
END;
/

/* TODO: NEED TO RE-INSERT to user and workgroup member table (KULUSER) */
insert into EN_WRKGRP_MBR_T (WRKGRP_MBR_PRSN_EN_ID,WRKGRP_ID,WRKGRP_MBR_TYP,WRKGRP_VER_NBR,DB_LOCK_VER_NBR)
select '0000000000',WRKGRP_ID,'U',WRKGRP_VER_NBR,DB_LOCK_VER_NBR from EN_WRKGRP_T;

COMMIT
/
/* TODO: add to SH_USR_PROP_T */

/* ** Users & Groups ** */

delete from FS_UNIVERSAL_USR_T where PERSON_USER_ID <> 'KULUSER';
delete from SH_USR_PROP_T where person_unvl_id NOT IN (SELECT person_unvl_id from FS_UNIVERSAL_USR_T);
update SH_USR_PROP_T set PERSON_UNVL_ID = '0000000000';
update SH_USR_PROP_T set USR_PROP_VAL = 'Y' where USR_PROP_NM = 'active';
update fs_universal_usr_t set PERSON_UNVL_ID = '0000000000',emp_stat_cd = 'A',emp_type_cd = 'P';


/* ** Workflow Constants, Document Types & Rules ** */

UPDATE en_appl_cnst_t
    SET appl_cnst_val_txt = 'KULUSER'
    WHERE appl_cnst_nm = 'Config.Application.AdminUserList'
/
    
    


/* ** System Parameters & Rules ** */

update SH_PARM_T
    set SH_PARM_TXT = '0000'
    where SH_PARM_NMSPC_CD = 'KFS-FP'
      and SH_PARM_DTL_TYP_CD = 'DisbursementVoucher'
      and SH_PARM_NM LIKE 'NON\_RESIDENT\_ALIEN\_TAX\_%OBJECT\_CODE%' ESCAPE '\'
/

update SH_PARM_T
    set SH_PARM_TXT = NULL
    where SH_PARM_NMSPC_CD = 'KFS-FP'
      and SH_PARM_DTL_TYP_CD = 'DisbursementVoucher'
      and SH_PARM_NM LIKE 'NON\_RESIDENT\_ALIEN\_TAX\_%ACCOUNT' ESCAPE '\'
/

update SH_PARM_T
    set SH_PARM_TXT = NULL
    where SH_PARM_NMSPC_CD = 'KFS-FP'
      and SH_PARM_DTL_TYP_CD = 'DisbursementVoucher'
      and SH_PARM_NM LIKE 'NON\_RESIDENT\_ALIEN\_TAX\_%CHART' ESCAPE '\'
/

update SH_PARM_T
    set SH_PARM_TXT = '0000'
    where SH_PARM_NMSPC_CD = 'KFS-GL'
      and SH_PARM_DTL_TYP_CD = 'ScrubberStep'
      and SH_PARM_NM LIKE '%OBJECT\_CODE%' ESCAPE '\'
/

update SH_PARM_T
    set SH_PARM_TXT = '0000'
    where SH_PARM_NMSPC_CD = 'KFS-FP'
      and SH_PARM_DTL_TYP_CD = 'BudgetAdjustment'
      and SH_PARM_NM = 'GLPE_INCOME_TRANSFER_OBJECT_CODE'
/

update SH_PARM_T
    set SH_PARM_TXT = '0000'
    where SH_PARM_NMSPC_CD = 'KFS-FP'
      and SH_PARM_DTL_TYP_CD = 'IndirectCostAdjustment'
      and SH_PARM_NM LIKE '%OBJECT\_CODE%' ESCAPE '\'
/

update SH_PARM_T
    set SH_PARM_TXT = NULL
    where SH_PARM_NMSPC_CD = 'KFS-FP'
      and SH_PARM_DTL_TYP_CD = 'ProcurementCardCreateDocumentsStep'
      and SH_PARM_NM LIKE '%ACCOUNT\_NUMBER' ESCAPE '\'
/

update SH_PARM_T
    set SH_PARM_TXT = NULL
    where SH_PARM_NMSPC_CD = 'KFS-FP'
      and SH_PARM_DTL_TYP_CD = 'ProcurementCardCreateDocumentsStep'
      and SH_PARM_NM LIKE '%CHART\_CODE' ESCAPE '\'
/

update SH_PARM_T
    set SH_PARM_TXT = '0000'
    where SH_PARM_NMSPC_CD = 'KFS-FP'
      and SH_PARM_DTL_TYP_CD = 'ProcurementCardLoadStep'
      and SH_PARM_NM LIKE '%OBJECT\_CODE' ESCAPE '\'
/

update SH_PARM_T
    set SH_PARM_TXT = '0000'
    where SH_PARM_NMSPC_CD = 'KFS-GL'
      and SH_PARM_DTL_TYP_CD = 'Batch'
      and SH_PARM_NM LIKE '%OBJECT_CODE'
/

update SH_PARM_T
    set SH_PARM_TXT = 'ZZ'
    where SH_PARM_NMSPC_CD = 'KFS-FP'
      and SH_PARM_TYP_CD = 'CONFG'
      and SH_PARM_NM LIKE '%DOCUMENTATION%'
/

COMMIT
/

-- fix up business rules

delete from SH_PARM_T
    where SH_PARM_NMSPC_CD = 'KFS-FP'
      and SH_PARM_DTL_TYP_CD = 'DisbursementVoucher'
      and SH_PARM_NM = 'INVALID_DOCUMENTATION_LOCATIONS_BY_CAMPUS'
/

-- remove global DV object level restrictions
update SH_PARM_T
    set SH_PARM_TXT = NULL
    where SH_PARM_NMSPC_CD = 'KFS-FP'
      and SH_PARM_DTL_TYP_CD = 'DisbursementVoucher'
      and SH_PARM_NM = 'OBJECT_LEVELS'
/

-- remove restrictions on DV object codes and levels based on payment reason
update SH_PARM_T
    set SH_PARM_TXT = NULL
    where SH_PARM_NMSPC_CD = 'KFS-FP'
      and SH_PARM_DTL_TYP_CD = 'DisbursementVoucher'
      and SH_PARM_NM IN ('VALID_OBJECT_CODES_BY_PAYMENT_REASON', 'INVALID_OBJECT_LEVELS_BY_PAYMENT_REASON', 'VALID_OBJECT_LEVELS_BY_PAYMENT_REASON')
/

-- remove IU chart values from GL rules
update SH_PARM_T
    set SH_PARM_TXT = NULL
    where SH_PARM_NMSPC_CD = 'KFS-GL'
      and SH_PARM_DTL_TYP_CD = 'ScrubberStep'
      and SH_PARM_NM = 'CAPITALIZATION_CHARTS'
/

-- remove IU object level from rule
delete from SH_PARM_T
    where SH_PARM_NMSPC_CD = 'KFS-FP'
      and SH_PARM_DTL_TYP_CD = 'AuxiliaryVoucher'
      and SH_PARM_NM = 'COMBINATION_OBJECT_TYPE_OBJECT_SUB_TYPE_OBJECT_LEVEL'
/

-- remove IU object level from rule
update SH_PARM_T
    set SH_PARM_TXT = NULL
    where SH_PARM_NMSPC_CD = 'KFS-FP'
      and SH_PARM_NM = 'OBJECT_CODES'
/

-- remove IU object consolidation from rule
update SH_PARM_T
    set SH_PARM_TXT = NULL
    where SH_PARM_NMSPC_CD = 'KFS-FP'
      and SH_PARM_NM = 'OBJECT_CONSOLIDATIONS'
/

update SH_PARM_T
    set SH_PARM_TXT = NULL
    where SH_PARM_NMSPC_CD = 'KFS-FP'
      and SH_PARM_NM = 'OBJECT_LEVELS'
/

update SH_PARM_T
    set SH_PARM_TXT = NULL
    where SH_PARM_NMSPC_CD = 'KFS-CA'
      and SH_PARM_TYP_CD = 'CONFG'
      and SH_PARM_DTL_TYP_CD = 'OrganizationReversionCategory'
/


update SH_PARM_T
    set SH_PARM_TXT = NULL
    where SH_PARM_NMSPC_CD = 'KFS-FP'
      and SH_PARM_DTL_TYP_CD = 'ProcurementCard'
      and SH_PARM_NM = 'OBJECT_CONSOLIDATIONS'
/

update SH_PARM_T
    set SH_PARM_TXT = '0000'
    where SH_PARM_NMSPC_CD = 'KFS-FP'
      and SH_PARM_DTL_TYP_CD = 'ProcurementCard'
      and SH_PARM_NM LIKE '%OBJECT_CODES%'
/

COMMIT
/


/* ** Reference Data ** */

/* One origin code */
truncate table fs_origin_code_t;
insert into FS_ORIGIN_CODE_T values ('01',sys_guid(),1,0,'KULSTG','KUL','KUL',0,0,'0','0',0,0,0,0);

/* Fix home origin table */
update fs_home_origin_t set fs_home_origin_cd = '01';

/* Fix the sh_campus_t table */
truncate table sh_campus_t;
insert into sh_campus_t values ('ZZ',sys_guid(),1,UPPER('Default Campus'),UPPER('Campus'),'F');

/* One motd */
truncate table fp_motd_t;
insert into fp_motd_t values ('01',sys_guid(),1,'Using Base Kuali Dataset');


/* ** Charts & Organizations ** */

/* Since sh_campus_t & ca_rc_t are truncated we need to satisfy constraints accordingly */
update ca_org_t
set ORG_PHYS_CMP_CD = 'ZZ'
/
update ca_org_t
set rc_cd = 'NO'
/

DELETE FROM ca_rc_t
  WHERE rc_cd <> 'NO'
/

DELETE FROM ca_org_type_t
   WHERE org_typ_cd NOT IN ( 'C', 'N', 'R', 'U' )
/


/* ** Chart of Accounts ** */

DELETE FROM ca_obj_sub_type_t
WHERE fin_obj_sub_typ_cd NOT IN (
  'AR'
, 'BU'
, 'CA'
, 'CM'
, 'FB'
, 'FR'
, 'GI'
, 'HW'
, 'IN'
, 'IV'
, 'MT'
, 'NA'
, 'SA'
, 'SF'
, 'SS'
, 'TN'
, 'TR'
)
/

DELETE FROM ca_sub_fund_grp_t
WHERE sub_fund_grp_cd NOT IN (
  'GENFND'
, 'DCEDU'
, 'DPSA'
, 'DOFDS'
, 'DSCFE'
, 'RESSCH'
, 'RESFEL'
, 'ROFDS'
, 'HIEDUA'
, 'FEDERA'
, 'COMMEA'
, 'STATEA'
, 'FOUNDA'
, 'NONPRA'
, 'OTGOVA'
, 'AUXENT'
, 'CLEAR'
, 'LOANFD'
, 'ENDOW'
, 'PFCMR'
, 'PFRI'
, 'PFRR'
, 'PFIP'
, 'EXTAGY'
, 'OTHFDS'
)
/

/* TODO 1f) */
-- delete some IU specific and testing SFGs
DELETE FROM ca_sub_fund_grp_t 
    WHERE sub_fund_grp_cd IN ( 'STUFF', 'SDCI', 'MWISH', 'MCLAR' )
      OR sub_fund_grp_cd LIKE 'RH%'
/

DELETE FROM ca_account_type_t
    WHERE acct_typ_cd NOT IN ( 'AI', 'BS', 'EQ', 'NA', 'RA', 'WS' )
/

DELETE FROM fp_doc_group_t
  WHERE fdoc_grp_cd IN ( 'AR', 'AN', 'CM', 'CR', 'MO', 'SF' )
/

-- clean out non phase-1 documents    
DELETE FROM fp_doc_type_t
  WHERE fdoc_grp_cd IN ( 'AR', 'AN', 'CM', 'CR', 'MO', 'SF' )
     OR FDOC_TYP_ACTIVE_CD = 'N'
/

/* offset definition */
delete from gl_offset_defn_t where univ_fiscal_yr <> '2008';
delete from gl_offset_defn_t where fin_coa_cd <> 'BL';
-- using ZZ as a template when creating charts via a script
update gl_offset_defn_t set fin_coa_cd = 'ZZ',fin_object_cd = '0000';

update CA_ACCOUNT_T set ACCT_PHYS_CMP_CD = 'ZZ'
/

update CA_ACCOUNT_T set SUB_FUND_GRP_CD = 'ZZ'
/




/* ** Disbursement Voucher Data ** */

-- create new DV document location    
INSERT INTO fp_dv_doc_loc_t
(DV_DOC_LOC_CD,OBJ_ID,VER_NBR,DV_DOC_LOC_NM,DV_DOC_LOC_ADDR)
VALUES
('ZZ',sys_guid(),1,'Kuali Default DV Doc Loc',NULL)
/

UPDATE fp_dv_pmt_reas_t 
    SET dv_pmt_reas_desc = SUBSTR( dv_pmt_reas_desc, 1, INSTR( dv_pmt_reas_desc, '.  Some common' ) )
    WHERE dv_pmt_reas_desc LIKE '%.  Some common%'
/    
UPDATE fp_dv_pmt_reas_t 
    SET dv_pmt_reas_desc = SUBSTR( dv_pmt_reas_desc, 1, INSTR( dv_pmt_reas_desc, '. Some common' ) )
    WHERE dv_pmt_reas_desc LIKE '%. Some common%'
/    
UPDATE fp_dv_pmt_reas_t 
    SET dv_pmt_reas_desc = SUBSTR( dv_pmt_reas_desc, 1, INSTR( dv_pmt_reas_desc, '.  Object codes' ) )
    WHERE dv_pmt_reas_desc LIKE '%.  Object codes%'
/    
UPDATE fp_dv_pmt_reas_t 
    SET dv_pmt_reas_desc = SUBSTR( dv_pmt_reas_desc, 1, INSTR( dv_pmt_reas_desc, '.  The object code' ) )
    WHERE dv_pmt_reas_desc LIKE '%.  The object code%'
/    
UPDATE fp_dv_pmt_reas_t 
    SET dv_pmt_reas_desc = SUBSTR( dv_pmt_reas_desc, 1, INSTR( dv_pmt_reas_desc, '.  Common object' ) )
    WHERE dv_pmt_reas_desc LIKE '%.  Common object%'
/

/* TODO 1j) */
DELETE FROM fp_dv_trvl_co_nm_t
WHERE ( dv_exp_cd, dv_exp_co_nm ) NOT IN (
 ( 'A', 'CONTINENTAL/CONTINENTAL EXPRESS' )
, ( 'A', 'DELTA' )
, ( 'A', 'NORTHWEST' )
, ( 'A', 'OTHER' )
, ( 'A', 'SOUTHWEST' )
, ( 'A', 'TRANS WORLD AIRLINES' )
, ( 'A', 'U.S. AIRWAYS' )
, ( 'A', 'UNITED AIRLINES' )
, ( 'L', 'BEST WESTERN' )
, ( 'L', 'CENTURY SUITES' )
, ( 'L', 'COMFORT AND CLARION INNS' )
, ( 'L', 'COURTYARD' )
, ( 'L', 'DAYS INN' )
, ( 'L', 'FAIRFIELD INN' )
, ( 'L', 'HAMPTON INN' )
, ( 'L', 'HOLIDAY INNS' )
, ( 'L', 'OTHER' )
, ( 'L', 'RAMADA INNS' )
, ( 'L', 'TRAVELODGE' )
, ( 'M', 'OTHER' )
, ( 'M', 'RYDER' )
, ( 'M', 'U-HAUL' )
, ( 'O', 'OTHER' )
, ( 'PA', 'CONTINENTAL/CONTINENTAL EXPRESS' )
, ( 'PA', 'DELTA' )
, ( 'PA', 'NORTHWEST' )
, ( 'PA', 'OTHER' )
, ( 'PA', 'SOUTHWEST' )
, ( 'PA', 'TRANS WORLD AIRLINES' )
, ( 'PA', 'U.S. AIRWAYS' )
, ( 'PA', 'UNITED AIRLINES' )
, ( 'PC', 'OTHER' )
, ( 'PL', 'BEST WESTERN' )
, ( 'PL', 'CENTURY SUITES' )
, ( 'PL', 'COMFORT AND CLARION INNS' )
, ( 'PL', 'COURTYARD' )
, ( 'PL', 'DAYS INN' )
, ( 'PL', 'FAIRFIELD INN' )
, ( 'PL', 'HAMPTON INN' )
, ( 'PL', 'HOLIDAY INNS' )
, ( 'PL', 'OTHER' )
, ( 'PL', 'RAMADA INNS' )
, ( 'PL', 'TRAVELODGE' )
, ( 'PM', 'OTHER' )
, ( 'PM', 'RYDER' )
, ( 'PM', 'U-HAUL' )
, ( 'PO', 'PREPAID OTHER' )
, ( 'PR', 'ALAMO' )
, ( 'PR', 'AVIS' )
, ( 'PR', 'BUDGET' )
, ( 'PR', 'HERTZ' )
, ( 'PR', 'NATIONAL' )
, ( 'PR', 'OTHER' )
, ( 'PT', 'OTHER' )
, ( 'PT', 'YELLOW CAB CO.' )
, ( 'R', 'ALAMO' )
, ( 'R', 'AVIS' )
, ( 'R', 'BUDGET' )
, ( 'R', 'HERTZ' )
, ( 'R', 'NATIONAL' )
, ( 'R', 'OTHER' )
, ( 'T', 'OTHER' )
, ( 'T', 'YELLOW CAB CO.' )
)
/

/* ** Financial Document Data ** */
/* No kuldba to bootstrap changes */


/* ** Labor Distribution ** */
/* No kuldba to bootstrap changes */


/* ** Pre-Disbursement Processor ** */
/* No kuldba to bootstrap changes */


/* ** Vendor ** */
DELETE FROM pur_contr_mgr_t WHERE contr_mgr_cd NOT IN ( 99 )
/

/* ** Purchasing/Accounts Payable ** */
/* No kuldba to bootstrap changes */


/* ** Contracts & Grants ** */
/* No kuldba to bootstrap changes */


/* ** Research Administration ** */
/* No kuldba to bootstrap changes */


/* Re-enable constraints */
DECLARE 
   CURSOR constraint_cursor IS 
      SELECT table_name, constraint_name 
         FROM user_constraints 
         WHERE constraint_type = 'R';
BEGIN 
   FOR r IN constraint_cursor LOOP
      execute immediate 'ALTER TABLE '||r.table_name||' ENABLE CONSTRAINT '||r.constraint_name; 
   END LOOP; 
END;
/
