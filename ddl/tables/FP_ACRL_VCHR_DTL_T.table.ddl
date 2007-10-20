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
CREATE TABLE "FP_ACRL_VCHR_DTL_T"    (
	"DOC_HDR_ID" VARCHAR(9) CONSTRAINT FP_ACRL_VCHR_DTL_TN1 NOT NULL ENABLE, 
	"FDOC_LINE_NBR" NUMBER(7,0) CONSTRAINT FP_ACRL_VCHR_DTL_TN2 NOT NULL ENABLE, 
	"FIN_COA_CD" VARCHAR(2), 
	"ACCOUNT_NBR" VARCHAR(7), 
	"FDOC_POST_YR" NUMBER(4,0), 
	"FIN_OBJECT_CD" VARCHAR(4), 
	"FIN_OBJ_TYP_CD" VARCHAR(2), 
	"FDOC_LINE_AMT" NUMBER(19,2), 
	"FDOC_LINE_DBCR_CD" VARCHAR(1), 
	"SUB_ACCT_NBR" VARCHAR(5), 
	"FIN_SUB_OBJ_CD" VARCHAR(3), 
	"PROJECT_CD" VARCHAR(10), 
	"ORG_REFERENCE_ID" VARCHAR(8), 
	"FS_REF_ORIGIN_CD" VARCHAR(2), 
	"DOC_HDR_REF_ID" NUMBER(14,0), 
	"FDOC_REF_TYP_CD" VARCHAR(4), 
	"FDOC_OVERRIDE_IND" VARCHAR(1), 
	"OBJ_ID" VARCHAR(36) DEFAULT SYS_GUID() CONSTRAINT FP_ACRL_VCHR_DTL_TN3 NOT NULL ENABLE, 
	"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT FP_ACRL_VCHR_DTL_TN4 NOT NULL ENABLE, 
	"EXT_REF_SYS_ID" VARCHAR(50), 
	 CONSTRAINT "FP_ACRL_VCHR_DTL_TP1" PRIMARY KEY ("DOC_HDR_ID", "FDOC_LINE_NBR"));
