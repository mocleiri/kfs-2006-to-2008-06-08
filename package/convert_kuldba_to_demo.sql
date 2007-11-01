/* KULDBA to Demo */

-- TODO: Clean up workflow document types and versions

DELETE
  FROM en_rte_node_lnk_t a
  WHERE from_rte_node_id IN ( SELECT rte_node_id FROM en_rte_node_t
  WHERE doc_typ_id IN (SELECT doc_typ_id FROM en_doc_typ_t
    WHERE doc_typ_nm LIKE 'Fake%'
       OR doc_typ_nm LIKE 'Test%'
       OR doc_typ_nm LIKE 'MyTest%'
       OR doc_typ_nm LIKE '%TEST%'
       OR doc_typ_nm LIKE 'aintenance%'
)
)
/

DELETE
  FROM en_rte_node_lnk_t a
  WHERE to_rte_node_id IN ( SELECT rte_node_id FROM en_rte_node_t
  WHERE doc_typ_id IN (SELECT doc_typ_id FROM en_doc_typ_t
    WHERE doc_typ_nm LIKE 'Fake%'
       OR doc_typ_nm LIKE 'Test%'
       OR doc_typ_nm LIKE 'MyTest%'
       OR doc_typ_nm LIKE '%TEST%'
       OR doc_typ_nm LIKE 'aintenance%'
)
)
/

DELETE
  FROM en_rte_node_t a
  WHERE doc_typ_id IN (SELECT doc_typ_id FROM en_doc_typ_t
    WHERE doc_typ_nm LIKE 'Fake%'
       OR doc_typ_nm LIKE 'Test%'
       OR doc_typ_nm LIKE 'MyTest%'
       OR doc_typ_nm LIKE '%TEST%'
       OR doc_typ_nm LIKE 'aintenance%'
)
/

-- clear out bad document types
DELETE FROM en_doc_typ_t
    WHERE doc_typ_nm LIKE 'Fake%'
       OR doc_typ_nm LIKE 'Test%'
       OR doc_typ_nm LIKE 'MyTest%'
       OR doc_typ_nm LIKE '%TEST%'
       OR doc_typ_nm LIKE 'aintenance%'
/

-- delete old version document types
DELETE FROM en_doc_typ_t
    WHERE doc_typ_cur_ind = 0
/

-- remove some old rule attributes
DELETE FROM en_rule_attrib_t WHERE 
--rule_attrib_lbl_txt = 'foo'
 --OR
  rule_attrib_nm LIKE 'KualiReporting%'
/

-- delete non-phase 1 rule templates
DELETE
  FROM en_rule_tmpl_attrib_t
 WHERE rule_tmpl_id IN (SELECT a.rule_tmpl_id
                          FROM en_rule_tmpl_t a
                         WHERE rule_tmpl_nm LIKE 'Test%' OR rule_tmpl_nm LIKE 'KualiReporting%')
/  

DELETE
  FROM en_rule_tmpl_attrib_t
  WHERE NOT EXISTS ( SELECT rule_attrib_id FROM en_rule_attrib_t )
/

DELETE
  FROM en_rule_tmpl_optn_t
 WHERE rule_tmpl_id IN (SELECT a.rule_tmpl_id
                          FROM en_rule_tmpl_t a
                         WHERE rule_tmpl_nm LIKE 'Test%' OR rule_tmpl_nm LIKE 'KualiReporting%')
/

DELETE
  FROM en_rule_tmpl_t
  WHERE rule_tmpl_nm LIKE 'Test%' OR rule_tmpl_nm LIKE 'KualiReporting%'
/ 

-- remove document numbers from workgroup table
UPDATE en_wrkgrp_t SET doc_hdr_id = -1 WHERE doc_hdr_id <> -1
/

-- remove old version workgroups 
DELETE FROM en_wrkgrp_t WHERE wrkgrp_cur_ind = 0
/

UPDATE en_appl_cnst_t
    SET appl_cnst_val_txt = '127.0.0.1'
    WHERE appl_cnst_nm = 'Config.Mailer.IPAddress'
/

UPDATE en_appl_cnst_t
    SET appl_cnst_val_txt = 'KualiEnterpriseWorkflow@yourinstitution.edu'
    WHERE appl_cnst_nm = 'Config.Mailer.FromAddress'
/



/* TODO 3e) ask jonathan */
DELETE FROM en_wrkgrp_t
    WHERE wrkgrp_id NOT IN (
     '166668'
    ,'176739'
    ,'177110'
    ,'180244'
    ,'183699'
    ,'25386'
    ,'2562'
    ,'2618'
    ,'2619'
    ,'2624'
    ,'2627'
    ,'2629'
    ,'2633'
    ,'2636'
    ,'2637'
    ,'2638'
    ,'2644'
    ,'2679'
    ,'2692'
    ,'2722'
    ,'2735'
    ,'2736'
    ,'2738'
    ,'2739'
    ,'2741'
    ,'2742'
    ,'2743'
    ,'2744'
    ,'2745'
    ,'2746'
    ,'2751'
    ,'2752'
    ,'46663'
    )
    AND wrkgrp_nm NOT IN (
        SELECT DISTINCT wrkgrp_nm FROM sh_parm_t
    )
/

-- based on above deletions, also update en_wrkgrp_ext_t and en_wrkgrp_ext_dta_t
DELETE FROM en_wrkgrp_ext_t where (WRKGRP_ID, WRKGRP_VER_NBR) not in (select WRKGRP_ID, WRKGRP_VER_NBR from en_wrkgrp_t);
DELETE FROM en_wrkgrp_ext_dta_t where WRKGRP_EXT_ID not in (select WRKGRP_EXT_ID from en_wrkgrp_ext_t);
