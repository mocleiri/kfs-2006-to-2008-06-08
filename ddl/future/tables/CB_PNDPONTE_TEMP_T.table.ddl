CREATE TABLE CB_PNDPONTE_TEMP_T(
        PO_NUMBER                      VARCHAR2(9) CONSTRAINT CB_PNDPONTE_TEMP_TN1 NOT NULL,
        NOTE_LINE_NUMBER               NUMBER(12) CONSTRAINT CB_PNDPONTE_TEMP_TN2 NOT NULL,
        NOTE_TYP_CD                    VARCHAR2(1) CONSTRAINT CB_PNDPONTE_TEMP_TN3 NOT NULL,
        OBJ_ID                         VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT CB_PNDPONTE_TEMP_TN4 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT CB_PNDPONTE_TEMP_TN5 NOT NULL,
        NOTE_DESCRIPTION               VARCHAR2(800),
     CONSTRAINT CB_PNDPONTE_TEMP_TP1 PRIMARY KEY (
        PO_NUMBER,
        NOTE_LINE_NUMBER,
        NOTE_TYP_CD),
     CONSTRAINT CB_PNDPONTE_TEMP_TC0 UNIQUE (OBJ_ID)
)
/
