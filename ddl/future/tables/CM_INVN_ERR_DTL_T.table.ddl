CREATE TABLE CM_INVN_ERR_DTL_T(
        AST_UPLDR_UNVL_ID              VARCHAR2(10) CONSTRAINT CM_INVN_ERR_DTL_TN1 NOT NULL,
        AST_UPLDR_SEQ_NBR              NUMBER(12) CONSTRAINT CM_INVN_ERR_DTL_TN2 NOT NULL,
        AST_UPLD_ROW_NBR               NUMBER(12) CONSTRAINT CM_INVN_ERR_DTL_TN3 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT CM_INVN_ERR_DTL_TN4 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT CM_INVN_ERR_DTL_TN5 NOT NULL,
        AST_ERRCOR_STAT_CD             VARCHAR2(1),
        AST_COR_ROW_TXT                VARCHAR2(125),
        AST_COR_UNVL_ID                VARCHAR2(10),
        AST_INVN_COR_TS                DATE,
     CONSTRAINT CM_INVN_ERR_DTL_TP1 PRIMARY KEY (
        AST_UPLDR_UNVL_ID,
        AST_UPLDR_SEQ_NBR,
        AST_UPLD_ROW_NBR),
     CONSTRAINT CM_INVN_ERR_DTL_TC0 UNIQUE (OBJ_ID)
)
/
