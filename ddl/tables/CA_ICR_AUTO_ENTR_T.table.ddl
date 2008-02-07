/*
 * Copyright 2005-2007 The Kuali Foundation.
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
CREATE TABLE CA_ICR_AUTO_ENTR_T(
        UNIV_FISCAL_YR                 NUMBER(4) CONSTRAINT CA_ICR_AUTO_ENTR_TN1 NOT NULL,
        FIN_SERIES_ID                  VARCHAR2(3) CONSTRAINT CA_ICR_AUTO_ENTR_TN2 NOT NULL,
        FIN_BALANCE_TYP_CD             VARCHAR2(2) CONSTRAINT CA_ICR_AUTO_ENTR_TN3 NOT NULL,
        AWRD_ICR_ENTRY_NBR             NUMBER(2) CONSTRAINT CA_ICR_AUTO_ENTR_TN4 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT CA_ICR_AUTO_ENTR_TN5 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT CA_ICR_AUTO_ENTR_TN6 NOT NULL,
        FIN_COA_CD                     VARCHAR2(2),
        ACCOUNT_NBR                    VARCHAR2(7),
        SUB_ACCT_NBR                   VARCHAR2(5),
        FIN_OBJECT_CD                  VARCHAR2(4),
        FIN_SUB_OBJ_CD                 VARCHAR2(3),
        OFST_BALSHT_OBJ_CD             VARCHAR2(4),
        TRN_DEBIT_CRDT_CD              VARCHAR2(1),
        AWRD_ICR_RATE_PCT              NUMBER(6,3),
     CONSTRAINT CA_ICR_AUTO_ENTR_TP1 PRIMARY KEY (
        UNIV_FISCAL_YR,
        FIN_SERIES_ID,
        FIN_BALANCE_TYP_CD,
        AWRD_ICR_ENTRY_NBR),
     CONSTRAINT CA_ICR_AUTO_ENTR_TC0 UNIQUE (OBJ_ID)
)
/
