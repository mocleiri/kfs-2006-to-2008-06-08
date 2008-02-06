ALTER TABLE CM_MULT_AST_DTL_T
ADD (CONSTRAINT CM_MULT_AST_DTL_TR1 FOREIGN KEY (
      CAMPUS_CD )
REFERENCES SH_CAMPUS_T (
      CAMPUS_CD ))
ADD (CONSTRAINT CM_MULT_AST_DTL_TR2 FOREIGN KEY (
      CAMPUS_CD, BLDG_CD )
REFERENCES SH_BUILDING_T (
      CAMPUS_CD, BLDG_CD ))
ADD (CONSTRAINT CM_MULT_AST_DTL_TR3 FOREIGN KEY (
      CAMPUS_CD, BLDG_CD, BLDG_ROOM_NBR )
REFERENCES SH_ROOM_T (
      CAMPUS_CD, BLDG_CD, BLDG_ROOM_NBR ))
ADD (CONSTRAINT CM_MULT_AST_DTL_TR4 FOREIGN KEY (
      AST_OFFCMP_ST_CD )
REFERENCES SH_STATE_T (
      POSTAL_STATE_CD ))
ADD (CONSTRAINT CM_MULT_AST_DTL_TR5 FOREIGN KEY ( 
      FDOC_NBR ) 
REFERENCES FP_DOC_HEADER_T ( 
      FDOC_NBR ))
ADD (CONSTRAINT CM_MULT_AST_DTL_TR6 FOREIGN KEY (
      AST_OFFCMP_CNTRY_CD )
REFERENCES SH_COUNTRY_T (
      POSTAL_CNTRY_CD ))
/
