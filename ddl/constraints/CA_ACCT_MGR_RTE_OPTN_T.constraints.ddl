ALTER TABLE CA_ACCT_MGR_RTE_OPTN_T
ADD (CONSTRAINT CA_ACCT_MGR_RTE_OPTN_TR2 FOREIGN KEY (
      FIN_COA_CD )
REFERENCES CA_CHART_T (
      FIN_COA_CD ))
/