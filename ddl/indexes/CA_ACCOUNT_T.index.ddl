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
CREATE INDEX CA_ACCOUNT_TI2 ON CA_ACCOUNT_T(
        ACCOUNT_NBR,
        FIN_COA_CD )
/
CREATE INDEX CA_ACCOUNT_TI3 ON CA_ACCOUNT_T(
        ACCT_TYP_CD )
/
CREATE INDEX CA_ACCOUNT_TI4 ON CA_ACCOUNT_T(
        FIN_HGH_ED_FUNC_CD )
/
CREATE INDEX CA_ACCOUNT_TI5 ON CA_ACCOUNT_T(
        FIN_COA_CD,
        ORG_CD )
/
CREATE INDEX CA_ACCOUNT_TI6 ON CA_ACCOUNT_T(
        ACCT_RSTRC_STAT_CD )
/
CREATE INDEX CA_ACCOUNT_TI7 ON CA_ACCOUNT_T(
        SUB_FUND_GRP_CD )
/
CREATE INDEX CA_ACCOUNT_TI8 ON CA_ACCOUNT_T(
        ACCT_FSC_OFC_UID )
/
CREATE INDEX CA_ACCOUNT_TI9 ON CA_ACCOUNT_T(
        ACCT_PHYS_CMP_CD )
/
CREATE INDEX CA_ACCOUNT_TI10 ON CA_ACCOUNT_T(
        ACCT_MGR_UNVL_ID )
/
CREATE INDEX CA_ACCOUNT_TI11 ON CA_ACCOUNT_T(
        ACCT_SPVSR_UNVL_ID )
/
