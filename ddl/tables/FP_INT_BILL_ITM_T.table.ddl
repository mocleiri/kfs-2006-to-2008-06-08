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
CREATE TABLE FP_INT_BILL_ITM_T(
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT FP_INT_BILL_ITM_TN1 NOT NULL,
        FDOC_ITM_NBR                   NUMBER(7) CONSTRAINT FP_INT_BILL_ITM_TN2 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT FP_INT_BILL_ITM_TN3 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT FP_INT_BILL_ITM_TN4 NOT NULL,
        FDOC_ITM_STCK_NBR              VARCHAR2(9),
        FDOC_ITM_STCK_DESC             VARCHAR2(40),
        FDOC_ITM_SRVC_DT               DATE,
        FDOC_ITM_QTY                   NUMBER(7,2),
        FDOC_ITM_UNIT_AMT              NUMBER(19, 2),
        FDOC_UNIT_MSR_CD               VARCHAR2(2),
     CONSTRAINT FP_INT_BILL_ITM_TP1 PRIMARY KEY (
        FDOC_NBR,
        FDOC_ITM_NBR),
     CONSTRAINT FP_INT_BILL_ITM_TC0 UNIQUE (OBJ_ID)
)
/
