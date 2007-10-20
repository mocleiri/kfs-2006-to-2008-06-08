ALTER TABLE CA_PRIOR_YR_ACCT_T
ADD (CONSTRAINT CA_PRIOR_YR_ACCT_TR1 FOREIGN KEY (
      FIN_COA_CD )
REFERENCES CA_CHART_T (
      FIN_COA_CD ))
ADD (CONSTRAINT CA_PRIOR_YR_ACCT_TR2 FOREIGN KEY (
      FIN_COA_CD, ORG_CD )
REFERENCES CA_ORG_T (
      FIN_COA_CD, ORG_CD ))
ADD (CONSTRAINT CA_PRIOR_YR_ACCT_TR3 FOREIGN KEY (
      ACCT_TYP_CD )
REFERENCES CA_ACCOUNT_TYPE_T (
      ACCT_TYP_CD ))
ADD (CONSTRAINT CA_PRIOR_YR_ACCT_TR4 FOREIGN KEY (
      ACCT_PHYS_CMP_CD )
REFERENCES SH_CAMPUS_T (
      CAMPUS_CD ))
ADD (CONSTRAINT CA_PRIOR_YR_ACCT_TR5 FOREIGN KEY (
      ACCT_STATE_CD )
REFERENCES SH_STATE_T (
      POSTAL_STATE_CD ))
ADD (CONSTRAINT CA_PRIOR_YR_ACCT_TR6 FOREIGN KEY (
      SUB_FUND_GRP_CD )
REFERENCES CA_SUB_FUND_GRP_T (
      SUB_FUND_GRP_CD ))
ADD (CONSTRAINT CA_PRIOR_YR_ACCT_TR7 FOREIGN KEY (
      FIN_HGH_ED_FUNC_CD )
REFERENCES CA_HIGHR_ED_FUNC_T (
      FIN_HGH_ED_FUNC_CD ))
ADD (CONSTRAINT CA_PRIOR_YR_ACCT_TR8 FOREIGN KEY (
      ACCT_RSTRC_STAT_CD )
REFERENCES CA_RESTRICT_STAT_T (
      ACCT_RSTRC_STAT_CD ))
ADD (CONSTRAINT CA_PRIOR_YR_ACCT_TR10 FOREIGN KEY (
      CONT_FIN_COA_CD, CONT_ACCOUNT_NBR )
REFERENCES CA_ACCOUNT_T (
      FIN_COA_CD, ACCOUNT_NBR ))
ADD (CONSTRAINT CA_PRIOR_YR_ACCT_TR13 FOREIGN KEY (
      INCOME_FIN_COA_CD, INCOME_ACCOUNT_NBR )
REFERENCES CA_ACCOUNT_T (
      FIN_COA_CD, ACCOUNT_NBR ))
ADD (CONSTRAINT CA_PRIOR_YR_ACCT_TR14 FOREIGN KEY (
      ICR_FIN_COA_CD, ICR_ACCOUNT_NBR )
REFERENCES CA_ACCOUNT_T (
      FIN_COA_CD, ACCOUNT_NBR ))
ADD (CONSTRAINT CA_PRIOR_YR_ACCT_TR18 FOREIGN KEY (
      ACCT_ZIP_CD )
REFERENCES SH_ZIP_CODE_T (
      POSTAL_ZIP_CD ))
ADD (CONSTRAINT CA_PRIOR_YR_ACCT_TR23 FOREIGN KEY (
      PGM_CD )
REFERENCES CA_PGM_T (
      PGM_CD ))
/