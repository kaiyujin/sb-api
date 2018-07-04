-- auto update updated_at
create function set_update_time()
  returns opaque as '
  begin
    new.updated_at := ''now'';
    return new;
  end;
' language 'plpgsql';

-- ユーザーマスタ
CREATE TABLE users
(
  -- ユーザーID
  id         bigint generated always as identity NOT NULL UNIQUE,
  -- password
  password   text                                NOT NULL,
  -- ユーザー名
  name       varchar(255)                        NOT NULL,
  -- e-mail アドレス
  email      varchar(255)                        NOT NULL,
  -- role type 1:admin 2:support 3:user
  role_type  smallint                            NOT NULL,
  -- 作成したユーザーID
  created_by bigint                              NOT NULL references users (id),
  -- 作成日時
  created_at timestamp with time zone NOT NULL default CURRENT_TIMESTAMP,
  -- 更新したユーザーID
  updated_by bigint references users (id),
  -- 更新日時
  updated_at timestamp with time zone NOT NULL default CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
);
create trigger update_tri
  before update
  on users
  for each row
  execute procedure set_update_time();

-- 国マスタ
CREATE TABLE countries
(
  -- ISO 3166-1 alpha-3
  code          char(3)    NOT NULL UNIQUE,
  name          text       NOT NULL,
  itu_code      varchar(2) NOT NULL,
  display_order smallint   NOT NULL,
  PRIMARY KEY (code)
);

-- 言語マスタ
CREATE TABLE languages
(
  code          varchar(5) NOT NULL,
  -- 言語名
  name          text       NOT NULL,
  display_order smallint   NOT NULL,
  PRIMARY KEY (code)
);

-- タイムゾーンマスタ
CREATE TABLE timezones
(
  -- タイムゾーン コード
  code          varchar(4) NOT NULL UNIQUE,
  -- タイムゾーン表示名
  name          text       NOT NULL,
  -- UTC差
  interval_time interval NOT NULL,
  display_order smallint   NOT NULL,
  PRIMARY KEY (code)
);

-- 会社マスタ
CREATE TABLE companies
(
  -- 会社ID
  id           bigint generated always as identity NOT NULL UNIQUE,
  -- 会社名
  name         text                                NOT NULL,
  -- 国ID
  country_code char(3)                             NOT NULL references countries (code),
  -- 都市ID
  --	state_id smallint NOT NULL,
  -- 市区町村ID
  --	city_id bigint NOT NULL,
  -- 住所
  address      text                                NOT NULL,
  -- ビル名
  building     text                                NOT NULL,
  -- 会社代表番号
  phone_number text                                NOT NULL,
  -- 会社のメールアドレス
  email        varchar(255)                        NOT NULL,
  -- テストデータ
  is_test      boolean                             NOT NULL,
  -- 作成したユーザーID
  created_by   bigint                              NOT NULL references users (id),
  -- 作成日時
  created_at   timestamp with time zone NOT NULL default CURRENT_TIMESTAMP,
  -- 更新したユーザーID
  updated_by   bigint references users (id),
  -- 更新日時
  updated_at   timestamp with time zone NOT NULL default CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
);
create trigger update_tri
  before update
  on companies
  for each row
  execute procedure set_update_time();

-- セクションマスタ
CREATE TABLE sections
(
  -- セクションID
  id         bigint generated always as identity NOT NULL UNIQUE,
  -- セクション名
  name       varchar(255)                        NOT NULL,
  -- 会社ID
  company_id bigint                              not null references companies (id),
  -- 作成したユーザーID
  created_by bigint                              NOT NULL references users (id),
  -- 作成日時
  created_at timestamp with time zone NOT NULL default CURRENT_TIMESTAMP,
  -- 更新したユーザーID
  updated_by bigint references users (id),
  -- 更新日時
  updated_at timestamp with time zone NOT NULL default CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
);
create trigger update_tri
  before update
  on sections
  for each row
  execute procedure set_update_time();

-- 店舗マスタ
CREATE TABLE shops
(
  -- 店舗ID
  id            bigint generated always as identity NOT NULL UNIQUE,
  -- 店名
  name          varchar(255)                        NOT NULL,
  -- セクションID
  section_id    bigint                              NOT NULL,
  -- ジャンルID
  -- genre_id smallint NOT NULL,
  -- 店舗の web サイトURL
  -- web_site_url text NOT NULL,
  -- 店舗電話番号
  phone_number  text                                NOT NULL,
  -- FAX番号
  -- fax_number text NOT NULL,
  -- 店舗のメールアドレス
  email         varchar(255)                        NOT NULL,
  -- 国ID
  country_code  char(3)                             NOT NULL references countries (code),
  -- timezone id
  timezone_code varchar(4)                          not null references timezones (code),
  -- 都市ID
  -- state_id smallint NOT NULL,
  -- 市区町村ID
  -- city_id bigint NOT NULL,
  -- 店舗の郵便番号
  -- postal_code text NOT NULL,
  -- ステータス 1:有効 2:解約 3:閉店
  -- status_id smallint NOT NULL,
  -- 表示順番
  display_order smallint                            NOT NULL,
  -- 作成したユーザーID
  created_by    bigint                              NOT NULL references users (id),
  -- 作成日時
  created_at    timestamp with time zone NOT NULL default CURRENT_TIMESTAMP,
  -- 更新したユーザーID
  updated_by    bigint references users (id),
  -- 更新日時
  updated_at    timestamp with time zone NOT NULL default CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
);
create trigger update_tri
  before update
  on shops
  for each row
  execute procedure set_update_time();

