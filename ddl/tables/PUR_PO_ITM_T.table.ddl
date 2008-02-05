/*
 * Copyright 2006-2007 The Kuali Foundation.
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
CREATE TABLE PUR_PO_ITM_T(
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT PUR_PO_ITM_TN1 NOT NULL,
        PO_ITM_ID                      NUMBER(10) CONSTRAINT PUR_PO_ITM_TN2 NOT NULL, 
        OBJ_ID                         VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT PUR_PO_ITM_TN3 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT PUR_PO_ITM_TN4 NOT NULL,
        ITM_LN_NBR                     NUMBER(3),
        ITM_TYP_CD                     VARCHAR2(4),
        CPTL_AST_TRN_TYP_CD            VARCHAR2(4),
        ITM_UOM_CD                     VARCHAR2(4),
        ITM_ORD_QTY                    NUMBER(11,2),
        ITM_INV_TOT_QTY                NUMBER(11,2),
        ITM_INV_TOT_AMT                NUMBER(19,2),
        ITM_CATLG_NBR                  VARCHAR2(30),
        ITM_RCVD_TOT_QTY               NUMBER(11,2),
        ITM_DESC                       VARCHAR2(4000),
        ITM_CPTL_AST_NTE_TXT           VARCHAR2(800),
        ITM_UNIT_PRC                   NUMBER(19,4),
        ITM_OSTND_ENC_QTY              NUMBER(11,2),
        ITM_OSTND_ENC_AMT              NUMBER(19,2),
        REQS_LN_ID                     VARCHAR2(64),
        ITM_AUX_PART_ID                VARCHAR2(300),
        ITM_ACTV_IND                   VARCHAR2(1),
        PO_CMDTY_CD                    VARCHAR2(11),
        EXT_ORG_B2B_PROD_REF_NBR       VARCHAR2(19),
        EXT_ORG_B2B_PROD_TYP_NM        VARCHAR2(15),
        ITM_ASND_TO_TRADE_IN_IND       VARCHAR2(1),
        ITM_DMGED_TOT_QTY              NUMBER(11,2),
     CONSTRAINT PUR_PO_ITM_TP1 PRIMARY KEY (
        FDOC_NBR,
        PO_ITM_ID),
     CONSTRAINT PUR_PO_ITM_TC0 UNIQUE (OBJ_ID)
)
/
