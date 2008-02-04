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
CREATE TABLE LD_BCN_UPDT_RQST02_MT(
        SESID                          VARCHAR2(36) CONSTRAINT LD_BCN_UPDT_RQST02_MTN1 NOT NULL,
        FIN_COA_CD                     VARCHAR2(2) CONSTRAINT LD_BCN_UPDT_RQST02_MTN2 NOT NULL,
        ACCOUNT_NBR                    VARCHAR2(7) CONSTRAINT LD_BCN_UPDT_RQST02_MTN3 NOT NULL,
        SUB_ACCT_NBR                   VARCHAR2(5) CONSTRAINT LD_BCN_UPDT_RQST02_MTN4 NOT NULL,
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT LD_BCN_UPDT_RQST02_MTN5 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT LD_BCN_UPDT_RQST02_MTN6 NOT NULL,
        ORG_COA_OF_LVL_CD              VARCHAR2(2),
        ORG_OF_LVL_CD                  VARCHAR2(4),
        ORG_LEVEL_CD                   NUMBER(7),
        CTRL_FLAG                      VARCHAR2(1),
     CONSTRAINT LD_BCN_UPDT_RQST02_MTP1 PRIMARY KEY (
        SESID,
        FIN_COA_CD,
        ACCOUNT_NBR,
        SUB_ACCT_NBR,
        FDOC_NBR)
)
/