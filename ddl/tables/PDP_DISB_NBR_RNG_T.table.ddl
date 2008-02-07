CREATE TABLE PDP_DISB_NBR_RNG_T(
        DISB_NBR_RNG_ID                NUMBER(8) CONSTRAINT PDP_DISB_NBR_RNG_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36),
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT PDP_DISB_NBR_RNG_TN3 NOT NULL, 
        BNK_ID                         NUMBER(8),
        PHYS_CMP_PROC_CD               VARCHAR2(2) CONSTRAINT PDP_DISB_NBR_RNG_TN4 NOT NULL,
        BEG_DISB_NBR                   NUMBER(9),
        LST_ASND_DISB_NBR              NUMBER(9),
        END_DISB_NBR                   NUMBER(9),
        DISB_NBR_EFF_DT                DATE,
        DISB_NBR_EXPR_DT               DATE,
        LST_UPDT_TS                    DATE CONSTRAINT PDP_DISB_NBR_RNG_TN5 NOT NULL,
        LST_UPDT_USR_ID                VARCHAR2(11) CONSTRAINT PDP_DISB_NBR_RNG_TN6 NOT NULL,
     CONSTRAINT PDP_DISB_NBR_RNG_TP1 PRIMARY KEY (
        DISB_NBR_RNG_ID)
)
/
