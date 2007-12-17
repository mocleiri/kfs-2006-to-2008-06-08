create or replace view AR_OVER_30_INV_V as
select  a.fdoc_nbr,
        a.ar_billing_dt,
        a.ar_entry_dt,
        a.ar_inv_due_dt,
        a.ar_inv_desc,
        a.ar_writeoff_ind,
        a.cust_nbr,
        a.cust_nm,
        sum(a.ar_inv_itm_tot_amt) as ar_inv_itm_tot_amt,
        sum(a.ar_inv_itm_tot_amt - a.ar_inv_itmapld_amt) as ar_inv_due_amt
FROM   AR_OPEN_INV_DTL_V a
where  (trunc(sysdate)) < (a.ar_billing_dt + 61) and
       (trunc(sysdate)) > (a.ar_billing_dt + 30)
group by a.fdoc_nbr,
         a.ar_billing_dt,
         a.ar_entry_dt,
         a.ar_inv_due_dt,
         a.ar_inv_desc,
         a.ar_writeoff_ind,
         a.cust_nbr,
         a.cust_nm                                                                                                 
/
