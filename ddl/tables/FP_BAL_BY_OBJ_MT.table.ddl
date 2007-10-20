CREATE TABLE FP_BAL_BY_OBJ_MT(
        SESID                          VARCHAR2(36) CONSTRAINT FP_BAL_BY_OBJ_MTN1 NOT NULL,
        SUB_ACCT_NBR                   VARCHAR2(5) CONSTRAINT FP_BAL_BY_OBJ_MTN2 NOT NULL,
        FIN_OBJECT_CD                  VARCHAR2(4) CONSTRAINT FP_BAL_BY_OBJ_MTN3 NOT NULL,
        OBJ_ID                         VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT FP_BAL_BY_OBJ_MTN4 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT FP_BAL_BY_OBJ_MTN5 NOT NULL,
        CURR_BDLN_BAL_AMT              NUMBER(19, 2),
        ACLN_ACTLS_BAL_AMT             NUMBER(19, 2),
        ACLN_ENCUM_BAL_AMT             NUMBER(19, 2),
        FIN_REPORT_SORT_CD             VARCHAR2(2),
     CONSTRAINT FP_BAL_BY_OBJ_MTP1 PRIMARY KEY (
        SESID,
        SUB_ACCT_NBR,
        FIN_OBJECT_CD),
     CONSTRAINT FP_BAL_BY_OBJ_MTC0 UNIQUE (OBJ_ID)
)
/
