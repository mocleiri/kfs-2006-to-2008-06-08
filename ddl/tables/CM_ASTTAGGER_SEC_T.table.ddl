CREATE TABLE CM_ASTTAGGER_SEC_T(
        FIN_COA_CD                     VARCHAR2(2) CONSTRAINT CM_ASTTAGGER_SEC_TN1 NOT NULL,
        ORG_CD                         VARCHAR2(4) CONSTRAINT CM_ASTTAGGER_SEC_TN2 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT CM_ASTTAGGER_SEC_TN3 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT CM_ASTTAGGER_SEC_TN4 NOT NULL,
        USER_FIN_COA_CD                VARCHAR2(2),
        USER_ORG_CD                    VARCHAR2(4),
        SEC_FIN_COA_CD                 VARCHAR2(2),
        SEC_ORG_CD                     VARCHAR2(4),
        RPT_FIN_COA_CD                 VARCHAR2(2),
        RPT_ORG_CD                     VARCHAR2(4),
     CONSTRAINT CM_ASTTAGGER_SEC_TP1 PRIMARY KEY (
        FIN_COA_CD,
        ORG_CD),
     CONSTRAINT CM_ASTTAGGER_SEC_TC0 UNIQUE (OBJ_ID)
)
/
