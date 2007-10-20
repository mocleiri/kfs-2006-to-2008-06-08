/* Script to clear out tables we don't need rows in */

truncate table gl_balance_t;
truncate table gl_entry_t;
truncate table gl_acct_balances_t;
truncate table gl_encumbrance_t;
truncate table ca_a21_sub_acct_t;
truncate table ca_prior_yr_acct_t;
truncate table ca_project_t;
truncate table ca_sub_acct_t;
truncate table gl_id_bill_t;
truncate table gl_pending_entry_t;
truncate table fp_interim1_level_mt;
truncate table fp_interim1_obj_mt;
truncate table fp_doc_header_t;
truncate table gl_sf_balances_t;
truncate table gl_sf_rebuild_t;
truncate table ca_obj_cd_chg_doc_t;
truncate table fp_sb_exp_desc_t;
truncate table ca_account_t;
truncate table ca_acct_chg_dtl_t;
truncate table ca_acct_delegate_t;
truncate table ca_acct_desc_t;
truncate table ca_acct_gdlnprps_t;
truncate table ca_dlgt_chg_doc_t;
truncate table ca_icr_auto_entr_t;
truncate table ca_obj_cd_chg_dtl_t;
truncate table ca_org_extns_t;
truncate table ca_org_reversion_t;
truncate table ca_org_rtng_mdl_nm_t;
truncate table ca_org_rtng_mdl_t;
truncate table ca_org_rvrsn_dtl_t;
truncate table ca_org_t;
truncate table ca_sub_object_cd_t;
truncate table fp_bdgt_adj_doc_t;
truncate table fp_cashier_doc_t;
truncate table fp_coin_dtl_t;
truncate table fp_currency_dtl_t;
truncate table fp_deposit_hdr_t;
truncate table fp_dv_doc_t;
truncate table fp_dv_nonem_trvl_t;
truncate table fp_dv_nra_tax_t;
truncate table fp_dv_payee_dtl_t;
truncate table fp_dv_payee_t;
truncate table fp_fnd_trnfr_doc_t;
truncate table fp_ofst_acct_t;
truncate table fp_pre_encum_doc_t;
truncate table fp_sales_tax_t;
truncate table fp_sb_inc_desc_t;
truncate table fp_cash_rcpt_hdr_t;
truncate table ld_lbr_obj_bene_t;
truncate table fp_maintenance_document_t;
truncate table ca_icr_excl_type_t;
truncate table ca_icr_excl_acct_t;
--truncate table ca_rc_t;
truncate table fp_acct_lines_t;
truncate table fp_bank_account_t;
truncate table fp_bank_t;
truncate table fp_cr_card_vndr_t;
truncate table fp_csh_drwr_t;
truncate table fp_dv_diem_t;
truncate table fp_dv_doc_loc_t;
truncate table fp_dv_mlg_t;
truncate table fp_rpt_cd_t;
truncate table fp_sb_ctrl_t;
truncate table ld_benefits_calc_t;
truncate table en_doc_hdr_t;
truncate table ca_chart_t;
truncate table ca_obj_consoldtn_t;
truncate table ca_obj_level_t;
truncate table ca_object_code_t;

/* One origin code */
truncate table fs_origin_code_t;
insert into FS_ORIGIN_CODE_T values ('01',sys_guid(),1,0,'KULSTG','KUL','KUL',0,0,0,'0','0',0,0,0,0);

/* One motd */
truncate table fp_motd_t;
insert into fp_motd_t values ('01',sys_guid(),1,'Using Base Kuali Dataset');

/* Options table - just 2007 */
delete from fs_option_t where univ_fiscal_yr <> '2007';
-- remove the reference to the IU chart - add invalid default chart
UPDATE fs_option_t SET univ_fin_coa_cd = 'ZZ';
DECLARE
    StartYear NUMBER := 2008;
    EndYear   NUMBER := 2015;
BEGIN
    FOR year IN StartYear..EndYear LOOP
    INSERT INTO fs_option_t ( univ_fiscal_yr, act_fin_bal_typ_cd,
           bdgt_chk_baltyp_cd, bdgt_chk_optn_cd, univ_fscyr_strt_yr,
           univ_fscyr_strt_mo, fobjtp_inc_csh_cd, fobjtp_xpnd_exp_cd,
           fobjtp_xpndnexp_cd, fobjtp_expnxpnd_cd, fobj_typ_asset_cd,
           fobj_typ_lblty_cd, fobj_typ_fndbal_cd, ext_enc_fbaltyp_cd,
           int_enc_fbaltyp_cd, pre_enc_fbaltyp_cd, elim_finbal_typ_cd,
           fobjtp_inc_ncsh_cd, fobjtp_csh_ninc_cd, univ_fin_coa_cd,
           univ_fiscal_yr_nm, fin_begbalload_ind,
           cstshr_encum_fin_bal_typ_cd, base_bdgt_fin_bal_typ_cd,
           mo_bdgt_fin_bal_typ_cd, fin_object_typ_trnfr_inc_cd,
           fin_object_typ_trnfr_exp_cd, nmnl_fin_bal_typ_cd )
    ( SELECT year, act_fin_bal_typ_cd,
           bdgt_chk_baltyp_cd, bdgt_chk_optn_cd, (year - 1),
           univ_fscyr_strt_mo, fobjtp_inc_csh_cd, fobjtp_xpnd_exp_cd,
           fobjtp_xpndnexp_cd, fobjtp_expnxpnd_cd, fobj_typ_asset_cd,
           fobj_typ_lblty_cd, fobj_typ_fndbal_cd, ext_enc_fbaltyp_cd,
           int_enc_fbaltyp_cd, pre_enc_fbaltyp_cd, elim_finbal_typ_cd,
           fobjtp_inc_ncsh_cd, fobjtp_csh_ninc_cd, univ_fin_coa_cd,
           (year - 1)||'-'||year, fin_begbalload_ind,
           cstshr_encum_fin_bal_typ_cd, base_bdgt_fin_bal_typ_cd,
           mo_bdgt_fin_bal_typ_cd, fin_object_typ_trnfr_inc_cd,
           fin_object_typ_trnfr_exp_cd, nmnl_fin_bal_typ_cd
      FROM fs_option_t
      WHERE univ_fiscal_yr = 2007
    );
    END LOOP;    
