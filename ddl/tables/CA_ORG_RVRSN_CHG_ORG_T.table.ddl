CREATE TABLE CA_ORG_RVRSN_CHG_ORG_T(
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT CA_ORG_RVRSN_CHG_ORG_TN1 NOT NULL,
        FIN_COA_CD                     VARCHAR2(2) CONSTRAINT CA_ORG_RVRSN_CHG_ORG_TN2 NOT NULL,
        ORG_CD                         VARCHAR2(4) CONSTRAINT CA_ORG_RVRSN_CHG_ORG_TN3 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT CA_ORG_RVRSN_CHG_ORG_TN4 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT CA_ORG_RVRSN_CHG_ORG_TN5 NOT NULL,
     CONSTRAINT CA_ORG_RVRSN_CHG_ORG_TP1 PRIMARY KEY (
        FDOC_NBR,
        FIN_COA_CD,
        ORG_CD),
     CONSTRAINT CA_ORG_RVRSN_CHG_ORG_TC0 UNIQUE (OBJ_ID)
)
/
