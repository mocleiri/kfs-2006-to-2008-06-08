CREATE TABLE CA_ACCTG_CTGRY_T(
        ACCTG_CTGRY_CD                 VARCHAR2(2) CONSTRAINT CA_ACCTG_CTGRY_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT CA_ACCTG_CTGRY_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT CA_ACCTG_CTGRY_TN3 NOT NULL,
        ACCTG_CTGRY_DESC               VARCHAR2(40),
        ACCTG_CTGRY_SHRT_NM            VARCHAR2(12),
        FIN_REPORT_SORT_CD             VARCHAR2(2),
        ROW_ACTV_IND                   VARCHAR2(1) CONSTRAINT CA_ACCTG_CTGRY_TN4 NOT NULL,
     CONSTRAINT CA_ACCTG_CTGRY_TP1 PRIMARY KEY (
        ACCTG_CTGRY_CD),
     CONSTRAINT CA_ACCTG_CTGRY_TC0 UNIQUE (OBJ_ID)
)
/
