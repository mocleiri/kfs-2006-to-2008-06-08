ALTER TABLE CB_PND_INV_TEMP_T
ADD (CONSTRAINT CB_PND_INV_TEMP_TR1 FOREIGN KEY (
      CAMPUS_CODE )
REFERENCES SH_CAMPUS_T (
      CAMPUS_CD ))
/