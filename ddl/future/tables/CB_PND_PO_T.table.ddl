CREATE TABLE CB_PND_PO_T(
        PO_NUMBER                      VARCHAR2(9) CONSTRAINT CB_PND_PO_TN1 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT CB_PND_PO_TN2 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT CB_PND_PO_TN3 NOT NULL,
        REQ_NUMBER                     VARCHAR2(8),
        DEPT_REP                       VARCHAR2(40),
        DELIVER_TO_NAME                VARCHAR2(40),
        DELIVER_TO_ADDR1               VARCHAR2(40),
        DELIVER_TO_ADDR2               VARCHAR2(40),
        REQUEST_NAME                   VARCHAR2(40),
        REQUEST_ADDR1                  VARCHAR2(40),
        REQUEST_ADDR2                  VARCHAR2(40),
        RQSTR_PRSN_EMAIL_ADDR          VARCHAR2(100),
        RQSTR_PRSN_PHN_NBR             VARCHAR2(45),
     CONSTRAINT CB_PND_PO_TP1 PRIMARY KEY (
        PO_NUMBER),
     CONSTRAINT CB_PND_PO_TC0 UNIQUE (OBJ_ID)
)
/
