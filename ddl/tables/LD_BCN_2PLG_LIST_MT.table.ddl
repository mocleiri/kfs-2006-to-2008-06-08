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
CREATE TABLE LD_BCN_2PLG_LIST_MT(
        PERSON_UNVL_ID                 VARCHAR2(10) CONSTRAINT LD_BCN_2PLG_LIST_MTN1 NOT NULL,
        ORG_FIN_COA_CD                 VARCHAR2(2) CONSTRAINT LD_BCN_2PLG_LIST_MTN2 NOT NULL,
        ORG_CD                         VARCHAR2(4) CONSTRAINT LD_BCN_2PLG_LIST_MTN3 NOT NULL,
        FIN_COA_CD                     VARCHAR2(2) CONSTRAINT LD_BCN_2PLG_LIST_MTN4 NOT NULL,
        ACCOUNT_NBR                    VARCHAR2(7) CONSTRAINT LD_BCN_2PLG_LIST_MTN5 NOT NULL,
        SUB_ACCT_NBR                   VARCHAR2(5) CONSTRAINT LD_BCN_2PLG_LIST_MTN6 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT LD_BCN_2PLG_LIST_MTN8 NOT NULL,
        ACLN_ANNL_BAL_AMT              NUMBER(19, 2),
     CONSTRAINT LD_BCN_2PLG_LIST_MTP1 PRIMARY KEY (
        PERSON_UNVL_ID,
        ORG_FIN_COA_CD,
        ORG_CD,
        FIN_COA_CD,
        ACCOUNT_NBR,
        SUB_ACCT_NBR)
)
/
