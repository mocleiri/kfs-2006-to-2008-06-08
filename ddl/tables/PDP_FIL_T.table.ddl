CREATE TABLE PDP_FIL_T(
        PMT_FIL_ID                     NUMBER(8) CONSTRAINT PDP_FIL_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36),
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT PDP_FIL_TN3 NOT NULL, 
        PMT_FL_NM                      VARCHAR2(30),
        CUST_FL_CRTN_TS                DATE,
        PMT_CNT                        NUMBER(5),
        PMT_TOT_AMT                    NUMBER(14,2),
        SBMTR_USR_ID                   VARCHAR2(11),
        FL_PROC_TS                     DATE,
        CUST_ID                        NUMBER(8),
        LST_UPDT_TS                    DATE CONSTRAINT PDP_FIL_TN4 NOT NULL,
     CONSTRAINT PDP_FIL_TP1 PRIMARY KEY (
        PMT_FIL_ID)
)
/