-- 店舗スタッフID
CREATE TABLE shop_staffs
(
  -- 店舗スタッフID
  id            bigint generated always as identity NOT NULL UNIQUE,
  -- 店舗ID
  shop_id       bigint                              NOT NULL references shops (id),
  -- スタッフ名
  name          varchar(255)                        NOT NULL,
  -- 表示順番
  display_order smallint                            NOT NULL,
  -- true: web 予約時のデフォルト受付スタッフ, false: web 予約時のデフォルト受付スタッフでない
  is_default    boolean                             NOT NULL,
  -- true: 表示する, false: 表示しない
  is_display    boolean                             NOT NULL,
  -- 作成したユーザーID
  created_by    bigint                              NOT NULL references users (id),
  -- 作成日時
  created_at    timestamp with time zone NOT NULL default CURRENT_TIMESTAMP,
  -- 更新したユーザーID
  updated_by    bigint references users (id),
  -- 更新日時
  updated_at    timestamp with time zone NOT NULL default CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
);
create trigger update_tri
  before update
  on shop_staffs
  for each row
  execute procedure set_update_time();

-- 予約
CREATE TABLE reservations
(
  -- 予約ID
  id                  bigint generated always as identity NOT NULL UNIQUE,
  -- org
  -- section
  -- 店舗ID
  shop_id             bigint                              NOT NULL,
  -- 1: TEL予約, 2: WEB予約, 3: 外部連携, 4: ウォークイン
  type                smallint                            NOT NULL,
  -- 予約ステータスID
  status_id           smallint                            NOT NULL,
  -- 予約日時
  date                timestamp with time zone NOT NULL default CURRENT_TIMESTAMP,
  -- 滞在時間
  stay_time interval NOT NULL,
  -- 全利用人数(大人 + 子供)
  number_person       smallint                            NOT NULL,
  -- 利用子供人数
  number_person_child smallint                            NOT NULL,
  -- 1:禁煙, 2:喫煙, 3: 指定なし
  smoking_type        smallint                            NOT NULL,
  -- 受付スタッフID
  staff_id            bigint                              NOT NULL references shop_staffs (id),
  -- 言語ID
  language_id         smallint                            NOT NULL,
  -- 作成したユーザーID
  created_by          bigint                              NOT NULL references users (id),
  -- 作成日時
  created_at          timestamp with time zone NOT NULL default CURRENT_TIMESTAMP,
  -- 更新したユーザーID
  updated_by          bigint references users (id),
  -- 更新日時
  updated_at          timestamp with time zone NOT NULL default CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
);
create trigger update_tri
  before update
  on reservations
  for each row
  execute procedure set_update_time();

-- 予約個人情報
CREATE TABLE reservation_personal_data
(
  -- 予約ID
  reservation_id bigint       NOT NULL references reservations (id),
  -- email
  email          varchar(255) NOT NULL,
  -- 電話番号
  phone_number   text         NOT NULL,
  -- 国ID
  country_code   char(3)      NOT NULL references countries (code),
  -- 作成したユーザーID
  created_by     bigint       NOT NULL references users (id),
  -- 作成日時
  created_at     timestamp with time zone NOT NULL default CURRENT_TIMESTAMP,
  -- 更新したユーザーID
  updated_by     bigint references users (id),
  -- 更新日時
  updated_at     timestamp with time zone NOT NULL default CURRENT_TIMESTAMP,
  PRIMARY KEY (reservation_id)
);
create trigger update_tri
  before update
  on reservation_personal_data
  for each row
  execute procedure set_update_time();

-- テーブルマスタ
CREATE TABLE tables
(
  -- テーブルID
  id               bigint generated always as identity NOT NULL UNIQUE,
  -- 店舗ID
  shop_id          bigint                              NOT NULL references shops (id),
  -- テーブル名
  name             text                                NOT NULL,
  -- 最小収容人数
  minimun_capacity smallint                            NOT NULL,
  -- 最大収容人数
  maximum_capacity smallint                            NOT NULL,
  -- 1: 禁煙, 2: 喫煙, 3: 指定なし
  smoking_type     smallint                            NOT NULL,
  -- 表示順番
  display_order    smallint                            NOT NULL,
  -- 作成したユーザーID
  created_by       bigint                              NOT NULL references users (id),
  -- 作成日時
  created_at       timestamp with time zone NOT NULL default CURRENT_TIMESTAMP,
  -- 更新したユーザーID
  updated_by       bigint references users (id),
  -- 更新日時
  updated_at       timestamp with time zone NOT NULL default CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
);
create trigger update_tri
  before update
  on tables
  for each row
  execute procedure set_update_time();

-- 予約-テーブル交差テーブル
CREATE TABLE reservations_tables
(
  -- 予約ID
  reservation_id bigint NOT NULL references reservations (id),
  -- テーブルID
  table_id       bigint NOT NULL references tables (id),
  -- 作成したユーザーID
  created_by     bigint NOT NULL references users (id),
  -- 作成日時
  created_at     timestamp with time zone NOT NULL default CURRENT_TIMESTAMP,
  PRIMARY KEY (reservation_id, table_id)
);

-- 共通テーブルタイプマスタ
CREATE TABLE table_types
(
  -- 共通テーブルタイプID
  id   smallint NOT NULL UNIQUE,
  -- テーブルタイプ名
  name text     NOT NULL,
  PRIMARY KEY (id)
);

-- テーブル-テーブルタイプ交差テーブル
CREATE TABLE tables_sheet_types
(
  -- テーブルID
  table_id      bigint   NOT NULL references tables (id),
  -- 共通テーブルタイプID
  table_type_id smallint NOT NULL references table_types (id),
  -- 作成したユーザーID
  created_by    bigint   NOT NULL references users (id),
  -- 作成日時
  created_at    timestamp with time zone NOT NULL default CURRENT_TIMESTAMP,
  PRIMARY KEY (table_id, table_type_id)
);