-- ユーザ
insert into users (password, name, email, is_admin, created_by, created_at, updated_by, updated_at) values
  ('test', 'admin', 'test@test.com', true, 1, now(), 1, now())
;
-- 国マスタ
insert into countries(code, name, itu_code) values
  ('JPN', 'Japan','81'),
  ('VNM', 'Vietnam','84')
;
-- 言語マスタ
insert into languages(code, name) values
  ('en', 'English'),
  ('ja', 'Japanese'),
  ('vi', 'Vietnamese')
;
-- タイムゾーンマスタ
insert into timezones(code, name, interval_time) values
  ('JST','Japan Standard Time', INTERVAL '9 hour'),
  ('ICT','Indochina Time', INTERVAL '7 hour')
;

insert into companies ( name,country_code, address, building, phone_number, email, is_test, created_by, created_at, updated_by, updated_at) values
  ('テスト株式会社','JPN','東京都渋谷区恵比寿西1-3-20','HOGEビル','0300000000','companytest@hoge.com',true,1,now(),1,now())
;

insert into sections (name, company_id, created_by, created_at, updated_by, updated_at) values
  ('セクションX',1,1,now(),1,now())
;

insert into shops (name, section_id, phone_number, email, country_code, timezone_code, display_order,created_by, created_at, updated_by, updated_at) values
  ('Bar foobar',1 ,'08000000000', 'shoptest@hoge.com','JPN','JST',1,1,now(),1,now())
;