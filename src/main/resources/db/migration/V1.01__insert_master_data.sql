-- ユーザ
insert into users (password, name, email, role_type, created_by, created_at, updated_by, updated_at) values
  ('$2a$08$IzBdL5stGhyzLyZwKe6Dquvs61juEWgH2v2hHhnxzRqYrtZ3nRqXa', 'admin', 'test@test.com', 1, 1, now(), 1,
   now()) -- pass=test
  , ('$2a$08$IzBdL5stGhyzLyZwKe6Dquvs61juEWgH2v2hHhnxzRqYrtZ3nRqXa', 'customer', 'customer@test.com', 3, 1, now(), 1,
     now()) -- pass=test
;
-- 国マスタ
insert into countries (code, name, itu_code, display_order) values
  ('JPN', 'Japan', '81', 1)
  , ('VNM', 'Vietnam', '84', 2)
  , ('USA', 'USA', '1', 3);
-- 言語マスタ
insert into languages (code, name, display_order) values
  ('ja', 'Japanese', 1)
  , ('en', 'English', 2)
  , ('vi', 'Vietnamese', 3);
-- タイムゾーンマスタ
insert into timezones (code, name, interval_time, display_order) values
  ('JST', 'Japan Standard Time', INTERVAL '9 hour', 1)
  , ('ICT', 'Indochina Time', INTERVAL '7 hour', 2);

insert into companies (name, country_code, address, building, phone_number, email, is_test, created_by, created_at, updated_by, updated_at)
values
  ('テスト株式会社', 'JPN', '東京都渋谷区恵比寿西1-3-20', 'HOGEビル', '0300000000', 'companytest@hoge.com', true, 1, now(), 1, now());

insert into sections (name, company_id, created_by, created_at, updated_by, updated_at) values
  ('セクションX', 1, 1, now(), 1, now());

insert into shops (name, section_id, phone_number, email, country_code, timezone_code, display_order, created_by, created_at, updated_by, updated_at)
values
  ('Bar foobar', 1, '08000000000', 'shoptest@hoge.com', 'JPN', 'JST', 1, 1, now(), 1, now());