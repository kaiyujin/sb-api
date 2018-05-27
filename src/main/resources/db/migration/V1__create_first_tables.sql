-- 店舗マスタ
CREATE TABLE shops
(
  -- 店舗ID
  id bigserial NOT NULL UNIQUE,
  -- 店名
  name varchar(255) NOT NULL,
  -- セクションID
  -- section_id bigint NOT NULL,
  -- ジャンルID
  -- genre_id smallint NOT NULL,
  -- 店舗の web サイトURL
  -- web_site_url text NOT NULL,
  -- 店舗電話番号
  phone_number text NOT NULL,
  -- FAX番号
  -- fax_number text NOT NULL,
  -- 店舗のメールアドレス
  email text NOT NULL,
  -- 国ID
  -- country_id smallint NOT NULL,
  -- 都市ID
  -- state_id smallint NOT NULL,
  -- 市区町村ID
  -- city_id bigint NOT NULL,
  -- 店舗の郵便番号
  -- postal_code text NOT NULL,
  -- ステータス
  -- status_id smallint NOT NULL,
  -- 表示順番
  -- display_order smallint NOT NULL,
  -- 作成したユーザーID
  created_by bigint NOT NULL,
  -- 作成日時
  created_at timestamp NOT NULL,
  -- 更新したユーザーID
  updated_by bigint NOT NULL,
  -- 更新日時
  updated_at timestamp NOT NULL,
  PRIMARY KEY (id)
)