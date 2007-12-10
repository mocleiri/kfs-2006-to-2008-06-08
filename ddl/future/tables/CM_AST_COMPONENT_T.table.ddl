CREATE TABLE CM_AST_COMPONENT_T(
        CPTLAST_NBR                    NUMBER(12) CONSTRAINT CM_AST_COMPONENT_TN1 NOT NULL,
        CACMP_NBR                      NUMBER(5) CONSTRAINT CM_AST_COMPONENT_TN2 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT CM_AST_COMPONENT_TN3 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT CM_AST_COMPONENT_TN4 NOT NULL,
        CACMP_DESC                     VARCHAR2(255),
        CACMP_CNTCTPHN_NBR             VARCHAR2(10),
        CACMP_COND_CD                  VARCHAR2(1),
        CACMP_EST_LFTM_LMT             NUMBER(5),
        CACMP_MFR_NM                   VARCHAR2(40),
        CACMP_MFR_MDL_NBR              VARCHAR2(25),
        CACMP_SERIAL_NBR               VARCHAR2(25),
        CACMP_ORG_TAG_NBR              VARCHAR2(8),
        CACMP_ORG_TXT                  VARCHAR2(255),
        CACMP_REPLACE_AMT              NUMBER(19, 2),
        CACMP_VENDOR_NM                VARCHAR2(40),
        CACMP_WRNTY_NBR                VARCHAR2(25),
        CACMP_WRNTYPHN_NBR             VARCHAR2(13),
        CACMP_WRNTYCNTC_NM             VARCHAR2(40),
        CACMP_WRNTY_PO_NBR             VARCHAR2(9),
        CACMP_WRNTY_BEG_DT             DATE,
        CACMP_WRNTY_END_DT             DATE,
        CACMP_WRNTY_TXT                VARCHAR2(40),
     CONSTRAINT CM_AST_COMPONENT_TP1 PRIMARY KEY (
        CPTLAST_NBR,
        CACMP_NBR),
     CONSTRAINT CM_AST_COMPONENT_TC0 UNIQUE (OBJ_ID)
)
/
