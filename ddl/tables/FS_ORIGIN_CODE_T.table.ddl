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
CREATE TABLE FS_ORIGIN_CODE_T(
        FS_ORIGIN_CD                   VARCHAR2(2) CONSTRAINT FS_ORIGIN_CODE_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT FS_ORIGIN_CODE_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT FS_ORIGIN_CODE_TN3 NOT NULL,
        NEXT_FDOC_NBR                  VARCHAR2(9),
        FS_SERVER_NM                   VARCHAR2(20),
        FS_DATABASE_NM                 VARCHAR2(15),
        FS_DATABASE_DESC               VARCHAR2(40),
        NEXT_CPTLAST_NBR               NUMBER(12),
        NEXT_NONCA_NBR                 NUMBER(12),
        NEXT_CG_AGENCY_NBR             VARCHAR2(5),
        NEXT_CG_SUBCNR_NBR             VARCHAR2(5),
        NXT_DV_PAYEEID_NBR             NUMBER(12),
        NXT_FDOC_ATT_ID                NUMBER(12),
        AR_NEXT_CUST_NBR               NUMBER(12),
        FDOC_NEXT_ITEM_ID              NUMBER(12),
     CONSTRAINT FS_ORIGIN_CODE_TP1 PRIMARY KEY (
        FS_ORIGIN_CD),
     CONSTRAINT FS_ORIGIN_CODE_TC0 UNIQUE (OBJ_ID)
)
/
