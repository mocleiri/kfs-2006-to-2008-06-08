CREATE TABLE PDP_PMT_GRP_T(
        PMT_GRP_ID                     NUMBER(8) CONSTRAINT PDP_PMT_GRP_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) DEFAULT SYS_GUID(),
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT PDP_PMT_GRP_TN3 NOT NULL, 
        PMT_PAYEE_NM                   VARCHAR2(40),
        DISB_NBR                       NUMBER(9),
        PAYEE_ID                       VARCHAR2(25),
        DISB_TS                        DATE,
        PAYEE_ID_TYP_CD                VARCHAR2(1),
        PHYS_CMP_PROC_CD               VARCHAR2(2),
        PMT_SORT_ORD_VAL               VARCHAR2(50),
        ALTRNT_PAYEE_ID                VARCHAR2(25),
        ALTRNT_PAYEE_ID_TYP_CD         VARCHAR2(1),
        PMT_ACH_EXTRT_TS               DATE,
        PAYEE_OWNR_CD                  VARCHAR2(2),
        CUST_INST_NBR                  VARCHAR2(30),
        PMT_LN1_ADDR                   VARCHAR2(55),
        PMT_LN2_ADDR                   VARCHAR2(55),
        PMT_LN3_ADDR                   VARCHAR2(55),
        PMT_LN4_ADDR                   VARCHAR2(55),
        PMT_CTY_NM                     VARCHAR2(30),
        PMT_ZIP_CD                     VARCHAR2(20),
        PMT_ST_NM                      VARCHAR2(30),
        CMP_ADDR_IND                   VARCHAR2(1),
        PMT_DT                         DATE,
        PMT_CNTRY_NM                   VARCHAR2(30),
        PMT_ATTCHMNT_IND               VARCHAR2(1),
        PMT_SPCL_HANDLG_IND            VARCHAR2(1),
        PMT_TXBL_IND                   VARCHAR2(1),
        NRA_PMT_IND                    VARCHAR2(1),
        PROC_IMD_IND                   VARCHAR2(1),
        CUST_ACCT_TYP_CD               VARCHAR2(2),
        ACH_BNK_RTNG_NBR               VARCHAR2(9),
        ADV_EMAIL_ADDR                 VARCHAR2(50),
        EMP_IND                        VARCHAR2(1),
        PMT_FIL_ID                     NUMBER(8),
        BNK_ID                         NUMBER(8),
        DISB_TYP_CD                    VARCHAR2(4),
        PMT_STAT_CD                    VARCHAR2(4),
        LST_UPDT_TS                    DATE CONSTRAINT PDP_PMT_GRP_TN4 NOT NULL,
        PROC_ID                        NUMBER(8),
        PMT_GRP_CMB_IND                VARCHAR2(1) CONSTRAINT PDP_PMT_GRP_TN5 NOT NULL,
        ADV_EMAIL_SNT_TS               DATE,
        PDP_EPIC_PMT_CNCL_EXTRT_TS     DATE,    
        PDP_EPIC_PMT_PD_EXTRT_TS       DATE,  
     CONSTRAINT PDP_PMT_GRP_TP1 PRIMARY KEY (
        PMT_GRP_ID)
)
/