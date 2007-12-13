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

/*  Clear out tables we don't need rows in  */

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
    , 'FS_OPTION_T'
	, 'FP_FUNC_CTRL_CD_T'
	, 'FP_INC_CLS_T'
	, 'FP_NRA_TAX_PCT_T'
    , 'GL_ORIGIN_ENTRY_SRC_T'
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
	, 'PUR_CONTR_MGR_T' 
    , 'QRTZ_LOCKS'
	);
BEGIN
  FOR rec IN tables_to_empty LOOP
    dbms_output.put_line( 'Truncated Table: '||rec.table_name );
    EXECUTE IMMEDIATE 'TRUNCATE TABLE '||rec.table_name;
  END LOOP;
END;
/

/*  Users & Groups  */

/* RE-INSERT to user and workgroup member table (KULUSER) */
INSERT INTO fs_universal_usr_t
("PERSON_UNVL_ID",obj_id,"PERSON_USER_ID","PERSON_NM","PRSN_1ST_NM","PRSN_LST_NM","EMP_STAT_CD","EMP_TYPE_CD","PRSN_TAX_ID_TYP_CD","PRSN_STAFF_IND","PRSN_FAC_IND","PRSN_STU_IND","PRSN_AFLT_IND")
VALUES
('KULUSER',SYS_GUID(),'KULUSER','Kuali System User','System User','Kuali','A','P','S','Y','N','N','N')
/
/* TODO: add to SH_USR_PROP_T */
INSERT INTO sh_usr_prop_t
("PERSON_UNVL_ID","APPL_MOD_ID","USR_PROP_NM","OBJ_ID","VER_NBR","USR_PROP_VAL")
VALUES
('KULUSER','chart','active',SYS_GUID(),1,'Y')
/

insert into EN_WRKGRP_MBR_T (WRKGRP_MBR_PRSN_EN_ID,WRKGRP_ID,WRKGRP_MBR_TYP,WRKGRP_VER_NBR,DB_LOCK_VER_NBR)
( select 'KULUSER',WRKGRP_ID,'U',WRKGRP_VER_NBR,DB_LOCK_VER_NBR from EN_WRKGRP_T )
/

COMMIT
/


/* Workflow Constants, Document Types & Rules */

UPDATE en_appl_cnst_t
    SET appl_cnst_val_txt = 'KULUSER'
    WHERE appl_cnst_nm = 'Config.Application.AdminUserList'
/
  
-- remove rules with IU data
DELETE 
    FROM KULBOOTSTRAP.EN_RULE_BASE_VAL_T
    WHERE rule_tmpl_id IN (
        SELECT RULE_TMPL_ID
            FROM KULBOOTSTRAP.EN_RULE_TMPL_T
            WHERE RULE_TMPL_ID IN (
                SELECT RULE_TMPL_ID
                    FROM KULBOOTSTRAP.EN_RULE_TMPL_ATTRIB_T
                    WHERE RULE_ATTRIB_ID IN ( 
                        SELECT rule_attrib_id 
                            FROM KULBOOTSTRAP.EN_RULE_ATTRIB_T
                            WHERE RULE_ATTRIB_NM LIKE '%Campus%'
                               OR RULE_ATTRIB_NM LIKE '%Content%'
                               OR RULE_ATTRIB_NM LIKE '%HigherEd%'
                               OR RULE_ATTRIB_NM LIKE '%Chart%'
                               OR RULE_ATTRIB_NM LIKE '%FundGroup%'
                               OR RULE_ATTRIB_NM LIKE '%ObjectCode%'
                               OR RULE_ATTRIB_NM LIKE '%ProjectCode%'
                               OR RULE_ATTRIB_NM LIKE '%SubAccount%'
                               OR RULE_ATTRIB_NM LIKE '%OrgReview%' 
                               OR RULE_ATTRIB_NM = 'KualiPurchaseOrderRemoveHoldNotification'
                               OR RULE_ATTRIB_NM = 'KualiPurchaseOrderContractAndGrantsAttribute'
                               OR RULE_ATTRIB_NM = 'KualiPurchaseOrderBudgetAttribute'
                    )
            )
    )
    OR RULE_TMPL_ID NOT IN ( SELECT RULE_TMPL_ID FROM EN_RULE_TMPL_T )
/
DELETE FROM EN_RULE_EXT_T 
    WHERE RULE_BASE_VAL_ID NOT IN ( SELECT RULE_BASE_VAL_ID FROM EN_RULE_BASE_VAL_T )
/
DELETE FROM EN_RULE_EXT_VAL_T 
    WHERE RULE_EXT_ID NOT IN ( SELECT RULE_EXT_ID FROM EN_RULE_EXT_T )
/

DELETE
    FROM EN_WRKGRP_T
    WHERE (
        WRKGRP_NM LIKE 'KUALI\_DV\_%' ESCAPE '\'
        OR WRKGRP_NM LIKE '%-Content' ESCAPE '\'
        OR WRKGRP_NM LIKE 'KUALI\_BRSR%' ESCAPE '\'
        OR WRKGRP_NM LIKE 'KUALI\_CONTENT%' ESCAPE '\'
   )
   AND WRKGRP_NM NOT IN ( 'KUALI_DV_TRAV', 'KUALI_DV_FRN', 'KUALI_DV_WIRE', 'KUALI_DV_PYAUDIT', 'KUALI_DV_TAXGRP' )
