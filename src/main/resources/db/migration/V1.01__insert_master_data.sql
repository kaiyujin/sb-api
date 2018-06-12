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