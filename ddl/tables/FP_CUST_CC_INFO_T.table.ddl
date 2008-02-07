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
CREATE TABLE FP_CUST_CC_INFO_T(
        CUST_CCRD_NBR                  VARCHAR2(16) CONSTRAINT FP_CUST_CC_INFO_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT FP_CUST_CC_INFO_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT FP_CUST_CC_INFO_TN3 NOT NULL,
        CUST_CCRD_HLDR_NM              VARCHAR2(40),
        CUST_CCRD_ISSR_NM              VARCHAR2(40),
        FDOC_CCRD_TYPE_CD              VARCHAR2(2),
        CUST_CCRD_EXP_DT               DATE,
        CUST_NBR                       VARCHAR2(9),
        FIDOC_CCRD_VNDR_NBR            VARCHAR2(10),
        CUST_CCRD_NTE_TXT              VARCHAR2(2000),
     CONSTRAINT FP_CUST_CC_INFO_TP1 PRIMARY KEY (
        CUST_CCRD_NBR),
     CONSTRAINT FP_CUST_CC_INFO_TC0 UNIQUE (OBJ_ID)
)
/
