CREATE TABLE PUR_THRSHLD_T(
        PUR_THRSHLD_ID                 NUMBER(10) CONSTRAINT PUR_THRSHLD_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT PUR_THRSHLD_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT PUR_THRSHLD_TN3 NOT NULL,
        FIN_COA_CD                     VARCHAR2(2), 
        ACCT_TYP_CD                    VARCHAR2(2), 
        SUB_FUND_GRP_CD                VARCHAR2(6), 
        FIN_OBJECT_CD                  VARCHAR2(4), 
        ORG_CD                         VARCHAR2(4), 
        PUR_THRSHLD_AMT                NUMBER(19,2), 
        VNDR_HDR_GNRTD_ID              NUMBER(10), 
        VNDR_DTL_ASND_ID               NUMBER(10), 
        PUR_COMM_CD                    VARCHAR2(40), 
        DOBJ_MAINT_CD_ACTV_IND         VARCHAR2(1) CONSTRAINT PUR_THRSHLD_TN4 NOT NULL, 
     CONSTRAINT PUR_THRSHLD_TP1 PRIMARY KEY (
        PUR_THRSHLD_ID),
     CONSTRAINT PUR_THRSHLD_TC0 UNIQUE (OBJ_ID)
)
/