END;
/


/* Fix home origin table */
update fs_home_origin_t set fs_home_origin_cd = '01';

/* Fix the sh_campus_t table */
truncate table sh_campus_t;
insert into sh_campus_t values ('ZZ',sys_guid(),1,UPPER('Default Campus'),UPPER('Campus'),'F');

/* user stuff */
delete from FS_UNIVERSAL_USR_T where PERSON_USER_ID <> 'KULUSER';
delete from FP_FIS_USER_T where person_unvl_id NOT IN (SELECT person_unvl_id from FS_UNIVERSAL_USR_T);
update fp_fis_user_t set PERSON_UNVL_ID = '0000000000',PERSON_ACTIVE_CD = 'Y',prsn_in_fp_cd = 'Y',fin_coa_cd=NULL,org_cd=NULL;
update fs_universal_usr_t set PERSON_UNVL_ID = '0000000000',emp_stat_cd = 'A',emp_type_cd = 'P';

/* offset definition */
delete from gl_offset_defn_t where univ_fiscal_yr <> '2007';
delete from gl_offset_defn_t where fin_coa_cd <> 'BL';
-- using ZZ as a template when creating charts via a script
update gl_offset_defn_t set fin_coa_cd = 'ZZ',fin_object_cd = '0000';

/* ensure that the balance types names are in upper case */
UPDATE ca_balance_type_t
  SET fin_balance_typ_nm = UPPER( fin_balance_typ_nm )
    , fin_baltyp_shrt_nm = UPPER( fin_baltyp_shrt_nm )
/

/* fscl yr ctrl */
delete from fp_fscl_yr_ctrl_t where univ_fiscal_yr <> '2007';
DECLARE
    StartYear NUMBER := 2008;
    EndYear   NUMBER := 2015;
BEGIN
    FOR year IN StartYear..EndYear LOOP
        insert into fp_fscl_yr_ctrl_t ( univ_fiscal_yr, fs_func_ctrl_cd, fs_func_active_ind )
        (SELECT year, fs_func_ctrl_cd, fs_func_active_ind
          FROM fp_fscl_yr_ctrl_t
          where univ_fiscal_yr = 2007);
    END LOOP;
END;
/

/* Put kuluser in all workgroups */
truncate table en_wrkgrp_mbr_t;

insert into EN_WRKGRP_MBR_T (WRKGRP_MBR_PRSN_EN_ID,WRKGRP_ID,WRKGRP_VER_NBR,DB_LOCK_VER_NBR)
select '0000000000',WRKGRP_ID,WRKGRP_VER_NBR,DB_LOCK_VER_NBR from EN_WRKGRP_T;

COMMIT
/

-- rename the "STATE OF INDIANA" sub fund groups            

UPDATE ca_sub_fund_grp_t 
SET sub_fund_grp_desc = 'STATE FUNDS'
WHERE sub_fund_grp_desc = 'STATE OF INDIANA'
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

-- delete some IU specific and testing SFGs
DELETE FROM ca_sub_fund_grp_t 
    WHERE sub_fund_grp_cd IN ( 'STUFF', 'SDCI', 'MWISH', 'MCLAR' )
      OR sub_fund_grp_cd LIKE 'RH%'
/
    
    
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
    
UPDATE ca_obj_sub_type_t
    SET fin_obj_sub_typ_nm = 'TRANSFERS - NONMANDATORY'
    WHERE fin_obj_sub_typ_cd = 'TN'
/
 
DELETE FROM ca_org_type_t
   WHERE org_typ_cd NOT IN ( 'C', 'N', 'R', 'U' )
/

DELETE FROM ca_rc_t
  WHERE rc_cd <> 'NO'
/
    
UPDATE ca_aicpa_func_t
    SET fin_aicpa_func_nm = UPPER( fin_aicpa_func_nm )
/    

UPDATE ca_federal_func_t
    SET FIN_FED_FUNC_NM = UPPER( FIN_FED_FUNC_NM )
/    

UPDATE ca_ubo_func_t
    SET FIN_UBO_FUNC_NM = UPPER( FIN_UBO_FUNC_NM )
/    
UPDATE ca_highr_ed_func_t
    SET FIN_HGH_ED_FUNC_NM = UPPER( FIN_HGH_ED_FUNC_NM )
/    

-- clean out non phase-1 documents    
DELETE FROM fp_doc_type_t
  WHERE fdoc_grp_cd IN ( 'AR', 'AN', 'CG', 'CH', 'CM', 'CR', 'LD', 'LR', 'MO', 'SF' )
     OR FDOC_TYP_ACTIVE_CD = 'N'
/

