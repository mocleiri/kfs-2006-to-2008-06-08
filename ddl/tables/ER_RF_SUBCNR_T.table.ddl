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
CREATE TABLE ER_RF_SUBCNR_T(
        RDOC_NBR                       VARCHAR2(14) CONSTRAINT ER_RF_SUBCNR_TN1 NOT NULL,
        RF_SUBCNR_SEQ_NBR              NUMBER(3,0) CONSTRAINT ER_RF_SUBCNR_TN2 NOT NULL,
        VER_NBR                        NUMBER(8,0) DEFAULT 1 CONSTRAINT ER_RF_SUBCNR_TN4 NOT NULL,
        RF_SBCNR_AMT                   NUMBER(19,4),
        RF_SBCNR_NBR                   VARCHAR2(5),
     CONSTRAINT ER_RF_SUBCNR_TP1 PRIMARY KEY (
        RDOC_NBR,
        RF_SUBCNR_SEQ_NBR)
)
/

