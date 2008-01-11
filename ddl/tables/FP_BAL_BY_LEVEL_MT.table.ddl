CREATE TABLE FP_BAL_BY_LEVEL_MT(
        SESID                          VARCHAR2(36) CONSTRAINT FP_BAL_BY_LEVEL_MTN1 NOT NULL,
        SUB_ACCT_NBR                   VARCHAR2(5) CONSTRAINT FP_BAL_BY_LEVEL_MTN2 NOT NULL,
        FIN_OBJ_LEVEL_CD               VARCHAR2(4) CONSTRAINT FP_BAL_BY_LEVEL_MTN3 NOT NULL,
        FIN_REPORT_SORT_CD             VARCHAR2(2) CONSTRAINT FP_BAL_BY_LEVEL_MTN4 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT FP_BAL_BY_LEVEL_MTN6 NOT NULL,
        CURR_BDLN_BAL_AMT              NUMBER(19, 2),
        ACLN_ACTLS_BAL_AMT             NUMBER(19, 2),
        ACLN_ENCUM_BAL_AMT             NUMBER(19, 2),
        TYP_FIN_REPORT_SORT_CD         VARCHAR2(2),
     CONSTRAINT FP_BAL_BY_LEVEL_MTP1 PRIMARY KEY (
        SESID,
        SUB_ACCT_NBR,
        FIN_OBJ_LEVEL_CD,
        FIN_REPORT_SORT_CD)
)
/