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
CREATE TABLE PUR_BILL_ADDR_T(
        BILL_CMP_CD                    VARCHAR2(2) CONSTRAINT PUR_BILL_ADDR_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT PUR_BILL_ADDR_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT PUR_BILL_ADDR_TN3 NOT NULL,
        BILL_NM                        VARCHAR2(45) CONSTRAINT PUR_BILL_ADDR_TN4 NOT NULL,
        BILL_LN1_ADDR                  VARCHAR2(45) CONSTRAINT PUR_BILL_ADDR_TN5 NOT NULL,
        BILL_LN2_ADDR                  VARCHAR2(45),
        BILL_CTY_NM                    VARCHAR2(45) CONSTRAINT PUR_BILL_ADDR_TN6 NOT NULL,
        BILL_ST_CD                     VARCHAR2(2) CONSTRAINT PUR_BILL_ADDR_TN7 NOT NULL,
        BILL_PSTL_CD                   VARCHAR2(20) CONSTRAINT PUR_BILL_ADDR_TN8 NOT NULL,
        BILL_CNTRY_CD                  VARCHAR2(2) CONSTRAINT PUR_BILL_ADDR_TN9 NOT NULL,
        BILL_PHN_NBR                   VARCHAR2(45) CONSTRAINT PUR_BILL_ADDR_TN10 NOT NULL,
        DOBJ_MAINT_CD_ACTV_IND         VARCHAR2(1) CONSTRAINT PUR_BILL_ADDR_TN11 NOT NULL,
     CONSTRAINT PUR_BILL_ADDR_TP1 PRIMARY KEY (
        BILL_CMP_CD),
     CONSTRAINT PUR_BILL_ADDR_TC0 UNIQUE (OBJ_ID)
)
/
