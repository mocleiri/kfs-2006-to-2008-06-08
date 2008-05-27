ALTER TABLE CM_CPTLAST_DOC_T
ADD (CONSTRAINT CM_CPTLAST_DOC_TR1 FOREIGN KEY (
      FDOC_NBR )
REFERENCES FP_DOC_HEADER_T (
      FDOC_NBR ))
ADD (CONSTRAINT CM_CPTLAST_DOC_TR2 FOREIGN KEY (
      CPTLAST_TYP_CD )
REFERENCES CM_ASSET_TYPE_T (
      CPTLAST_TYP_CD ))
ADD (CONSTRAINT CM_CPTLAST_DOC_TR3 FOREIGN KEY (
      CPTLAST_COND_CD )
REFERENCES CM_AST_CONDITION_T (
      CPTLAST_COND_CD ))
ADD (CONSTRAINT CM_CPTLAST_DOC_TR4 FOREIGN KEY (
      AST_INVN_STAT_CD )
REFERENCES CM_AST_STATUS_T (
      AST_INVN_STAT_CD ))
ADD (CONSTRAINT CM_CPTLAST_DOC_TR5 FOREIGN KEY (
      ORG_OWNER_COA_CD )
REFERENCES CA_CHART_T (
      FIN_COA_CD ))
ADD (CONSTRAINT CM_CPTLAST_DOC_TR6 FOREIGN KEY (
      ORG_OWNER_COA_CD, ORG_OWNER_ACCT_NBR )
REFERENCES CA_ACCOUNT_T (
      FIN_COA_CD, ACCOUNT_NBR ))
/
