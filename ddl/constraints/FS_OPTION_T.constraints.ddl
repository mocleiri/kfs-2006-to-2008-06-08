ALTER TABLE FS_OPTION_T
ADD (CONSTRAINT FS_OPTION_TR1 FOREIGN KEY (
      UNIV_FIN_COA_CD )
REFERENCES CA_CHART_T (
      FIN_COA_CD ))
ADD (CONSTRAINT FS_OPTION_TR2 FOREIGN KEY (
      FOBJTP_INC_CSH_CD )
REFERENCES CA_OBJ_TYPE_T (
      FIN_OBJ_TYP_CD ))
ADD (CONSTRAINT FS_OPTION_TR3 FOREIGN KEY (
      FOBJTP_XPND_EXP_CD )
REFERENCES CA_OBJ_TYPE_T (
      FIN_OBJ_TYP_CD ))
ADD (CONSTRAINT FS_OPTION_TR4 FOREIGN KEY (
      FOBJTP_XPNDNEXP_CD )
REFERENCES CA_OBJ_TYPE_T (
      FIN_OBJ_TYP_CD ))
ADD (CONSTRAINT FS_OPTION_TR5 FOREIGN KEY (
      FOBJTP_EXPNXPND_CD )
REFERENCES CA_OBJ_TYPE_T (
      FIN_OBJ_TYP_CD ))
ADD (CONSTRAINT FS_OPTION_TR6 FOREIGN KEY (
      FOBJ_TYP_ASSET_CD )
REFERENCES CA_OBJ_TYPE_T (
      FIN_OBJ_TYP_CD ))
ADD (CONSTRAINT FS_OPTION_TR7 FOREIGN KEY (
      FOBJ_TYP_LBLTY_CD )
REFERENCES CA_OBJ_TYPE_T (
      FIN_OBJ_TYP_CD ))
ADD (CONSTRAINT FS_OPTION_TR8 FOREIGN KEY (
      FOBJ_TYP_FNDBAL_CD )
REFERENCES CA_OBJ_TYPE_T (
      FIN_OBJ_TYP_CD ))
ADD (CONSTRAINT FS_OPTION_TR9 FOREIGN KEY (
      FOBJTP_INC_NCSH_CD )
REFERENCES CA_OBJ_TYPE_T (
      FIN_OBJ_TYP_CD ))
ADD (CONSTRAINT FS_OPTION_TR10 FOREIGN KEY (
      FOBJTP_CSH_NINC_CD )
REFERENCES CA_OBJ_TYPE_T (
      FIN_OBJ_TYP_CD ))
ADD (CONSTRAINT FS_OPTION_TR11 FOREIGN KEY (
      ACT_FIN_BAL_TYP_CD )
REFERENCES CA_BALANCE_TYPE_T (
      FIN_BALANCE_TYP_CD ))
ADD (CONSTRAINT FS_OPTION_TR12 FOREIGN KEY (
      BDGT_CHK_BALTYP_CD )
REFERENCES CA_BALANCE_TYPE_T (
      FIN_BALANCE_TYP_CD ))
ADD (CONSTRAINT FS_OPTION_TR13 FOREIGN KEY (
      EXT_ENC_FBALTYP_CD )
REFERENCES CA_BALANCE_TYPE_T (
      FIN_BALANCE_TYP_CD ))
ADD (CONSTRAINT FS_OPTION_TR14 FOREIGN KEY (
      INT_ENC_FBALTYP_CD )
REFERENCES CA_BALANCE_TYPE_T (
      FIN_BALANCE_TYP_CD ))
ADD (CONSTRAINT FS_OPTION_TR15 FOREIGN KEY (
      PRE_ENC_FBALTYP_CD )
REFERENCES CA_BALANCE_TYPE_T (
      FIN_BALANCE_TYP_CD ))
ADD (CONSTRAINT FS_OPTION_TR16 FOREIGN KEY (
      ELIM_FINBAL_TYP_CD )
REFERENCES CA_BALANCE_TYPE_T (
      FIN_BALANCE_TYP_CD ))
ADD (CONSTRAINT FS_OPTION_TR17 FOREIGN KEY (
      CSTSHR_ENCUM_FIN_BAL_TYP_CD )
REFERENCES CA_BALANCE_TYPE_T (
      FIN_BALANCE_TYP_CD ))
ADD (CONSTRAINT FS_OPTION_TR18 FOREIGN KEY (
      FIN_OBJECT_TYP_TRNFR_INC_CD )
REFERENCES CA_OBJ_TYPE_T (
      FIN_OBJ_TYP_CD ))
ADD (CONSTRAINT FS_OPTION_TR19 FOREIGN KEY (
      FIN_OBJECT_TYP_TRNFR_EXP_CD )
REFERENCES CA_OBJ_TYPE_T (
      FIN_OBJ_TYP_CD ))
ADD (CONSTRAINT FS_OPTION_TR20 FOREIGN KEY (
      BASE_BDGT_FIN_BAL_TYP_CD )
REFERENCES CA_BALANCE_TYPE_T (
      FIN_BALANCE_TYP_CD ))
ADD (CONSTRAINT FS_OPTION_TR21 FOREIGN KEY (
      MO_BDGT_FIN_BAL_TYP_CD )
REFERENCES CA_BALANCE_TYPE_T (
      FIN_BALANCE_TYP_CD ))
ADD (CONSTRAINT FS_OPTION_TR22 FOREIGN KEY (
      NMNL_FIN_BAL_TYP_CD )
REFERENCES CA_BALANCE_TYPE_T (
      FIN_BALANCE_TYP_CD ))
/