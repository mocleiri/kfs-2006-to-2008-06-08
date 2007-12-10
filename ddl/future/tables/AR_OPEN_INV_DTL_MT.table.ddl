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
DECLARE
  obj_count INTEGER;

BEGIN
        /* drop any existing table */
        SELECT count(*) into obj_count
        FROM user_tables
        WHERE table_name = 'AR_OPEN_INV_DTL_MT';

        IF obj_count > 0 THEN
                EXECUTE IMMEDIATE('drop table AR_OPEN_INV_DTL_MT');
        END IF;
END;
/

/* create the table */
CREATE TABLE AR_OPEN_INV_DTL_MT(
        SESID                          VARCHAR2(36) CONSTRAINT AR_OPEN_INV_DTL_MTN1 NOT NULL,
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT AR_OPEN_INV_DTL_MTN2 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT AR_OPEN_INV_DTL_MTN3 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT AR_OPEN_INV_DTL_MTN4 NOT NULL,
        FDOC_TYP_CD                    VARCHAR2(4),
        AR_INV_ITM_NBR                 NUMBER(7),
        FIN_COA_CD                     VARCHAR2(2),
        ACCOUNT_NBR                    VARCHAR2(7),
        AR_BILL_BY_COA_CD              VARCHAR2(2),
        AR_BILL_BY_ORG_CD              VARCHAR2(4),
        AR_BILLING_DT                  DATE,
        AR_ENTRY_DT                    DATE,
        AR_INV_DUE_DT                  DATE,
        AR_INV_DESC                    VARCHAR2(40),
        AR_WRITEOFF_IND                VARCHAR2(1),
        CUST_NBR                       VARCHAR2(9),
        PRCS_FIN_COA_CD                VARCHAR2(2),
        PRCS_ORG_CD                    VARCHAR2(4),
        CUST_NM                        VARCHAR2(60),
        AR_INV_ITM_TOT_AMT             NUMBER(19, 2),
        AR_INV_ITMAPLD_AMT             NUMBER(19, 2),
     CONSTRAINT AR_OPEN_INV_DTL_MTP1 PRIMARY KEY (
        SESID,
        FDOC_NBR),
     CONSTRAINT AR_OPEN_INV_DTL_MTC0 UNIQUE (OBJ_ID)
)
/


