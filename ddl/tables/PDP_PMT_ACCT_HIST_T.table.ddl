CREATE TABLE PDP_PMT_ACCT_HIST_T(
        PMT_ACCT_HIST_ID               NUMBER(8) CONSTRAINT PDP_PMT_ACCT_HIST_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36),
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT PDP_PMT_ACCT_HIST_TN3 NOT NULL, 
        ACCTG_CHG_CD                   VARCHAR2(4),
        ACCT_ATTRIB_NM                 VARCHAR2(25),
        ACCT_ATTRIB_ORIG_VAL           VARCHAR2(15),
        ACCT_ATTRIB_NEW_VAL            VARCHAR2(15),
        ACCT_CHG_TS                    DATE,
        PMT_ACCT_DTL_ID                NUMBER(8),
        LST_UPDT_TS                    DATE CONSTRAINT PDP_PMT_ACCT_HIST_TN4 NOT NULL,
     CONSTRAINT PDP_PMT_ACCT_HIST_TP1 PRIMARY KEY (
        PMT_ACCT_HIST_ID)
)
/
