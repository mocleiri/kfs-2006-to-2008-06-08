CREATE TABLE CM_CPTLAST_CHG_DTL_T(
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT CM_CPTLAST_CHG_DTL_TN1 NOT NULL,
        CPTLAST_NBR                    NUMBER(12) CONSTRAINT CM_CPTLAST_CHG_DTL_TN2 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT CM_CPTLAST_CHG_DTL_TN3 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT CM_CPTLAST_CHG_DTL_TN4 NOT NULL,
        CAMPUS_CD                      VARCHAR2(2),
        BLDG_CD                        VARCHAR2(10),
        BLDG_ROOM_NBR                  VARCHAR2(8),
        BLDG_SUB_ROOM_NBR              VARCHAR2(2),
     CONSTRAINT CM_CPTLAST_CHG_DTL_TP1 PRIMARY KEY (
        FDOC_NBR, CPTLAST_NBR),
     CONSTRAINT CM_CPTLAST_CHG_DTL_TC0 UNIQUE (OBJ_ID)
)
/
