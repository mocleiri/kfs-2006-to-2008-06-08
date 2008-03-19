CREATE TABLE CB_PND_ACTION_T(
        ACTION_CODE                    VARCHAR2(2) CONSTRAINT CB_PND_ACTION_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT CB_PND_ACTION_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT CB_PND_ACTION_TN3 NOT NULL,
        ACTION_DESC                    VARCHAR2(20),
        ROW_ACTV_IND                   VARCHAR2(1) CONSTRAINT CB_PND_ACTION_TN4 NOT NULL, 
     CONSTRAINT CB_PND_ACTION_TP1 PRIMARY KEY (
        ACTION_CODE),
     CONSTRAINT CB_PND_ACTION_TC0 UNIQUE (OBJ_ID)
)
/