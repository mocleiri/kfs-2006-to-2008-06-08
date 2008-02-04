CREATE TABLE AR_APPLICATION_DOC_T(
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT AR_APPLICATION_DOC_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT AR_APPLICATION_DOC_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT AR_APPLICATION_DOC_TN3 NOT NULL,
     CONSTRAINT AR_APPLICATION_DOC_TP1 PRIMARY KEY (
        FDOC_NBR),
     CONSTRAINT AR_APPLICATION_DOC_TC0 UNIQUE (OBJ_ID)
)
/