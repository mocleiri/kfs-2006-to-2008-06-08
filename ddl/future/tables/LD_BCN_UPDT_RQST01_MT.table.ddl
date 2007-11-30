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
    WHERE table_name = 'LD_BCN_UPDT_RQST01_MT';

	IF obj_count > 0 THEN
		EXECUTE IMMEDIATE('drop table LD_BCN_UPDT_RQST01_MT');
	END IF;
END;
/

/* create the table */
CREATE TABLE LD_BCN_UPDT_RQST01_MT
AS
	(SELECT  USERENV('SESSIONID') "SESID",
		m.fin_coa_cd,
		m.account_nbr,
		m.sub_acct_nbr,
		m.fin_object_cd,
		m.fin_sub_obj_cd,
        m.VER_NBR,
		m.fin_obj_typ_cd,
		m.acln_annl_bal_amt,
		m.rqst_updt_err_cd,
		m.fdoc_ln_mo1_amt,
		m.fdoc_ln_mo2_amt,
		m.fdoc_ln_mo3_amt,
		m.fdoc_ln_mo4_amt,
		m.fdoc_ln_mo5_amt,
		m.fdoc_ln_mo6_amt,
		m.fdoc_ln_mo7_amt,
		m.fdoc_ln_mo8_amt,
		m.fdoc_ln_mo9_amt,
		m.fdoc_ln_mo10_amt,
		m.fdoc_ln_mo11_amt,
		m.fdoc_ln_mo12_amt,
		h.fdoc_nbr,
		h.org_coa_of_lvl_cd,
		h.org_of_lvl_cd,
		h.org_level_cd
	FROM ld_bcn_rqst_mt m, ld_bcnstr_hdr_t h
	WHERE 1=2)
/
ALTER TABLE LD_BCN_UPDT_RQST01_MT ADD CONSTRAINT LD_BCN_UPDT_RQST01_MTP1 PRIMARY KEY (
        SESID,
		fin_coa_cd,
		account_nbr,
		sub_acct_nbr,
		fin_object_cd,
		fin_sub_obj_cd)
/
ALTER TABLE LD_BCN_UPDT_RQST01_MT MODIFY (VER_NBR DEFAULT 1);
