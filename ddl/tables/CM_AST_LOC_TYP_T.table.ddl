CREATE TABLE CM_AST_LOC_TYP_T(
        CPTLAST_LOC_TYP_CD             VARCHAR2(2) CONSTRAINT CM_AST_LOC_TYP_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT CM_AST_LOC_TYP_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT CM_AST_LOC_TYP_TN3 NOT NULL,
        CPTLAST_LOC_TYP_NM             VARCHAR2(30),
        ROW_ACTV_IND                   VARCHAR2(1) CONSTRAINT CM_AST_LOC_TYP_TN4 NOT NULL,
     CONSTRAINT CM_AST_LOC_TYP_TP1 PRIMARY KEY (
        CPTLAST_LOC_TYP_CD),
     CONSTRAINT CM_AST_LOC_TYP_TC0 UNIQUE (OBJ_ID)
)
/