/

DELETE FROM EN_WRKGRP_EXT_T
    WHERE ( WRKGRP_ID, WRKGRP_VER_NBR ) NOT IN ( SELECT WRKGRP_ID, WRKGRP_VER_NBR FROM EN_WRKGRP_T )
/
DELETE FROM EN_WRKGRP_MBR_T
    WHERE ( WRKGRP_ID, WRKGRP_VER_NBR ) NOT IN ( SELECT WRKGRP_ID, WRKGRP_VER_NBR FROM EN_WRKGRP_T )
/
DELETE FROM EN_WRKGRP_EXT_DTA_T
    WHERE WRKGRP_EXT_ID NOT IN ( SELECT WRKGRP_EXT_ID FROM EN_WRKGRP_EXT_T )
/
COMMIT
/

/*  System Parameters & Rules  */
-- blank out rules which contain values from other emptied tables

--SELECT a.sh_parm_nmspc_cd, a.sh_parm_dtl_typ_cd, a.sh_parm_nm, a.sh_parm_typ_cd, a.sh_parm_txt
--  FROM sh_parm_t a
  UPDATE sh_parm_t 
	SET sh_parm_txt = NULL
  WHERE sh_parm_typ_cd <> 'HELP'
    AND sh_parm_txt IS NOT NULL
    AND ( 
		sh_parm_nm LIKE '%CHARTS'
	 OR sh_parm_nm LIKE '%CHART'
	 OR sh_parm_nm LIKE '%CHART_CODE'
	 OR sh_parm_nm LIKE '%OBJECT_CODE%'
	 OR sh_parm_nm LIKE '%OBJECT_LEVEL%'
	 OR sh_parm_nm LIKE '%OBJECT_CONS%'
	 OR sh_parm_nm LIKE '%CAMPUS%'
	 OR sh_parm_nm LIKE '%ORIGINATIONS%'
	 OR sh_parm_nm LIKE '%ACCOUNT%'
	 OR sh_parm_nm LIKE '%BANK_ACCOUNT%'
	 OR sh_parm_nm LIKE '%ORGANIZATION'
	 OR sh_parm_nm LIKE '%USER'
   )
   AND sh_parm_nm NOT LIKE '%GROUP'
   AND sh_parm_nm <> 'SUB_ACCOUNT_TYPES'
   AND sh_parm_nm NOT LIKE '%OBJECT_SUB_TYPES'
   AND sh_parm_nm NOT LIKE 'MAX_FILE_SIZE%'
/
COMMIT
/



/*  Reference Data  */

/* One origin code */
insert into FS_ORIGIN_CODE_T values ('01',sys_guid(),1,0,'KULSTG','KUL','KUL',0,0,'0','0',0,0,0,0)
/
/* Fix home origin table */
update fs_home_origin_t set fs_home_origin_cd = '01'
/
/* Fix the sh_campus_t table */
insert into sh_campus_t values ('01',sys_guid(),1,'Default Campus','Campus','F')
/
/*  Charts & Organizations  */

DELETE FROM ca_org_type_t
   WHERE org_typ_cd NOT IN ( 'C', 'N', 'R', 'U' )
/

DELETE FROM ca_rc_t WHERE rc_cd <> 'NO'
/
COMMIT
/

/ clean up the options table /

UPDATE fs_option_t
	SET univ_fin_coa_cd = NULL
/

/*  Chart of Accounts  */

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

DELETE FROM ca_account_type_t
    WHERE acct_typ_cd NOT IN ( 'AI', 'BS', 'EQ', 'NA', 'RA', 'WS' )
/

/*  Disbursement Voucher Data  */

-- create new DV document location    
INSERT INTO fp_dv_doc_loc_t
(DV_DOC_LOC_CD,OBJ_ID,VER_NBR,DV_DOC_LOC_NM,DV_DOC_LOC_ADDR)
VALUES
('01',sys_guid(),1,'Kuali Default DV Doc Loc',NULL)
/

-- clear out references to exact object codes
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

/*  Financial Document Data  */
/* No demo to bootstrap changes */


/*  Labor Distribution  */
/* No demo to bootstrap changes */


/*  Pre-Disbursement Processor  */
/* No demo to bootstrap changes */


/*  Vendor  */
/* No demo to bootstrap changes */

/*  Purchasing/Accounts Payable  */
DELETE FROM pur_contr_mgr_t 
	WHERE contr_mgr_cd NOT IN ( 99 )
/
UPDATE pur_contr_mgr_t 
	SET contr_mgr_usr_id = 'KULUSER'
      , contr_mgr_phn_nbr = '(000) 555-1212'
      , contr_mgr_fax_nbr = '(000) 555-1212'
/
COMMIT
/


/*  Contracts & Grants  */
/* No demo to bootstrap changes */


/*  Research Administration  */
/* No demo to bootstrap changes */


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
