CREATE TABLE PDP_PMT_GRP_HIST_T(
        PMT_GRP_HIST_ID                NUMBER(8) CONSTRAINT PDP_PMT_GRP_HIST_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36),
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT PDP_PMT_GRP_HIST_TN3 NOT NULL, 
        PMT_CHG_CD                     VARCHAR2(4),
        PMT_CHG_NTE_TXT                VARCHAR2(250),
        PMT_CHG_USR_ID                 VARCHAR2(11),
        PMT_CHG_TS                     DATE,
        ORIG_PMT_STAT_CD               VARCHAR2(4),
        ORIG_PMT_DT                    DATE,
        ORIG_ACH_BNK_RTNG_NBR          VARCHAR2(9),
        ORIG_ADV_EMAIL_ADDR            VARCHAR2(50),
        ORIG_DISB_NBR                  NUMBER(9),
        ORIG_DISB_TS                   DATE,
        ORIG_PROC_IMD_IND              VARCHAR2(1),
        ORIG_PMT_SPCL_HANDLG_IND       VARCHAR2(1),
        PMT_CNCL_EXTRT_TS              DATE,
        ORIG_BNK_ID                    NUMBER(8),
        ORIG_DISB_TYP_CD               VARCHAR2(4),
        PMT_CNCL_EXTRT_STAT_IND        VARCHAR2(1),
        ORIG_PROC_ID                   NUMBER(8),
        PMT_GRP_ID                     NUMBER(8),
        LST_UPDT_TS                    DATE CONSTRAINT PDP_PMT_GRP_HIST_TN4 NOT NULL,
     CONSTRAINT PDP_PMT_GRP_HIST_TP1 PRIMARY KEY (
        PMT_GRP_HIST_ID)
)
/
