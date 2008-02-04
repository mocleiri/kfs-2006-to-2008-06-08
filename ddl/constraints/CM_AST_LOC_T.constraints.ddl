ALTER TABLE CM_AST_LOC_T
ADD (CONSTRAINT CM_AST_LOC_TR1 FOREIGN KEY (
      CPTLAST_NBR )
REFERENCES CM_CPTLAST_T (
      CPTLAST_NBR ))
ADD (CONSTRAINT CM_AST_LOC_TR2 FOREIGN KEY (
      AST_LOC_TYP_CD )
REFERENCES CM_AST_LOC_TYP_T (
      CPTLAST_LOC_TYP_CD ))
/