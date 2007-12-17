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
    WHERE table_name = 'LD_BCN_BUILD_SALTOT05_MT';

	IF obj_count > 0 THEN
		EXECUTE IMMEDIATE('drop table LD_BCN_BUILD_SALTOT05_MT');
	END IF;
END;
/

/* create the table */
CREATE TABLE LD_BCN_BUILD_SALTOT05_MT
AS
	(SELECT  USERENV('SESSIONID') "SESID",
		ctrl.sel_org_fin_coa "ORG_FIN_COA_CD",
		ctrl.sel_org_cd "ORG_CD",
		bcaf.emplid,
        bcaf.VER_NBR,
		bcsf.pos_csf_amt,
		bcaf.appt_rqst_amt,
		bcaf.appt_rqst_fte_qty,
		bcaf.appt_rqst_amt "INIT_RQST_AMT",
		bcaf.appt_rqst_fte_qty "INIT_RQST_FTE"
	FROM ld_pndbc_apptfnd_t bcaf,
		ld_bcn_csf_trckr_t bcsf,
		ld_bcn_ctrl_list_t ctrl
	WHERE 1=2)
/
ALTER TABLE LD_BCN_BUILD_SALTOT05_MT ADD CONSTRAINT LD_BCN_BUILD_SALTOT05_MTP1 PRIMARY KEY (
        SESID,
		ORG_FIN_COA_CD,
		ORG_CD,
		emplid)
/
ALTER TABLE LD_BCN_BUILD_SALTOT05_MT MODIFY (VER_NBR DEFAULT 1);
