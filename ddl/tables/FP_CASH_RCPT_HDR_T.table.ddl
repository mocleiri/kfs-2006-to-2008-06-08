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
CREATE TABLE FP_CASH_RCPT_HDR_T(
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT FP_CASH_RCPT_HDR_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT FP_CASH_RCPT_HDR_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT FP_CASH_RCPT_HDR_TN3 NOT NULL,
        FDOC_NXT_CK_LN_NBR             NUMBER(7),
        FDC_NXTCC_CRLN_NBR             NUMBER(7),
        FDC_NXTCC_CDLN_NBR             NUMBER(7),
        FDOC_NXT_RF_LN_NBR             NUMBER(7),
        FDOC_NXT_AD_LN_NBR             NUMBER(7),
        WRKGRP_NM                      VARCHAR2(70),
        FDOC_DEPOSIT_DT                DATE,
     CONSTRAINT FP_CASH_RCPT_HDR_TP1 PRIMARY KEY (
        FDOC_NBR),
     CONSTRAINT FP_CASH_RCPT_HDR_TC0 UNIQUE (OBJ_ID)
)
/
