CREATE TABLE addresses(
	id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
	street VARCHAR(200),
    number VARCHAR(30),
    neighborhood VARCHAR(200),
    city VARCHAR(100),
    state VARCHAR(100),
    complement VARCHAR(100),
    zipcode VARCHAR(100),
	user_id BIGINT,
	FOREIGN KEY(user_id) REFERENCES users(id)
);