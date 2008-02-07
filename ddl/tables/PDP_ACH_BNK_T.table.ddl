CREATE TABLE PDP_ACH_BNK_T(
        BNK_RTNG_NBR                   VARCHAR2(9) CONSTRAINT PDP_ACH_BNK_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36),
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT PDP_ACH_BNK_TN3 NOT NULL,
        BNK_OFFC_CD                    VARCHAR2(1),
        BNK_SRVC_NBR                   VARCHAR2(9),
        BNK_TYP_CD                     VARCHAR2(1),
        BNK_NEW_RTNG_NBR               VARCHAR2(9),
        BNK_NM                         VARCHAR2(36),
        BNK_STR_ADDR                   VARCHAR2(36),
        BNK_CTY_NM                     VARCHAR2(20),
        BNK_ST_CD                      VARCHAR2(2),
        BNK_ZIP_CD                     VARCHAR2(5),
        BNK_ZIP_EXTNS_CD               VARCHAR2(4),
        BNK_PHN_AREA_CD                VARCHAR2(3),
        BNK_PHN_PRFX_NBR               VARCHAR2(3),
        BNK_PHN_SFX_NBR                VARCHAR2(4),
        BNK_INST_STAT_CD               VARCHAR2(1),
        BNK_DTA_V_CD                   VARCHAR2(1),
     CONSTRAINT PDP_ACH_BNK_TP1 PRIMARY KEY (
        BNK_RTNG_NBR)
)
/
