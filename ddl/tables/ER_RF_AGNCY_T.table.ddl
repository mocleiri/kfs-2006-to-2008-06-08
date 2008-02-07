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
CREATE TABLE ER_RF_AGNCY_T(
        RDOC_NBR                       VARCHAR2(14) CONSTRAINT ER_RF_AGNCY_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT ER_RF_AGNCY_TN2 NOT NULL,
        VER_NBR                        NUMBER(8,0) DEFAULT 1 CONSTRAINT ER_RF_AGNCY_TN3 NOT NULL,
        AGNCY_ADDR_DESC                VARCHAR2(500),
        AGNCY_CNTCT_NM                 VARCHAR2(50),
        AGNCY_CNTRY_CD                 VARCHAR2(2),
        AGNCY_CTY_NM                   VARCHAR2(30),
        AGNCY_DSK_ACMPY_IND            VARCHAR2(1),
        AGNCY_ELCTRNC_SUBM_IND         VARCHAR2(1),
        AGNCY_LN1_ADDR                 VARCHAR2(40),
        AGNCY_LN2_ADDR                 VARCHAR2(40),
        AGNCY_LN3_ADDR                 VARCHAR2(40),
        AGNCY_LN4_ADDR                 VARCHAR2(40),
        AGNCY_LN5_ADDR                 VARCHAR2(40),
        AGNCY_LN6_ADDR                 VARCHAR2(40),
        AGNCY_LN7_ADDR                 VARCHAR2(40),
        AGNCY_LN8_ADDR                 VARCHAR2(40),
        AGNCY_NBR                      VARCHAR2(5),
        AGNCY_SHIP_INSTRC_DESC         VARCHAR2(1000),
        AGNCY_ST_CD                    VARCHAR2(2),
        AGNCY_ZIP_CD                   VARCHAR2(10),
        RF_DUE_DT_TYP_CD               VARCHAR2(2),
        RF_DUE_DT                      DATE,
        RF_DUE_TM                      VARCHAR2(15),
        RF_REQ_CPY_TXT                 VARCHAR2(20),
        RF_SUBMT_DT                    DATE,
     CONSTRAINT ER_RF_AGNCY_TP1 PRIMARY KEY (
        RDOC_NBR),
        CONSTRAINT ER_RF_AGNCY_TC0 UNIQUE (OBJ_ID)
)
/

