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
CREATE TABLE FP_SALES_TAX_T(
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT FP_SALES_TAX_TN1 NOT NULL,
        FDOC_LN_TYP_CD                 VARCHAR2(1) CONSTRAINT FP_SALES_TAX_TN2 NOT NULL,
        FDOC_LINE_NBR                  NUMBER(7) CONSTRAINT FP_SALES_TAX_TN3 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT FP_SALES_TAX_TN4 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT FP_SALES_TAX_TN5 NOT NULL,
        FIN_COA_CD                     VARCHAR2(2),
        ACCOUNT_NBR                    VARCHAR2(7),
        FDOC_GRS_SALES_AMT             NUMBER(19, 2),
        FDOC_TXBL_SALES_AMT            NUMBER(19, 2),
        FDOC_SALE_DT                   DATE,
     CONSTRAINT FP_SALES_TAX_TP1 PRIMARY KEY (
        FDOC_NBR,
        FDOC_LN_TYP_CD,
        FDOC_LINE_NBR),
     CONSTRAINT FP_SALES_TAX_TC0 UNIQUE (OBJ_ID)
)
/
