ALTER TABLE GL_SF_REBUILD_T
ADD (CONSTRAINT GL_SF_REBUILD_TR1 FOREIGN KEY (
      FIN_COA_CD )
REFERENCES CA_CHART_T (
      FIN_COA_CD ))
/