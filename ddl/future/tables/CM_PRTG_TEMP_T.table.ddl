CREATE TABLE CM_PRTG_TEMP_T(
        PO_NUMBER                      VARCHAR2(9) CONSTRAINT CM_PRTG_TEMP_TN1 NOT NULL,
        LINE_ITEM_NUMBER               NUMBER(12) CONSTRAINT CM_PRTG_TEMP_TN2 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT CM_PRTG_TEMP_TN3 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT CM_PRTG_TEMP_TN4 NOT NULL,
        QUANTITY_INVOICED              NUMBER,
        VENDOR_NAME                    VARCHAR2(40),
        CPTLAST_TOPS_DESC              VARCHAR2(2000),
        ORG_INVN_NM                    VARCHAR2(40),
        CM_PRTG_CRT_DT                 DATE,
        FIN_COA_CD                     VARCHAR2(2),
        ORG_CD                         VARCHAR2(4),
     CONSTRAINT CM_PRTG_TEMP_TP1 PRIMARY KEY (
        PO_NUMBER,
        LINE_ITEM_NUMBER),
     CONSTRAINT CM_PRTG_TEMP_TC0 UNIQUE (OBJ_ID)
)
/
