CREATE TABLE AR_NON_INV_T(
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT AR_NON_INV_TN1 NOT NULL,
        FDOC_LINE_NBR                  NUMBER(7) CONSTRAINT AR_NON_INV_TN2 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT AR_NON_INV_TN3 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT AR_NON_INV_TN4 NOT NULL,
        FDOC_POST_YR                   NUMBER(4),
        FIN_COA_CD                     VARCHAR2(2),
        ACCOUNT_NBR                    VARCHAR2(7),
        SUB_ACCT_NBR                   VARCHAR2(5),
        FIN_OBJECT_CD                  VARCHAR2(4),
        FIN_SUB_OBJ_CD                 VARCHAR2(3),
        PROJECT_CD                     VARCHAR2(10),
        FDOC_LINE_AMT                  NUMBER(19, 2),
        FDOC_OVERRIDE_CD               VARCHAR2(1),
     CONSTRAINT AR_NON_INV_TP1 PRIMARY KEY (
        FDOC_NBR,
        FDOC_LINE_NBR),
     CONSTRAINT AR_NON_INV_TC0 UNIQUE (OBJ_ID)
)
/
