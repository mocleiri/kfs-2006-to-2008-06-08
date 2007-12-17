CREATE TABLE AR_DOC_HDR_T(
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT AR_DOC_HDR_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT AR_DOC_HDR_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT AR_DOC_HDR_TN3 NOT NULL,
        CUST_NBR                       VARCHAR2(9),
        PRCS_FIN_COA_CD                VARCHAR2(2),
        PRCS_ORG_CD                    VARCHAR2(4),
        AR_ENTRY_DT                    DATE,
        FDOC_EXPLAIN_TXT               VARCHAR2(400),
     CONSTRAINT AR_DOC_HDR_TP1 PRIMARY KEY (
        FDOC_NBR),
     CONSTRAINT AR_DOC_HDR_TC0 UNIQUE (OBJ_ID)
)
/

