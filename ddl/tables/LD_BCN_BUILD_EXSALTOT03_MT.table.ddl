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
    WHERE table_name = 'LD_BCN_BUILD_EXSALTOT03_MT';

	IF obj_count > 0 THEN
		EXECUTE IMMEDIATE('drop table LD_BCN_BUILD_EXSALTOT03_MT');
	END IF;
END;
/

/* create the table */
CREATE TABLE LD_BCN_BUILD_EXSALTOT03_MT
AS
	(SELECT  USERENV('SESSIONID') "SESID",
       bcaf.emplid,
       bcaf.VER_NBR,  
       bcaf.appt_fnd_mo "SAL_MTHS",
       posn.iu_pay_months "SAL_PMTHS"
	FROM ld_pndbc_apptfnd_t bcaf,
		ld_bcn_pos_t posn
	WHERE 1=2)
/
ALTER TABLE LD_BCN_BUILD_EXSALTOT03_MT ADD CONSTRAINT LD_BCN_BUILD_EXSALTOT03_MTP1 PRIMARY KEY (
        SESID,
        emplid)
/
ALTER TABLE LD_BCN_BUILD_EXSALTOT03_MT MODIFY (VER_NBR DEFAULT 1);
