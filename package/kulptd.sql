spool /opt/oracle/exp/KUALI/kulptd_sql.log

/*
  update URLs
*/

UPDATE en_doc_typ_t SET doc_typ_hdlr_url_addr = 'http://kualitestdrive.org/kuali-ptd' || SUBSTR(doc_typ_hdlr_url_addr,instr(doc_typ_hdlr_url_addr,'/',instr(doc_typ_hdlr_url_addr,'/kuali-')+1)) WHERE doc_typ_hdlr_url_addr LIKE '%/kuali-%'
/
UPDATE en_doc_typ_t SET doc_typ_hdlr_url_addr = 'http://kualitestdrive.org/en-ptd' || SUBSTR(doc_typ_hdlr_url_addr,instr(doc_typ_hdlr_url_addr,'/',instr(doc_typ_hdlr_url_addr,'/en-')+1)) WHERE doc_typ_hdlr_url_addr LIKE '%/en-%'
/
update fs_origin_code_t set fs_server_nm = sys_context('USERENV','SESSION_USER') where fs_origin_cd = '01'
/

/*
  nullify doc_hdr_id in workflow
*/

update en_rule_base_val_t set doc_hdr_id=NULL
/
update en_wrkgrp_t set doc_hdr_id=-1
/
update en_doc_typ_t set doc_hdr_id=NULL
/

/*
  truncate transactional tables
*/

truncate table FP_ACCT_LINES_T
/
truncate table FP_ACRL_VCHR_DOC_T
/
truncate table FP_CASH_RCPT_DOC_T
/
truncate table FP_CHECKS_T
/
truncate table FP_DISTRIB_DOC_T
/
truncate table FP_ERROR_COR_DOC_T
/
truncate table FP_FND_TRNFR_DOC_T
/
truncate table FP_INT_BILL_DOC_T
/
truncate table FP_INT_BILL_ITM_T
/
truncate table FP_JRNL_VCHR_DOC_T
/
truncate table FP_MAINTENANCE_DOCUMENT_T
/
truncate table FP_NCHK_DSBRSDOC_T
/
truncate table FP_PRE_ENCUM_DOC_T
/
truncate table FP_ATT_T
/
truncate table FP_DOC_HEADER_T
/
truncate table FP_DOC_NOTE_T
/
truncate table FS_EXT_ATTRIB_OPT_T
/
truncate table FS_EXT_ATTRIB_T
/
truncate table FS_EXT_ATTRIB_VAL_T
/
truncate table GL_PENDING_ENTRY_T
/
truncate table GL_ORIGIN_ENTRY_GRP_T
/
truncate table GL_ORIGIN_ENTRY_T
/
truncate table EN_ACTN_ITM_T
/
truncate table EN_ACTN_RQST_T
/
truncate table EN_ACTN_TKN_T
/
truncate table EN_DOC_HDR_T
/
truncate table EN_INIT_RTE_NODE_INSTN_T
/
truncate table EN_RTE_NODE_INSTN_T
/
truncate table EN_RTE_NODE_INSTN_LNK_T
/
truncate table EN_RTE_NODE_INSTN_ST_T
/
truncate table EN_RTE_BRCH_T
/
truncate table EN_RTE_BRCH_ST_T
/
truncate table EN_DOC_HDR_EXT_T
/
truncate table EN_DOC_RTE_QUE_T
/
truncate table EN_DOC_NTE_T
/

/*
  grants & public synonyms
*/

set serveroutput on size 1000000
declare
  permissions varchar2(80);
  obj_count integer;
begin
for objects in (select object_name,object_type from user_objects where object_type in ('TABLE','VIEW','SEQUENCE'))
loop
  dbms_output.put_line(objects.object_name);

  if objects.object_type = 'TABLE' then
    permissions := 'select,insert,update,delete';
  else
    permissions := 'select';
  end if;
  execute immediate('grant ' || permissions || ' on ' || objects.object_name || ' to kul_user');

  select count(*) into obj_count from all_objects where object_name = objects.object_name and object_type = 'SYNONYM' and owner = 'PUBLIC';
  if obj_count > 0 then
    execute immediate('drop public synonym ' || objects.object_name);
  end if;
  execute immediate('create public synonym ' || objects.object_name || ' for ' || objects.object_name);
end loop;
end;
/

spool off
