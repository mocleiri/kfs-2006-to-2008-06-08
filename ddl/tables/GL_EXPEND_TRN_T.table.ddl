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
CREATE TABLE GL_EXPEND_TRN_T(
        UNIV_FISCAL_YR                 VARCHAR2(4) CONSTRAINT GL_EXPEND_TRN_TN1 NOT NULL,
        FIN_COA_CD                     VARCHAR2(2) CONSTRAINT GL_EXPEND_TRN_TN2 NOT NULL,
        ACCOUNT_NBR                    VARCHAR2(7) CONSTRAINT GL_EXPEND_TRN_TN3 NOT NULL,
        SUB_ACCT_NBR                   VARCHAR2(5) CONSTRAINT GL_EXPEND_TRN_TN4 NOT NULL,
        FIN_OBJECT_CD                  VARCHAR2(4) CONSTRAINT GL_EXPEND_TRN_TN5 NOT NULL,
        FIN_SUB_OBJ_CD                 VARCHAR2(3) CONSTRAINT GL_EXPEND_TRN_TN6 NOT NULL,
        FIN_BALANCE_TYP_CD             VARCHAR2(2) CONSTRAINT GL_EXPEND_TRN_TN7 NOT NULL,
        FIN_OBJ_TYP_CD                 VARCHAR2(2) CONSTRAINT GL_EXPEND_TRN_TN8 NOT NULL,
        UNIV_FISCAL_PRD_CD             VARCHAR2(2) CONSTRAINT GL_EXPEND_TRN_TN9 NOT NULL,
        PROJECT_CD                     VARCHAR2(10) CONSTRAINT GL_EXPEND_TRN_TN10 NOT NULL,
        ORG_REFERENCE_ID               VARCHAR2(8) CONSTRAINT GL_EXPEND_TRN_TN11 NOT NULL,
        ACCT_OBJ_DCST_AMT              NUMBER(19, 2),
     CONSTRAINT GL_EXPEND_TRN_TP1 PRIMARY KEY (
        UNIV_FISCAL_YR,
        FIN_COA_CD,
        ACCOUNT_NBR,
        SUB_ACCT_NBR,
        FIN_OBJECT_CD,
        FIN_SUB_OBJ_CD,
        FIN_BALANCE_TYP_CD,
        FIN_OBJ_TYP_CD,
        UNIV_FISCAL_PRD_CD,
        PROJECT_CD,
        ORG_REFERENCE_ID)
)
/
