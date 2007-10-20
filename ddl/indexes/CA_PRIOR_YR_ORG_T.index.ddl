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
CREATE INDEX CA_PRIOR_YR_ORG_TI2 ON CA_PRIOR_YR_ORG_T(
        ORG_TYP_CD )
/
CREATE INDEX CA_PRIOR_YR_ORG_TI3 ON CA_PRIOR_YR_ORG_T(
        RC_CD )
/
CREATE INDEX CA_PRIOR_YR_ORG_TI4 ON CA_PRIOR_YR_ORG_T(
        ORG_MGR_UNVL_ID )
/
CREATE INDEX CA_PRIOR_YR_ORG_TI5 ON CA_PRIOR_YR_ORG_T(
        RPTS_TO_FIN_COA_CD,
        RPTS_TO_ORG_CD )
/
