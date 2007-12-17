/*
 * Copyright 2005-2006 The Kuali Foundation.
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
        WHERE table_name = 'AR_NON_APLD_DIST_MT';

        IF obj_count > 0 THEN
                EXECUTE IMMEDIATE('drop table AR_NON_APLD_DIST_MT');
        END IF;
END;
/

/* create the table */
CREATE TABLE AR_NON_APLD_DIST_MT(
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT AR_NON_APLD_DIST_MTN1 NOT NULL,
        AR_PD_APLD_ITM_NBR             NUMBER(7) CONSTRAINT AR_NON_APLD_DIST_MTN2 NOT NULL,
        FDOC_REF_NBR                   VARCHAR2(14) CONSTRAINT AR_NON_APLD_DIST_MTN3 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT AR_NON_APLD_DIST_MTN4 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT AR_NON_APLD_DIST_MTN5 NOT NULL,
        UNIV_FISCAL_YR                 NUMBER(4),
        UNIV_FISCAL_PRD_CD             VARCHAR2(2),
        FDOC_LINE_AMT                  NUMBER(19, 2),
     CONSTRAINT AR_NON_APLD_DIST_MTP1 PRIMARY KEY (
        FDOC_NBR,
        AR_PD_APLD_ITM_NBR,
        FDOC_REF_NBR),
     CONSTRAINT AR_NON_APLD_DIST_MTC0 UNIQUE (OBJ_ID)
)
/
