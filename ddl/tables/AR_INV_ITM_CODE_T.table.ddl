CREATE TABLE AR_INV_ITM_CODE_T(
        FIN_COA_CD                     VARCHAR2(2) CONSTRAINT AR_INV_ITM_CODE_TN1 NOT NULL,
        ORG_CD                         VARCHAR2(4) CONSTRAINT AR_INV_ITM_CODE_TN2 NOT NULL,
        AR_INV_ITM_CD                  VARCHAR2(6) CONSTRAINT AR_INV_ITM_CODE_TN3 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT AR_INV_ITM_CODE_TN4 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT AR_INV_ITM_CODE_TN5 NOT NULL,
        AR_INV_ITM_DESC                VARCHAR2(40),
        AR_RLTD_STOCK_NBR              VARCHAR2(10),
        INV_FIN_COA_CD                 VARCHAR2(2),
        INV_ACCT_NBR                   VARCHAR2(7),
        INV_SUB_ACCT_NBR               VARCHAR2(5),
        INV_FIN_OBJ_CD                 VARCHAR2(4),
        INV_FIN_SUB_OBJ_CD             VARCHAR2(3),
        INV_PROJECT_CD                 VARCHAR2(10),
        INV_ORG_REF_ID                 VARCHAR2(8),
        AR_ITM_DFLT_PRC                NUMBER(19, 2),
        AR_DFLT_UOM_CD                 VARCHAR2(2),
        AR_ITM_DFLT_QTY                NUMBER,
        ROW_ACTV_IND                   VARCHAR2(1) CONSTRAINT AR_INV_ITM_CODE_TN6 NOT NULL,
     CONSTRAINT AR_INV_ITM_CODE_TP1 PRIMARY KEY (
        FIN_COA_CD,
        ORG_CD,
        AR_INV_ITM_CD),
     CONSTRAINT AR_INV_ITM_CODE_TC0 UNIQUE (OBJ_ID)
)
/
