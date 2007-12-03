ALTER TABLE LD_A21_DETAIL_T
ADD (CONSTRAINT LD_A21_DETAIL_TR1 FOREIGN KEY (
      FDOC_NBR )
REFERENCES FP_DOC_HEADER_T (
      FDOC_NBR ))
ADD (CONSTRAINT LD_A21_DETAIL_TR2 FOREIGN KEY (
      FIN_COA_CD )
REFERENCES CA_CHART_T (
      FIN_COA_CD ))
ADD (CONSTRAINT LD_A21_DETAIL_TR3 FOREIGN KEY (
      A21_LBR_RPT_NBR, A21_LBR_FSCL_YR )
REFERENCES LD_A21_REPORT_T (
      A21_LBR_RPT_NBR, UNIV_FISCAL_YR ))
ADD (CONSTRAINT LD_A21_DETAIL_TR4 FOREIGN KEY (
      FIN_COA_CD, ORG_CD )
REFERENCES CA_ORG_T (
      FIN_COA_CD, ORG_CD ))
/