ALTER TABLE FP_NCHK_DSBRSDOC_T
ADD (CONSTRAINT FP_NCHK_DSBRSDOC_TR1 FOREIGN KEY (
      FDOC_NBR )
REFERENCES FP_DOC_HEADER_T (
      FDOC_NBR ))
/