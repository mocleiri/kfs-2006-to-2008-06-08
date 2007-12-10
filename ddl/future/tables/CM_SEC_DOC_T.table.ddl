CREATE TABLE CM_SEC_DOC_T(
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT CM_SEC_DOC_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT CM_SEC_DOC_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT CM_SEC_DOC_TN3 NOT NULL,
        FIN_COA_CD                     VARCHAR2(2),
        ORG_CD                         VARCHAR2(4),
        CPTLAST_SECACTN_CD             VARCHAR2(1),
        CPTLAST_SEC_TYP_CD             VARCHAR2(1),
        AST_SEC_FIN_COA_CD             VARCHAR2(2),
        CPTLAST_SEC_ORG_CD             VARCHAR2(4),
     CONSTRAINT CM_SEC_DOC_TP1 PRIMARY KEY (
        FDOC_NBR),
     CONSTRAINT CM_SEC_DOC_TC0 UNIQUE (OBJ_ID)
)
/
