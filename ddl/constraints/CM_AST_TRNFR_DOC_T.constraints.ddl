ALTER TABLE CM_AST_TRNFR_DOC_T
ADD (CONSTRAINT CM_AST_TRNFR_DOC_TR1 FOREIGN KEY (
      FDOC_NBR )
REFERENCES FP_DOC_HEADER_T (
      FDOC_NBR ))
ADD (CONSTRAINT CM_AST_TRNFR_DOC_TR2 FOREIGN KEY (
      CAMPUS_CD )
REFERENCES SH_CAMPUS_T (
      CAMPUS_CD ))
ADD (CONSTRAINT CM_AST_TRNFR_DOC_TR3 FOREIGN KEY (
      ORG_OWNER_COA_CD, ORG_OWNER_ACCT_NBR )
REFERENCES CA_ACCOUNT_T (
      FIN_COA_CD, ACCOUNT_NBR ))
ADD (CONSTRAINT CM_AST_TRNFR_DOC_TR4 FOREIGN KEY (
      ORG_OWNER_COA_CD )
REFERENCES CA_CHART_T (
      FIN_COA_CD ))
ADD (CONSTRAINT CM_AST_TRNFR_DOC_TR5 FOREIGN KEY (
      ORG_OWNER_COA_CD, ORG_CD )
REFERENCES CA_ORG_T (
      FIN_COA_CD, ORG_CD ))
ADD (CONSTRAINT CM_AST_TRNFR_DOC_TR6 FOREIGN KEY (
      TRNFR_FND_FDOC_NBR )
REFERENCES FP_DOC_HEADER_T (
      FDOC_NBR ))
ADD (CONSTRAINT CM_AST_TRNFR_DOC_TR7 FOREIGN KEY (
      AST_OFFCMP_ST_CD )
REFERENCES SH_STATE_T (
      POSTAL_STATE_CD ))
ADD (CONSTRAINT CM_AST_TRNFR_DOC_TR8 FOREIGN KEY (
      CAMPUS_CD, BLDG_CD )
REFERENCES SH_BUILDING_T (
      CAMPUS_CD, BLDG_CD ))
ADD (CONSTRAINT CM_AST_TRNFR_DOC_TR9 FOREIGN KEY (
      CAMPUS_CD, BLDG_CD, BLDG_ROOM_NBR )
REFERENCES SH_ROOM_T (
      CAMPUS_CD, BLDG_CD, BLDG_ROOM_NBR ))
ADD (CONSTRAINT CM_AST_TRNFR_DOC_TR10 FOREIGN KEY (
      CPTLAST_NBR )
REFERENCES CM_CPTLAST_T (
      CPTLAST_NBR ))
/
