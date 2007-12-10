CREATE TABLE CM_AST_RETIREDOC_T(
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT CM_AST_RETIREDOC_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT CM_AST_RETIREDOC_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT CM_AST_RETIREDOC_TN3 NOT NULL,
        AST_INVN_STAT_CD               VARCHAR2(1),
        AST_RETIR_COA_CD               VARCHAR2(2),
        AST_RETIR_ACCT_NBR             VARCHAR2(7),
        AST_RETIRE_REAS_CD             VARCHAR2(1),
        CPTLAST_RETIRE_DT              DATE,
        AST_RETIR_CNTCT_NM             VARCHAR2(40),
        AST_RETIR_INST_NM              VARCHAR2(40),
        AST_RETIRSTRT_ADDR             VARCHAR2(25),
        AST_RETIR_CITY_NM              VARCHAR2(25),
        AST_RETIR_STATE_CD             VARCHAR2(2),
        AST_RETIR_ZIP_CD               VARCHAR2(9),
        AST_RETIR_CNTRY_CD             VARCHAR2(2),
        AST_RETIR_PHN_NBR              VARCHAR2(13),
        AST_EST_SELL_PRC               NUMBER(19, 2),
        CPTLAST_SALE_PRC               NUMBER(19, 2),
        CASH_RCPT_FDOC_NBR             VARCHAR2(14),
        AST_HANDLG_FEE_AMT             NUMBER(19, 2),
        AST_PRVNTMAINT_AMT             NUMBER(19, 2),
        CPTLAST_BUYER_DESC             VARCHAR2(25),
        AST_IUPD_CASE_NBR              VARCHAR2(14),
     CONSTRAINT CM_AST_RETIREDOC_TP1 PRIMARY KEY (
        FDOC_NBR),
     CONSTRAINT CM_AST_RETIREDOC_TC0 UNIQUE (OBJ_ID)
)
/
