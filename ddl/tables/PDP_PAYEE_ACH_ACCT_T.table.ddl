CREATE TABLE PDP_PAYEE_ACH_ACCT_T(
        ACH_ACCT_GNRTD_ID              NUMBER(10) CONSTRAINT PDP_PAYEE_ACH_ACCT_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) DEFAULT SYS_GUID(),
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT PDP_PAYEE_ACH_ACCT_TN3 NOT NULL,
        BNK_RTNG_NBR                   VARCHAR2(9),
        BNK_ACCT_NBR                   VARCHAR2(255), 
        PAYEE_NM                       VARCHAR2(50),
        PAYEE_EMAIL_ADDR               VARCHAR2(43),
        VNDR_HDR_GNRTD_ID              NUMBER(10),
        VNDR_DTL_ASND_ID               NUMBER(10),
        DV_PAYEE_ID_NBR                VARCHAR2(10),
        PERSON_UNVL_ID                 VARCHAR2(10),
        PAYEE_SSN_NBR                  VARCHAR2(255),
        PAYEE_FEIN_NBR                 VARCHAR2(9),
        PAYEE_ID_TYP_CD                VARCHAR2(1),
        PSD_TRN_CD                     VARCHAR2(4),
        ROW_ACTV_IND                   VARCHAR2(1) CONSTRAINT PDP_PAYEE_ACH_ACCT_TN4 NOT NULL, 
        BNK_ACCT_TYP_CD                VARCHAR2(2),
     CONSTRAINT PDP_PAYEE_ACH_ACCT_TP1 PRIMARY KEY (
        ACH_ACCT_GNRTD_ID)
)
/
