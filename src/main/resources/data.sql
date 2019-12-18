-- # password = user
-- INSERT INTO user(id, password, email, role) VALUES
-- ('user', '$2a$10$VvmDU4Si9YyTwCyEFS8XOuvNPTXlK34/Wb0VoAfcZcEpLYVHPZACW', 'test@test.com', 'ROLE_USER')
INSERT INTO exp.roles(id, name) VALUE (1, 'ROLE_USER');
INSERT INTO exp.roles(id, name) VALUE (2, 'ROLE_ADMIN');