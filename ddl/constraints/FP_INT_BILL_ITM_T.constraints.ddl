ALTER TABLE FP_INT_BILL_ITM_T
ADD (CONSTRAINT FP_INT_BILL_ITM_TR1 FOREIGN KEY (
      FDOC_NBR )
REFERENCES FP_INT_BILL_DOC_T (
      FDOC_NBR ))
/