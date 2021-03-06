-- Project Name : チーズSNS
-- Date/Time    : 2020/04/17 15:45:04
-- Author       : チーズSNS
-- RDBMS Type   : MySQL
-- Application  : A5:SQL Mk-2

-- 通報
drop table if exists t_ban_report cascade;

create table t_ban_report (
  report_id INT(10) not null AUTO_INCREMENT comment '通報ID'
  , report_target ENUM('ACCOUNT', 'PHOTO', 'COMMENT') not null comment '通報対象'
  , reason ENUM('DISLIKE', 'SPAM', 'ADULT', 'DISCRIMINATION', 'OTHER') not null comment '理由'
  , account_id INT(10) comment 'アカウントID'
  , photo_id BIGINT comment '写真ID'
  , read_flag TINYINT(1) not null comment '既読フラグ'
  , done_flag TINYINT(1) not null comment '処理済みフラグ'
  , memo VARCHAR(256) comment 'メモ'
  , deleted VARCHAR(1) default '0' not null comment '削除フラグ'
  , created_at DATETIME default CURRENT_TIMESTAMP not null comment '作成日時'
  , created_by VARCHAR(20) default 'SYSTEM' not null comment '作成者'
  , updated_at DATETIME default CURRENT_TIMESTAMP not null comment '更新日時'
  , updated_by VARCHAR(20) default 'SYSTEM' not null comment '更新者'
  , constraint t_ban_report_PKC primary key (report_id)
) comment '通報' ;

-- 写真タグ
drop table if exists t_tag_photo cascade;

create table t_tag_photo (
  tag_photo_id BIGINT not null AUTO_INCREMENT comment '写真タグID'
  , tag_id BIGINT not null comment 'タグID'
  , photo_id BIGINT not null comment '写真ID'
  , deleted VARCHAR(1) default '0' not null comment '削除フラグ'
  , created_at DATETIME default CURRENT_TIMESTAMP not null comment '作成日時'
  , created_by VARCHAR(20) default 'SYSTEM' not null comment '作成者'
  , updated_at DATETIME default CURRENT_TIMESTAMP not null comment '更新日時'
  , updated_by VARCHAR(20) default 'SYSTEM' not null comment '更新者'
  , constraint t_tag_photo_PKC primary key (tag_photo_id)
) comment '写真タグ' ENGINE=InnoDB DEFAULT CHARSET=utf8;

create index t_tag_photo_IX1
  on t_tag_photo(tag_id);

-- タグフォロー
drop table if exists t_follow_tag cascade;

create table t_follow_tag (
  follow_tag_id BIGINT not null AUTO_INCREMENT comment 'タグフォローID'
  , account_id INT(10) not null comment 'アカウントID'
  , tag_id BIGINT not null comment 'タグID'
  , block_flag TINYINT(1) default 0 comment 'ブロックフラグ'
  , deleted VARCHAR(1) default '0' not null comment '削除フラグ'
  , created_at DATETIME default CURRENT_TIMESTAMP not null comment '作成日時'
  , created_by VARCHAR(20) default 'SYSTEM' not null comment '作成者'
  , updated_at DATETIME default CURRENT_TIMESTAMP not null comment '更新日時'
  , updated_by VARCHAR(20) default 'SYSTEM' not null comment '更新者'
  , constraint t_follow_tag_PKC primary key (follow_tag_id)
) comment 'タグフォロー' ENGINE=InnoDB DEFAULT CHARSET=utf8;

create index t_follow_tag_IX1
  on t_follow_tag(account_id);

-- タグ
drop table if exists t_tag cascade;

create table t_tag (
  tag_id BIGINT not null AUTO_INCREMENT comment 'タグID'
  , tag_name VARCHAR(50) not null comment 'タグ名'
  , deleted VARCHAR(1) default '0' not null comment '削除フラグ'
  , created_at DATETIME default CURRENT_TIMESTAMP not null comment '作成日時'
  , created_by VARCHAR(20) default 'SYSTEM' not null comment '作成者'
  , updated_at DATETIME default CURRENT_TIMESTAMP not null comment '更新日時'
  , updated_by VARCHAR(20) default 'SYSTEM' not null comment '更新者'
  , constraint t_tag_PKC primary key (tag_id)
) comment 'タグ' ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ログイン履歴
drop table if exists t_login_history cascade;

