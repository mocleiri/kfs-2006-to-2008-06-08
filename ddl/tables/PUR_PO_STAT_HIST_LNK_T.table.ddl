CREATE TABLE PUR_PO_STAT_HIST_LNK_T(
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT PUR_PO_STAT_HIST_LNK_TN1 NOT NULL,
        PO_STAT_HIST_ID                NUMBER(10) CONSTRAINT PUR_PO_STAT_HIST_LNK_TN2 NOT NULL,        
     CONSTRAINT PUR_PO_STAT_HIST_LNK_TP1 PRIMARY KEY (
        FDOC_NBR,
        PO_STAT_HIST_ID)
)
/
