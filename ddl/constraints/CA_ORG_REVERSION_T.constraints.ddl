ALTER TABLE CA_ORG_REVERSION_T
ADD (CONSTRAINT CA_ORG_REVERSION_TR1 FOREIGN KEY (
      FIN_COA_CD )
REFERENCES CA_CHART_T (
      FIN_COA_CD ))
ADD (CONSTRAINT CA_ORG_REVERSION_TR2 FOREIGN KEY (
      FIN_COA_CD, ORG_CD )
REFERENCES CA_ORG_T (
      FIN_COA_CD, ORG_CD ))
ADD (CONSTRAINT CA_ORG_REVERSION_TR3 FOREIGN KEY (
      CSH_RVRSNFINCOA_CD, CSH_RVRSN_ACCT_NBR )
REFERENCES CA_ACCOUNT_T (
      FIN_COA_CD, ACCOUNT_NBR ))
ADD (CONSTRAINT CA_ORG_REVERSION_TR4 FOREIGN KEY (
      BDGT_RVRSN_COA_CD, BDGT_RVRSNACCT_NBR )
REFERENCES CA_ACCOUNT_T (
      FIN_COA_CD, ACCOUNT_NBR ))
ADD (CONSTRAINT CA_ORG_REVERSION_TR5 FOREIGN KEY (
      BDGT_RVRSN_COA_CD )
REFERENCES CA_CHART_T (
      FIN_COA_CD ))
ADD (CONSTRAINT CA_ORG_REVERSION_TR6 FOREIGN KEY (
      CSH_RVRSNFINCOA_CD )
REFERENCES CA_CHART_T (
      FIN_COA_CD ))
ADD (CONSTRAINT CA_ORG_REVERSION_TR7 FOREIGN KEY (
      UNIV_FISCAL_YR )
REFERENCES FS_OPTION_T (
      UNIV_FISCAL_YR ))
/