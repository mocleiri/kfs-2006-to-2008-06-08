ALTER TABLE LD_LBR_OBJ_BENE_T
ADD (CONSTRAINT LD_LBR_OBJ_BENE_TR1 FOREIGN KEY (
      UNIV_FISCAL_YR, FIN_COA_CD, FINOBJ_BENE_TYP_CD )
REFERENCES LD_BENEFITS_CALC_T (
      UNIV_FISCAL_YR, FIN_COA_CD, POS_BENEFIT_TYP_CD ))
ADD (CONSTRAINT LD_LBR_OBJ_BENE_TR3 FOREIGN KEY (
      FINOBJ_BENE_TYP_CD )
REFERENCES LD_BENEFITS_TYPE_T (
      POS_BENEFIT_TYP_CD ))
ADD (CONSTRAINT LD_LBR_OBJ_BENE_TR4 FOREIGN KEY (
      FIN_COA_CD )
REFERENCES CA_CHART_T (
      FIN_COA_CD ))
ADD (CONSTRAINT LD_LBR_OBJ_BENE_TR5 FOREIGN KEY (
      UNIV_FISCAL_YR )
REFERENCES FS_OPTION_T (
      UNIV_FISCAL_YR ))
/