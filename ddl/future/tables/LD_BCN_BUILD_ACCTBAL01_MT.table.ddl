/*
 * Copyright 2006 The Kuali Foundation.
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
DECLARE
  obj_count INTEGER;

BEGIN
    /* drop any existing table */
    SELECT count(*) into obj_count
    FROM user_tables
    WHERE table_name = 'LD_BCN_BUILD_ACCTBAL01_MT';

    IF obj_count > 0 THEN
        EXECUTE IMMEDIATE('drop table LD_BCN_BUILD_ACCTBAL01_MT');
    END IF;
END;
/

/* create the table */
CREATE TABLE LD_BCN_BUILD_ACCTBAL01_MT
AS
    (SELECT  USERENV('SESSIONID') "SESID",
        org_fin_coa_cd,
        org_cd,
        sub_fund_grp_cd,
        univ_fiscal_yr,
        fin_coa_cd,
        account_nbr,
        sub_acct_nbr,
        inc_exp_cd,
        fin_cons_sort_cd,
        fin_level_sort_cd,
        fin_object_cd,
        fin_sub_obj_cd,
        OBJ_ID,
        VER_NBR,
        fin_obj_level_cd,
        appt_rqst_fte_qty,
        appt_rqcsf_fte_qty,
        position_fte_qty "POS_CSF_FTE_QTY",
        fin_beg_bal_ln_amt,
        acln_annl_bal_amt,
        pos_csf_lv_fte_qty
    FROM ld_bcn_acct_bal_t
    WHERE 1=2)
/
ALTER TABLE LD_BCN_BUILD_ACCTBAL01_MT ADD CONSTRAINT LD_BCN_BUILD_ACCTBAL01_MTP1 PRIMARY KEY (
        SESID,
        org_fin_coa_cd,
        org_cd,
        sub_fund_grp_cd,
        univ_fiscal_yr,
        fin_coa_cd,
        account_nbr,
        sub_acct_nbr,
        inc_exp_cd,
        fin_cons_sort_cd,
        fin_level_sort_cd,
        fin_object_cd,
        fin_sub_obj_cd)
/
ALTER TABLE LD_BCN_BUILD_ACCTBAL01_MT ADD CONSTRAINT LD_BCN_BUILD_ACCTBAL01_MTC0 UNIQUE (OBJ_ID)
/
ALTER TABLE LD_BCN_BUILD_ACCTBAL01_MT MODIFY (OBJ_ID DEFAULT SYS_GUID()); 
ALTER TABLE LD_BCN_BUILD_ACCTBAL01_MT MODIFY (VER_NBR DEFAULT 1);

