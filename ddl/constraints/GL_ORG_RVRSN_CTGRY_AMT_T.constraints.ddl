ALTER TABLE GL_ORG_RVRSN_CTGRY_AMT_T
ADD (CONSTRAINT GL_ORG_RVRSN_CTGRY_AMT_TR1 FOREIGN KEY (
      FIN_COA_CD )
REFERENCES CA_CHART_T (
      FIN_COA_CD))
ADD (CONSTRAINT GL_ORG_RVRSN_CTGRY_AMT_TR2 FOREIGN KEY (
      FIN_COA_CD, ACCOUNT_NBR )
REFERENCES CA_ACCOUNT_T (
      FIN_COA_CD, ACCOUNT_NBR ))
ADD (CONSTRAINT GL_ORG_RVRSN_CTGRY_AMT_TR4 FOREIGN KEY (
      ORG_RVRSN_CTGRY_CD )
REFERENCES CA_ORG_RVRSN_CTGRY_T (
      ORG_RVRSN_CTGRY_CD ))
ADD (CONSTRAINT GL_ORG_RVRSN_CTGRY_AMT_TR5 FOREIGN KEY ( 
      FIN_COA_CD, ACCOUNT_NBR, SUB_ACCT_NBR ) 
REFERENCES GL_ORG_RVRSN_UNIT_WRK_T ( 
      FIN_COA_CD, ACCOUNT_NBR, SUB_ACCT_NBR )) 
/