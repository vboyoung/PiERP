select count(*)
  from ezmaru.nsb_t_dmail
     , ezmaru.nsb_t_dmail_receive_user
     , ezmaru.nsb_t_usr
 where nsb_t_dmail.DML_CODE = nsb_t_dmail_receive_user.DRU_MCODE
   and nsb_t_usr.NUR_UUID   = nsb_t_dmail_receive_user.DRU_UID
   and nsb_t_usr.NUR_USERID                                 = 'ksi20'
   and nsb_t_dmail_receive_user.DRU_DELETE                  = 'N'
   and IFNULL( nsb_t_dmail_receive_user.DRU_READ_DATE, '' ) = ''	