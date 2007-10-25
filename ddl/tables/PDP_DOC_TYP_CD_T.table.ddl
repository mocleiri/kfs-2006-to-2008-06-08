CREATE TABLE PDP_DOC_TYP_CD_T(
        DOC_TYP_ID                     NUMBER(8) CONSTRAINT PDP_DOC_TYP_CD_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) DEFAULT SYS_GUID(),
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT PDP_DOC_TYP_CD_TN3 NOT NULL, 
        DISB_TYP_CD                    VARCHAR2(4),
        FS_ORIGIN_CD                   VARCHAR2(2),
        FDOC_TYP_CD                    VARCHAR2(4),
        LST_UPDT_TS                    DATE CONSTRAINT PDP_DOC_TYP_CD_TN4 NOT NULL,
        LST_UPDT_USR_ID                VARCHAR2(11) CONSTRAINT PDP_DOC_TYP_CD_TN5 NOT NULL,
        TRN_TYP_CD                     VARCHAR2(4) CONSTRAINT PDP_DOC_TYP_CD_TN6 NOT NULL,
     CONSTRAINT PDP_DOC_TYP_CD_TP1 PRIMARY KEY (
        DOC_TYP_ID)
)
/
