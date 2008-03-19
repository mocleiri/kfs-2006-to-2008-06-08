ALTER TABLE CA_OBJECT_CODE_T
ADD (CONSTRAINT CA_OBJECT_CODE_TR2 FOREIGN KEY (
      FOBJ_BDGT_AGGR_CD )
REFERENCES CA_BDGT_AGGR_T (
      FOBJ_BDGT_AGGR_CD ))
ADD (CONSTRAINT CA_OBJECT_CODE_TR3 FOREIGN KEY (
      FOBJ_MNXFR_ELIM_CD )
REFERENCES CA_MNXFR_ELIM_T (
      FOBJ_MNXFR_ELIM_CD ))
ADD (CONSTRAINT CA_OBJECT_CODE_TR4 FOREIGN KEY (
      FIN_FED_FUNDED_CD )
REFERENCES CA_FED_FND_T (
      FIN_FED_FUNDED_CD ))
ADD (CONSTRAINT CA_OBJECT_CODE_TR5 FOREIGN KEY (
      UNIV_FISCAL_YR, FIN_COA_CD, NXT_YR_FIN_OBJ_CD )
REFERENCES CA_OBJECT_CODE_T (
      UNIV_FISCAL_YR, FIN_COA_CD, FIN_OBJECT_CD ))
ADD (CONSTRAINT CA_OBJECT_CODE_TR6 FOREIGN KEY (
      FIN_COA_CD, FIN_OBJ_LEVEL_CD )
REFERENCES CA_OBJ_LEVEL_T (
      FIN_COA_CD, FIN_OBJ_LEVEL_CD ))
ADD (CONSTRAINT CA_OBJECT_CODE_TR7 FOREIGN KEY (
      FIN_COA_CD )
REFERENCES CA_CHART_T (
      FIN_COA_CD ))
ADD (CONSTRAINT CA_OBJECT_CODE_TR8 FOREIGN KEY (
      RPTS_TO_FIN_COA_CD )
REFERENCES CA_CHART_T (
      FIN_COA_CD ))
ADD (CONSTRAINT CA_OBJECT_CODE_TR10 FOREIGN KEY (
      FIN_OBJ_TYP_CD )
REFERENCES CA_OBJ_TYPE_T (
      FIN_OBJ_TYP_CD ))
ADD (CONSTRAINT CA_OBJECT_CODE_TR11 FOREIGN KEY (
      FIN_OBJ_SUB_TYP_CD )
REFERENCES CA_OBJ_SUB_TYPE_T (
      FIN_OBJ_SUB_TYP_CD ))
ADD (CONSTRAINT CA_OBJECT_CODE_TR12 FOREIGN KEY (
      UNIV_FISCAL_YR )
REFERENCES FS_OPTION_T (
      UNIV_FISCAL_YR ))
/