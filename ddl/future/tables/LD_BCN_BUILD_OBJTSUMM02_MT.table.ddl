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
    WHERE table_name = 'LD_BCN_BUILD_OBJTSUMM02_MT';

	IF obj_count > 0 THEN
		EXECUTE IMMEDIATE('drop table LD_BCN_BUILD_OBJTSUMM02_MT');
	END IF;
END;
/

/* create the table */
CREATE TABLE LD_BCN_BUILD_OBJTSUMM02_MT
AS
	(SELECT  USERENV('SESSIONID') "SESID",
		s.org_fin_coa_cd,
		s.org_cd,
		s.sub_fund_grp_cd,
		s.fin_coa_cd,
		s.inc_exp_cd,
		s.fin_cons_obj_cd,
		s.fin_obj_level_cd,
		s.fin_object_cd,
        s.VER_NBR,
		b.pos_csf_fndstat_cd,
		s.pos_csf_fte_qty,
		s.pos_csf_lv_fte_qty
	FROM ld_bcn_objt_summ_t s,
		ld_bcn_csf_trckr_t b
	WHERE 1=2)
/
ALTER TABLE LD_BCN_BUILD_OBJTSUMM02_MT ADD CONSTRAINT LD_BCN_BUILD_OBJTSUMM02_MTP1 PRIMARY KEY (
        SESID,
		org_fin_coa_cd,
		org_cd,
		sub_fund_grp_cd,
		fin_coa_cd,
		inc_exp_cd,
		fin_cons_obj_cd,
		fin_obj_level_cd,
		fin_object_cd)
/
ALTER TABLE LD_BCN_BUILD_OBJTSUMM02_MT MODIFY (VER_NBR DEFAULT 1);
