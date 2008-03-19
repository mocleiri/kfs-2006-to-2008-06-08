CREATE TABLE AR_CLCTN_STATUS_T(
        CUST_CLCTN_STAT_CD             VARCHAR2(3) CONSTRAINT AR_CLCTN_STATUS_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT AR_CLCTN_STATUS_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT AR_CLCTN_STATUS_TN3 NOT NULL,
        CUST_CLCTN_STAT_NM             VARCHAR2(40),
        ROW_ACTV_IND                   VARCHAR2(1) CONSTRAINT AR_CLCTN_STATUS_TN4 NOT NULL, 
     CONSTRAINT AR_CLCTN_STATUS_TP1 PRIMARY KEY (
        CUST_CLCTN_STAT_CD),
     CONSTRAINT AR_CLCTN_STATUS_TC0 UNIQUE (OBJ_ID)
)
/