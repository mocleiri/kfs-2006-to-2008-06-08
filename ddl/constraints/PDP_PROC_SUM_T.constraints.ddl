ALTER TABLE PDP_PROC_SUM_T
ADD (CONSTRAINT PDP_PROC_SUM_TR1 FOREIGN KEY (
      PROC_ID )
REFERENCES PDP_PROC_T (
      PROC_ID ) ON DELETE SET NULL )
ADD (CONSTRAINT PDP_PROC_SUM_TR2 FOREIGN KEY (
      CUST_ID )
REFERENCES PDP_CUST_PRFL_T (
      CUST_ID ) ON DELETE SET NULL )
ADD (CONSTRAINT PDP_PROC_SUM_TR3 FOREIGN KEY (
      DISB_TYP_CD )
REFERENCES PDP_DISB_TYP_CD_T (
      DISB_TYP_CD ) ON DELETE SET NULL )
/