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
CREATE INDEX CA_ICR_AUTO_ENTR_TI2 ON CA_ICR_AUTO_ENTR_T(
        FIN_COA_CD,
        ACCOUNT_NBR )
/
CREATE INDEX CA_ICR_AUTO_ENTR_TI3 ON CA_ICR_AUTO_ENTR_T(
        FIN_BALANCE_TYP_CD )
/
CREATE INDEX CA_ICR_AUTO_ENTR_TI4 ON CA_ICR_AUTO_ENTR_T(
        UNIV_FISCAL_YR )
/