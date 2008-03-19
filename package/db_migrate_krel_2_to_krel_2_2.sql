
ALTER TABLE EN_DOC_TYP_T ADD DOC_TYP_SECURITY_XML CLOB
/

ALTER TABLE EN_RULE_BASE_VAL_T ADD RULE_NM VARCHAR2(256) NULL
/

ALTER TABLE EN_RULE_TMPL_ATTRIB_T ADD ACTV_IND NUMBER(1) DEFAULT 1 NOT NULL
/

ALTER TABLE EN_RULE_TMPL_ATTRIB_T MODIFY ACTV_IND NUMBER(1) DEFAULT NULL
/

CREATE TABLE EN_EDL_DMP_T (
	DOC_HDR_ID NUMBER(14) NOT NULL,
	DOC_TYP_NM VARCHAR2(255) NOT NULL,
	DOC_RTE_STAT_CD CHAR(1) NOT NULL,
	DOC_MDFN_DT DATE NOT NULL,
	DOC_CRTE_DT DATE NOT NULL,
	DOC_TTL VARCHAR2(255) NULL,
	DOC_INITR_ID VARCHAR2(30) NOT NULL,
	DOC_CRNT_NODE_NM VARCHAR2(30) NOT NULL,
	DB_LOCK_VER_NBR	NUMBER(8) DEFAULT 0,
	CONSTRAINT EN_EDL_DMP_PK PRIMARY KEY (DOC_HDR_ID)
)
/

CREATE TABLE EN_EDL_FIELD_DMP_T (
	EDL_FIELD_DMP_ID NUMBER(14) NOT NULL,
	DOC_HDR_ID NUMBER(14) NOT NULL,
	FLD_NM VARCHAR2(255) NOT NULL,
	FLD_VAL VARCHAR2(4000) NULL,
	DB_LOCK_VER_NBR	NUMBER(8) DEFAULT 0,
	CONSTRAINT EN_EDL_FIELD_DMP_T_PK PRIMARY KEY (EDL_FIELD_DMP_ID)
)
/

CREATE TABLE EN_RMV_RPLC_DOC_T (
	DOC_HDR_ID			NUMBER(14) NOT NULL,
	OPRN				CHAR(1) NOT NULL,
	PRSN_EN_ID			VARCHAR2(30) NOT NULL,
	RPLC_PRSN_EN_ID     VARCHAR2(30) NULL,
	DB_LOCK_VER_NBR		NUMBER(8) DEFAULT 0,
	CONSTRAINT EN_RMV_RPLC_DOC_T_PK PRIMARY KEY (DOC_HDR_ID)
)
/

CREATE TABLE EN_RMV_RPLC_RULE_T (
	DOC_HDR_ID			NUMBER(14) NOT NULL,
	RULE_ID				NUMBER(19) NOT NULL,
	CONSTRAINT EN_RMV_RPLC_RULE_T_PK PRIMARY KEY (DOC_HDR_ID, RULE_ID)
)
/

CREATE TABLE EN_RMV_RPLC_WRKGRP_T (
	DOC_HDR_ID			NUMBER(14) NOT NULL,
	WRKGRP_ID			NUMBER(14) NOT NULL,
	CONSTRAINT EN_RMV_RPLC_WRKGRP_T_PK PRIMARY KEY (DOC_HDR_ID, WRKGRP_ID)
)
/

CREATE INDEX EN_MSG_QUE_TI1 ON EN_MSG_QUE_T (MESSAGE_SERVICE_NM, SERVICE_METHOD_NM)
/
CREATE INDEX EN_MSG_QUE_TI2 ON EN_MSG_QUE_T (MESSAGE_ENTITY_NM, MESSAGE_QUE_STAT_CD, MESSAGE_QUE_IP_NBR, MESSAGE_QUE_DT)
/


INSERT INTO sh_parm_dtl_typ_t
("SH_PARM_NMSPC_CD","SH_PARM_DTL_TYP_CD","OBJ_ID","VER_NBR","SH_PARM_DTL_TYP_NM","ACTIVE_IND")
VALUES
('KR-NS','N/A','4C4AE503F3644C97B6C90540B10751CA',1,'Not Applicable','Y')
/




INSERT INTO sh_parm_t
("SH_PARM_NMSPC_CD","SH_PARM_DTL_TYP_CD","SH_PARM_NM","OBJ_ID","VER_NBR","SH_PARM_TYP_CD","SH_PARM_TXT","SH_PARM_DESC","SH_PARM_CONS_CD","WRKGRP_NM")
VALUES
('KR-NS','Document','DEFAULT_CAN_PERFORM_ROUTE_REPORT','8DE1B1CC0D2D413DAD28C845940F0119',1,'CONFG','N','If Y, the "route report" button will be displayed on the document actions bar if the document is using the default DocumentAuthorizerBase.getDocumentActionFlags to set the canPerformRouteReport property of the returned DocumentActionFlags instance.','A','WorkflowAdmin')
/

INSERT INTO SH_PARM_T
(SH_PARM_NMSPC_CD, SH_PARM_DTL_TYP_CD, SH_PARM_NM, SH_PARM_TYP_CD, SH_PARM_TXT, SH_PARM_DESC, SH_PARM_CONS_CD, WRKGRP_NM,OBJ_ID)
VALUES
('KR-NS', 'All', 'ENABLE_DIRECT_INQUIRIES_IND', 'CONFG', 'Y', 'Flag for enabling;disabling direct inquiries on screens that are drawn by the nervous system (i.e. lookups and maintenance documents)', 'A', 'WorkflowAdmin',SYS_GUID())
/

