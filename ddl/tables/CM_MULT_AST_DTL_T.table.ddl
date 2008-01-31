CREATE TABLE CM_MULT_AST_DTL_T(
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT CM_MULT_AST_DTL_TN1 NOT NULL,
        CPTLAST_NBR                    NUMBER(12) CONSTRAINT CM_MULT_AST_DTL_TN2 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT CM_MULT_AST_DTL_TN3 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT CM_MULT_AST_DTL_TN4 NOT NULL,
        CAMPUS_CD                      VARCHAR2(2),
        BLDG_CD                        VARCHAR2(10),
        CPTLAST_SERIAL_NBR             VARCHAR2(25),
        BLDG_ROOM_NBR                  VARCHAR2(8),
        BLDG_SUB_ROOM_NBR              VARCHAR2(2),
        CPTLAST_IU_TAG_NBR             VARCHAR2(8),
        ORG_INVN_NM                    VARCHAR2(40),
        ORG_CPTLAST_TYP_ID             VARCHAR2(20),
        AST_OFFCMP_NM                  VARCHAR2(30),
        AST_OFFCMP_ADDR                VARCHAR2(30),
        AST_OFFCMP_CITY_NM             VARCHAR2(25),
        AST_OFFCMP_ST_CD               VARCHAR2(2),
        AST_OFFCMP_ZIP_CD              VARCHAR2(9),
        AST_OFFCMP_CNTRY_CD            VARCHAR2(2),
     CONSTRAINT CM_MULT_AST_DTL_TP1 PRIMARY KEY (
        FDOC_NBR,
        CPTLAST_NBR),
     CONSTRAINT CM_MULT_AST_DTL_TC0 UNIQUE (OBJ_ID)
)
/
