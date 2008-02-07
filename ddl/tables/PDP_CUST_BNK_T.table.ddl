CREATE TABLE PDP_CUST_BNK_T(
        CUST_BNK_ID                    NUMBER(8) CONSTRAINT PDP_CUST_BNK_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36),
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT PDP_CUST_BNK_TN3 NOT NULL, 
        CUST_ID                        NUMBER(8),
        BNK_ID                         NUMBER(8),
        DISB_TYP_CD                    VARCHAR2(4),
        LST_UPDT_TS                    DATE CONSTRAINT PDP_CUST_BNK_TN4 NOT NULL,
        LST_UPDT_USR_ID                VARCHAR2(11) CONSTRAINT PDP_CUST_BNK_TN5 NOT NULL,
     CONSTRAINT PDP_CUST_BNK_TP1 PRIMARY KEY (
        CUST_BNK_ID),
     CONSTRAINT PDP_CUST_BNK_TC1 UNIQUE (CUST_ID, DISB_TYP_CD)
)
/
