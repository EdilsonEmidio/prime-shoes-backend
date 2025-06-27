CREATE TABLE reviews(
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	user_id BIGINT NOT NULL,
	product_variation_id BIGINT NOT NULL,
	rating TINYINT NOT NULL,
	comment VARCHAR(255),
	createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	FOREIGN KEY(user_id) REFERENCES users(id),
	FOREIGN KEY(product_variation_id) REFERENCES product_variations(id)
);