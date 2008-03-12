CREATE TABLE PUR_RCVNG_ADDR_T(
        PUR_RCVNG_ADDR_ID              NUMBER(10) CONSTRAINT PUR_RCVNG_ADDR_TN1 NOT NULL,
        FIN_COA_CD                     VARCHAR2(2) CONSTRAINT PUR_RCVNG_ADDR_TN2 NOT NULL, 
        ORG_CD                         VARCHAR2(4) CONSTRAINT PUR_RCVNG_ADDR_TN3 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT PUR_RCVNG_ADDR_TN4 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT PUR_RCVNG_ADDR_TN5 NOT NULL,
        PUR_RCVNG_NM                   VARCHAR2(45),
        PUR_RCVNG_LN1_ADDR             VARCHAR2(45),
        PUR_RCVNG_LN2_ADDR             VARCHAR2(45),
        PUR_RCVNG_CTY_NM               VARCHAR2(45),
        PUR_RCVNG_ST_CD                VARCHAR2(2),
        PUR_RCVNG_PSTL_CD              VARCHAR2(20),
        PUR_RCVNG_CNTRY_CD             VARCHAR2(2),
        PUR_RCVNG_DFLT_ADDR_IND        VARCHAR2(1),
        VNDR_ADDR_USE_RCVNG_IND        VARCHAR2(1),
        DOBJ_MAINT_CD_ACTV_IND         VARCHAR2(1) CONSTRAINT PUR_RCVNG_ADDR_TN6 NOT NULL,
     CONSTRAINT PUR_RCVNG_ADDR_TP1 PRIMARY KEY (
        PUR_RCVNG_ADDR_ID,
        FIN_COA_CD,
        ORG_CD),
     CONSTRAINT PUR_RCVNG_ADDR_TC0 UNIQUE (OBJ_ID)
)
/
