CREATE TABLE CM_ROOM_CHANGE_T(
        CAMPUS_CD                      VARCHAR2(2) CONSTRAINT CM_ROOM_CHANGE_TN1 NOT NULL,
        BLDG_CD                        VARCHAR2(10) CONSTRAINT CM_ROOM_CHANGE_TN2 NOT NULL,
        OLD_BLDG_ROOM_NBR              VARCHAR2(8) CONSTRAINT CM_ROOM_CHANGE_TN3 NOT NULL,
        CHG_TRN_REQUEST_DT             DATE CONSTRAINT CM_ROOM_CHANGE_TN4 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT CM_ROOM_CHANGE_TN5 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT CM_ROOM_CHANGE_TN6 NOT NULL,
        OLD_BLDG_SUBRM_NBR             VARCHAR2(2),
        NEW_BLDG_ROOM_NBR              VARCHAR2(8),
        NEW_BLDG_SUBRM_NBR             VARCHAR2(2),
        CHG_TRN_EXECUTE_DT             DATE,
        CHG_TRN_RECORD_CNT             NUMBER(12),
     CONSTRAINT CM_ROOM_CHANGE_TP1 PRIMARY KEY (
        CAMPUS_CD,
        BLDG_CD,
        OLD_BLDG_ROOM_NBR,
        CHG_TRN_REQUEST_DT),
     CONSTRAINT CM_ROOM_CHANGE_TC0 UNIQUE (OBJ_ID)
)
/
