CREATE TABLE PUR_RCVNG_LN_ITM_T(
        RCVNG_LN_ITM_ID                NUMBER(10) CONSTRAINT PUR_RCVNG_LN_ITM_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT PUR_RCVNG_LN_ITM_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT PUR_RCVNG_LN_ITM_TN3 NOT NULL,
        FDOC_NBR                       VARCHAR2(14),
        PO_ID                          NUMBER(9), 
        ITM_LN_NBR                     NUMBER(3),
        ITM_TYP_CD                     VARCHAR2(4),
        ITM_UOM_CD                     VARCHAR2(4),
        ITM_ORD_QTY                    NUMBER(11,2),
        ITM_CATLG_NBR                  VARCHAR2(30),
        ITM_DESC                       VARCHAR2(4000),
        ITM_RCVD_TOT_QTY               NUMBER(11,2),
        ITM_RTRN_TOT_QTY               NUMBER(11,2),
        ITM_DMGED_TOT_QTY              NUMBER(11,2),
        ITM_REAS_ADD_CD                VARCHAR2(4),
     CONSTRAINT PUR_RCVNG_LN_ITM_TP1 PRIMARY KEY (
        RCVNG_LN_ITM_ID),
     CONSTRAINT PUR_RCVNG_LN_ITM_TC0 UNIQUE (OBJ_ID)
)
/