ALTER TABLE pdp_payee_ach_acct_t MODIFY ( bnk_acct_nbr VARCHAR2(255) )
/

ALTER TABLE pdp_bnk_cd_t MODIFY ( bnk_acct_nbr VARCHAR2(255) )
/

ALTER TABLE pdp_ach_acct_nbr_t MODIFY ( ach_bnk_acct_nbr VARCHAR2(255) )
/

ALTER TABLE sh_univ_date_t DROP CONSTRAINT sh_univ_date_tr2
/

ALTER TABLE fp_dv_doc_t ADD (
    dv_extrt_dt DATE
,   dv_pd_dt DATE
,   dv_cncl_dt DATE
)
/

CREATE TABLE EN_OUT_BOX_ITM_T (
    ACTN_ITM_ID             NUMBER(14) NOT NULL,
    ACTN_ITM_PRSN_EN_ID     VARCHAR2(30) NOT NULL,
    ACTN_ITM_ASND_DT        DATE NOT NULL,
    ACTN_ITM_RQST_CD        CHAR(1) NOT NULL,
    ACTN_RQST_ID            NUMBER(14) NOT NULL,
    DOC_HDR_ID              NUMBER(14) NOT NULL,
    WRKGRP_ID               NUMBER(14) NULL,
    ROLE_NM                 VARCHAR2(2000) NULL,
    ACTN_ITM_DLGN_PRSN_EN_ID VARCHAR2(30) NULL,
    ACTN_ITM_DLGN_WRKGRP_ID NUMBER(14) NULL,
    DOC_TTL                 VARCHAR2(255) NULL,
    DOC_TYP_LBL_TXT         VARCHAR2(255) NOT NULL,
    DOC_TYP_HDLR_URL_ADDR   VARCHAR2(255) NOT NULL,
    DOC_TYP_NM              VARCHAR2(255) NOT NULL,
    ACTN_ITM_RESP_ID        NUMBER(14) NOT NULL,
    DLGN_TYP                VARCHAR2(1) NULL,
    DB_LOCK_VER_NBR         NUMBER(8) DEFAULT 0,
    CONSTRAINT EN_OUT_BOX_ITM_T_PK PRIMARY KEY (ACTN_ITM_ID)
)
/

CREATE TABLE EN_RTE_NODE_CFG_PARM_T (
    RTE_NODE_CFG_PARM_ID    NUMBER(19) NOT NULL,
    RTE_NODE_CFG_PARM_ND    NUMBER(19) NOT NULL,
    RTE_NODE_CFG_PARM_KEY   VARCHAR2(255) NOT NULL,
    RTE_NODE_CFG_PARM_VAL   VARCHAR2(4000),
    CONSTRAINT EN_RTE_NODE_CFG_PARM_T_PK PRIMARY KEY (RTE_NODE_CFG_PARM_ID)
)
/

CREATE TABLE EN_RULE_EXPR_T (
    RULE_EXPR_ID    NUMBER(19) NOT NULL,
    RULE_EXPR_TYP   VARCHAR(256) NOT NULL,
    RULE_EXPR       VARCHAR2(4000),
    CONSTRAINT EN_RULE_EXPR_T_PK PRIMARY KEY (RULE_EXPR_ID)
)
/

ALTER TABLE EN_RULE_BASE_VAL_T MODIFY RULE_TMPL_ID NUMBER(19) NULL
/
ALTER TABLE EN_RULE_BASE_VAL_T ADD RULE_EXPR_ID NUMBER(19) NULL
/

CREATE SEQUENCE SEQ_OUT_BOX_ITM INCREMENT BY 1 START WITH 10000
/
CREATE SEQUENCE SEQ_RTE_NODE_CFG_PARM INCREMENT BY 1 START WITH 2000
/
CREATE SEQUENCE SEQ_RULE_EXPR INCREMENT BY 1 START WITH 2000
/

CREATE INDEX EN_OUT_BOX_ITM_T1 ON EN_OUT_BOX_ITM_T (ACTN_ITM_PRSN_EN_ID)
/
CREATE INDEX EN_OUT_BOX_ITM_TI2 ON EN_OUT_BOX_ITM_T (DOC_HDR_ID)
/
CREATE INDEX EN_OUT_BOX_ITM_TI3 ON EN_OUT_BOX_ITM_T (ACTN_RQST_ID)
/
CREATE INDEX EN_OUT_BOX_ITM_TI4 ON EN_OUT_BOX_ITM_T (ACTN_ITM_DLGN_PRSN_EN_ID, ACTN_ITM_DLGN_WRKGRP_ID, ACTN_ITM_PRSN_EN_ID, DLGN_TYP)
/

ALTER TABLE EN_RTE_NODE_CFG_PARM_T ADD CONSTRAINT EN_RTE_NODE_CFG_PARM_TR1
FOREIGN KEY (RTE_NODE_CFG_PARM_ND)
REFERENCES EN_RTE_NODE_T (RTE_NODE_ID)
/

ALTER TABLE EN_RULE_BASE_VAL_T ADD CONSTRAINT EN_RULE_BASE_VAL_TR1
FOREIGN KEY (RULE_EXPR_ID)
REFERENCES EN_RULE_EXPR_T (RULE_EXPR_ID)
/
COMMIT
/