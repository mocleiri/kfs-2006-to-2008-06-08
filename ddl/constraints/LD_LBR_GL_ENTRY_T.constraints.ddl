ALTER TABLE LD_LBR_GL_ENTRY_T
ADD (CONSTRAINT LD_LBR_GL_ENTRY_TR1 FOREIGN KEY (
      UNIV_FISCAL_YR, FIN_COA_CD, FIN_OBJECT_CD )
REFERENCES CA_OBJECT_CODE_T (
      UNIV_FISCAL_YR, FIN_COA_CD, FIN_OBJECT_CD ))
ADD (CONSTRAINT LD_LBR_GL_ENTRY_TR2 FOREIGN KEY (
      FIN_COA_CD, ACCOUNT_NBR )
REFERENCES CA_ACCOUNT_T (
      FIN_COA_CD, ACCOUNT_NBR ))
ADD (CONSTRAINT LD_LBR_GL_ENTRY_TR3 FOREIGN KEY (
      FIN_COA_CD )
REFERENCES CA_CHART_T (
      FIN_COA_CD ))
ADD (CONSTRAINT LD_LBR_GL_ENTRY_TR6 FOREIGN KEY (
      FIN_BALANCE_TYP_CD )
REFERENCES CA_BALANCE_TYPE_T (
      FIN_BALANCE_TYP_CD ))
ADD (CONSTRAINT LD_LBR_GL_ENTRY_TR8 FOREIGN KEY (
      FIN_OBJ_TYP_CD )
REFERENCES CA_OBJ_TYPE_T (
      FIN_OBJ_TYP_CD ))
ADD (CONSTRAINT LD_LBR_GL_ENTRY_TR9 FOREIGN KEY (
      UNIV_FISCAL_YR, UNIV_FISCAL_PRD_CD )
REFERENCES SH_ACCT_PERIOD_T (
      UNIV_FISCAL_YR, UNIV_FISCAL_PRD_CD ))
ADD (CONSTRAINT LD_LBR_GL_ENTRY_TR10 FOREIGN KEY (
      FDOC_TYP_CD )
REFERENCES FP_DOC_TYPE_T (
      FDOC_TYP_CD ))
ADD (CONSTRAINT LD_LBR_GL_ENTRY_TR14 FOREIGN KEY (
      FDOC_NBR )
REFERENCES FP_DOC_HEADER_T (
      FDOC_NBR ))
/
