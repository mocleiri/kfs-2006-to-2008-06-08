ALTER TABLE PUR_ASGN_CONTR_MGR_T
ADD (CONSTRAINT PUR_ASGN_CONTR_MGR_TR1 FOREIGN KEY (
      FDOC_NBR )
REFERENCES FP_DOC_HEADER_T (
      FDOC_NBR ))
/