create table t_login_history (
  history_id BIGINT not null AUTO_INCREMENT comment 'ログイン履歴ID'
  , account_id INT(10) not null comment 'アカウントID'
  , ip_address VARCHAR(16) comment 'IPアドレス'
  , user_agent VARCHAR(128) comment 'ユーザーエージェント'
  , host VARCHAR(32) comment 'ホスト'
  , deleted VARCHAR(1) default '0' not null comment '削除フラグ'
  , created_at DATETIME default CURRENT_TIMESTAMP not null comment '作成日時'
  , created_by VARCHAR(20) default 'SYSTEM' not null comment '作成者'
  , updated_at DATETIME default CURRENT_TIMESTAMP not null comment '更新日時'
  , updated_by VARCHAR(20) default 'SYSTEM' not null comment '更新者'
  , constraint t_login_history_PKC primary key (history_id)
) comment 'ログイン履歴' ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- アクティビティ
drop table if exists t_activity cascade;

create table t_activity (
  activity_id BIGINT not null AUTO_INCREMENT comment 'アクティビティID'
  , account_id INT(10) not null comment 'アカウントID'
  , activity_type ENUM('COMMENT', 'LIKE', 'FOLLOW', 'NEW_POST', 'COMMENT_LIKE') not null comment 'アクティビティ種別'
  , photo_id BIGINT comment '写真ID'
  , follow_account_id INT(10) comment 'フォローアカウントID'
  , deleted VARCHAR(1) default '0' not null comment '削除フラグ'
  , created_at DATETIME default CURRENT_TIMESTAMP not null comment '作成日時'
  , created_by VARCHAR(20) default 'SYSTEM' not null comment '作成者'
  , updated_at DATETIME default CURRENT_TIMESTAMP not null comment '更新日時'
  , updated_by VARCHAR(20) default 'SYSTEM' not null comment '更新者'
  , constraint t_activity_PKC primary key (activity_id)
) comment 'アクティビティ' ENGINE=InnoDB DEFAULT CHARSET=utf8;

create index t_activity_IX1
  on t_activity(account_id);

-- ブックマーク
drop table if exists t_bookmark cascade;

create table t_bookmark (
  bookmark_id BIGINT not null AUTO_INCREMENT comment 'ブックマークID'
  , account_id INT(10) not null comment 'アカウントID'
  , photo_id BIGINT comment '写真ID'
  , deleted VARCHAR(1) default '0' not null comment '削除フラグ'
  , created_at DATETIME default CURRENT_TIMESTAMP not null comment '作成日時'
  , created_by VARCHAR(20) default 'SYSTEM' not null comment '作成者'
  , updated_at DATETIME default CURRENT_TIMESTAMP not null comment '更新日時'
  , updated_by VARCHAR(20) default 'SYSTEM' not null comment '更新者'
  , constraint t_bookmark_PKC primary key (bookmark_id)
) comment 'ブックマーク' ENGINE=InnoDB DEFAULT CHARSET=utf8;

create index t_bookmark_IX1
  on t_bookmark(account_id);

-- 写真
drop table if exists t_photo cascade;

create table t_photo (
  photo_id BIGINT not null AUTO_INCREMENT comment '写真ID'
  , photo_cd VARCHAR(128) not null comment '写真コード'
  , caption VARCHAR(1000) comment '説明'
  , img_url VARCHAR(1000) not null comment '画像パス'
  , video_url VARCHAR(1000) comment '動画パス'
  , account_id INT(10) not null comment 'アカウントID'
  , like_count INT(10) default 0 comment 'いいね件数'
  , comment_id BIGINT comment 'コメントID'
  , location GEOMETRY  comment '緯度経度'
  , lat DOUBLE(8,6) comment '緯度'
  , lng DOUBLE(9,6) comment '経度'
  , address VARCHAR(1000) comment '住所'
  , moderation_result ENUM('UNCENSORED', 'OK', 'NG') comment '検閲結果'
  , moderation_labels VARCHAR(128) comment '検閲詳細'
  , deleted VARCHAR(1) default '0' not null comment '削除フラグ'
  , created_at DATETIME default CURRENT_TIMESTAMP not null comment '作成日時'
  , created_by VARCHAR(20) default 'SYSTEM' not null comment '作成者'
  , updated_at DATETIME default CURRENT_TIMESTAMP not null comment '更新日時'
  , updated_by VARCHAR(20) default 'SYSTEM' not null comment '更新者'
  , constraint t_photo_PKC primary key (photo_id)
) comment '写真' ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_photo add unique photo_cd (photo_cd) ;

