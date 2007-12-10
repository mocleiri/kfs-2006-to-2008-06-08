CREATE TABLE CB_PND_INV_T(
        PO_NUMBER                      VARCHAR2(9) CONSTRAINT CB_PND_INV_TN1 NOT NULL,
        INVOICE_NUMBER                 VARCHAR2(14) CONSTRAINT CB_PND_INV_TN2 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT CB_PND_INV_TN3 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT CB_PND_INV_TN4 NOT NULL,
        DOCUMENT_AMT                   NUMBER(19, 2),
        DOCUMENT_DATE                  DATE,
        DOCUMENT_TYPE                  VARCHAR2(4),
        DOCUMENT_NUM                   VARCHAR2(9),
        INVOICE_ADDLCHG                NUMBER(19, 2),
        INVOICE_DISCOUNT               NUMBER(19, 2),
        VENDOR_NAME                    VARCHAR2(40),
        TRANSACTION_DATE               DATE,
        CAMPUS_CODE                    VARCHAR2(2),
        LAST_ACTION_CD                 VARCHAR2(2),
        PMT_RQST_STAT_CD               VARCHAR2(4),
     CONSTRAINT CB_PND_INV_TP1 PRIMARY KEY (
        PO_NUMBER,
        INVOICE_NUMBER),
     CONSTRAINT CB_PND_INV_TC0 UNIQUE (OBJ_ID),
     CONSTRAINT CB_PND_INV_TC1 UNIQUE (DOCUMENT_NUM, DOCUMENT_TYPE)
)
/
