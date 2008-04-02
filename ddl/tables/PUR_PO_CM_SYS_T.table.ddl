CREATE TABLE PUR_PO_CM_SYS_T(
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT PUR_PO_CM_SYS_TN1 NOT NULL,
        CM_SYS_NBR                     NUMBER(10) CONSTRAINT PUR_PO_CM_SYS_TN2 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT PUR_PO_CM_SYS_TN3 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT PUR_PO_CM_SYS_TN4 NOT NULL,
        CM_SYS_DESC                    VARCHAR2(4000),
        CM_NOTRCVD_CUR_FSCL_YR_IND     VARCHAR2(1),
        CPTLAST_TYP_CD                 VARCHAR2(7),
        CM_MFR_IS_VNDR_IND             VARCHAR2(1),
        CM_MFR_NM                      VARCHAR2(45),
        CM_MDL_DESC                    VARCHAR2(45),
     CONSTRAINT PUR_PO_CM_SYS_TP1 PRIMARY KEY (
        FDOC_NBR,
        CM_SYS_NBR),
     CONSTRAINT PUR_PO_CM_SYS_TC0 UNIQUE (OBJ_ID)
)
/
