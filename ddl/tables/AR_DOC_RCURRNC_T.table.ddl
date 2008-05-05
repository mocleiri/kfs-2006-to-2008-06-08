CREATE TABLE AR_DOC_RCURRNC_T(
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT AR_DOC_RCURRNC_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT AR_DOC_RCURRNC_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT AR_DOC_RCURRNC_TN3 NOT NULL,
        FDOC_REF_NBR                   VARCHAR2(14),
        CUST_NBR                       VARCHAR2(9),
        DOC_RCURRNC_BEG_DT             DATE, 
        DOC_RCURRNC_END_DT             DATE, 
        DOC_TOT_RCURRNC_NBR            NUMBER(7), 
        DOC_RCURRNC_INTRV_CD           VARCHAR2(1),
        WRKGRP_ID                      NUMBER(19),  
        DOC_INITR_USR_ID               VARCHAR2(10), 
        DOC_LST_CRTE_DT                DATE, 
        ROW_ACTV_IND                   VARCHAR2(1) CONSTRAINT AR_DOC_RCURRNC_TN4 NOT NULL,
     CONSTRAINT AR_DOC_RCURRNC_TP1 PRIMARY KEY (
        FDOC_NBR),
     CONSTRAINT AR_DOC_RCURRNC_TC0 UNIQUE (OBJ_ID)
)
/
