ALTER TABLE AR_NON_INV_DIST_T
ADD (CONSTRAINT AR_NON_INV_DIST_TR1 FOREIGN KEY (
      FDOC_REF_NBR )
REFERENCES AR_NON_APLD_HLDG_T (
      FDOC_REF_NBR ))
ADD (CONSTRAINT AR_NON_INV_DIST_TR2 FOREIGN KEY (
      FDOC_NBR )
REFERENCES AR_DOC_HDR_T ( 
      FDOC_NBR ))
/
