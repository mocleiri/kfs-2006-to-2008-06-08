CREATE TABLE GL_ORG_RVRSN_CTGRY_AMT_T(
        FIN_COA_CD                     VARCHAR2(2) CONSTRAINT GL_ORG_RVRSN_CTGRY_AMT_TN1 NOT NULL,
        ACCOUNT_NBR                    VARCHAR2(7) CONSTRAINT GL_ORG_RVRSN_CTGRY_AMT_TN2 NOT NULL,
        SUB_ACCT_NBR                   VARCHAR2(5) CONSTRAINT GL_ORG_RVRSN_CTGRY_AMT_TN3 NOT NULL,
        ORG_RVRSN_CTGRY_CD             VARCHAR2(8) CONSTRAINT GL_ORG_RVRSN_CTGRY_AMT_TN4 NOT NULL,
        OBJ_ID                         VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT GL_ORG_RVRSN_CTGRY_AMT_TN5 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT GL_ORG_RVRSN_CTGRY_AMT_TN6 NOT NULL, 
        ORG_TOT_ACTL_AMT               NUMBER(19,2),
        ORG_TOT_BDGT_AMT               NUMBER(19,2),
        ORG_TOT_ENCUM_AMT              NUMBER(19,2),
        ORG_TOT_CF_AMT                 NUMBER(19,2),
        ORG_TOT_AVAIL_AMT              NUMBER(19,2),
     CONSTRAINT GL_ORG_RVRSN_CTGRY_AMT_TP1 PRIMARY KEY (
        FIN_COA_CD,
        ACCOUNT_NBR,
        SUB_ACCT_NBR,
        ORG_RVRSN_CTGRY_CD),
     CONSTRAINT GL_ORG_RVRSN_CTGRY_AMT_TC0 UNIQUE (OBJ_ID))
/
