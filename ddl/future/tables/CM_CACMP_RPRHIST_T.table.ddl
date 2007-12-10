CREATE TABLE CM_CACMP_RPRHIST_T(
        CPTLAST_NBR                    NUMBER(12) CONSTRAINT CM_CACMP_RPRHIST_TN1 NOT NULL,
        CACMP_NBR                      NUMBER(5) CONSTRAINT CM_CACMP_RPRHIST_TN2 NOT NULL,
        CACMP_INCIDENT_DT              DATE CONSTRAINT CM_CACMP_RPRHIST_TN3 NOT NULL,
        OBJ_ID                         VARCHAR2(36) CONSTRAINT CM_CACMP_RPRHIST_TN4 NOT NULL,
        VER_NBR                        NUMBER(8) DEFAULT 1 CONSTRAINT CM_CACMP_RPRHIST_TN5 NOT NULL,
        CACMP_EST_RPR_DT               DATE,
        CACMP_PROBLEM_DESC             VARCHAR2(255),
        CACMP_RPR_AMT                  NUMBER(19, 2),
        CACMP_RPR_CNTCT_NM             VARCHAR2(40),
        CACMP_RPR_DT                   DATE,
        CACMP_RPR_NT_TXT               VARCHAR2(255),
        CACMP_RPRSOLN_DESC             VARCHAR2(255),
     CONSTRAINT CM_CACMP_RPRHIST_TP1 PRIMARY KEY (
        CPTLAST_NBR,
        CACMP_NBR,
        CACMP_INCIDENT_DT),
     CONSTRAINT CM_CACMP_RPRHIST_TC0 UNIQUE (OBJ_ID)
)
/
