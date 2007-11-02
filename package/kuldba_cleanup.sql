/* Run in KULDBA */

/* 1a) */
truncate table ER_BDGT_DOC_T;

/* 1b) */
-- remove orphan members
DELETE FROM en_wrkgrp_mbr_t
    WHERE NOT EXISTS ( SELECT 'x' FROM en_wrkgrp_t 
        WHERE wrkgrp_id = en_wrkgrp_mbr_t.wrkgrp_id
          AND wrkgrp_ver_nbr = en_wrkgrp_mbr_t.wrkgrp_ver_nbr 
    )
/  

/* 1d) */
UPDATE ca_obj_sub_type_t
    SET fin_obj_sub_typ_nm = 'TRANSFERS - NONMANDATORY'
    WHERE fin_obj_sub_typ_cd = 'TN'
/

/* 1e) */
-- rename the "STATE OF INDIANA" sub fund groups            
UPDATE ca_sub_fund_grp_t 
SET sub_fund_grp_desc = 'STATE FUNDS'
WHERE sub_fund_grp_desc = 'STATE OF INDIANA'
/

/* 1h) */
-- remove references to "IU" and specific object codes
UPDATE fp_dv_pmt_reas_t 
    SET dv_pmt_reas_desc = REPLACE( dv_pmt_reas_desc, ' IU ', ' University ' )
/

/* 1i) */
-- Tax Control Name referenced "IU"       
UPDATE fp_dv_tax_ctrl_t
   SET dv_tax_ctrl_nm = 'Approved for Employee'
   WHERE dv_payee_txctrl_cd = 'A'
/

-- TODO: cleanup unused workgroups