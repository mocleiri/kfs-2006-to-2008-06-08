CREATE TABLE QRTZ_TRIGGERS 
  ( 
    TRIGGER_NAME VARCHAR2(80) NOT NULL, 
    TRIGGER_GROUP VARCHAR2(80) NOT NULL, 
    JOB_NAME VARCHAR2(80) NOT NULL, 
    JOB_GROUP VARCHAR2(80) NOT NULL, 
    IS_VOLATILE VARCHAR2(1) NOT NULL, 
    DESCRIPTION VARCHAR2(120) NULL, 
    NEXT_FIRE_TIME NUMBER(13) NULL, 
    PREV_FIRE_TIME NUMBER(13) NULL, 
    PRIORITY NUMBER(13) NULL, 
    TRIGGER_STATE VARCHAR2(16) NOT NULL, 
    TRIGGER_TYPE VARCHAR2(8) NOT NULL, 
    START_TIME NUMBER(13) NOT NULL, 
    END_TIME NUMBER(13) NULL, 
    CALENDAR_NAME VARCHAR2(80) NULL, 
    MISFIRE_INSTR NUMBER(2) NULL, 
    JOB_DATA BLOB NULL, 
    PRIMARY KEY (TRIGGER_NAME,TRIGGER_GROUP), 
    FOREIGN KEY (JOB_NAME,JOB_GROUP) 
REFERENCES QRTZ_JOB_DETAILS(JOB_NAME,JOB_GROUP) 
) 
/