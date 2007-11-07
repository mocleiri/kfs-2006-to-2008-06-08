ALTER TABLE CM_AST_PAYMENT_T
ADD (CONSTRAINT CM_AST_PAYMENT_TR1 FOREIGN KEY (
      CPTLAST_NBR )
REFERENCES CM_CPTLAST_T (
      CPTLAST_NBR ))
ADD (CONSTRAINT CM_AST_PAYMENT_TR2 FOREIGN KEY (
      FIN_COA_CD )
REFERENCES CA_CHART_T (
      FIN_COA_CD ))
ADD (CONSTRAINT CM_AST_PAYMENT_TR3 FOREIGN KEY (
      FIN_COA_CD, ACCOUNT_NBR, SUB_ACCT_NBR )
REFERENCES CA_SUB_ACCT_T (
      FIN_COA_CD, ACCOUNT_NBR, SUB_ACCT_NBR ))
ADD (CONSTRAINT CM_AST_PAYMENT_TR4 FOREIGN KEY (
      FDOC_POST_YR, FIN_COA_CD, FIN_OBJECT_CD )
REFERENCES CA_OBJECT_CODE_T (
      UNIV_FISCAL_YR, FIN_COA_CD, FIN_OBJECT_CD ))
ADD (CONSTRAINT CM_AST_PAYMENT_TR5 FOREIGN KEY (
      FIN_COA_CD, ACCOUNT_NBR )
REFERENCES CA_ACCOUNT_T (
      FIN_COA_CD, ACCOUNT_NBR ))
ADD (CONSTRAINT CM_AST_PAYMENT_TR6 FOREIGN KEY (
      FDOC_POST_YR, FIN_COA_CD, ACCOUNT_NBR, FIN_OBJECT_CD, FIN_SUB_OBJ_CD )
REFERENCES CA_SUB_OBJECT_CD_T (
      UNIV_FISCAL_YR, FIN_COA_CD, ACCOUNT_NBR, FIN_OBJECT_CD, FIN_SUB_OBJ_CD ))
ADD (CONSTRAINT CM_AST_PAYMENT_TR7 FOREIGN KEY (
      PROJECT_CD )
REFERENCES CA_PROJECT_T (
      PROJECT_CD ))
ADD (CONSTRAINT CM_AST_PAYMENT_TR8 FOREIGN KEY (
      FDOC_POST_YR, FDOC_POST_PRD_CD )
REFERENCES SH_ACCT_PERIOD_T (
      UNIV_FISCAL_YR, UNIV_FISCAL_PRD_CD ))
ADD (CONSTRAINT CM_AST_PAYMENT_TR9 FOREIGN KEY (
      FDOC_TYP_CD )
REFERENCES FP_DOC_TYPE_T (
      FDOC_TYP_CD ))
ADD (CONSTRAINT CM_AST_PAYMENT_TR10 FOREIGN KEY (
      FDOC_NBR )
REFERENCES FP_DOC_HEADER_T (
      FDOC_NBR ))
ADD (CONSTRAINT CM_AST_PAYMENT_TR11 FOREIGN KEY (
      FS_ORIGIN_CD )
REFERENCES FS_ORIGIN_CODE_T (
      FS_ORIGIN_CD ))
ADD (CONSTRAINT CM_AST_PAYMENT_TR12 FOREIGN KEY (
      FDOC_POST_YR )
REFERENCES FS_OPTION_T (
      UNIV_FISCAL_YR ))
/

