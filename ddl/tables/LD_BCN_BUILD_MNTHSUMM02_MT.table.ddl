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
    WHERE table_name = 'LD_BCN_BUILD_MNTHSUMM02_MT';

	IF obj_count > 0 THEN
		EXECUTE IMMEDIATE('drop table LD_BCN_BUILD_MNTHSUMM02_MT');
	END IF;
END;
/

/* create the table */
CREATE TABLE LD_BCN_BUILD_MNTHSUMM02_MT
NOLOGGING
AS
	(SELECT  USERENV('SESSIONID') "SESID",
		s.org_fin_coa_cd "SEL_ORG_FIN_COA",
		s.org_cd "SEL_ORG_CD",
		s.sub_fund_grp_cd "SEL_SUB_FUND_GRP",
		c.univ_fiscal_yr,
		s.fin_coa_cd,
		s.inc_exp_cd,
		s.fin_object_cd,
		s.fin_sub_obj_cd,
        s.VER_NBR,
		s.fdoc_ln_mo1_amt,
		s.fdoc_ln_mo2_amt,
		s.fdoc_ln_mo3_amt,
		s.fdoc_ln_mo4_amt,
		s.fdoc_ln_mo5_amt,
		s.fdoc_ln_mo6_amt,
		s.fdoc_ln_mo7_amt,
		s.fdoc_ln_mo8_amt,
		s.fdoc_ln_mo9_amt,
		s.fdoc_ln_mo10_amt,
		s.fdoc_ln_mo11_amt,
		s.fdoc_ln_mo12_amt
	FROM ld_bcn_mnth_summ_t s,
		ld_bcn_ctrl_list_t c
	WHERE 1=2)
/
ALTER TABLE LD_BCN_BUILD_MNTHSUMM02_MT ADD CONSTRAINT LD_BCN_BUILD_MNTHSUMM02_MTP1 PRIMARY KEY (
        SESID,
		SEL_ORG_FIN_COA,
		SEL_ORG_CD,
		SEL_SUB_FUND_GRP,
		univ_fiscal_yr,
		fin_coa_cd,
		inc_exp_cd,
		fin_object_cd,
		fin_sub_obj_cd)
/
ALTER TABLE LD_BCN_BUILD_MNTHSUMM02_MT MODIFY (VER_NBR DEFAULT 1);
