CREATE TABLE CM_AST_LOC_DOC_T(
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT CM_AST_LOC_DOC_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT CM_AST_LOC_DOC_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT CM_AST_LOC_DOC_TN3 NOT NULL,
     CONSTRAINT CM_AST_LOC_DOC_TP1 PRIMARY KEY (
        FDOC_NBR),
     CONSTRAINT CM_AST_LOC_DOC_TC0 UNIQUE (OBJ_ID)
)
/