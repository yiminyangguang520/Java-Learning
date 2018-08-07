-- the password hash is generated by BCrypt Calculator Generator(https://www.dailycred.com/article/bcrypt-calculator)
INSERT INTO USERS (id, username, password, first_name, last_name, email, phone_number, enabled, last_password_reset_date) VALUES (1, 'user',  '$2a$10$QuQAXLJmoxzw033iHaO0PumDhbQ6aMhZibPfhor3mchGj.Z/XrwH2', 'Fan', 'Jin', 'user@example.com', '+1234567890', true, '2017-10-01 21:58:58');
INSERT INTO USERS (id, username, password, first_name, last_name, email, phone_number, enabled, last_password_reset_date) VALUES (2, 'admin', '$2a$10$ux6t3vFsO.3Z0LBz4FFNqOrcNel.J2wdIsLDnMKR3lKqDtRle4NqW', 'Jing', 'Xiao', 'admin@example.com', '+0987654321', true, '2017-10-01 18:57:58');

INSERT INTO AUTHORITY (id, name) VALUES (1, 'ROLE_USER');
INSERT INTO AUTHORITY (id, name) VALUES (2, 'ROLE_ADMIN');

INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (1, 1);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (2, 1);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (2, 2);