-- 写真コメント
drop table if exists t_photo_comment cascade;

create table t_photo_comment (
  comment_id BIGINT not null AUTO_INCREMENT comment 'コメントID'
  , comment_cd VARCHAR(128) not null comment 'コメントコード'
  , account_id INT(10) not null comment 'アカウントID'
  , photo_id BIGINT not null comment '写真ID'
  , content VARCHAR(120) not null comment '内容'
  , deleted VARCHAR(1) default '0' not null comment '削除フラグ'
  , created_at DATETIME default CURRENT_TIMESTAMP not null comment '作成日時'
  , created_by VARCHAR(20) default 'SYSTEM' not null comment '作成者'
  , updated_at DATETIME default CURRENT_TIMESTAMP not null comment '更新日時'
  , updated_by VARCHAR(20) default 'SYSTEM' not null comment '更新者'
  , constraint t_photo_comment_PKC primary key (comment_id)
) comment '写真コメント' ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table t_photo_comment add unique `コメントコード` (comment_cd) ;

-- 写真コメントいいね
drop table if exists t_photo_comment_like cascade;

create table t_photo_comment_like (
  like_id BIGINT not null AUTO_INCREMENT comment 'いいねID'
  , account_id INT(10) not null comment 'アカウントID'
  , photo_id BIGINT not null comment '写真ID'
  , comment_id BIGINT not null comment 'コメントID'
  , deleted VARCHAR(1) default '0' not null comment '削除フラグ'
  , created_at DATETIME default CURRENT_TIMESTAMP not null comment '作成日時'
  , created_by VARCHAR(20) default 'SYSTEM' not null comment '作成者'
  , updated_at DATETIME default CURRENT_TIMESTAMP not null comment '更新日時'
  , updated_by VARCHAR(20) default 'SYSTEM' not null comment '更新者'
  , constraint t_photo_comment_like_PKC primary key (like_id)
) comment '写真コメントいいね' ENGINE=InnoDB DEFAULT CHARSET=utf8;

create index t_photo_comment_like_IX1
  on t_photo_comment_like(account_id);

-- 写真いいね
drop table if exists t_photo_like cascade;

create table t_photo_like (
  like_id BIGINT not null AUTO_INCREMENT comment 'いいねID'
  , account_id INT(10) comment 'アカウントID'
  , photo_id BIGINT comment '写真ID'
  , deleted VARCHAR(1) default '0' not null comment '削除フラグ'
  , created_at DATETIME default CURRENT_TIMESTAMP not null comment '作成日時'
  , created_by VARCHAR(20) default 'SYSTEM' not null comment '作成者'
  , updated_at DATETIME default CURRENT_TIMESTAMP not null comment '更新日時'
  , updated_by VARCHAR(20) default 'SYSTEM' not null comment '更新者'
  , constraint t_photo_like_PKC primary key (like_id)
) comment '写真いいね' ENGINE=InnoDB DEFAULT CHARSET=utf8;

create index t_photo_like_IX1
  on t_photo_like(account_id);

-- フォロー
drop table if exists t_follow cascade;

