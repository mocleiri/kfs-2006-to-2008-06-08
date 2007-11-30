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
    WHERE table_name = 'LD_BCN_BUILD_PULLUP01_MT';

	IF obj_count > 0 THEN
		EXECUTE IMMEDIATE('drop table LD_BCN_BUILD_PULLUP01_MT');
	END IF;
END;
/

/* create the table */
CREATE TABLE LD_BCN_BUILD_PULLUP01_MT
AS
(select USERENV('SESSIONID') as sesid,
	r.fin_coa_cd,
	r.org_cd,
    r.VER_NBR,
	r.rpts_to_fin_coa_cd,
	r.rpts_to_org_cd
from ld_bcn_org_rpts_t r
 where 1 = 2)
/
ALTER TABLE LD_BCN_BUILD_PULLUP01_MT ADD CONSTRAINT LD_BCN_BUILD_PULLUP01_MTP1 PRIMARY KEY (
        SESID,
	    fin_coa_cd,
	    org_cd)
/
ALTER TABLE LD_BCN_BUILD_PULLUP01_MT MODIFY (VER_NBR DEFAULT 1);
