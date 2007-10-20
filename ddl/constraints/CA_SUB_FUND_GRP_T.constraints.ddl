ALTER TABLE CA_SUB_FUND_GRP_T
ADD (CONSTRAINT CA_SUB_FUND_GRP_TR1 FOREIGN KEY (
      FUND_GRP_CD )
REFERENCES CA_FUND_GRP_T (
      FUND_GRP_CD ))
ADD (CONSTRAINT CA_SUB_FUND_GRP_TR2 FOREIGN KEY (
      SUB_FUNDGRP_TYP_CD )
REFERENCES CA_SUB_FND_GRP_TYP_T (
      SUB_FUNDGRP_TYP_CD ))
ADD (CONSTRAINT CA_SUB_FUND_GRP_TR3 FOREIGN KEY (
      ACCT_RSTRC_STAT_CD )
REFERENCES CA_RESTRICT_STAT_T (
      ACCT_RSTRC_STAT_CD ))
/