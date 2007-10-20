CREATE TABLE AR_ORG_ACCTGDFLT_T(
        UNIV_FISCAL_YR                 NUMBER(4) CONSTRAINT AR_ORG_ACCTGDFLT_TN1 NOT NULL,
        FIN_COA_CD                     VARCHAR2(2) CONSTRAINT AR_ORG_ACCTGDFLT_TN2 NOT NULL,
        ORG_CD                         VARCHAR2(4) CONSTRAINT AR_ORG_ACCTGDFLT_TN3 NOT NULL,
        OBJ_ID                         VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT AR_ORG_ACCTGDFLT_TN4 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT AR_ORG_ACCTGDFLT_TN5 NOT NULL,
        ORG_LT_CHRG_OBJ_CD             VARCHAR2(4),
        INV_FIN_COA_CD                 VARCHAR2(2),
        INV_ACCT_NBR                   VARCHAR2(7),
        INV_SUB_ACCT_NBR               VARCHAR2(5),
        INV_FIN_OBJ_CD                 VARCHAR2(4),
        INV_FIN_SUB_OBJ_CD             VARCHAR2(3),
        INV_PROJECT_CD                 VARCHAR2(10),
        INV_ORG_REF_ID                 VARCHAR2(8),
        PMT_FIN_COA_CD                 VARCHAR2(2),
        PMT_ACCT_NBR                   VARCHAR2(7),
        PMT_SUB_ACCT_NBR               VARCHAR2(5),
        PMT_PROJECT_CD                 VARCHAR2(10),
        PMT_ORG_REF_ID                 VARCHAR2(8),
        AR_WRITEOFF_OBJ_CD             VARCHAR2(4),
     CONSTRAINT AR_ORG_ACCTGDFLT_TP1 PRIMARY KEY (
        UNIV_FISCAL_YR,
        FIN_COA_CD,
        ORG_CD),
     CONSTRAINT AR_ORG_ACCTGDFLT_TC0 UNIQUE (OBJ_ID)
)
/
