CREATE VIEW PUR_PO_V as(
SELECT
        AP_PUR_DOC_LNK_ID, 
        PO_ID, 
        PO_CUR_IND, 
        FDOC_NBR, 
        OBJ_ID 
FROM PUR_PO_T
)
/