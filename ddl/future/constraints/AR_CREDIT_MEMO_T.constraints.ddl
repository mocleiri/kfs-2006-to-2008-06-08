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
ALTER TABLE AR_CREDIT_MEMO_T
ADD (CONSTRAINT AR_CREDIT_MEMO_TR1 FOREIGN KEY (
      FS_ORIGIN_CD,
      FDOC_NBR )
REFERENCES FP_DOC_HEADER_T ( 
      FS_ORIGIN_CD,
      FDOC_NBR ))
ADD (CONSTRAINT AR_CREDIT_MEMO_TR2 FOREIGN KEY (
      FS_ORIGIN_CD,
      FDOC_NBR )
REFERENCES AR_DOC_HDR_T ( 
      FS_ORIGIN_CD,
      FDOC_NBR ))
ADD (CONSTRAINT AR_CREDIT_MEMO_TR3 FOREIGN KEY (
      FS_ORIGIN_CD,
      FDOC_NBR )
REFERENCES AR_NON_APLD_HLDG_T ( 
      FS_REF_ORIGIN_CD,
      FDOC_REF_NBR ))
ADD (CONSTRAINT AR_CREDIT_MEMO_TR4 FOREIGN KEY (
      FIN_COA_CD,
      ACCOUNT_NBR )
REFERENCES CA_ACCOUNT_T ( 
      FIN_COA_CD,
      ACCOUNT_NBR ))
ADD (CONSTRAINT AR_CREDIT_MEMO_TR5 FOREIGN KEY (
      FDOC_POST_YR,
      FIN_COA_CD,
      FIN_OBJECT_CD )
REFERENCES CA_OBJECT_CODE_T ( 
      UNIV_FISCAL_YR,
      FIN_COA_CD,
      FIN_OBJECT_CD ))
/
