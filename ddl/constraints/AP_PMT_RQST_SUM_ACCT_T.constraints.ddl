ALTER TABLE AP_PMT_RQST_SUM_ACCT_T
ADD (CONSTRAINT AP_PMT_RQST_SUM_ACCT_TR1 FOREIGN KEY (
      PMT_RQST_ID )
REFERENCES AP_PMT_RQST_T (
      PMT_RQST_ID ))
ADD (CONSTRAINT AP_PMT_RQST_SUM_ACCT_TR2 FOREIGN KEY (
      FIN_COA_CD )
REFERENCES CA_CHART_T (
      FIN_COA_CD ))
ADD (CONSTRAINT AP_PMT_RQST_SUM_ACCT_TR3 FOREIGN KEY (
      FIN_COA_CD, ACCOUNT_NBR )
REFERENCES CA_ACCOUNT_T (
      FIN_COA_CD, ACCOUNT_NBR ))
ADD (CONSTRAINT AP_PMT_RQST_SUM_ACCT_TR4 FOREIGN KEY (
      FIN_COA_CD, ACCOUNT_NBR, SUB_ACCT_NBR )
REFERENCES CA_SUB_ACCT_T (
      FIN_COA_CD, ACCOUNT_NBR, SUB_ACCT_NBR ))
/
