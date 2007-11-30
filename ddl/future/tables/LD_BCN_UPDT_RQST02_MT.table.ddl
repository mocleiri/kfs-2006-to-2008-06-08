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
    WHERE table_name = 'LD_BCN_UPDT_RQST02_MT';

	IF obj_count > 0 THEN
		EXECUTE IMMEDIATE('drop table LD_BCN_UPDT_RQST02_MT');
	END IF;
END;
/

/* create the table */
CREATE TABLE LD_BCN_UPDT_RQST02_MT
AS
	(SELECT  USERENV('SESSIONID') "SESID",
		h.fin_coa_cd,
		h.account_nbr,
		h.sub_acct_nbr,
		h.fdoc_nbr,
        h.VER_NBR,
		h.org_coa_of_lvl_cd,
		h.org_of_lvl_cd,
		h.org_level_cd,
		TO_CHAR(1) "CTRL_FLAG"
	FROM ld_bcnstr_hdr_t h
	WHERE 1=2)
/
ALTER TABLE LD_BCN_UPDT_RQST02_MT ADD CONSTRAINT LD_BCN_UPDT_RQST02_MTP1 PRIMARY KEY (
        SESID,
		fin_coa_cd,
		account_nbr,
		sub_acct_nbr,
		fdoc_nbr)
/
ALTER TABLE LD_BCN_UPDT_RQST02_MT MODIFY (VER_NBR DEFAULT 1);
