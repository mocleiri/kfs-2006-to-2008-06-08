ALTER TABLE FP_DV_WIRE_CHRG_T
ADD (CONSTRAINT FP_DV_WIRE_CHRG_TR1 FOREIGN KEY (
      UNIV_FISCAL_YR )
REFERENCES FS_OPTION_T (
      UNIV_FISCAL_YR ))
ADD (CONSTRAINT FP_DV_WIRE_CHRG_TR2 FOREIGN KEY (
      FIN_COA_CD )
REFERENCES CA_CHART_T (
      FIN_COA_CD ))
ADD (CONSTRAINT FP_DV_WIRE_CHRG_TR3 FOREIGN KEY (
      UNIV_FISCAL_YR, FIN_COA_CD, INC_FIN_OBJ_CD )
REFERENCES CA_OBJECT_CODE_T (
      UNIV_FISCAL_YR, FIN_COA_CD, FIN_OBJECT_CD ))
ADD (CONSTRAINT FP_DV_WIRE_CHRG_TR4 FOREIGN KEY (
      UNIV_FISCAL_YR, FIN_COA_CD, EXP_FIN_OBJ_CD )
REFERENCES CA_OBJECT_CODE_T (
      UNIV_FISCAL_YR, FIN_COA_CD, FIN_OBJECT_CD ))
ADD (CONSTRAINT FP_DV_WIRE_CHRG_TR5 FOREIGN KEY (
      FIN_COA_CD, ACCOUNT_NBR )
REFERENCES CA_ACCOUNT_T (
      FIN_COA_CD, ACCOUNT_NBR ))
/