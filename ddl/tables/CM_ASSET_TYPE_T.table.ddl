CREATE TABLE CM_ASSET_TYPE_T(
        CPTLAST_TYP_CD                 VARCHAR2(7) CONSTRAINT CM_ASSET_TYPE_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT CM_ASSET_TYPE_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT CM_ASSET_TYPE_TN3 NOT NULL,
        CPTLAST_TYP_DESC               VARCHAR2(70),
        CPTLAST_DEPRLF_LMT             NUMBER(5),
        CPTLAST_MOVING_CD              VARCHAR2(1),
        CPTLAST_RQDBLDG_CD             VARCHAR2(1),
        ROW_ACTV_IND                   VARCHAR2(1) CONSTRAINT CM_ASSET_TYPE_TN4 NOT NULL,
     CONSTRAINT CM_ASSET_TYPE_TP1 PRIMARY KEY (
        CPTLAST_TYP_CD),
     CONSTRAINT CM_ASSET_TYPE_TC0 UNIQUE (OBJ_ID)
)
/