CREATE TABLE AR_LOCKBOX_T(
        AR_INV_SEQ_NBR                 NUMBER(12) CONSTRAINT AR_LOCKBOX_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT AR_LOCKBOX_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT AR_LOCKBOX_TN3 NOT NULL,
        AR_LOCKBOX_NBR                 VARCHAR2(10),
        CUST_NBR                       VARCHAR2(9),
        FDOC_REF_INV_NBR               VARCHAR2(14),
        AR_BILLING_DT                  DATE,
        AR_INV_TOT_AMT                 NUMBER(19, 2),
        AR_INV_PD_APLD_AMT             NUMBER(19, 2),
        AR_SCAN_INV_DT                 DATE,
        CUST_PMT_MEDIUM_CD             VARCHAR2(2),
        AR_PRCS_INV_DT                 DATE,
        AR_BATCH_SEQ_NBR               NUMBER(5),
     CONSTRAINT AR_LOCKBOX_TP1 PRIMARY KEY (
        AR_INV_SEQ_NBR),
     CONSTRAINT AR_LOCKBOX_TC0 UNIQUE (OBJ_ID)
)
/
