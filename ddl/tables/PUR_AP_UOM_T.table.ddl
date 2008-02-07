CREATE TABLE PUR_AP_UOM_T(
        ITM_UOM_CD                     VARCHAR2(4) CONSTRAINT PUR_AP_UOM_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT PUR_AP_UOM_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT PUR_AP_UOM_TN3 NOT NULL,
        ITM_UOM_DESC                   VARCHAR2(45),
        DOBJ_MAINT_CD_ACTV_IND         VARCHAR2(1) CONSTRAINT PUR_AP_UOM_TN4 NOT NULL,
     CONSTRAINT PUR_AP_UOM_TP1 PRIMARY KEY (
        ITM_UOM_CD),
     CONSTRAINT PUR_AP_UOM_TC0 UNIQUE (OBJ_ID)
)
/
