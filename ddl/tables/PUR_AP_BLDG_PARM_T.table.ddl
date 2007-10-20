CREATE TABLE PUR_AP_BLDG_PARM_T(
        CAMPUS_CD                      VARCHAR2(2) CONSTRAINT PUR_AP_BLDG_PARM_TN1 NOT NULL,
        BLDG_CD                        VARCHAR2(10) CONSTRAINT PUR_AP_BLDG_PARM_TN2 NOT NULL,
        BLDG_OVRD_CD                   VARCHAR2(4) CONSTRAINT PUR_AP_BLDG_PARM_TN3 NOT NULL, 
        OBJ_ID                         VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT PUR_AP_BLDG_PARM_TN4 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT PUR_AP_BLDG_PARM_TN5 NOT NULL,
        BLDG_OVRD_NM                   VARCHAR2(45),
        BLDG_OVRD_STR_ADDR             VARCHAR2(45),
        BLDG_OVRD_ADDR_CTY_NM          VARCHAR2(45), 
        BLDG_OVRD_ADDR_ST_CD           VARCHAR2(2), 
        BLDG_OVRD_ADDR_ZIP_CD          VARCHAR2(11),  
     CONSTRAINT PUR_AP_BLDG_PARM_TP1 PRIMARY KEY (
        CAMPUS_CD,
        BLDG_CD,
        BLDG_OVRD_CD),
     CONSTRAINT PUR_AP_BLDG_PARM_TC0 UNIQUE (OBJ_ID)
)
/
