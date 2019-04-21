-- システムアカウント
delete from t_account where account_id = 1;
insert
into t_account(
  account_id
  , login_id
  , name
  , password
  , password_change_date
  , mail
  , deleted
  , created_at
  , created_by
  , updated_at
  , updated_by
)
values (
  1
  , 'admin'
  , 'admin'
  , 'admin'
  , NOW()
  , 'spcial.smile.aoi@gmail.com' -- TODO
  , '0'
  , NOW()
  , 1
  , NOW()
  , 1
);

-- ゲストアカウント
delete from t_account where account_id = 2;
insert
into t_account(
  account_id
  , login_id
  , name
  , password
  , password_change_date
  , mail
  , deleted
  , created_at
  , created_by
  , updated_at
  , updated_by
)
values (
  2
  , 'guest'
  , 'guest'
  , 'guest'
  , NOW()
  , 'spcial.smile.aoi@gmail.com' -- TODO
  , '0'
  , NOW()
  , 1
  , NOW()
  , 1
);

-- バッチアカウント
delete from t_account where account_id = 3;
insert
into t_account(
  account_id
  , login_id
  , name
  , password
  , password_change_date
  , mail
  , deleted
  , created_at
  , created_by
  , updated_at
  , updated_by
)
values (
  3
  , 'batch'
  , 'batch'
  , 'batch'
  , NOW()
  , 'spcial.smile.aoi@gmail.com' -- TODO
  , '0'
  , NOW()
  , 1
  , NOW()
  , 1
);
