CREATE TABLE CM_ACQ_TYPE_T(
        CPTLAST_ACQ_TYP_CD             VARCHAR2(1) CONSTRAINT CM_ACQ_TYPE_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT CM_ACQ_TYPE_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT CM_ACQ_TYPE_TN3 NOT NULL,
        CPTLAST_ACQ_TYP_NM             VARCHAR2(40),
        CPTLAST_INC_OBJ_CD             VARCHAR2(4),
        ROW_ACTV_IND                   VARCHAR2(1) CONSTRAINT CM_ACQ_TYPE_TN4 NOT NULL,
     CONSTRAINT CM_ACQ_TYPE_TP1 PRIMARY KEY (
        CPTLAST_ACQ_TYP_CD),
     CONSTRAINT CM_ACQ_TYPE_TC0 UNIQUE (OBJ_ID)
)
/