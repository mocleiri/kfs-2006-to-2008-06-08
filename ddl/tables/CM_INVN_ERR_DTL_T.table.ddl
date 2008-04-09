CREATE TABLE CM_INVN_ERR_DTL_T(
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT CM_INVN_ERR_DTL_TN1 NOT NULL,
        AST_UPLD_ROW_NBR               NUMBER(12) CONSTRAINT CM_INVN_ERR_DTL_TN3 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT CM_INVN_ERR_DTL_TN4 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT CM_INVN_ERR_DTL_TN5 NOT NULL,
        AST_ERRCOR_STAT_CD             VARCHAR2(1),
        AST_COR_UNVL_ID                VARCHAR2(10),
        AST_INVN_COR_TS                DATE,
        CPTLAST_TAG_NBR                VARCHAR2(8),
        AST_UPLD_SCAN_IND              VARCHAR2(1),
        AST_UPLD_SCAN_TS               DATE,
        CAMPUS_CD                      VARCHAR2(2),
        BLDG_CD                        VARCHAR2(10),
        BLDG_ROOM_NBR                  VARCHAR2(8),
        BLDG_SUB_ROOM_NBR              VARCHAR2(2),
        CPTLAST_COND_CD                VARCHAR2(1),
     CONSTRAINT CM_INVN_ERR_DTL_TP1 PRIMARY KEY (
        FDOC_NBR,
        AST_UPLD_ROW_NBR),
     CONSTRAINT CM_INVN_ERR_DTL_TC0 UNIQUE (OBJ_ID)
)
/
