CREATE TABLE carts(
	id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
	user_id BIGINT UNIQUE,
	FOREIGN KEY (user_id) REFERENCES users(id)
	
);
CREATE TABLE cart_items(
	id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
	product_variation_id BIGINT NOT NULL,
	cart_id BIGINT NOT NULL,
	quantity INT NOT NULL,
	subtotal FLOAT NOT NULL,
	FOREIGN KEY (product_variation_id) REFERENCES product_variations(id),
	FOREIGN KEY (cart_id) REFERENCES carts(id)
);
