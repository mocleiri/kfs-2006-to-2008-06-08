CREATE TABLE AR_NON_APLD_DIST_T(
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT AR_NON_APLD_DIST_TN1 NOT NULL,
        AR_PD_APLD_ITM_NBR             NUMBER(7) CONSTRAINT AR_NON_APLD_DIST_TN2 NOT NULL,
        FDOC_REF_NBR                   VARCHAR2(14) CONSTRAINT AR_NON_APLD_DIST_TN3 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT AR_NON_APLD_DIST_TN4 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT AR_NON_APLD_DIST_TN5 NOT NULL,
        UNIV_FISCAL_YR                 NUMBER(4),
        UNIV_FISCAL_PRD_CD             VARCHAR2(2),
        FDOC_LINE_AMT                  NUMBER(19, 2),
     CONSTRAINT AR_NON_APLD_DIST_TP1 PRIMARY KEY (
        FDOC_NBR,
        AR_PD_APLD_ITM_NBR,
        FDOC_REF_NBR),
     CONSTRAINT AR_NON_APLD_DIST_TC0 UNIQUE (OBJ_ID)
)
/