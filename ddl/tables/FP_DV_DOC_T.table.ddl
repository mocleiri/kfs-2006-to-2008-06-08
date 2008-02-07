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
CREATE TABLE FP_DV_DOC_T(
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT FP_DV_DOC_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT FP_DV_DOC_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT FP_DV_DOC_TN3 NOT NULL,
        FDOC_NXT_LINE_NBR              NUMBER(7),
        FDOC_NXT_REG_NBR               NUMBER(7),
        DV_CNTCT_PRSN_NM               VARCHAR2(40),
        DV_CNTCT_PHN_NBR               VARCHAR2(10),
        DV_CNTCT_EMAIL_ID              VARCHAR2(50),
        FDOC_POST_YR                   NUMBER(4),
        FDOC_POST_PRD_CD               VARCHAR2(2),
        DV_DUE_DT                      DATE,
        DV_ATTCH_IND                   CHAR(1),
        DV_SPCL_HANDLG_IND             CHAR(1),
        DV_CHK_TOT_AMT                 NUMBER(19, 2),
        DV_FRGN_CRNCY_IND              CHAR(1),
        DV_DOC_LOC_CD                  VARCHAR2(2),
        DV_CHK_STUB_TXT                VARCHAR2(1400),
        DV_CHKSTUBOVFL_IND             CHAR(1),
        CAMPUS_CD                      VARCHAR2(2),
        DV_PAYEE_TXCTRL_CD             VARCHAR2(1),
        DV_PAYEE_CHG_IND               CHAR(1),
        DV_CHK_NBR                     VARCHAR2(6),
        DV_CHK_DT                      DATE,
        DV_W9_CMPLT_IND                CHAR(1),
        DV_PMT_MTHD_CD                 VARCHAR2(1),
        DV_PAYEE_TYP_CD                VARCHAR2(1),
        DV_EXTRT_DT                    DATE, 
        DV_PD_DT                       DATE, 
        DV_CNCL_DT                     DATE, 
     CONSTRAINT FP_DV_DOC_TP1 PRIMARY KEY (
        FDOC_NBR),
     CONSTRAINT FP_DV_DOC_TC0 UNIQUE (OBJ_ID)
)
/
