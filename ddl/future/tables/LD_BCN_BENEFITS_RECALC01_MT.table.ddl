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
    WHERE table_name = 'LD_BCN_BENEFITS_RECALC01_MT';

	IF obj_count > 0 THEN
		EXECUTE IMMEDIATE('drop table LD_BCN_BENEFITS_RECALC01_MT');
	END IF;
END;
/

/* create the table */
CREATE TABLE LD_BCN_BENEFITS_RECALC01_MT(
        SESID                          VARCHAR2(36) CONSTRAINT LD_BCN_BENEFITS_RECALC01_MTN1 NOT NULL,
        POS_FRNGBEN_OBJ_CD             VARCHAR2(4) CONSTRAINT LD_BCN_BENEFITS_RECALC01_MTN2 NOT NULL,
        OBJ_ID                         VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT LD_BCN_BENEFITS_RECALC01_MTN3 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT LD_BCN_BENEFITS_RECALC01_MTN4 NOT NULL,
        FB_SUM                         NUMBER(19, 2),
     CONSTRAINT LD_BCN_BENEFITS_RECALC01_MTP1 PRIMARY KEY (
        SESID,
        POS_FRNGBEN_OBJ_CD),
     CONSTRAINT LD_BCN_BENEFITS_RECALC01_MTC0 UNIQUE (OBJ_ID)
)
/
