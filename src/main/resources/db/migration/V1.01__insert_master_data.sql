-- ユーザ
insert into users (password, name, email, is_admin, created_by, created_at, updated_by, updated_at) values
  ('test', 'admin', 'test@test.com', true, 1, now(), 1, now())
;
-- 国マスタ
-- 言語マスタ
-- タイムゾーンマスタ