DELETE FROM fp_doc_group_t
  WHERE fdoc_grp_cd IN ( 'AR', 'AN', 'CG', 'CH', 'CM', 'CR', 'LD', 'LR', 'MO', 'SF' )
/

-- create new DV document location    
INSERT INTO fp_dv_doc_loc_t
(DV_DOC_LOC_CD,OBJ_ID,VER_NBR,DV_DOC_LOC_NM,DV_DOC_LOC_ADDR)
VALUES
('ZZ',sys_guid(),1,'Kuali Default DV Doc Loc',NULL)
/

-- Tax Control Name referenced "IU"       
UPDATE fp_dv_tax_ctrl_t
   SET dv_tax_ctrl_nm = 'Approved for Employee'
   WHERE dv_payee_txctrl_cd = 'A'
/       

-- remove references to "IU" and specific object codes
UPDATE fp_dv_pmt_reas_t 
    SET dv_pmt_reas_desc = REPLACE( dv_pmt_reas_desc, ' IU ', ' University ' )
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

DELETE FROM FP_DV_WIRE_CHRG_T
  WHERE univ_fiscal_yr <> '2007'
/
UPDATE FP_DV_WIRE_CHRG_T
	SET fin_coa_cd = 'ZZ'
	  , account_nbr = 'ZZZZZZZ'
	  , exp_fin_obj_cd = '0000'
	  , inc_fin_obj_cd = '0000'
/	  
DECLARE
    StartYear NUMBER := 2008;
    EndYear   NUMBER := 2015;
BEGIN
    FOR year IN StartYear..EndYear LOOP
        insert into FP_DV_WIRE_CHRG_T ( univ_fiscal_yr, fin_coa_cd,
       account_nbr, inc_fin_obj_cd, exp_fin_obj_cd,
       dv_domstc_chg_amt, dv_frgn_chrg_amt )
        (
			SELECT year, fin_coa_cd,
			       account_nbr, inc_fin_obj_cd, exp_fin_obj_cd,
			       dv_domstc_chg_amt, dv_frgn_chrg_amt
			  FROM fp_dv_wire_chrg_t   
              WHERE univ_fiscal_yr = 2007     
		);
    END LOOP;
END;
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

-- fix up business rules

DELETE FROM fs_bsns_rule_t
      WHERE fs_rule_grp_nm = 'DVCampusDocLocationRestrictions'
/

-- remove global DV object level restrictions
UPDATE fs_bsns_rule_t
    SET fs_rule_txt = NULL
      WHERE fs_rule_grp_nm = 'DVGlobalFieldRestrictions'
        AND fs_rule_nm = 'OBJECT_LEVEL_RESTRICTIONS'
/

-- rule references IU object code
DELETE FROM fs_bsns_rule_t
  WHERE fs_rule_grp_nm = 'DVObjectCodePaymentRestrictions'
/
 
-- remove restrictions on DV object codes and levels based on payment reason
UPDATE fs_bsns_rule_t
    SET fs_rule_txt = NULL
  WHERE fs_rule_grp_nm IN ( 'DVPaymentObjectCodeRestrictions', 'DVPaymentObjectLevelRestrictions', 'DVPaymentDocLocationRestrictions' )
/  

-- remove IU chart values from GL rules
UPDATE fs_bsns_rule_t
    SET fs_rule_txt = NULL
      WHERE fs_rule_grp_nm = 'GL.SCRUBBER'
        AND fs_rule_nm LIKE '%.CHART_CODES'
/
    
-- remove IU object level from rule
DELETE FROM fs_bsns_rule_t
      WHERE fs_rule_grp_nm = 'Kuali.FinancialTransactionProcessing.AuxiliaryVoucherDocument'
        AND fs_rule_nm = 'RestrictedCombinationOfCodes'
/

-- remove IU object level from rule
UPDATE fs_bsns_rule_t
    SET fs_rule_txt = NULL
      WHERE fs_rule_grp_nm = 'Kuali.FinancialTransactionProcessing.BudgetAdjustmentDocument'
        AND fs_rule_nm = 'RestrictedObjectCodes'
/

-- remove IU object consolidation from rule
UPDATE fs_bsns_rule_t
    SET fs_rule_txt = NULL
      WHERE fs_rule_grp_nm = 'Kuali.FinancialTransactionProcessing.CashReceiptDocument'
        AND fs_rule_nm = 'RestrictedConsolidatedObjectCodes'
/

-- remove IU object codes from rule
UPDATE fs_bsns_rule_t
    SET fs_rule_txt = NULL
      WHERE fs_rule_grp_nm = 'Kuali.FinancialTransactionProcessing.GlobalRules'
        AND fs_rule_nm = 'RestrictedObjectCodes'
/

UPDATE fs_bsns_rule_t
    SET fs_rule_txt = NULL
      WHERE fs_rule_grp_nm = 'Kuali.FinancialTransactionProcessing.InternalBillingDocument'
        AND fs_rule_nm = 'RestrictedObjectLevelCodes'
/

UPDATE fs_bsns_rule_t
    SET fs_rule_txt = NULL
      WHERE fs_rule_grp_nm = 'Kuali.FinancialTransactionProcessing.NonCheckDisbursementDocument'
        AND fs_rule_nm = 'RestrictedConsolidationCodes'
/

