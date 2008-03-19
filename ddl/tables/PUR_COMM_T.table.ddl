CREATE TABLE PUR_COMM_T(
        PUR_COMM_CD                    VARCHAR2(40) CONSTRAINT PUR_COMM_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT PUR_COMM_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT PUR_COMM_TN3 NOT NULL,
        PUR_COMM_DESC                  VARCHAR2(200) CONSTRAINT PUR_COMM_TN4 NOT NULL,
        PUR_SALES_TAX_IND              VARCHAR2(1),
        ITM_RSTRC_IND                  VARCHAR2(1),
        RSTRC_MTRL_CD                  VARCHAR2(4),
        DOBJ_MAINT_CD_ACTV_IND         VARCHAR2(1) CONSTRAINT PUR_COMM_TN5 NOT NULL,
     CONSTRAINT PUR_COMM_TP1 PRIMARY KEY (
        PUR_COMM_CD),
     CONSTRAINT PUR_COMM_TC0 UNIQUE (OBJ_ID)
)
/