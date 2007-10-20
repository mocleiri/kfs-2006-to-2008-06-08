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
CREATE TABLE "FIS_LODALL_HOLDBENE_MT"    (
	"SESID" NUMBER, 
	"UNIV_FISCAL_YR" NUMBER(4,0) CONSTRAINT FIS_LODALL_HOLDBENE_MTN1 NOT NULL ENABLE, 
	"FIN_COA_CD" VARCHAR(2) CONSTRAINT FIS_LODALL_HOLDBENE_MTN2 NOT NULL ENABLE, 
	"FIN_OBJECT_CD" VARCHAR(4) CONSTRAINT FIS_LODALL_HOLDBENE_MTN3 NOT NULL ENABLE, 
	"TOTAL_RATE" NUMBER, 
	"OBJ_ID" VARCHAR(36) DEFAULT SYS_GUID() CONSTRAINT FIS_LODALL_HOLDBENE_MTN4 NOT NULL ENABLE, 
	"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT FIS_LODALL_HOLDBENE_MTN5 NOT NULL ENABLE   );
