CREATE TABLE CA_ACCT_SF_T(
        ACCT_SF_CD                     VARCHAR2(1) CONSTRAINT CA_ACCT_SF_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT CA_ACCT_SF_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT CA_ACCT_SF_TN3 NOT NULL,
        ACCT_SF_NM                     VARCHAR2(40),
        ROW_ACTV_IND                   VARCHAR2(1) CONSTRAINT CA_ACCT_SF_TN4 NOT NULL,
     CONSTRAINT CA_ACCT_SF_TP1 PRIMARY KEY (
        ACCT_SF_CD),
     CONSTRAINT CA_ACCT_SF_TC0 UNIQUE (OBJ_ID)
)
/
