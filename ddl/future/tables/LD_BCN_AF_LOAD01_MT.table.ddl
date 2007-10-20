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
DECLARE
  obj_count INTEGER;

BEGIN
        /* drop any existing table */
        SELECT count(*) into obj_count
        FROM user_tables
        WHERE table_name = 'LD_BCN_AF_LOAD01_MT';

        IF obj_count > 0 THEN
                EXECUTE IMMEDIATE('drop table LD_BCN_AF_LOAD01_MT');
        END IF;
END;
/

/* create the table */
CREATE TABLE LD_BCN_AF_LOAD01_MT
AS
        (SELECT F.UNIV_FISCAL_YR,
                F.FIN_COA_CD,
                F.ACCOUNT_NBR,
                F.SUB_ACCT_NBR,
                F.FIN_OBJECT_CD,
                F.FIN_SUB_OBJ_CD,
                F.POSITION_NBR,
                F.EMPLID,
                F.OBJ_ID,
                F.VER_NBR,
                F.APPT_FND_DUR_CD,
                F.APPT_RQST_CSF_AMT,
                F.APPT_RQCSF_FTE_QTY,
                F.APPT_RQCSF_TM_PCT,
                F.APPT_TOT_INTND_AMT,
                F.APPT_TOTINTFTE_QTY,
                F.APPT_RQST_AMT,
                F.APPT_RQST_TM_PCT,
                F.APPT_RQST_FTE_QTY,
                F.APPT_RQST_PAY_RT,
                F.APPT_FND_DLT_CD,
                F.APPT_FND_MO,
                F.FIN_COA_CD "NEW_FIN_COA_CD",
                F.ACCOUNT_NBR "NEW_ACCOUNT_NBR"
          FROM  LD_PNDBC_APPTFND_T F
          WHERE 1=2)
/
ALTER TABLE LD_BCN_AF_LOAD01_MT ADD CONSTRAINT LD_BCN_AF_LOAD01_MTP1 PRIMARY KEY (
        UNIV_FISCAL_YR,
        FIN_COA_CD,
        ACCOUNT_NBR,
        SUB_ACCT_NBR,
        FIN_OBJECT_CD,
        FIN_SUB_OBJ_CD,
        POSITION_NBR,
        EMPLID)
/
ALTER TABLE LD_BCN_AF_LOAD01_MT ADD CONSTRAINT LD_BCN_AF_LOAD01_MTC0 UNIQUE (OBJ_ID)
/
ALTER TABLE LD_BCN_AF_LOAD01_MT MODIFY (OBJ_ID DEFAULT SYS_GUID()); 
ALTER TABLE LD_BCN_AF_LOAD01_MT MODIFY (VER_NBR DEFAULT 1);
