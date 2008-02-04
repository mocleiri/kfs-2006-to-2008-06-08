CREATE UNIQUE INDEX AP_PMT_RQST_SUM_ACCT_TI1 ON AP_PMT_RQST_SUM_ACCT_T(
        PMT_RQST_ID,
        FIN_COA_CD,
        ACCOUNT_NBR,
        SUB_ACCT_NBR,
        FIN_SUB_OBJ_CD,
        FIN_OBJECT_CD,
        PROJECT_CD,
        ORG_REFERENCE_ID)
/