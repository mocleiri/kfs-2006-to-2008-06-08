CREATE TABLE CM_PRTG_DTL_T(
        PO_NUMBER                      VARCHAR2(9) CONSTRAINT CM_PRTG_DTL_TN1 NOT NULL,
        LINE_ITEM_NUMBER               NUMBER(12) CONSTRAINT CM_PRTG_DTL_TN2 NOT NULL,
        CPTLAST_IU_TAG_NBR             VARCHAR2(8) CONSTRAINT CM_PRTG_DTL_TN3 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT CM_PRTG_DTL_TN4 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT CM_PRTG_DTL_TN5 NOT NULL,
        CPTLAST_SERIAL_NBR             VARCHAR2(25),
        CPTLAST_ORGTAG_NBR             VARCHAR2(8),
        CAMPUS_CD                      VARCHAR2(2),
        BLDG_CD                        VARCHAR2(10),
        BLDG_ROOM_NBR                  VARCHAR2(8),
        BLDG_SUB_ROOM_NBR              VARCHAR2(2),
        AST_PRTGTAG_CRT_DT             DATE,
     CONSTRAINT CM_PRTG_DTL_TP1 PRIMARY KEY (
        PO_NUMBER,
        LINE_ITEM_NUMBER,
        CPTLAST_IU_TAG_NBR),
     CONSTRAINT CM_PRTG_DTL_TC0 UNIQUE (OBJ_ID)
)
/
