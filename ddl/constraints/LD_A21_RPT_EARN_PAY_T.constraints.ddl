ALTER TABLE LD_A21_RPT_EARN_PAY_T
ADD (CONSTRAINT LD_A21_RPT_EARN_PAY_TR1 FOREIGN KEY (
      A21_LBR_RPT_TYP_CD )
REFERENCES LD_A21_RPT_TYP_T ( 
      A21_LBR_RPT_TYP_CD ))
/