CREATE TABLE CM_PEND_AST_UPD_T(
        FDOC_TYP_CD                    VARCHAR2(4) CONSTRAINT CM_PEND_AST_UPD_TN1 NOT NULL,
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT CM_PEND_AST_UPD_TN2 NOT NULL,
        CPTLAST_NBR                    NUMBER(12) CONSTRAINT CM_PEND_AST_UPD_TN3 NOT NULL,
        LINE_ITEM_NUMBER               NUMBER(12) CONSTRAINT CM_PEND_AST_UPD_TN4 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT CM_PEND_AST_UPD_TN5 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT CM_PEND_AST_UPD_TN6 NOT NULL,
     CONSTRAINT CM_PEND_AST_UPD_TP1 PRIMARY KEY (
        FDOC_TYP_CD,
        FDOC_NBR,
        CPTLAST_NBR,
        LINE_ITEM_NUMBER),
     CONSTRAINT CM_PEND_AST_UPD_TC0 UNIQUE (OBJ_ID)
)
/
