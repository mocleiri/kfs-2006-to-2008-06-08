ALTER TABLE PUR_PO_CM_LOC_T
ADD (CONSTRAINT PUR_PO_CM_LOC_TR1 FOREIGN KEY (
      CAMPUS_CD )
REFERENCES SH_CAMPUS_T (
      CAMPUS_CD ))
/
