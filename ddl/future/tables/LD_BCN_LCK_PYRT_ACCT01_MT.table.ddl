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
    WHERE table_name = 'LD_BCN_LCK_PYRT_ACCT01_MT';

	IF obj_count > 0 THEN
		EXECUTE IMMEDIATE('drop table LD_BCN_LCK_PYRT_ACCT01_MT');
	END IF;
END;
/

/* create the table */
CREATE TABLE LD_BCN_LCK_PYRT_ACCT01_MT
AS
	(SELECT  USERENV('SESSIONID') "SESID",
		f.univ_fiscal_yr,
		f.fin_coa_cd,
		f.account_nbr,
		f.sub_acct_nbr,
        f.VER_NBR
	FROM ld_pndbc_apptfnd_t f
	WHERE 1=2)
/
ALTER TABLE LD_BCN_LCK_PYRT_ACCT01_MT ADD CONSTRAINT LD_BCN_LCK_PYRT_ACCT01_MTP1 PRIMARY KEY (
        SESID,
		univ_fiscal_yr,
		fin_coa_cd,
		account_nbr,
		sub_acct_nbr)
/
ALTER TABLE LD_BCN_LCK_PYRT_ACCT01_MT MODIFY (VER_NBR DEFAULT 1);
