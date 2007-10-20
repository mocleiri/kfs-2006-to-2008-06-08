ALTER TABLE CA_SUB_OBJ_CD_CHG_DOC_T
  ADD (CONSTRAINT CA_SUB_OBJ_CD_CHG_DOC_TR1 FOREIGN KEY (
        FDOC_NBR)
  REFERENCES FP_DOC_HEADER_T (
        FDOC_NBR))
  ADD (CONSTRAINT CA_SUB_OBJ_CD_CHG_DOC_TR2 FOREIGN KEY (
        UNIV_FISCAL_YR)
  REFERENCES FS_OPTION_T (
        UNIV_FISCAL_YR))
  ADD (CONSTRAINT CA_SUB_OBJ_CD_CHG_DOC_TR3 FOREIGN KEY (
        FIN_COA_CD)
  REFERENCES CA_CHART_T (
        FIN_COA_CD))
/