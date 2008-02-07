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
CREATE TABLE GL_ID_BILL_T(
        UNIV_FISCAL_PRD_CD             VARCHAR2(2) CONSTRAINT GL_ID_BILL_TN1 NOT NULL,
        UNIV_FISCAL_YR                 NUMBER(4) CONSTRAINT GL_ID_BILL_TN2 NOT NULL,
        CREATE_DT                      DATE CONSTRAINT GL_ID_BILL_TN3 NOT NULL,
        CREATE_SEQ                     VARCHAR2(8) CONSTRAINT GL_ID_BILL_TN4 NOT NULL,
        FIN_COA_CD                     VARCHAR2(2) CONSTRAINT GL_ID_BILL_TN5 NOT NULL,
        ACCOUNT_NBR                    VARCHAR2(7) CONSTRAINT GL_ID_BILL_TN6 NOT NULL,
        SUB_ACCT_NBR                   VARCHAR2(5) CONSTRAINT GL_ID_BILL_TN7 NOT NULL,
        FIN_OBJECT_CD                  VARCHAR2(4) CONSTRAINT GL_ID_BILL_TN8 NOT NULL,
        FIN_SUB_OBJ_CD                 VARCHAR2(3) CONSTRAINT GL_ID_BILL_TN9 NOT NULL,
        FIN_BALANCE_TYP_CD             VARCHAR2(2) CONSTRAINT GL_ID_BILL_TN10 NOT NULL,
        FIN_OBJ_TYP_CD                 VARCHAR2(2) CONSTRAINT GL_ID_BILL_TN11 NOT NULL,
        FDOC_IDBIL_SEQ_NBR             VARCHAR2(2) CONSTRAINT GL_ID_BILL_TN12 NOT NULL,
        FDOC_TYP_CD                    VARCHAR2(4) CONSTRAINT GL_ID_BILL_TN13 NOT NULL,
        FS_ORIGIN_CD                   VARCHAR2(2) CONSTRAINT GL_ID_BILL_TN14 NOT NULL,
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT GL_ID_BILL_TN15 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT GL_ID_BILL_TN16 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT GL_ID_BILL_TN17 NOT NULL,
        FDOC_IDBIL_ITM_AMT             NUMBER(19,2) CONSTRAINT GL_ID_BILL_TN18 NOT NULL,
        FDOC_IDBIL_NTE_TXT             VARCHAR2(120) CONSTRAINT GL_ID_BILL_TN19 NOT NULL,
     CONSTRAINT GL_ID_BILL_TP1 PRIMARY KEY (
        UNIV_FISCAL_PRD_CD,
        UNIV_FISCAL_YR,
        CREATE_DT,
        CREATE_SEQ,
        FIN_COA_CD,
        ACCOUNT_NBR,
        SUB_ACCT_NBR,
        FIN_OBJECT_CD,
        FIN_SUB_OBJ_CD,
        FIN_BALANCE_TYP_CD,
        FIN_OBJ_TYP_CD,  
        FDOC_IDBIL_SEQ_NBR,
        FDOC_TYP_CD,
        FS_ORIGIN_CD,
        FDOC_NBR),
     CONSTRAINT GL_ID_BILL_TC0 UNIQUE (OBJ_ID)
)
/
