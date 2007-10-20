CREATE VIEW AP_CRDT_MEMO_V AS
 SELECT
        AP_PUR_DOC_LNK_ID, 
        cm.CRDT_MEMO_ID, 
        FDOC_NBR, 
        cm.OBJ_ID, 
        CRDT_MEMO_NBR, 
        PMT_RQST_ID, 
        PO_ID,
        CRDT_MEMO_STAT_CD, 
        CRDT_HLD_IND, 
        VNDR_CUST_NBR, 
        AP_APRVL_DT, 
        CRDT_MEMO_EXTRT_DT,
        CRDT_MEMO_PD_TS,
        VNDR_NM, 
        SUM( CASE WHEN item.ITM_EXTND_PRC IS NULL THEN 0.00 ELSE item.ITM_EXTND_PRC END ) AS TOTAL_AMOUNT
 FROM AP_CRDT_MEMO_T cm, AP_CRDT_MEMO_ITM_T item 
 WHERE cm.CRDT_MEMO_ID = item.CRDT_MEMO_ID 
 GROUP BY AP_PUR_DOC_LNK_ID, cm.CRDT_MEMO_ID, FDOC_NBR, 
          cm.OBJ_ID, CRDT_MEMO_NBR, PMT_RQST_ID, PO_ID,
          CRDT_MEMO_STAT_CD, CRDT_HLD_IND, VNDR_CUST_NBR, 
          AP_APRVL_DT, CRDT_MEMO_EXTRT_DT, CRDT_MEMO_PD_TS, VNDR_NM
/