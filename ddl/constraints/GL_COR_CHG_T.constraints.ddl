ALTER TABLE GL_COR_CHG_T
ADD (CONSTRAINT GL_COR_CHG_TR1 FOREIGN KEY (
      FDOC_NBR, GL_COR_CHG_GRP_LN_NBR )
REFERENCES GL_COR_CHG_GRP_T (
      FDOC_NBR, GL_COR_CHG_GRP_LN_NBR ))
/