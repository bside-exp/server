-- # password = user
INSERT INTO user(username, password, nick_name, created_date, updated_date) VALUES
('test@test.com', '$2a$10$VvmDU4Si9YyTwCyEFS8XOuvNPTXlK34/Wb0VoAfcZcEpLYVHPZACW', 'user','2020-01-08 13:33:43','2020-01-08 13:33:43');
INSERT INTO exp.roles(id, name) VALUE (1, 'ROLE_USER');
INSERT INTO exp.roles(id, name) VALUE (2, 'ROLE_ADMIN');
INSERT INTO user_roles VALUE (1,1);

