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
CREATE TABLE LD_BCN_BUILD_EXSALTOT05_MT(
        SESID                          VARCHAR2(36) CONSTRAINT LD_BCN_BUILD_EXSALTOT05_MTN1 NOT NULL,
        EMPLID                         VARCHAR2(11) CONSTRAINT LD_BCN_BUILD_EXSALTOT05_MTN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT LD_BCN_BUILD_EXSALTOT05_MTN3 NOT NULL,
        POS_CSF_AMT                    NUMBER(19, 2),
        RES_CSF_AMT                    NUMBER(19, 2),
        POS_CSF_TM_PCT                 NUMBER,
        SAL_AMT                        NUMBER(19, 2),
        SAL_PCT                        NUMBER,
        SAL_FTE                        NUMBER,
        SAL_MTHS                       NUMBER(2),
        SAL_PMTHS                      NUMBER(2),
        CSF_MTHS                       NUMBER(2),
        CSF_PMTHS                      NUMBER(2),
		USE_FLAG                       VARCHAR2(1) DEFAULT 'Y',
     CONSTRAINT LD_BCN_BUILD_EXSALTOT05_MTP1 PRIMARY KEY (
        SESID,
        EMPLID)
)
/
