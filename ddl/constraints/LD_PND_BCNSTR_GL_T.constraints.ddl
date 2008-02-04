ALTER TABLE LD_PND_BCNSTR_GL_T
ADD (CONSTRAINT LD_PND_BCNSTR_GL_TR1 FOREIGN KEY (
      FDOC_NBR, UNIV_FISCAL_YR, FIN_COA_CD, ACCOUNT_NBR, SUB_ACCT_NBR )
REFERENCES LD_BCNSTR_HDR_T (
      FDOC_NBR, UNIV_FISCAL_YR, FIN_COA_CD, ACCOUNT_NBR, SUB_ACCT_NBR ))
ADD (CONSTRAINT LD_PND_BCNSTR_GL_TR3 FOREIGN KEY (
      UNIV_FISCAL_YR, FIN_COA_CD, FIN_OBJECT_CD )
REFERENCES CA_OBJECT_CODE_T (
      UNIV_FISCAL_YR, FIN_COA_CD, FIN_OBJECT_CD ))
ADD (CONSTRAINT LD_PND_BCNSTR_GL_TR4 FOREIGN KEY (
      FIN_COA_CD )
REFERENCES CA_CHART_T (
      FIN_COA_CD ))
ADD (CONSTRAINT LD_PND_BCNSTR_GL_TR5 FOREIGN KEY (
      FIN_COA_CD, ACCOUNT_NBR )
REFERENCES CA_ACCOUNT_T (
      FIN_COA_CD, ACCOUNT_NBR ))
ADD (CONSTRAINT LD_PND_BCNSTR_GL_TR8 FOREIGN KEY (
      FIN_BALANCE_TYP_CD )
REFERENCES CA_BALANCE_TYPE_T (
      FIN_BALANCE_TYP_CD ))
ADD (CONSTRAINT LD_PND_BCNSTR_GL_TR9 FOREIGN KEY (
      FIN_OBJ_TYP_CD )
REFERENCES CA_OBJ_TYPE_T (
      FIN_OBJ_TYP_CD ))
/