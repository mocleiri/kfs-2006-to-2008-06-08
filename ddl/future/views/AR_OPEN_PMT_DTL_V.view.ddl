create or replace view AR_OPEN_PMT_DTL_V as
select  f.fdoc_nbr, 
	    f.fdoc_typ_cd,
        trunc(f.fdoc_approved_dt) as fdoc_approved_dt, 
	    d.ar_entry_dt,
        a.cust_nbr, 
	    d.prcs_fin_coa_cd, 
	    d.prcs_org_cd,
        e.cust_nm,
        (a.fdoc_line_amt * -1) as fdoc_line_amt,
        nvl((select (sum(b.fdoc_line_amt) * -1)
               FROM AR_NON_INV_DIST_T b
               where a.fdoc_ref_nbr=b.fdoc_ref_nbr), 0) -
          nvl((select (sum(c.fdoc_line_amt))
             FROM AR_NON_APLD_DIST_T c
               where a.fdoc_ref_nbr=c.fdoc_ref_nbr), 0)
          as ar_pmt_apld_amt,
        f.fdoc_desc
FROM    AR_NON_APLD_HLDG_T a,
        AR_DOC_HDR_T d,
        AR_CUST_T e,
        FP_DOC_HEADER_T f
where a.fdoc_ref_nbr = d.fdoc_nbr and
      a.fdoc_ref_nbr = f.fdoc_nbr and
      f.fdoc_typ_cd = 'APP' and
      a.cust_nbr = e.cust_nbr and
      f.fdoc_status_cd = 'A' and
      a.cust_nbr is not null                                          
/
