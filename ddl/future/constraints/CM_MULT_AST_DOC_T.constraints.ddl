ALTER TABLE CM_MULT_AST_DOC_T
ADD (CONSTRAINT CM_MULT_AST_DOC_TR1 FOREIGN KEY (
      FS_ORIGIN_CD,
      FDOC_NBR )
REFERENCES FP_DOC_HEADER_T ( 
      FS_ORIGIN_CD,
      FDOC_NBR ))
/
