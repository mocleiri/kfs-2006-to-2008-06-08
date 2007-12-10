CREATE TABLE CM_MULT_AST_HDR_T(
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT CM_MULT_AST_HDR_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT CM_MULT_AST_HDR_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT CM_MULT_AST_HDR_TN3 NOT NULL,
        STRT_CPTLAST_NBR               NUMBER(12),
        NEW_AST_CRTE_CNT               NUMBER(7),
        AST_REP_UNVL_ID                VARCHAR2(10),
        ORG_OWNER_COA_CD               VARCHAR2(2),
        ORG_OWNER_ACCT_NBR             VARCHAR2(7),
        CG_AGENCY_NBR                  VARCHAR2(5),
        NXT_AST_PMT_LN_NBR             NUMBER(5),
     CONSTRAINT CM_MULT_AST_HDR_TP1 PRIMARY KEY (
        FDOC_NBR),
     CONSTRAINT CM_MULT_AST_HDR_TC0 UNIQUE (OBJ_ID)
)
/
