CREATE TABLE users (
  username VARCHAR (45) NOT NULL,
  password VARCHAR (100) NOT NULL,
  enabled TINYINT NOT NULL DEFAULT 1,
  PRIMARY KEY (username)
);

CREATE TABLE user_roles (
  user_role_id INT (11) NOT NULL AUTO_INCREMENT,
  username VARCHAR (45) NOT NULL,
  role VARCHAR (45) NOT NULL,
  PRIMARY KEY (user_role_id),
  UNIQUE KEY uni_username_role (role, username),
  KEY fk_username_idx (username),
  CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES users (username)
);

INSERT INTO users (username, password, enabled) VALUES('priya', '$2a$04$sE2PBd6gnuukKQ4STWzYYO9wUXeZ1wWnFOUH1eaAFCICO0fZSqVUu', TRUE);
INSERT INTO users (username, password, enabled) VALUES('naveen', '$2a$04$PPU7TsIPZxL8/cvBZ/bti./SZA.XAXM8/hmJkloW8huzr3xEzb3WC', TRUE);

INSERT INTO user_roles (username, role) VALUES ('priya', 'ROLE_USER');
INSERT INTO user_roles (username, role) VALUES ('priya', 'ROLE_ADMIN');
INSERT INTO user_roles (username, role) VALUES ('naveen', 'ROLE_USER');