create or replace view AR_OPEN_INV_DTL_V as
select  a.fdoc_nbr,
        f.fdoc_typ_cd,
        a.ar_inv_itm_nbr,
        a.fin_coa_cd,
        a.account_nbr,
        c.ar_bill_by_coa_cd,
        c.ar_bill_by_org_cd,
        trunc(f.fdoc_approved_dt) as ar_billing_dt,
        d.ar_entry_dt,
        c.ar_inv_due_dt,
        c.ar_inv_desc,
        c.ar_writeoff_ind,
        d.cust_nbr,
        d.prcs_fin_coa_cd,
        d.prcs_org_cd,
        e.cust_nm,
        a.ar_inv_itm_tot_amt,
       	nvl((select sum(b.ar_inv_itmapld_amt) FROM AR_INV_PD_APLD_T b
               where a.fdoc_nbr=b.fdoc_ref_inv_nbr and
                     a.ar_inv_itm_nbr = b.ar_inv_itm_nbr), 0)
           as ar_inv_itmapld_amt
FROM    AR_INV_DTL_T a,
        AR_INV_DOC_T c,
        AR_DOC_HDR_T d,
        AR_CUST_T e,
        FP_DOC_HEADER_T f
where a.fdoc_nbr = c.fdoc_nbr and
      a.fdoc_nbr = d.fdoc_nbr and
      a.fdoc_nbr = f.fdoc_nbr and
      c.ar_open_inv_ind = 'Y' and
      d.cust_nbr = e.cust_nbr and
      f.fdoc_status_cd = 'A'
/
