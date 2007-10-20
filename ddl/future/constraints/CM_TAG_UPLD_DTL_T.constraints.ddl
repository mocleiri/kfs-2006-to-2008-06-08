ALTER TABLE CM_TAG_UPLD_DTL_T
ADD (CONSTRAINT CM_TAG_UPLD_DTL_TR1 FOREIGN KEY (
      AST_UPLDR_UNVL_ID,
      AST_UPLDR_SEQ_NBR,
      AST_UPLD_ROW_NBR )
REFERENCES CM_TAG_ERR_DTL_T ( 
      AST_UPLDR_UNVL_ID,
      AST_UPLDR_SEQ_NBR,
      AST_UPLD_ROW_NBR ))
ADD (CONSTRAINT CM_TAG_UPLD_DTL_TR2 FOREIGN KEY (
      AST_UPLDR_UNVL_ID,
      AST_UPLDR_SEQ_NBR )
REFERENCES CM_TAG_UPLD_HDR_T ( 
      AST_UPLDR_UNVL_ID,
      AST_UPLDR_SEQ_NBR ))
/
