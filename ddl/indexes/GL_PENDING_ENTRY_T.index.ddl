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
CREATE INDEX GL_PENDING_ENTRY_TI2 ON GL_PENDING_ENTRY_T(
        UNIV_FISCAL_YR,
        FIN_COA_CD,
        ACCOUNT_NBR,
        SUB_ACCT_NBR,
        FIN_OBJECT_CD,
        FIN_SUB_OBJ_CD,
        FIN_BALANCE_TYP_CD,
        FIN_OBJ_TYP_CD )
/
CREATE INDEX GL_PENDING_ENTRY_TI3 ON GL_PENDING_ENTRY_T(
        UNIV_FISCAL_YR,
        FIN_COA_CD,
        ACCOUNT_NBR,
        FIN_OBJECT_CD,
        FIN_BALANCE_TYP_CD,
        ACCT_SF_FINOBJ_CD )
/
CREATE INDEX GL_PENDING_ENTRY_TI4 ON GL_PENDING_ENTRY_T(
        FIN_OBJ_TYP_CD )
/
CREATE INDEX GL_PENDING_ENTRY_TI5 ON GL_PENDING_ENTRY_T(
        FS_ORIGIN_CD,
        FDOC_NBR )
/
CREATE INDEX GL_PENDING_ENTRY_TI6 ON GL_PENDING_ENTRY_T(
        UNIV_FISCAL_YR,
        FIN_COA_CD,
        ACCOUNT_NBR,
        FIN_OBJECT_CD )
/
