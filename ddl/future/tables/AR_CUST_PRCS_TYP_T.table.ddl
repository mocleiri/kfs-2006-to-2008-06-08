CREATE TABLE AR_CUST_PRCS_TYP_T(
        CUST_SPCL_PRCS_CD              VARCHAR2(2) CONSTRAINT AR_CUST_PRCS_TYP_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT AR_CUST_PRCS_TYP_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT AR_CUST_PRCS_TYP_TN3 NOT NULL,
        CUST_SPCLPRCS_DESC             VARCHAR2(40),
        ROW_ACTV_IND                   VARCHAR2(1) CONSTRAINT AR_CUST_PRCS_TYP_TN4 NOT NULL, 
     CONSTRAINT AR_CUST_PRCS_TYP_TP1 PRIMARY KEY (
        CUST_SPCL_PRCS_CD),
     CONSTRAINT AR_CUST_PRCS_TYP_TC0 UNIQUE (OBJ_ID)
)
/
