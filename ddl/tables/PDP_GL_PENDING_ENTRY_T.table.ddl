CREATE TABLE PDP_GL_PENDING_ENTRY_T(
        GL_PENDING_ENTRY_ID            NUMBER(8) CONSTRAINT PDP_GL_PENDING_ENTRY_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT PDP_GL_PENDING_ENTRY_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT PDP_GL_PENDING_ENTRY_TN3 NOT NULL, 
        FS_ORIGIN_CD                   VARCHAR2(2) CONSTRAINT PDP_GL_PENDING_ENTRY_TN4 NOT NULL,
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT PDP_GL_PENDING_ENTRY_TN5 NOT NULL,
        TRN_ENTR_SEQ_NBR               NUMBER(5),
        FIN_COA_CD                     VARCHAR2(2),
        ACCOUNT_NBR                    VARCHAR2(7),
        SUB_ACCOUNT_NBR                VARCHAR2(5),
        FIN_OBJECT_CD                  VARCHAR2(4),
        FIN_SUB_OBJ_CD                 VARCHAR2(3),
        FIN_BALANCE_TYP_CD             VARCHAR2(2),
        FIN_OBJ_TYP_CD                 VARCHAR2(2),
        UNIV_FISCAL_YR                 NUMBER(4),
        UNIV_FISCAL_PRD_CD             VARCHAR2(2),
        TRN_LDGR_ENTR_DESC             VARCHAR2(40),
        TRN_LDGR_ENTR_AMT              NUMBER(19,2),
        TRN_DEBIT_CRDT_CD              VARCHAR2(1),
        TRANSACTION_DT                 DATE,
        FDOC_TYP_CD                    VARCHAR2(4),
        ORG_DOC_NBR                    VARCHAR2(10),
        PROJECT_CD                     VARCHAR2(10),
        ORG_REFERENCE_ID               VARCHAR2(8),
        FDOC_REF_TYP_CD                VARCHAR2(4),
        FS_REF_ORIGIN_CD               VARCHAR2(2),
        FDOC_REF_NBR                   VARCHAR2(14),
        FDOC_REVERSAL_DT               DATE,
        TRN_ENCUM_UPDT_CD              VARCHAR2(1),
        FDOC_APPROVED_CD               VARCHAR2(1),
        ACCT_SF_FINOBJ_CD              VARCHAR2(4),
        TRN_ENTR_OFST_CD               VARCHAR2(1),
        TRN_EXTRT_IND                  VARCHAR2(1),
     CONSTRAINT PDP_GL_PENDING_ENTRY_TP1 PRIMARY KEY (
        GL_PENDING_ENTRY_ID),
     CONSTRAINT PDP_GL_PENDING_ENTRY_TC0 UNIQUE (OBJ_ID)
)
/
