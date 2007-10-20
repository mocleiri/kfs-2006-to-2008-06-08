CREATE TABLE PEND_INVOICET(
        PO_NUMBER                      VARCHAR2(9) CONSTRAINT PEND_INVOICETN1 NOT NULL,
        INVOICE_NUMBER                 VARCHAR2(14) CONSTRAINT PEND_INVOICETN2 NOT NULL,
        OBJ_ID                         VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT PEND_INVOICETN3 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT PEND_INVOICETN4 NOT NULL,
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
     CONSTRAINT PEND_INVOICETP1 PRIMARY KEY (
        PO_NUMBER,
        INVOICE_NUMBER),
     CONSTRAINT PEND_INVOICETC0 UNIQUE (OBJ_ID),
     CONSTRAINT PEND_INVOICETC1 UNIQUE (DOCUMENT_NUM, DOCUMENT_TYPE)
)
/
