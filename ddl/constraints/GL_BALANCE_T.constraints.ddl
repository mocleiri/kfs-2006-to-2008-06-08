ALTER TABLE GL_BALANCE_T
ADD (CONSTRAINT GL_BALANCE_TR2 FOREIGN KEY (
      FIN_COA_CD )
REFERENCES CA_CHART_T (
      FIN_COA_CD ))
ADD (CONSTRAINT GL_BALANCE_TR3 FOREIGN KEY (
      FIN_COA_CD, ACCOUNT_NBR )
REFERENCES CA_ACCOUNT_T (
      FIN_COA_CD, ACCOUNT_NBR ))
ADD (CONSTRAINT GL_BALANCE_TR6 FOREIGN KEY (
      FIN_BALANCE_TYP_CD )
REFERENCES CA_BALANCE_TYPE_T (
      FIN_BALANCE_TYP_CD ))
ADD (CONSTRAINT GL_BALANCE_TR7 FOREIGN KEY (
      FIN_OBJ_TYP_CD )
REFERENCES CA_OBJ_TYPE_T (
      FIN_OBJ_TYP_CD ))
/