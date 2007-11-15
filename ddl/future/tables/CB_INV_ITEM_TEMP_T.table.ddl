CREATE TABLE CB_INV_ITEM_TEMP_T(
        PO_NUMBER                      VARCHAR2(9) CONSTRAINT CB_INV_ITEM_TEMP_TN1 NOT NULL,
        INVOICE_NUMBER                 VARCHAR2(14) CONSTRAINT CB_INV_ITEM_TEMP_TN2 NOT NULL,
        LINE_ITEM_NUMBER               NUMBER(12) CONSTRAINT CB_INV_ITEM_TEMP_TN3 NOT NULL,
        OBJ_ID                         VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT CB_INV_ITEM_TEMP_TN4 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT CB_INV_ITEM_TEMP_TN5 NOT NULL,
        ITEM_EXP_CLASS                 VARCHAR2(4) CONSTRAINT CB_INV_ITEM_TEMP_TN6 NOT NULL,
        FIN_SUB_OBJ_CD                 VARCHAR2(3) CONSTRAINT CB_INV_ITEM_TEMP_TN7 NOT NULL,
        QUANTITY_INVOICED              NUMBER(6,6) CONSTRAINT CB_INV_ITEM_TEMP_TN8 NOT NULL,
        LINE_ITEM_COST                 NUMBER(19, 2) CONSTRAINT CB_INV_ITEM_TEMP_TN9 NOT NULL,
        LINE_ITEM_DISCOUNT             NUMBER(19, 2) CONSTRAINT CB_INV_ITEM_TEMP_TN10 NOT NULL,
        LINE_ITEM_ADDLCHG              NUMBER(19, 2) CONSTRAINT CB_INV_ITEM_TEMP_TN11 NOT NULL,
        MANUFACTURER_NM                VARCHAR2(40) CONSTRAINT CB_INV_ITEM_TEMP_TN12 NOT NULL,
        PURCH_COMM_CODE                VARCHAR2(7) CONSTRAINT CB_INV_ITEM_TEMP_TN13 NOT NULL,
        LINE_ITEM_DESC                 VARCHAR2(2000) CONSTRAINT CB_INV_ITEM_TEMP_TN15 NOT NULL,
        ITM_TYP_CD                     VARCHAR2(4),
        CPTL_AST_TRN_TYP_CD            VARCHAR2(4),
        CPTL_AST_TRN_TYP_SRVC_IND      CHAR(1),
        ITM_ASND_TO_TRADE_IN_IND       CHAR(1),
     CONSTRAINT CB_INV_ITEM_TEMP_TP1 PRIMARY KEY (
        PO_NUMBER,
        INVOICE_NUMBER,
        LINE_ITEM_NUMBER),
     CONSTRAINT CB_INV_ITEM_TEMP_TC0 UNIQUE (OBJ_ID)
)
/
