ALTER TABLE CM_AST_DEPR_CNVNTN_T
ADD (CONSTRAINT CM_AST_DEPR_CNVNTN_TR1 FOREIGN KEY (
      FIN_OBJ_SUB_TYPE_CD )
REFERENCES CA_OBJ_SUB_TYPE_T (
      FIN_OBJ_SUB_TYP_CD ))
/