CREATE TABLE CM_PRTG_T(
        PO_NUMBER                      VARCHAR2(9) CONSTRAINT CM_PRTG_TN1 NOT NULL,
        LINE_ITEM_NUMBER               NUMBER(12) CONSTRAINT CM_PRTG_TN2 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT CM_PRTG_TN3 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT CM_PRTG_TN4 NOT NULL,
        QUANTITY_INVOICED              NUMBER,
        CPTLAST_TYP_CD                 VARCHAR2(7),
        CPTLAST_MFR_NM                 VARCHAR2(40),
        CPTLAST_MFRMDL_NBR             VARCHAR2(25),
        VENDOR_NAME                    VARCHAR2(40),
        CPTLAST_TOPS_DESC              VARCHAR2(2000),
        ORG_DESC                       VARCHAR2(255),
        CPTLAST_ORG_TXT                VARCHAR2(255),
        ORG_INVN_NM                    VARCHAR2(40),
        AST_REP_UNVL_ID                VARCHAR2(10),
        FIN_COA_CD                     VARCHAR2(2),
        CM_PRTG_CRT_DT                 DATE,
        ORG_CD                         VARCHAR2(4),
     CONSTRAINT CM_PRTG_TP1 PRIMARY KEY (
        PO_NUMBER,
        LINE_ITEM_NUMBER),
     CONSTRAINT CM_PRTG_TC0 UNIQUE (OBJ_ID)
)
/
