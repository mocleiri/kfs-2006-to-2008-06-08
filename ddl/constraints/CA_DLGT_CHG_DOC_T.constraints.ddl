ALTER TABLE CA_DLGT_CHG_DOC_T
ADD (CONSTRAINT CA_DLGT_CHG_DOC_TR2 FOREIGN KEY (
      FDOC_TYP_CD )
REFERENCES FP_DOC_TYPE_T (
      FDOC_TYP_CD ))
/