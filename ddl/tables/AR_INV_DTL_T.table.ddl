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
CREATE TABLE AR_INV_DTL_T(
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT AR_INV_DTL_TN1 NOT NULL,
        AR_INV_ITM_NBR                 NUMBER(7) CONSTRAINT AR_INV_DTL_TN2 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT AR_INV_DTL_TN3 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT AR_INV_DTL_TN4 NOT NULL,
        FIN_COA_CD                     VARCHAR2(2),
        ACCOUNT_NBR                    VARCHAR2(7),
        SUB_ACCT_NBR                   VARCHAR2(5),
        FDOC_POST_YR                   NUMBER(4),
        FIN_OBJECT_CD                  VARCHAR2(4),
        FIN_SUB_OBJ_CD                 VARCHAR2(3),
        PROJECT_CD                     VARCHAR2(10),
        ORG_REFERENCE_ID               VARCHAR2(8),
        FIN_AR_OBJ_CD                  VARCHAR2(4),
        FIN_AR_SUB_OBJ_CD              VARCHAR2(3),
        AR_INV_ITM_QTY                 NUMBER,
        AR_INV_ITM_UOM_CD              VARCHAR2(2),
        AR_INVITM_UNIT_PRC             NUMBER(19, 2),
        AR_INV_ITM_TOT_AMT             NUMBER(19, 2),
        AR_INV_ITM_SRVC_DT             DATE,
        AR_INV_ITM_CD                  VARCHAR2(6),
        AR_INV_ITM_DESC                VARCHAR2(800),
        FDOC_OVERRIDE_CD               VARCHAR2(1),
        AR_INV_ITM_TAX_AMT             NUMBER(19, 2),
     CONSTRAINT AR_INV_DTL_TP1 PRIMARY KEY (
        FDOC_NBR,
        AR_INV_ITM_NBR),
     CONSTRAINT AR_INV_DTL_TC0 UNIQUE (OBJ_ID)
)
/
