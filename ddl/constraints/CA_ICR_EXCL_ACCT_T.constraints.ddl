ALTER TABLE CA_ICR_EXCL_ACCT_T
ADD (CONSTRAINT CA_ICR_EXCL_ACCT_TR1 FOREIGN KEY (
      FIN_COA_CD )
REFERENCES CA_CHART_T (
      FIN_COA_CD ))
ADD (CONSTRAINT CA_ICR_EXCL_ACCT_TR2 FOREIGN KEY (
      FIN_COA_CD, ACCOUNT_NBR )
REFERENCES CA_ACCOUNT_T (
      FIN_COA_CD, ACCOUNT_NBR ))
ADD (CONSTRAINT CA_ICR_EXCL_ACCT_TR3 FOREIGN KEY (
      FIN_OBJ_COA_CD )
REFERENCES CA_CHART_T (
      FIN_COA_CD ))
/