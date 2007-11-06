CREATE TABLE PDP_PMT_DTL_T(
        PMT_DTL_ID                     NUMBER(8) CONSTRAINT PDP_PMT_DTL_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) DEFAULT SYS_GUID(),
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT PDP_PMT_DTL_TN3 NOT NULL, 
        CUST_PMT_DOC_NBR               VARCHAR2(14),
        LST_UPDT_TS                    DATE CONSTRAINT PDP_PMT_DTL_TN4 NOT NULL,
        INV_NBR                        VARCHAR2(25),
        PO_NBR                         VARCHAR2(9),
        INV_DT                         DATE CONSTRAINT PDP_PMT_DTL_TN5 NOT NULL,
        PMT_GRP_ID                     NUMBER(8),
        REQS_NBR                       VARCHAR2(8),
        ORG_DOC_NBR                    VARCHAR2(10),
        ORIG_INV_AMT                   NUMBER(14,2) CONSTRAINT PDP_PMT_DTL_TN6 NOT NULL,
        NET_PMT_AMT                    NUMBER(14,2) CONSTRAINT PDP_PMT_DTL_TN7 NOT NULL,
        INV_TOT_DSCT_AMT               NUMBER(14,2) CONSTRAINT PDP_PMT_DTL_TN8 NOT NULL,
        INV_TOT_SHP_AMT                NUMBER(14,2) CONSTRAINT PDP_PMT_DTL_TN9 NOT NULL,
        INV_TOT_OTHR_DEBIT_AMT         NUMBER(14,2) CONSTRAINT PDP_PMT_DTL_TN10 NOT NULL,
        INV_TOT_OTHR_CRDT_AMT          NUMBER(14,2) CONSTRAINT PDP_PMT_DTL_TN11 NOT NULL,
        PDP_PRM_PMT_CNCL_IND           VARCHAR2(1),   
        FDOC_TYP_CD                    VARCHAR2(4),   
     CONSTRAINT PDP_PMT_DTL_TP1 PRIMARY KEY (
        PMT_DTL_ID)
)
/
