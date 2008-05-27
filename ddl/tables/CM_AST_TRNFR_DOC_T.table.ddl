CREATE TABLE CM_AST_TRNFR_DOC_T(
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT CM_AST_TRNFR_DOC_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT CM_AST_TRNFR_DOC_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT CM_AST_TRNFR_DOC_TN3 NOT NULL,
        AST_REP_UNVL_ID                VARCHAR2(10),
        CAMPUS_CD                      VARCHAR2(2),
        BLDG_CD                        VARCHAR2(10),
        BLDG_ROOM_NBR                  VARCHAR2(8),
        BLDG_SUB_ROOM_NBR              VARCHAR2(2),
        CPTLAST_ORGTAG_NBR             VARCHAR2(8),
        ORG_OWNER_COA_CD               VARCHAR2(2),
        ORG_OWNER_ACCT_NBR             VARCHAR2(7),
        ORG_TXT                        VARCHAR2(255),
        ORG_INVN_NM                    VARCHAR2(40),
        TRNFR_FND_FDOC_NBR             VARCHAR2(14),
        ORG_CD                         VARCHAR2(4),
        AST_OFFCMP_ADDR                VARCHAR2(30),
        AST_OFFCMP_CITY_NM             VARCHAR2(25),
        AST_OFFCMP_ST_CD               VARCHAR2(2),
        AST_OFFCMP_ZIP_CD              VARCHAR2(9),
        AST_INTRDPTSALE_CD             CHAR(1),
        CPTLAST_NBR                    NUMBER(12),
     CONSTRAINT CM_AST_TRNFR_DOC_TP1 PRIMARY KEY (
        FDOC_NBR),
     CONSTRAINT CM_AST_TRNFR_DOC_TC0 UNIQUE (OBJ_ID)
)
/
