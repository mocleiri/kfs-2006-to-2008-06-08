ALTER TABLE PDP_DISB_NBR_RNG_T
ADD (CONSTRAINT PDP_DISB_NBR_RNG_TR1 FOREIGN KEY (
      BNK_ID )
REFERENCES PDP_BNK_CD_T (
      BNK_ID ) ON DELETE SET NULL )
/