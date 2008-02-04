CREATE TABLE QRTZ_SCHEDULER_STATE 
  ( 
    INSTANCE_NAME VARCHAR2(80) NOT NULL, 
    LAST_CHECKIN_TIME NUMBER(13) NOT NULL, 
    CHECKIN_INTERVAL NUMBER(13) NOT NULL, 
    PRIMARY KEY (INSTANCE_NAME) 
) 
/