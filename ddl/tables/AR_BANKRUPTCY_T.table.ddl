CREATE TABLE AR_BANKRUPTCY_T(
        CUST_BNKRPTCY_CD               VARCHAR2(3) CONSTRAINT AR_BANKRUPTCY_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT AR_BANKRUPTCY_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT AR_BANKRUPTCY_TN3 NOT NULL,
        CUST_BNKRPTCY_DESC             VARCHAR2(40),
        ROW_ACTV_IND                   VARCHAR2(1) CONSTRAINT AR_BANKRUPTCY_TN4 NOT NULL, 
     CONSTRAINT AR_BANKRUPTCY_TP1 PRIMARY KEY (
        CUST_BNKRPTCY_CD),
     CONSTRAINT AR_BANKRUPTCY_TC0 UNIQUE (OBJ_ID)
)
/