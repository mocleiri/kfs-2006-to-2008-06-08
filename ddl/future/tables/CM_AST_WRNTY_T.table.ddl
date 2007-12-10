CREATE TABLE CM_AST_WRNTY_T(
        CPTLAST_NBR                    NUMBER(12) CONSTRAINT CM_AST_WRNTY_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT CM_AST_WRNTY_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT CM_AST_WRNTY_TN3 NOT NULL,
        AST_WRNTY_CNTCT_NM             VARCHAR2(40),
        AST_WRNTYPHN_NBR               VARCHAR2(13),
        AST_WRNTY_BEG_DT               DATE,
        AST_WRNTY_END_DT               DATE,
        AST_WRNTY_NBR                  VARCHAR2(25),
        AST_WRNTY_PO_NBR               VARCHAR2(9),
        AST_WRNTY_TXT                  VARCHAR2(40),
     CONSTRAINT CM_AST_WRNTY_TP1 PRIMARY KEY (
        CPTLAST_NBR),
     CONSTRAINT CM_AST_WRNTY_TC0 UNIQUE (OBJ_ID)
)
/
