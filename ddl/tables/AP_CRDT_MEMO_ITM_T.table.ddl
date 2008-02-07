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
CREATE TABLE AP_CRDT_MEMO_ITM_T(
        CRDT_MEMO_ITM_ID               NUMBER(10) CONSTRAINT AP_CRDT_MEMO_ITM_TN1 NOT NULL, 
        OBJ_ID                         VARCHAR2(36) CONSTRAINT AP_CRDT_MEMO_ITM_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT AP_CRDT_MEMO_ITM_TN3 NOT NULL,
        CRDT_MEMO_ID                   NUMBER(9) CONSTRAINT AP_CRDT_MEMO_ITM_TN4 NOT NULL,
        ITM_LN_NBR                     NUMBER(4),
        ITM_TYP_CD                     VARCHAR2(4) CONSTRAINT AP_CRDT_MEMO_ITM_TN5 NOT NULL,
        ITM_CRDT_QTY                   NUMBER(11,2),
        ITM_UNT_PRC                    NUMBER(19,4),
        ITM_EXTND_PRC                  NUMBER(19,2),
        ITM_ASND_TO_TRADE_IN_IND       VARCHAR2(1),
        ITM_DESC                       VARCHAR2(4000), 
        PO_INV_TOT_QTY                 NUMBER(11,2),
        PO_UNT_PRC                     NUMBER(19,4),
        PO_EXTND_PRC                   NUMBER(19,2),
        PMT_RQST_INV_TOT_QTY           NUMBER(11,2),
        PMT_RQST_UNT_PRC               NUMBER(19,4),
        PMT_RQST_EXTND_PRC             NUMBER(19,2),
     CONSTRAINT AP_CRDT_MEMO_ITM_TP1 PRIMARY KEY (
        CRDT_MEMO_ITM_ID),
     CONSTRAINT AP_CRDT_MEMO_ITM_TC0 UNIQUE (OBJ_ID)
)
/
