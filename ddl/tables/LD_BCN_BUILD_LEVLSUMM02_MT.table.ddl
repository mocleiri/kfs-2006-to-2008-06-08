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
    WHERE table_name = 'LD_BCN_BUILD_LEVLSUMM02_MT';

	IF obj_count > 0 THEN
		EXECUTE IMMEDIATE('drop table LD_BCN_BUILD_LEVLSUMM02_MT');
	END IF;
END;
/

/* create the table */
CREATE TABLE LD_BCN_BUILD_LEVLSUMM02_MT(
        SESID                          VARCHAR2(36) CONSTRAINT LD_BCN_BUILD_LEVLSUMM02_MTN1 NOT NULL,
        ORG_FIN_COA_CD                 VARCHAR2(2) CONSTRAINT LD_BCN_BUILD_LEVLSUMM02_MTN2 NOT NULL,
        ORG_CD                         VARCHAR2(4) CONSTRAINT LD_BCN_BUILD_LEVLSUMM02_MTN3 NOT NULL,
        SUB_FUND_GRP_CD                VARCHAR2(6) CONSTRAINT LD_BCN_BUILD_LEVLSUMM02_MTN4 NOT NULL,
        FIN_COA_CD                     VARCHAR2(2) CONSTRAINT LD_BCN_BUILD_LEVLSUMM02_MTN5 NOT NULL,
        INC_EXP_CD                     VARCHAR2(1) CONSTRAINT LD_BCN_BUILD_LEVLSUMM02_MTN6 NOT NULL,
        FIN_CONS_OBJ_CD                VARCHAR2(4) CONSTRAINT LD_BCN_BUILD_LEVLSUMM02_MTN7 NOT NULL,
        FIN_OBJ_LEVEL_CD               VARCHAR2(4) CONSTRAINT LD_BCN_BUILD_LEVLSUMM02_MTN8 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT LD_BCN_BUILD_LEVLSUMM02_MTN9 NOT NULL,
        POS_CSF_FNDSTAT_CD             VARCHAR2(1),
        POS_CSF_FTE_QTY                NUMBER,
        POS_CSF_LV_FTE_QTY             NUMBER,
     CONSTRAINT LD_BCN_BUILD_LEVLSUMM02_MTP1 PRIMARY KEY (
        SESID,
        ORG_FIN_COA_CD,
        ORG_CD,
        SUB_FUND_GRP_CD,
        FIN_COA_CD,
        INC_EXP_CD,
        FIN_CONS_OBJ_CD,
        FIN_OBJ_LEVEL_CD)
)
/
