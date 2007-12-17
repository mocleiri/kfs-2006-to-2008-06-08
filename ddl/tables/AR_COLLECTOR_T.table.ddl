CREATE TABLE AR_COLLECTOR_T(
        FIN_COA_CD                     VARCHAR2(2) CONSTRAINT AR_COLLECTOR_TN1 NOT NULL,
        ORG_CD                         VARCHAR2(4) CONSTRAINT AR_COLLECTOR_TN2 NOT NULL,
        ORG_ASGN_CLCTR_ID              VARCHAR2(9) CONSTRAINT AR_COLLECTOR_TN3 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT AR_COLLECTOR_TN4 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT AR_COLLECTOR_TN5 NOT NULL,
        AR_CLCTR_NM                    VARCHAR2(40),
        AR_CLCTR_TTL                   VARCHAR2(40),
        AR_CLCTR_BLDG_ADDR             VARCHAR2(30),
        AR_CLCTR_STRT_ADDR             VARCHAR2(30),
        AR_CLCTR_CTY_NM                VARCHAR2(25),
        AR_CLCTR_ST_CD                 VARCHAR2(2),
        AR_CLCTR_ZIP_CD                VARCHAR2(11),
        AR_CLCTR_PHONE_NBR             VARCHAR2(13),
        AR_CLCTR_RMT1_ADDR             VARCHAR2(30),
        AR_CLCTR_RMT2_ADDR             VARCHAR2(30),
        AR_CLCTR_RMTCTY_NM             VARCHAR2(25),
        AR_CLCTR_RMTST_CD              VARCHAR2(2),
        AR_CLCTR_RMTZIP_CD             VARCHAR2(11),
        AR_CLCTR_UNVL_ID               VARCHAR2(10),
        AR_CLCTR_FAX_NBR               VARCHAR2(13),
        AR_CLCTR_800_NBR               VARCHAR2(13),
     CONSTRAINT AR_COLLECTOR_TP1 PRIMARY KEY (
        FIN_COA_CD,
        ORG_CD,
        ORG_ASGN_CLCTR_ID),
     CONSTRAINT AR_COLLECTOR_TC0 UNIQUE (OBJ_ID)
)
/
