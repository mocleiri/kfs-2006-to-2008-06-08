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
    WHERE table_name = 'LD_BCN_BUILD_POS_SEL01_MT';

	IF obj_count > 0 THEN
		EXECUTE IMMEDIATE('drop table LD_BCN_BUILD_POS_SEL01_MT');
	END IF;
END;
/

/* create the table */
CREATE TABLE LD_BCN_BUILD_POS_SEL01_MT
AS
	(SELECT  USERENV('SESSIONID') "SESID",
		p.fin_coa_cd,
		p.org_cd,
        p.VER_NBR,
		h.org_level_cd
	FROM ld_bcn_pullup_t p,
		ld_bcn_acct_org_hier_t h
	WHERE 1=2)
/
ALTER TABLE LD_BCN_BUILD_POS_SEL01_MT ADD CONSTRAINT LD_BCN_BUILD_POS_SEL01_MTP1 PRIMARY KEY (
        SESID,
		fin_coa_cd,
		org_cd)
/
ALTER TABLE LD_BCN_BUILD_POS_SEL01_MT MODIFY (VER_NBR DEFAULT 1);
