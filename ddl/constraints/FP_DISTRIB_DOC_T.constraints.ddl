ALTER TABLE FP_DISTRIB_DOC_T
ADD (CONSTRAINT FP_DISTRIB_DOC_TR1 FOREIGN KEY (
      FDOC_NBR )
REFERENCES FP_DOC_HEADER_T (
      FDOC_NBR ))
/