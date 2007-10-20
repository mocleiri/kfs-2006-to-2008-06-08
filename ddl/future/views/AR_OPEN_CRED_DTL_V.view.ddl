create or replace view AR_OPEN_CRED_DTL_V as
select 	a.fdoc_ref_nbr as fdoc_nbr,
       	f.fdoc_typ_cd,
       	1 as ar_inv_itm_nbr,
       	(select distinct c1.fin_coa_cd
         FROM AR_CREDIT_MEMO_T c1
         where c1.fdoc_nbr = a.fdoc_ref_nbr) as fin_coa_cd,
       	(select distinct c1.account_nbr
         FROM AR_CREDIT_MEMO_T c1
         where c1.fdoc_nbr = a.fdoc_ref_nbr) as account_nbr,
        null as ar_bill_by_coa_cd,
        null as ar_bill_by_org_cd,
        trunc(f.fdoc_approved_dt) as ar_billing_dt,
        d.ar_entry_dt,
        trunc(f.fdoc_approved_dt) as ar_inv_due_dt,
        f.fdoc_desc as ar_inv_desc,
        least('N') as ar_writeoff_ind,
        d.cust_nbr,
        d.prcs_fin_coa_cd, 
	    d.prcs_org_cd,
        e.cust_nm,
        (f.fdoc_total_amt*-1) as ar_inv_itm_tot_amt,
        -1 * (nvl((select (sum(b.fdoc_line_amt)) FROM AR_NON_INV_DIST_T b
                        where a.fdoc_ref_nbr=b.fdoc_ref_nbr), 0) +
                nvl((select (sum(c.fdoc_line_amt))
                        FROM AR_NON_APLD_DIST_T c
                        where a.fdoc_ref_nbr=c.fdoc_ref_nbr), 0))
                as ar_inv_itmapld_amt
FROM    AR_NON_APLD_HLDG_T a,
        AR_DOC_HDR_T d,
        AR_CUST_T e,
        FP_DOC_HEADER_T f
where a.fdoc_ref_nbr = d.fdoc_nbr and
      a.fdoc_ref_nbr = f.fdoc_nbr and
      f.fdoc_typ_cd = 'CRM' and
      d.cust_nbr = e.cust_nbr and
      f.fdoc_status_cd = 'A'
/

