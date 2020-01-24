-- # password = user
INSERT INTO user(username, password, nick_name, created_date, updated_date) VALUES
('test@test.com', '$2a$10$VvmDU4Si9YyTwCyEFS8XOuvNPTXlK34/Wb0VoAfcZcEpLYVHPZACW', 'user','2020-01-08 13:33:43','2020-01-08 13:33:43');
INSERT INTO exp.roles(id, name) VALUE (1, 'ROLE_USER');
INSERT INTO exp.roles(id, name) VALUE (2, 'ROLE_ADMIN');
INSERT INTO user_roles VALUE (1,2);

INSERT INTO duty(id, name) VALUE (1, '전략/기획');
INSERT INTO duty(id, name) VALUE (2, '개발');
INSERT INTO duty(id, name) VALUE (3, '기타');

INSERT INTO industry(id, name) VALUE (1, '기술/IT');
INSERT INTO industry(id, name) VALUE (2, '보건/복지 서비스업');
INSERT INTO industry(id, name) VALUE (3, '제조업');
INSERT INTO industry(id, name) VALUE (4, '금융업');
INSERT INTO industry(id, name) VALUE (5, '교육서비스업');
INSERT INTO industry(id, name) VALUE (6, '도매/소매업');
INSERT INTO industry(id, name) VALUE (7, '부동산업');

INSERT INTO exp_offer_type(id, name) VALUE (1, 'Email_문의');
INSERT INTO exp_offer_type(id, name) VALUE (2, '유선통화');
INSERT INTO exp_offer_type(id, name) VALUE (3, '오프라인_인터뷰');

