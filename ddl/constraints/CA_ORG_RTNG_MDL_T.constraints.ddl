ALTER TABLE CA_ORG_RTNG_MDL_T
ADD (CONSTRAINT CA_ORG_RTNG_MDL_TR1 FOREIGN KEY (
      FIN_COA_CD )
REFERENCES CA_CHART_T (
      FIN_COA_CD ))
ADD (CONSTRAINT CA_ORG_RTNG_MDL_TR2 FOREIGN KEY (
      FIN_COA_CD, ORG_CD, ORG_RTNG_MDL_NM )
REFERENCES CA_ORG_RTNG_MDL_NM_T (
      FIN_COA_CD, ORG_CD, ORG_RTNG_MDL_NM ))
ADD (CONSTRAINT CA_ORG_RTNG_MDL_TR3 FOREIGN KEY (
      FDOC_TYP_CD )
REFERENCES FP_DOC_TYPE_T (
      FDOC_TYP_CD ))
/