CREATE TABLE CB_PND_AST_TEMP_T(
        UNIV_FISCAL_YR                 NUMBER(7) CONSTRAINT CB_PND_AST_TEMP_TN1 NOT NULL,
        FIN_COA_CD                     VARCHAR2(2) CONSTRAINT CB_PND_AST_TEMP_TN2 NOT NULL,
        ACCOUNT_NBR                    VARCHAR2(7) CONSTRAINT CB_PND_AST_TEMP_TN3 NOT NULL,
        SUB_ACCT_NBR                   VARCHAR2(5) CONSTRAINT CB_PND_AST_TEMP_TN4 NOT NULL,
        FIN_OBJECT_CD                  VARCHAR2(4) CONSTRAINT CB_PND_AST_TEMP_TN5 NOT NULL,
        FIN_SUB_OBJ_CD                 VARCHAR2(3) CONSTRAINT CB_PND_AST_TEMP_TN6 NOT NULL,
        FIN_BALANCE_TYP_CD             VARCHAR2(2) CONSTRAINT CB_PND_AST_TEMP_TN7 NOT NULL,
        FIN_OBJ_TYP_CD                 VARCHAR2(2) CONSTRAINT CB_PND_AST_TEMP_TN8 NOT NULL,
        UNIV_FISCAL_PRD_CD             VARCHAR2(2) CONSTRAINT CB_PND_AST_TEMP_TN9 NOT NULL,
        FDOC_TYP_CD                    VARCHAR2(4) CONSTRAINT CB_PND_AST_TEMP_TN10 NOT NULL,
        FS_ORIGIN_CD                   VARCHAR2(2) CONSTRAINT CB_PND_AST_TEMP_TN11 NOT NULL,
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT CB_PND_AST_TEMP_TN12 NOT NULL,
        TRN_ENTR_SEQ_NBR               NUMBER(12) CONSTRAINT CB_PND_AST_TEMP_TN13 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT CB_PND_AST_TEMP_TN14 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT CB_PND_AST_TEMP_TN15 NOT NULL,
        TRN_LDGR_ENTR_DESC             VARCHAR2(40),
        TRN_LDGR_ENTR_AMT              NUMBER(19, 2),
        TRN_DEBIT_CRDT_CD              VARCHAR2(1),
        TRANSACTION_DT                 DATE,
        ORG_DOC_NBR                    VARCHAR2(10),
        PROJECT_CD                     VARCHAR2(10),
        ORG_REFERENCE_ID               VARCHAR2(8),
        FDOC_REF_TYP_CD                VARCHAR2(4),
        FS_REF_ORIGIN_CD               VARCHAR2(2),
        FDOC_REF_NBR                   VARCHAR2(9),
        FDOC_REVERSAL_DT               DATE,
        TRN_ENCUM_UPDT_CD              VARCHAR2(1),
        TRN_POST_DT                    DATE,
        JUNK                           VARCHAR2(1),
     CONSTRAINT CB_PND_AST_TEMP_TP1 PRIMARY KEY (
        UNIV_FISCAL_YR,
        FIN_COA_CD,
        ACCOUNT_NBR,
        SUB_ACCT_NBR,
        FIN_OBJECT_CD,
        FIN_SUB_OBJ_CD,
        FIN_BALANCE_TYP_CD,
        FIN_OBJ_TYP_CD,
        UNIV_FISCAL_PRD_CD,
        FDOC_TYP_CD,
        FS_ORIGIN_CD,
        FDOC_NBR,
        TRN_ENTR_SEQ_NBR),
     CONSTRAINT CB_PND_AST_TEMP_TC0 UNIQUE (OBJ_ID)
)
/
