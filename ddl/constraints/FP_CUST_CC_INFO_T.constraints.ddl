ALTER TABLE FP_CUST_CC_INFO_T
ADD (CONSTRAINT FP_CUST_CC_INFO_TR1 FOREIGN KEY (
      FIDOC_CCRD_VNDR_NBR )
REFERENCES FP_CR_CARD_VNDR_T (
      FDOC_CCRD_VNDR_NBR ))
/