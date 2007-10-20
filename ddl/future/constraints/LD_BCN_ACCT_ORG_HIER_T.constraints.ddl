ALTER TABLE LD_BCN_ACCT_ORG_HIER_T
ADD (CONSTRAINT LD_BCN_ACCT_ORG_HIER_TR2 FOREIGN KEY (
      FIN_COA_CD, ACCOUNT_NBR )
REFERENCES CA_ACCOUNT_T (
      FIN_COA_CD, ACCOUNT_NBR ))
ADD (CONSTRAINT LD_BCN_ACCT_ORG_HIER_TR3 FOREIGN KEY (
      ORG_FIN_COA_CD, ORG_CD )
REFERENCES CA_ORG_T (
      FIN_COA_CD, ORG_CD ))
/