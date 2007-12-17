CREATE TABLE CM_AST_LOC_T(
        CPTLAST_NBR                    NUMBER(12) CONSTRAINT CM_AST_LOC_TN1 NOT NULL,
        AST_LOC_TYP_CD                 VARCHAR2(2) CONSTRAINT CM_AST_LOC_TN2 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT CM_AST_LOC_TN3 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT CM_AST_LOC_TN4 NOT NULL,
        AST_LOC_CNTCT_NM               VARCHAR2(50),
        AST_LOC_CNTCT_ID               VARCHAR2(10),
        AST_LOC_INST_NM                VARCHAR2(50),
        AST_LOC_PHN_NBR                VARCHAR2(13),
        AST_LOC_STRT_ADDR              VARCHAR2(50),
        AST_LOC_CITY_NM                VARCHAR2(50),
        AST_LOC_STATE_CD               VARCHAR2(2),
        AST_LOC_CNTRY_CD               VARCHAR2(2),
        AST_LOC_ZIP_CD                 VARCHAR2(9),
     CONSTRAINT CM_AST_LOC_TP1 PRIMARY KEY (
        CPTLAST_NBR,
        AST_LOC_TYP_CD),
     CONSTRAINT CM_AST_LOC_TC0 UNIQUE (OBJ_ID)
)
/
