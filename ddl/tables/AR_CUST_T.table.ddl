CREATE TABLE AR_CUST_T(
        CUST_NBR                       VARCHAR2(9) CONSTRAINT AR_CUST_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT AR_CUST_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT AR_CUST_TN3 NOT NULL,
        CUST_NM                        VARCHAR2(60),
        CUST_PARENT_CO_NBR             VARCHAR2(9),
        CUST_TYP_CD                    VARCHAR2(2),
        CUST_ADDR_CHG_DT               DATE,
        CUST_REC_ADD_DT                DATE,
        CUST_LST_ACTV_DT               DATE,
        CUST_SSN_ID                    VARCHAR2(9),
        CUST_FED_ID_NBR                VARCHAR2(9),
        CUST_ACTIVE_CD                 VARCHAR2(1),
        CUST_PHONE_NBR                 VARCHAR2(20),
        CUST_800_PHONE_NBR             VARCHAR2(20),
        CUST_CNTCT_NM                  VARCHAR2(40),
        CUST_CNTCT_PHN_NBR             VARCHAR2(20),
        CUST_FAX_NBR                   VARCHAR2(20),
        CUST_BIRTH_DT                  DATE,
        CUST_TAX_EXMPT_IND             VARCHAR2(1),
        CUST_CRDT_LMT_AMT              NUMBER(19, 2),
        CUST_CRDT_APRV_NM              VARCHAR2(40),
        CUST_EMAIL_ADDR                VARCHAR2(60),
     CONSTRAINT AR_CUST_TP1 PRIMARY KEY (
        CUST_NBR),
     CONSTRAINT AR_CUST_TC0 UNIQUE (OBJ_ID)
)
/
