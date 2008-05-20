CREATE TABLE AR_CUST_ADDR_T(
        CUST_NBR                       VARCHAR2(9) CONSTRAINT AR_CUST_ADDR_TN1 NOT NULL,
        CUST_ADDR_ID                   NUMBER(7) CONSTRAINT AR_CUST_ADDR_TN2 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT AR_CUST_ADDR_TN3 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT AR_CUST_ADDR_TN4 NOT NULL,
        CUST_ADDR_NM                   VARCHAR2(40),
        CUST_LINE_1_ADDR               VARCHAR2(30),
        CUST_LINE_2_ADDR               VARCHAR2(30),
        CUST_CITY_NM                   VARCHAR2(25),
        CUST_STATE_CD                  VARCHAR2(2),
        CUST_ZIP_CD                    VARCHAR2(11),
        CUST_CNTRY_CD                  VARCHAR2(2), 
        CUST_ADDR_INTL_PROV_NM         VARCHAR2(45),
        CUST_INTL_MAIL_CD              VARCHAR2(20),
        CUST_EMAIL_ADDR                VARCHAR2(60),
        CUST_ADDR_TYPE_CD              VARCHAR2(2),
        CUST_ADDR_END_DT               DATE,
     CONSTRAINT AR_CUST_ADDR_TP1 PRIMARY KEY (
        CUST_NBR,
        CUST_ADDR_ID),
     CONSTRAINT AR_CUST_ADDR_TC0 UNIQUE (OBJ_ID)
)
/
