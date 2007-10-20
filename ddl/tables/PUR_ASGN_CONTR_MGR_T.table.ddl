CREATE TABLE PUR_ASGN_CONTR_MGR_T(
        FDOC_NBR                       VARCHAR2(14) CONSTRAINT PUR_ASGN_CONTR_MGR_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT PUR_ASGN_CONTR_MGR_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT PUR_ASGN_CONTR_MGR_TN3 NOT NULL,
     CONSTRAINT PUR_ASGN_CONTR_MGR_TP1 PRIMARY KEY (
        FDOC_NBR),
     CONSTRAINT PUR_ASGN_CONTR_MGR_TC0 UNIQUE (OBJ_ID)
)
/