create table t_follow (
  follow_id BIGINT not null AUTO_INCREMENT comment 'フォローID'
  , account_id INT(10) not null comment 'アカウントID'
  , follow_account_id INT(10) not null comment 'フォローアカウントID'
  , block_flag TINYINT(1) default 0 comment 'ブロックフラグ'
  , deleted VARCHAR(1) default '0' not null comment '削除フラグ'
  , created_at DATETIME default CURRENT_TIMESTAMP not null comment '作成日時'
  , created_by VARCHAR(20) default 'SYSTEM' not null comment '作成者'
  , updated_at DATETIME default CURRENT_TIMESTAMP not null comment '更新日時'
  , updated_by VARCHAR(20) default 'SYSTEM' not null comment '更新者'
  , constraint t_follow_PKC primary key (follow_id)
) comment 'フォロー' ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- お問合せ
drop table if exists t_contact cascade;

create table t_contact (
  contact_id INT(10) not null AUTO_INCREMENT comment 'お問合せID'
  , read_flag TINYINT(1) default 0 not null comment '既読フラグ'
  , name VARCHAR(50) not null comment '名前'
  , mail VARCHAR(256) not null comment 'メールアドレス'
  , content VARCHAR(1000) not null comment '内容'
  , deleted VARCHAR(1) default '0' not null comment '削除フラグ'
  , created_at DATETIME default CURRENT_TIMESTAMP not null comment '作成日時'
  , created_by VARCHAR(20) default 'SYSTEM' not null comment '作成者'
  , updated_at DATETIME default CURRENT_TIMESTAMP not null comment '更新日時'
  , updated_by VARCHAR(20) default 'SYSTEM' not null comment '更新者'
  , constraint t_contact_PKC primary key (contact_id)
) comment 'お問合せ' ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- アカウント
drop table if exists t_account cascade;

create table t_account (
  account_id INT(10) not null AUTO_INCREMENT comment 'アカウントID'
  , login_id VARCHAR(30) not null comment 'ログインID'
  , name VARCHAR(30) not null comment 'アカウント名'
  , password VARCHAR(512) not null comment 'パスワード'
  , password_change_date DATETIME not null comment 'パスワード変更日時'
  , mail VARCHAR(256) comment 'メールアドレス'
  , description VARCHAR(120) comment '自己紹介'
  , place VARCHAR(30) comment '場所'
  , url VARCHAR(100) comment 'ウェブサイト'
  , img_url VARCHAR(256) comment '画像パス'
  , twitter VARCHAR(30) comment 'Twitterアカウント'
  , facebook VARCHAR(30) comment 'Facebookアカウント'
  , instagram VARCHAR(30) comment 'Instagramアカウント'
  , deleted VARCHAR(1) default '0' not null comment '削除フラグ'
  , created_at DATETIME default CURRENT_TIMESTAMP not null comment '作成日時'
  , created_by VARCHAR(20) default 'SYSTEM' not null comment '作成者'
  , updated_at DATETIME default CURRENT_TIMESTAMP not null comment '更新日時'
  , updated_by VARCHAR(20) default 'SYSTEM' not null comment '更新者'
  , constraint t_account_PKC primary key (account_id)
) comment 'アカウント' ENGINE=InnoDB DEFAULT CHARSET=utf8;

create unique index t_account_IX1
  on t_account(login_id);

-- フォロービュー
drop view if exists v_follow;

create view v_follow as
SELECT
  tFollow.follow_id
  , tFollow.block_flag
  , tFollow.account_id as follow_account_id
  , tFollow.follow_account_id as follower_account_id
  , tFollowAccount.login_id as follow_login_id
  , tFollowAccount.name as follow_name
  , tFollowAccount.description as follow_description
  , tFollowAccount.img_url as follow_img_url
  , tFollowerAccount.login_id as follower_login_id
  , tFollowerAccount.name as follower_name
  , tFollowerAccount.description as follower_description
  , tFollowerAccount.img_url as follower_img_url
FROM
  t_follow as tFollow
  inner join t_account as tFollowAccount
    on tFollow.account_id = tFollowAccount.account_id
    and tFollowAccount.deleted = '0'
  inner join t_account as tFollowerAccount
    on tFollow.follow_account_id = tFollowerAccount.account_id
    and tFollowerAccount.deleted = '0'
WHERE
  tFollow.deleted = '0'


;
