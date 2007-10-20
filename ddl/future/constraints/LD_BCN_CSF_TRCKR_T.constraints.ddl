ALTER TABLE LD_BCN_CSF_TRCKR_T
ADD (CONSTRAINT LD_BCN_CSF_TRCKR_TR2 FOREIGN KEY (
      UNIV_FISCAL_YR, FIN_COA_CD, ACCOUNT_NBR, SUB_ACCT_NBR, FIN_OBJECT_CD, FIN_SUB_OBJ_CD, POSITION_NBR, EMPLID )
REFERENCES LD_PNDBC_APPTFND_T (
      UNIV_FISCAL_YR, FIN_COA_CD, ACCOUNT_NBR, SUB_ACCT_NBR, FIN_OBJECT_CD, FIN_SUB_OBJ_CD, POSITION_NBR, EMPLID ))
/