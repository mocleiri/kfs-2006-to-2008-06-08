CREATE TABLE CM_CPTLAST_OBJ_T(
        UNIV_FISCAL_YR                 NUMBER(4) CONSTRAINT CM_CPTLAST_OBJ_TN1 NOT NULL,
        FIN_COA_CD                     VARCHAR2(2) CONSTRAINT CM_CPTLAST_OBJ_TN2 NOT NULL,
        FIN_OBJ_SUB_TYPE_CD            VARCHAR2(2) CONSTRAINT CM_CPTLAST_OBJ_TN3 NOT NULL,
        OBJ_ID                         VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT CM_CPTLAST_OBJ_TN4 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT CM_CPTLAST_OBJ_TN5 NOT NULL,
        CPTLZTN_FOBJ_CD                VARCHAR2(4),
        ACCUM_DEPR_FOBJ_CD             VARCHAR2(4),
        DEPR_EXP_FOBJ_CD               VARCHAR2(4),
        ROW_ACTV_IND                   VARCHAR2(1) CONSTRAINT CM_CPTLAST_OBJ_TN6 NOT NULL, 
     CONSTRAINT CM_CPTLAST_OBJ_TP1 PRIMARY KEY (
        UNIV_FISCAL_YR,
        FIN_COA_CD,
        FIN_OBJ_SUB_TYPE_CD),
     CONSTRAINT CM_CPTLAST_OBJ_TC0 UNIQUE (OBJ_ID)
)
/
