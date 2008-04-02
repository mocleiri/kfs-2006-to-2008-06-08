CREATE TABLE PUR_PO_CM_LOC_T(
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT PUR_PO_CM_LOC_TN1 NOT NULL,
        CM_SYS_NBR                     NUMBER(10) CONSTRAINT PUR_PO_CM_LOC_TN2 NOT NULL,
        CM_LOC_NBR                     NUMBER(10) CONSTRAINT PUR_PO_CM_LOC_TN3 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT PUR_PO_CM_LOC_TN4 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT PUR_PO_CM_LOC_TN5 NOT NULL,
        CM_ITM_QTY                     NUMBER(11,2),
        CAMPUS_CD                      VARCHAR2(2),
        CM_OFF_CMP_IND                 VARCHAR2(1),
        BLDG_CD                        VARCHAR2(10),
        BLDG_ROOM_NBR                  VARCHAR2(8),
        CM_LN1_ADDR                    VARCHAR2(45),
        CM_CTY_NM                      VARCHAR2(45),
        CM_ST_CD                       VARCHAR2(2),
        CM_PSTL_CD                     VARCHAR2(20),
        CM_CNTRY_CD                    VARCHAR2(2),
     CONSTRAINT PUR_PO_CM_LOC_TP1 PRIMARY KEY (
        FDOC_NBR,
        CM_SYS_NBR,
        CM_LOC_NBR),
     CONSTRAINT PUR_PO_CM_LOC_TC0 UNIQUE (OBJ_ID)
)
/
