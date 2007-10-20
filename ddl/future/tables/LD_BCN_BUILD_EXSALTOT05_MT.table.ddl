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
    WHERE table_name = 'LD_BCN_BUILD_EXSALTOT05_MT';

	IF obj_count > 0 THEN
		EXECUTE IMMEDIATE('drop table LD_BCN_BUILD_EXSALTOT05_MT');
	END IF;
END;
/

/* create the table */
CREATE TABLE LD_BCN_BUILD_EXSALTOT05_MT
AS
	(SELECT  USERENV('SESSIONID') "SESID",
		bcaf.emplid,
        bcaf.OBJ_ID,
        bcaf.VER_NBR,     
		bcsf.pos_csf_amt,
		bcsf.pos_csf_amt "RES_CSF_AMT",
		bcsf.pos_csf_tm_pct,
		bcaf.appt_rqst_amt "SAL_AMT",
		bcaf.appt_rqst_tm_pct "SAL_PCT",
		bcaf.appt_rqst_fte_qty "SAL_FTE",
		bcaf.appt_fnd_mo "SAL_MTHS",
		posn.iu_pay_months "SAL_PMTHS",
		posn.iu_norm_work_months "CSF_MTHS",
		posn.iu_pay_months "CSF_PMTHS",
		'Y' "USE_FLAG"
	FROM ld_pndbc_apptfnd_t bcaf,
		ld_bcn_csf_trckr_t bcsf,
		ld_bcn_pos_t posn
	WHERE 1=2)
/
ALTER TABLE LD_BCN_BUILD_EXSALTOT05_MT ADD CONSTRAINT LD_BCN_BUILD_EXSALTOT05_MTP1 PRIMARY KEY (
        SESID,
        emplid)
/
ALTER TABLE LD_BCN_BUILD_EXSALTOT05_MT ADD CONSTRAINT LD_BCN_BUILD_EXSALTOT05_MTC0 UNIQUE (OBJ_ID)
/
ALTER TABLE LD_BCN_BUILD_EXSALTOT05_MT MODIFY (OBJ_ID DEFAULT SYS_GUID()); 
ALTER TABLE LD_BCN_BUILD_EXSALTOT05_MT MODIFY (VER_NBR DEFAULT 1);

