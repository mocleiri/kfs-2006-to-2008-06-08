ALTER TABLE GL_COR_CHG_GRP_T
ADD (CONSTRAINT GL_COR_CHG_GRP_TR1 FOREIGN KEY (
      FDOC_NBR )
REFERENCES GL_COR_DOC_T (
      FDOC_NBR ))
/