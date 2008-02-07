CREATE TABLE FP_CASHIER_ITM_IN_PROC_T(
        WRKGRP_NM                      VARCHAR2(70) CONSTRAINT FP_CASHIER_ITM_IN_PROC_TN1 NOT NULL,
        ITM_ID                         NUMBER(9) CONSTRAINT FP_CASHIER_ITM_IN_PROC_TN2 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT FP_CASHIER_ITM_IN_PROC_TN3 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT FP_CASHIER_ITM_IN_PROC_TN4 NOT NULL,
        ITM_AMT                        NUMBER(19, 2),
        ITM_RDCD_AMT                   NUMBER(19, 2),
        ITM_TOT_AMT                    NUMBER(19, 2),
        ITM_OPEN_DT                    DATE,
        ITM_CLOSED_DT                  DATE,
        ITM_DESC                       VARCHAR2(200),
     CONSTRAINT FP_CASHIER_ITM_IN_PROC_TP1 PRIMARY KEY (
        WRKGRP_NM,
        ITM_ID),
     CONSTRAINT FP_CASHIER_ITM_IN_PROC_TC0 UNIQUE (OBJ_ID)
)
/
