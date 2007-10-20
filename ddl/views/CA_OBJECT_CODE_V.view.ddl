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
CREATE VIEW CA_OBJECT_CODE_V as(
SELECT
        C.UNIV_FISCAL_YR,
        FIN_COA_CD,
        FIN_OBJECT_CD,
        FIN_OBJ_CD_NM,
        FIN_OBJ_CD_SHRT_NM,
        FIN_OBJ_LEVEL_CD,
        RPTS_TO_FIN_COA_CD,
        RPTS_TO_FIN_OBJ_CD ,
        FIN_OBJ_TYP_CD,
        FIN_OBJ_SUB_TYP_CD,
        HIST_FIN_OBJECT_CD,
        FIN_OBJ_ACTIVE_CD,
        FOBJ_BDGT_AGGR_CD,
        FOBJ_MNXFR_ELIM_CD,
        FIN_FED_FUNDED_CD,
        NXT_YR_FIN_OBJ_CD  
FROM CA_OBJECT_CODE_T C, SH_UNIV_DATE_T S WHERE C.UNIV_FISCAL_YR = S.UNIV_FISCAL_YR AND UNIV_DT = TRUNC(SYSDATE)
)
/