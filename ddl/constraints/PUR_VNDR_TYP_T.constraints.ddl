ALTER TABLE PUR_VNDR_TYP_T
ADD (CONSTRAINT PUR_VNDR_TYP_TR1 FOREIGN KEY (
      VNDR_ADDR_TYP_REQ_CD )
REFERENCES PUR_ADDR_TYP_T (
      VNDR_ADDR_TYP_CD ))
/