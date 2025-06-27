CREATE TABLE orders(
	id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
	user_id BIGINT NOT NULL UNIQUE,
	total_price FLOAT NOT NULL,
	status VARCHAR(100) NOT NULL,
	payment_method VARCHAR(100) NOT NULL,
	created_at TIMESTAMP,
	updated_at TIMESTAMP,
	FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE order_items(
	id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
	order_id BIGINT NOT NULL UNIQUE,
	product_variation_id BIGINT NOT NULL UNIQUE,
	quantity INT NOT NULL,
	subtotal FLOAT NOT NULL,
	FOREIGN KEY (order_id) REFERENCES orders(id),
	FOREIGN KEY (product_variation_id) REFERENCES product_variations(id)
);
