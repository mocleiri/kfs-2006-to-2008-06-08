ALTER TABLE SH_BUILDING_T
ADD (CONSTRAINT SH_BUILDING_TR1 FOREIGN KEY (
      CAMPUS_CD )
REFERENCES SH_CAMPUS_T (
      CAMPUS_CD ))
ADD (CONSTRAINT SH_BUILDING_TR2 FOREIGN KEY (
      BLDG_ADDR_ST_CD )
REFERENCES SH_STATE_T (
      POSTAL_STATE_CD ))
ADD (CONSTRAINT SH_BUILDING_TR3 FOREIGN KEY (
      BLDG_ADDR_ZIP_CD )
REFERENCES SH_ZIP_CODE_T (
      POSTAL_ZIP_CD ))
/