UPDATE fs_bsns_rule_t
    SET fs_rule_txt = NULL
      WHERE fs_rule_grp_nm = 'OrgReversion'
        AND (fs_rule_nm LIKE 'C__\_Level' ESCAPE '\'
        OR fs_rule_nm LIKE 'C__\_Consolidation' ESCAPE '\')
/

UPDATE fs_bsns_rule_t
    SET fs_rule_txt = NULL
      WHERE fs_rule_grp_nm = 'PCGlobalFieldRestrictions'
        AND fs_rule_nm = 'OBJECT_CONSOLIDATION_RESTRICTIONS'
/

-- replace invalid universal user ID
UPDATE fs_bsns_rule_t
    SET fs_rule_txt = '0000000000'
      WHERE fs_rule_grp_nm = 'fis_a21_in_crdoc.sh'
        AND fs_rule_nm = 'DOC_INIT_UUID'
/


UPDATE fs_bsns_rule_t
    SET fs_rule_txt = NULL
      WHERE fs_rule_grp_nm = 'fis_arlockbox_in.sh'
        AND fs_rule_nm = 'AR_EMAIL_LIST'
/


UPDATE fs_bsns_rule_t
    SET fs_rule_txt = NULL
      WHERE fs_rule_grp_nm = 'fis_cfda_update.sh'
        AND fs_rule_nm = 'CFDA_EMAIL_LIST'
/

UPDATE fs_bsns_rule_t
    SET fs_rule_txt = NULL
      WHERE fs_rule_grp_nm = 'fis_cfda_update.sh'
        AND fs_rule_nm = 'CFDA_URL'
/

UPDATE fs_bsns_rule_t
    SET fs_rule_txt = NULL
      WHERE fs_rule_grp_nm = 'fis_dv_out.sh'
        AND fs_rule_nm = 'DOCPOP_URL'
/

UPDATE fs_bsns_rule_t
    SET fs_rule_txt = NULL
      WHERE fs_rule_grp_nm = 'fis_dv_out.sh'
        AND fs_rule_nm = 'FED_TAX_ACCOUNT'
/
UPDATE fs_bsns_rule_t
    SET fs_rule_txt = NULL
      WHERE fs_rule_grp_nm = 'fis_dv_out.sh'
        AND fs_rule_nm = 'FED_TAX_CHART'
/
UPDATE fs_bsns_rule_t
    SET fs_rule_txt = '0000'
      WHERE fs_rule_grp_nm = 'fis_dv_out.sh'
        AND fs_rule_nm = 'INC_FELLOWSHIP_OBJECT_CD'
/
UPDATE fs_bsns_rule_t
    SET fs_rule_txt = '0000'
      WHERE fs_rule_grp_nm = 'fis_dv_out.sh'
        AND fs_rule_nm = 'INC_OTHER_OBJECT_CD'
/

UPDATE fs_bsns_rule_t
    SET fs_rule_txt = NULL
      WHERE fs_rule_grp_nm = 'fis_dv_out.sh'
        AND fs_rule_nm = 'STATE_TAX_ACCOUNT'
/
UPDATE fs_bsns_rule_t
    SET fs_rule_txt = NULL
      WHERE fs_rule_grp_nm = 'fis_dv_out.sh'
        AND fs_rule_nm = 'STATE_TAX_CHART'
/
UPDATE fs_bsns_rule_t
    SET fs_rule_txt = NULL
      WHERE fs_rule_grp_nm = 'fis_dv_out.sh'
        AND fs_rule_nm = 'VENDOR_URL'
/

UPDATE fs_bsns_rule_t
    SET fs_rule_txt = '0000'
      WHERE fs_rule_grp_nm = 'fis_gl_year_end.sh'
        AND fs_rule_nm LIKE '%OBJECT\_CD' ESCAPE '\'
/

UPDATE fs_bsns_rule_t
    SET fs_rule_txt = NULL
      WHERE fs_rule_grp_nm = 'fis_ld_std_reports.sh'
        AND fs_rule_nm LIKE '%\_COA\_CD\_L%' ESCAPE '\'
/

UPDATE fs_bsns_rule_t
    SET fs_rule_txt = NULL
      WHERE fs_rule_grp_nm = 'fis_prcd_trans_in.sh'
        AND fs_rule_nm = 'ERROR_ACCT'
/

UPDATE fs_bsns_rule_t
    SET fs_rule_txt = NULL
      WHERE fs_rule_grp_nm = 'fis_prcd_trans_in.sh'
        AND fs_rule_nm = 'ERROR_COA'
/

UPDATE fs_bsns_rule_t
    SET fs_rule_txt = NULL
      WHERE fs_rule_grp_nm = 'fis_prcd_trans_in.sh'
        AND fs_rule_nm = 'GL_ACCT'
/

UPDATE fs_bsns_rule_t
    SET fs_rule_txt = NULL
      WHERE fs_rule_grp_nm = 'fis_prcd_trans_in.sh'
        AND fs_rule_nm = 'GL_COA'
/
UPDATE fs_bsns_rule_t
    SET fs_rule_txt = '0000'
      WHERE fs_rule_grp_nm = 'fis_prcd_trans_in.sh'
        AND fs_rule_nm = 'GL_OBJECT'
/

UPDATE fs_bsns_rule_t
    SET fs_rule_txt = '0000'
      WHERE fs_rule_grp_nm = 'fis_prcd_trans_in.sh'
        AND fs_rule_nm LIKE 'MCC%OBJECT'
/

UPDATE fs_bsns_rule_t
    SET fs_rule_txt = '0000'
      WHERE fs_rule_grp_nm = 'PCMccObjectCodeRestrictions'
        AND fs_rule_nm LIKE 'MCC%'
/

COMMIT
/

-- populate the date table through FY 2015
TRUNCATE TABLE sh_univ_date_t
/
DECLARE
    StartDate DATE := TO_DATE( '7/1/2006', 'MM/DD/YYYY' );
    EndDate DATE := TO_DATE( '6/30/2015', 'MM/DD/YYYY' );
    CurrDate DATE;
    CurrMonth NUMBER(2);
    FiscalPeriod CHAR(2);
    FiscalYear NUMBER(4);
BEGIN
    CurrDate := StartDate;
    WHILE CurrDate <= EndDate LOOP
        CurrMonth := TO_NUMBER( TO_CHAR( CurrDate, 'MM' ) );
        IF CurrMonth >= 7 THEN
            FiscalYear := TO_NUMBER( TO_CHAR( CurrDate, 'YYYY' ) ) + 1;
            FiscalPeriod := TO_CHAR( CurrMonth - 6, 'FM00' );
        ELSE 
            FiscalYear := TO_NUMBER( TO_CHAR( CurrDate, 'YYYY' ) );
            FiscalPeriod := TO_CHAR( CurrMonth + 6, 'FM00' );
        END IF;
        --dbms_output.put_line(CurrDate||'==>'||FiscalYear||'/'||FiscalPeriod);   
        INSERT INTO sh_univ_date_t ( univ_dt, univ_fiscal_yr, univ_fiscal_prd_cd )
        VALUES ( CurrDate, FiscalYear, FiscalPeriod );
        CurrDate := CurrDate + 1;
    END LOOP;
    COMMIT;
END;
/
        
-- update the accounting period table
TRUNCATE TABLE sh_acct_period_t
/
DECLARE
    StartYear NUMBER(4) := 2007;
    EndYear NUMBER(4) := 2015;
BEGIN
    FOR year IN StartYear..EndYear LOOP
        INSERT INTO sh_acct_period_t ( univ_fiscal_yr, univ_fiscal_prd_cd, 
           UNIV_FISCAL_PRD_NM, UNIV_FSCPD_STAT_CD, BDGT_ROLLOVER_CD, UNIV_FSCPD_END_DT )
        VALUES ( year, '01', 'JULY '||(year - 1), 'O', 'N', TO_DATE( '07/31/'||(year - 1), 'MM/DD/YYYY' ) );
        INSERT INTO sh_acct_period_t ( univ_fiscal_yr, univ_fiscal_prd_cd, 
           UNIV_FISCAL_PRD_NM, UNIV_FSCPD_STAT_CD, BDGT_ROLLOVER_CD, UNIV_FSCPD_END_DT )
        VALUES ( year, '02', 'AUG. '||(year - 1), 'O', 'N', TO_DATE( '08/31/'||(year - 1), 'MM/DD/YYYY' ) );
        INSERT INTO sh_acct_period_t ( univ_fiscal_yr, univ_fiscal_prd_cd, 
           UNIV_FISCAL_PRD_NM, UNIV_FSCPD_STAT_CD, BDGT_ROLLOVER_CD, UNIV_FSCPD_END_DT )
        VALUES ( year, '03', 'SEP. '||(year - 1), 'O', 'N', TO_DATE( '09/30/'||(year - 1), 'MM/DD/YYYY' ) );
        INSERT INTO sh_acct_period_t ( univ_fiscal_yr, univ_fiscal_prd_cd, 
           UNIV_FISCAL_PRD_NM, UNIV_FSCPD_STAT_CD, BDGT_ROLLOVER_CD, UNIV_FSCPD_END_DT )
        VALUES ( year, '04', 'OCT. '||(year - 1), 'O', 'N', TO_DATE( '10/31/'||(year - 1), 'MM/DD/YYYY' ) );
        INSERT INTO sh_acct_period_t ( univ_fiscal_yr, univ_fiscal_prd_cd, 
           UNIV_FISCAL_PRD_NM, UNIV_FSCPD_STAT_CD, BDGT_ROLLOVER_CD, UNIV_FSCPD_END_DT )
        VALUES ( year, '05', 'NOV. '||(year - 1), 'O', 'N', TO_DATE( '11/30/'||(year - 1), 'MM/DD/YYYY' ) );
        INSERT INTO sh_acct_period_t ( univ_fiscal_yr, univ_fiscal_prd_cd, 
           UNIV_FISCAL_PRD_NM, UNIV_FSCPD_STAT_CD, BDGT_ROLLOVER_CD, UNIV_FSCPD_END_DT )
        VALUES ( year, '06', 'DEC. '||(year - 1), 'O', 'N', TO_DATE( '12/31/'||(year - 1), 'MM/DD/YYYY' ) );
        INSERT INTO sh_acct_period_t ( univ_fiscal_yr, univ_fiscal_prd_cd, 
           UNIV_FISCAL_PRD_NM, UNIV_FSCPD_STAT_CD, BDGT_ROLLOVER_CD, UNIV_FSCPD_END_DT )
        VALUES ( year, '07', 'JAN. '||year, 'O', 'N', TO_DATE( '1/31/'||year, 'MM/DD/YYYY' ) );
        INSERT INTO sh_acct_period_t ( univ_fiscal_yr, univ_fiscal_prd_cd, 
           UNIV_FISCAL_PRD_NM, UNIV_FSCPD_STAT_CD, BDGT_ROLLOVER_CD, UNIV_FSCPD_END_DT )
        VALUES ( year, '08', 'FEB. '||year, 'O', 'N', TO_DATE( '3/1/'||year, 'MM/DD/YYYY' ) - 1 );
        INSERT INTO sh_acct_period_t ( univ_fiscal_yr, univ_fiscal_prd_cd, 
           UNIV_FISCAL_PRD_NM, UNIV_FSCPD_STAT_CD, BDGT_ROLLOVER_CD, UNIV_FSCPD_END_DT )
        VALUES ( year, '09', 'MAR. '||year, 'O', 'N', TO_DATE( '3/31/'||year, 'MM/DD/YYYY' ) );
        INSERT INTO sh_acct_period_t ( univ_fiscal_yr, univ_fiscal_prd_cd, 
           UNIV_FISCAL_PRD_NM, UNIV_FSCPD_STAT_CD, BDGT_ROLLOVER_CD, UNIV_FSCPD_END_DT )
        VALUES ( year, '10', 'APR. '||year, 'O', 'N', TO_DATE( '4/30/'||year, 'MM/DD/YYYY' ) );
        INSERT INTO sh_acct_period_t ( univ_fiscal_yr, univ_fiscal_prd_cd, 
           UNIV_FISCAL_PRD_NM, UNIV_FSCPD_STAT_CD, BDGT_ROLLOVER_CD, UNIV_FSCPD_END_DT )
        VALUES ( year, '11', 'MAY  '||year, 'O', 'N', TO_DATE( '5/31/'||year, 'MM/DD/YYYY' ) );
        INSERT INTO sh_acct_period_t ( univ_fiscal_yr, univ_fiscal_prd_cd, 
           UNIV_FISCAL_PRD_NM, UNIV_FSCPD_STAT_CD, BDGT_ROLLOVER_CD, UNIV_FSCPD_END_DT )
        VALUES ( year, '12', 'JUNE '||year, 'O', 'N', TO_DATE( '6/30/'||year, 'MM/DD/YYYY' ) );
        INSERT INTO sh_acct_period_t ( univ_fiscal_yr, univ_fiscal_prd_cd, 
           UNIV_FISCAL_PRD_NM, UNIV_FSCPD_STAT_CD, BDGT_ROLLOVER_CD, UNIV_FSCPD_END_DT )
        VALUES ( year, '13', 'CLOSING '||SUBSTR(year, 3,2), 'O', 'N', TO_DATE( '6/30/'||year, 'MM/DD/YYYY' ) );
        INSERT INTO sh_acct_period_t ( univ_fiscal_yr, univ_fiscal_prd_cd, 
           UNIV_FISCAL_PRD_NM, UNIV_FSCPD_STAT_CD, BDGT_ROLLOVER_CD, UNIV_FSCPD_END_DT )
        VALUES ( year, 'AB', 'ANN BAL '||SUBSTR(year,3,2), 'O', 'N', TO_DATE( '6/30/'||year, 'MM/DD/YYYY' ) );
        INSERT INTO sh_acct_period_t ( univ_fiscal_yr, univ_fiscal_prd_cd, 
           UNIV_FISCAL_PRD_NM, UNIV_FSCPD_STAT_CD, BDGT_ROLLOVER_CD, UNIV_FSCPD_END_DT )
        VALUES ( year, 'BB', 'BEG BAL '||SUBSTR(year,3,2), 'O', 'N', TO_DATE( '6/30/'||year, 'MM/DD/YYYY' ) );
        INSERT INTO sh_acct_period_t ( univ_fiscal_yr, univ_fiscal_prd_cd, 
           UNIV_FISCAL_PRD_NM, UNIV_FSCPD_STAT_CD, BDGT_ROLLOVER_CD, UNIV_FSCPD_END_DT )
        VALUES ( year, 'CB', 'CG BAL  '||SUBSTR(year,3,2), 'O', 'N', TO_DATE( '6/30/'||year, 'MM/DD/YYYY' ) );
    END LOOP;
    COMMIT;
END;
/

-- clean up FS_PARM_T
UPDATE fs_parm_t
    SET fs_parm_txt = '0000'
    WHERE fs_scr_nm = 'DVNRATaxParameters'
      AND fs_parm_nm LIKE '%OBJECT\_CODE%' ESCAPE '\'
/      
UPDATE fs_parm_t
    SET fs_parm_txt = NULL
    WHERE fs_scr_nm = 'DVNRATaxParameters'
      AND fs_parm_nm LIKE '%ACCOUNT' ESCAPE '\'
/

UPDATE fs_parm_t
    SET fs_parm_txt = NULL
    WHERE fs_scr_nm = 'DVNRATaxParameters'
      AND fs_parm_nm LIKE '%CHART' ESCAPE '\'
/

UPDATE fs_parm_t
    SET fs_parm_txt = '01'
    WHERE fs_scr_nm = 'GL.ENCUMBRANCECLOSING'
      AND fs_parm_nm = 'LaborDistributionOriginCode'
/

UPDATE fs_parm_t
    SET fs_parm_txt = '0000'
    WHERE fs_scr_nm = 'GL.SCRUBBER'
      AND fs_parm_nm LIKE '%OBJECT%' ESCAPE '\'
/

DELETE FROM fs_parm_t
    WHERE fs_scr_nm LIKE 'Kra%'
/

UPDATE fs_parm_t
    SET fs_parm_txt = '0000'
    WHERE fs_scr_nm = 'Kuali.FinancialTransactionProcessing.AuxiliaryVoucherDocument'
      AND fs_parm_nm = 'GeneralLedgerPendingEntryOffsetObjectCode'
/

UPDATE fs_parm_t
    SET fs_parm_txt = '0000'
    WHERE fs_scr_nm = 'Kuali.FinancialTransactionProcessing.BudgetAdjustmentDocument'
      AND fs_parm_nm = 'TransferObjectCode'
/

UPDATE fs_parm_t
    SET fs_parm_txt = '0000'
    WHERE fs_scr_nm = 'Kuali.FinancialTransactionProcessing.IndirectCostAdjustmentDocument'
      AND fs_parm_nm LIKE '%ObjectCode'
/

UPDATE fs_parm_t
    SET fs_parm_txt = NULL
    WHERE fs_scr_nm = 'Kuali.FinancialTransactionProcessing.ProcurementCardDocument'
      AND fs_parm_nm LIKE '%ACCOUNT\_NUMBER' ESCAPE '\'
/

UPDATE fs_parm_t
    SET fs_parm_txt = NULL
    WHERE fs_scr_nm = 'Kuali.FinancialTransactionProcessing.ProcurementCardDocument'
      AND fs_parm_nm LIKE '%CHART\_CODE' ESCAPE '\'
/

UPDATE fs_parm_t
    SET fs_parm_txt = '0000'
    WHERE fs_scr_nm = 'Kuali.FinancialTransactionProcessing.ProcurementCardDocument'
      AND fs_parm_nm LIKE '%OBJECT\_CODE' ESCAPE '\'
/

UPDATE fs_parm_t
    SET fs_parm_txt = '0000'
    WHERE fs_scr_nm = 'Kuali.FinancialTransactionProcessing.SufficientFundsService'
      AND fs_parm_nm = 'SufficientFundsServiceSpecialFinancialObjectCodes'
/

UPDATE fs_parm_t
    SET fs_parm_txt = NULL
    WHERE fs_scr_nm = 'SYSTEM'
      AND fs_parm_nm = 'HELP_URL'
/

UPDATE fs_parm_t
    SET fs_parm_txt = '0000'
    WHERE fs_scr_nm = 'fis_gl_year_end.sh'
      AND fs_parm_nm LIKE '%OBJECT_CD'
/

UPDATE fs_parm_t
    SET fs_parm_txt = 'ZZ'
  WHERE fs_parm_nm LIKE '%DOCUMENTATION%'
    AND fs_scr_nm = 'Kuali.FinancialTransactionProcessing.DisbursementVoucherDocument'
/

COMMIT
/

-- KEW table cleanup
TRUNCATE TABLE en_cache_server_t
/

UPDATE en_appl_cnst_t
    SET appl_cnst_val_txt = 'KULUSER'
    WHERE appl_cnst_nm = 'Config.Application.AdminUserList'
/
    
UPDATE en_appl_cnst_t
    SET appl_cnst_val_txt = '127.0.0.1'
    WHERE appl_cnst_nm = 'Config.Mailer.IPAddress'
/
    
UPDATE en_appl_cnst_t
    SET appl_cnst_val_txt = 'KualiEnterpriseWorkflow@yourinstitution.edu'
    WHERE appl_cnst_nm = 'Config.Mailer.FromAddress'
/
     
TRUNCATE TABLE en_actn_rqst_t
/
TRUNCATE TABLE en_actn_tkn_t
/
TRUNCATE TABLE en_doc_hdr_t
/
TRUNCATE TABLE en_rte_node_instn_t
/
TRUNCATE TABLE en_rte_node_instn_lnk_t
/
TRUNCATE TABLE en_rte_brch_t
/
TRUNCATE TABLE en_rte_brch_st_t
/
TRUNCATE TABLE en_init_rte_node_instn_t
/
TRUNCATE TABLE en_usr_optn_t
/
 
-- update the rule responsibility table
UPDATE en_rule_rsp_t SET rule_rsp_nm = '0000000000'
WHERE rule_rsp_typ = 'F'
/


DELETE
  FROM en_rte_node_lnk_t a
  WHERE from_rte_node_id IN ( SELECT rte_node_id FROM en_rte_node_t
  WHERE doc_typ_id IN (SELECT doc_typ_id FROM en_doc_typ_t
    WHERE doc_typ_nm LIKE 'Fake%'
       OR doc_typ_nm LIKE 'Test%'
       OR doc_typ_nm LIKE 'MyTest%'
       OR doc_typ_nm LIKE '%TEST%'
       OR doc_typ_nm LIKE 'aintenance%'
       OR doc_typ_nm LIKE '%Purap%'
       OR doc_typ_nm LIKE '%Research%'
       OR doc_typ_nm = 'KualiBudgetDocument'
       OR doc_typ_parnt_id IN (
        SELECT doc_typ_id
            FROM en_doc_typ_t
            WHERE doc_typ_nm LIKE '%Purap%'
               OR doc_typ_nm LIKE '%Research%'
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
       OR doc_typ_nm LIKE '%Purap%'
       OR doc_typ_nm LIKE '%Research%'
       OR doc_typ_nm = 'KualiBudgetDocument'
       OR doc_typ_parnt_id IN (
        SELECT doc_typ_id
            FROM en_doc_typ_t
            WHERE doc_typ_nm LIKE '%Purap%'
               OR doc_typ_nm LIKE '%Research%'
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
       OR doc_typ_nm LIKE '%Purap%'
       OR doc_typ_nm LIKE '%Research%'
       OR doc_typ_nm = 'KualiBudgetDocument'
       OR doc_typ_parnt_id IN (
        SELECT doc_typ_id
            FROM en_doc_typ_t
            WHERE doc_typ_nm LIKE '%Purap%'
               OR doc_typ_nm LIKE '%Research%'
       )
)
/

-- clear out bad document types
DELETE FROM en_doc_typ_t
    WHERE doc_typ_nm LIKE 'Fake%'
       OR doc_typ_nm LIKE 'Test%'
       OR doc_typ_nm LIKE 'MyTest%'
       OR doc_typ_nm LIKE '%TEST%'
       OR doc_typ_nm LIKE 'aintenance%'
       OR doc_typ_nm LIKE '%Purap%'
       OR doc_typ_nm LIKE '%Research%'
       OR doc_typ_nm = 'KualiBudgetDocument'
       OR doc_typ_parnt_id IN (
        SELECT doc_typ_id
            FROM en_doc_typ_t
            WHERE doc_typ_nm LIKE '%Purap%'
               OR doc_typ_nm LIKE '%Research%'
       )
/

-- delete inactive document types
DELETE FROM en_doc_typ_t
    WHERE doc_typ_cur_ind = 0
/

-- remove some old rule attributes
DELETE FROM en_rule_attrib_t WHERE 
--rule_attrib_lbl_txt = 'foo'
 --OR
  rule_attrib_nm LIKE 'KualiReporting%'
 OR rule_attrib_nm = 'KualiBudgetDocumentAttribute'
/


TRUNCATE TABLE EN_DLGN_RSP_T
/
TRUNCATE TABLE EN_RULE_EXT_VAL_T
/
TRUNCATE TABLE EN_RULE_EXT_T
/
TRUNCATE TABLE EN_RULE_BASE_VAL_T
/
TRUNCATE TABLE EN_RULE_RSP_T
/

-- delete non-phase 1 rule templates
DELETE
  FROM en_rule_tmpl_attrib_t
 WHERE rule_tmpl_id IN (SELECT a.rule_tmpl_id
                          FROM en_rule_tmpl_t a
                         WHERE rule_tmpl_nm LIKE 'Test%' OR rule_tmpl_nm LIKE 'KualiReporting%' OR rule_tmpl_nm LIKE 'Kra%')
/  

DELETE
  FROM en_rule_tmpl_attrib_t
  WHERE NOT EXISTS ( SELECT rule_attrib_id FROM en_rule_attrib_t )
/

DELETE
  FROM en_rule_tmpl_optn_t
 WHERE rule_tmpl_id IN (SELECT a.rule_tmpl_id
                          FROM en_rule_tmpl_t a
                         WHERE rule_tmpl_nm LIKE 'Test%' OR rule_tmpl_nm LIKE 'KualiReporting%' OR rule_tmpl_nm LIKE 'Kra%')
/
DELETE
  FROM en_rule_tmpl_t
  WHERE rule_tmpl_nm LIKE 'Test%' OR rule_tmpl_nm LIKE 'KualiReporting%' OR rule_tmpl_nm LIKE 'Kra%'       
/ 

-- remove document numbers from workgroup table
UPDATE en_wrkgrp_t SET doc_hdr_id = -1 WHERE doc_hdr_id <> -1
/

-- remove inactive workgroups 
DELETE FROM en_wrkgrp_t WHERE wrkgrp_cur_ind = 0
/

DELETE FROM en_wrkgrp_t
    WHERE wrkgrp_id NOT IN (
     '166668'
    ,'176739'
    ,'177110'
    ,'180244'
    ,'183699'
    ,'25386'
    ,'2562'
    ,'2618'
    ,'2619'
    ,'2624'
    ,'2627'
    ,'2629'
    ,'2633'
    ,'2636'
    ,'2637'
    ,'2638'
    ,'2644'
    ,'2679'
    ,'2692'
    ,'2722'
    ,'2735'
    ,'2736'
    ,'2738'
    ,'2739'
    ,'2741'
    ,'2742'
    ,'2743'
    ,'2744'
    ,'2745'
    ,'2746'
    ,'2751'
    ,'2752'
    ,'46663'
    )
    AND wrkgrp_nm NOT IN (
        SELECT DISTINCT wrkgrp_nm FROM fs_bsns_rule_sec_t
    )
    AND wrkgrp_nm NOT IN (
        SELECT DISTINCT wrkgrp_nm FROM fs_parm_sec_t
    )
/

-- remove orphan members
DELETE FROM en_wrkgrp_mbr_t
    WHERE NOT EXISTS ( SELECT 'x' FROM en_wrkgrp_t 
        WHERE wrkgrp_id = en_wrkgrp_mbr_t.wrkgrp_id
          AND wrkgrp_ver_nbr = en_wrkgrp_mbr_t.wrkgrp_ver_nbr 
    )
/          

-- remove the test.kuali.org URLs
UPDATE en_doc_typ_t 
    SET doc_typ_hdlr_url_addr = 
        REPLACE( doc_typ_hdlr_url_addr,
'https://test.kuali.org/en-dev/', 'http://localhost:8080/en-dev/' )
    WHERE doc_typ_hdlr_url_addr LIKE 'https://test.kuali.org/en-dev/%'
/

-- remove the extra "/en" in one url's path
UPDATE en_doc_typ_t 
    SET doc_typ_hdlr_url_addr = 
        REPLACE( doc_typ_hdlr_url_addr,
'/en-dev/en/', '/en-dev/' )
    WHERE doc_typ_hdlr_url_addr LIKE '%/en-dev/en/%'
/

COMMIT
/

