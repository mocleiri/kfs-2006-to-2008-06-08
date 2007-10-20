ALTER TABLE FP_BANK_ACCOUNT_T
ADD (CONSTRAINT FP_BANK_ACCOUNT_TR1 FOREIGN KEY (
      FDOC_BANK_CD )
REFERENCES FP_BANK_T (
      FDOC_BANK_CD ))
ADD (CONSTRAINT FP_BANK_ACCOUNT_TR2 FOREIGN KEY (
      CSH_OFST_FIN_COA_CD, CSH_OFST_ACCT_NBR )
REFERENCES CA_ACCOUNT_T (
      FIN_COA_CD, ACCOUNT_NBR ))
ADD (CONSTRAINT FP_BANK_ACCOUNT_TR3 FOREIGN KEY (
      CSH_OFST_FIN_COA_CD )
REFERENCES CA_CHART_T (
      FIN_COA_CD ))
ADD (CONSTRAINT FP_BANK_ACCOUNT_TR5 FOREIGN KEY (
      CSH_OFST_FIN_COA_CD, CSH_OFST_ACCT_NBR, CSH_OFST_SUB_ACCT_NBR )
REFERENCES CA_SUB_ACCT_T (
      FIN_COA_CD, ACCOUNT_NBR, SUB_ACCT_NBR ))
/