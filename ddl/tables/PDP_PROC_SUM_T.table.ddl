CREATE TABLE PDP_PROC_SUM_T(
        PROC_SUM_ID                    NUMBER(8) CONSTRAINT PDP_PROC_SUM_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) DEFAULT SYS_GUID(),
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT PDP_PROC_SUM_TN3 NOT NULL, 
        DISB_TYP_CD                    VARCHAR2(4) CONSTRAINT PDP_PROC_SUM_TN4 NOT NULL,
        PMT_SORT_ORD_VAL               VARCHAR2(1) CONSTRAINT PDP_PROC_SUM_TN5 NOT NULL,
        BEG_DISB_NBR                   NUMBER(9) CONSTRAINT PDP_PROC_SUM_TN6 NOT NULL,
        END_DISB_NBR                   NUMBER(9) CONSTRAINT PDP_PROC_SUM_TN7 NOT NULL,
        PROC_TOT_AMT                   NUMBER(14,2) CONSTRAINT PDP_PROC_SUM_TN8 NOT NULL,
        PROC_TOT_CNT                   NUMBER(6) CONSTRAINT PDP_PROC_SUM_TN9 NOT NULL,
        CUST_ID                        NUMBER(8) CONSTRAINT PDP_PROC_SUM_TN10 NOT NULL,
        PROC_ID                        NUMBER(8),
        LST_UPDT_TS                    DATE CONSTRAINT PDP_PROC_SUM_TN11 NOT NULL,
     CONSTRAINT PDP_PROC_SUM_TP1 PRIMARY KEY (
        PROC_SUM_ID)
)
/