CREATE TABLE CM_MULT_AST_DOC_T(
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT CM_MULT_AST_DOC_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT CM_MULT_AST_DOC_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT CM_MULT_AST_DOC_TN3 NOT NULL,
        FDOC_NXT_LINE_NBR              NUMBER(7),
        CPTLAST_ACQ_TYP_CD             VARCHAR2(1),
        CPTLAST_DESC                   VARCHAR2(2000),
        AST_INVN_STAT_CD               VARCHAR2(1),
        CPTLAST_COND_CD                VARCHAR2(1),
        AST_DEPR_MTHD1_CD              VARCHAR2(3),
        AST_DEPR1_BASE_AMT             NUMBER(19, 2),
        CPTLAST_ACQ_SRC_NM             VARCHAR2(40),
        CPTLAST_TYP_CD                 VARCHAR2(7),
        CPTLAST_MFR_NM                 VARCHAR2(40),
        CPTLAST_MFRMDL_NBR             VARCHAR2(25),
        CPTLAST_CPTLZTN_DT             DATE,
        CPTLAST_TOTCST_AMT             NUMBER(19, 2),
        CPTL_AST_LAND_CNTY_NM          VARCHAR2(20),
        CPTL_AST_LAND_ACRG_SZ          NUMBER(7),
        CPTL_AST_LAND_PRCL_NBR         VARCHAR2(30),
     CONSTRAINT CM_MULT_AST_DOC_TP1 PRIMARY KEY (
        FDOC_NBR),
     CONSTRAINT CM_MULT_AST_DOC_TC0 UNIQUE (OBJ_ID)
)
/
