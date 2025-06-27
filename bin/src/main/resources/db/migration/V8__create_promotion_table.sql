CREATE TABLE promotions(
	id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
	name VARCHAR(200) NOT NULL,
	discountPercentage DECIMAL(5,2) NOT NULL,
	startDate TIMESTAMP,
	endDate TIMESTAMP,
	isActive BOOLEAN NOT NULL,
	product_variation_id BIGINT NOT NULL,
	FOREIGN KEY(product_variation_id) REFERENCES product_variations(id)
);