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
CREATE VIEW CA_SUB_OBJECT_CD_V as(
SELECT
        C.UNIV_FISCAL_YR,
        FIN_COA_CD,
        ACCOUNT_NBR,
        FIN_OBJECT_CD,
        FIN_SUB_OBJ_CD,
        FIN_SUB_OBJ_CD_NM,
        FIN_SUBOBJ_SHRT_NM,
        FIN_SUBOBJ_ACTV_CD FROM CA_SUB_OBJECT_CD_T C, SH_UNIV_DATE_T S WHERE C.UNIV_FISCAL_YR = S.UNIV_FISCAL_YR AND UNIV_DT = TRUNC(SYSDATE)
)
/