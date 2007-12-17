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
    WHERE table_name = 'LD_BCN_BUILD_MNTHSUMM01_MT';

	IF obj_count > 0 THEN
		EXECUTE IMMEDIATE('drop table LD_BCN_BUILD_MNTHSUMM01_MT');
	END IF;
END;
/

/* create the table */
CREATE TABLE LD_BCN_BUILD_MNTHSUMM01_MT(
        SESID                          VARCHAR2(36) CONSTRAINT LD_BCN_BUILD_MNTHSUMM01_MTN1 NOT NULL,
        SEL_ORG_FIN_COA                VARCHAR2(2) CONSTRAINT LD_BCN_BUILD_MNTHSUMM01_MTN2 NOT NULL,
        SEL_ORG_CD                     VARCHAR2(4) CONSTRAINT LD_BCN_BUILD_MNTHSUMM01_MTN3 NOT NULL,
        SEL_SUB_FUND_GRP               VARCHAR2(6) CONSTRAINT LD_BCN_BUILD_MNTHSUMM01_MTN4 NOT NULL,
        UNIV_FISCAL_YR                 NUMBER(4) CONSTRAINT LD_BCN_BUILD_MNTHSUMM01_MTN5 NOT NULL,
        FIN_COA_CD                     VARCHAR2(2) CONSTRAINT LD_BCN_BUILD_MNTHSUMM01_MTN6 NOT NULL,
        INC_EXP_CD                     VARCHAR2(1) CONSTRAINT LD_BCN_BUILD_MNTHSUMM01_MTN7 NOT NULL,
        FIN_OBJECT_CD                  VARCHAR2(4) CONSTRAINT LD_BCN_BUILD_MNTHSUMM01_MTN8 NOT NULL,
        FIN_SUB_OBJ_CD                 VARCHAR2(3) CONSTRAINT LD_BCN_BUILD_MNTHSUMM01_MTN9 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT LD_BCN_BUILD_MNTHSUMM01_MTN10 NOT NULL,
        ACLN_ANNL_BAL_AMT              NUMBER(19, 2),
     CONSTRAINT LD_BCN_BUILD_MNTHSUMM01_MTP1 PRIMARY KEY (
        SESID,
        SEL_ORG_FIN_COA,
        SEL_ORG_CD,
        SEL_SUB_FUND_GRP,
        UNIV_FISCAL_YR,
        FIN_COA_CD,
        INC_EXP_CD,
        FIN_OBJECT_CD,
        FIN_SUB_OBJ_CD)
)
/
