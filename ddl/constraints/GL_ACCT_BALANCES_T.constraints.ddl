ALTER TABLE GL_ACCT_BALANCES_T
ADD (CONSTRAINT GL_ACCT_BALANCES_TR1 FOREIGN KEY (
      FIN_COA_CD, ACCOUNT_NBR )
REFERENCES CA_ACCOUNT_T (
      FIN_COA_CD, ACCOUNT_NBR ))
ADD (CONSTRAINT GL_ACCT_BALANCES_TR2 FOREIGN KEY (
      FIN_COA_CD )
REFERENCES CA_CHART_T (
      FIN_COA_CD ))
ADD (CONSTRAINT GL_ACCT_BALANCES_TR6 FOREIGN KEY (
      UNIV_FISCAL_YR )
REFERENCES FS_OPTION_T (
      UNIV_FISCAL_YR ))
/