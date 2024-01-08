-- liquibase formatted sql

-- changeset vamsi:1
CREATE TABLE products (
    id INT PRIMARY KEY,
    name VARCHAR(20),
    description VARCHAR(30),
    category VARCHAR(30)
);
-- changeset vamsi:2
ALTER TABLE products ADD price INT;

--changeset vamsi:3
ALTER TABLE products DROP COLUMN description;
--changeset vamsi:4
ALTER TABLE products DROP COLUMN category;
--changeset vamsi:5
ALTER TABLE products RENAME price TO totalPrice ;

--changeset vamsi:6
ALTER TABLE products RENAME name TO product_name;
--changeset vamsi:7
ALTER TABLE products RENAME product_name TO name;
--changeset vamsi:8
ALTER TABLE products RENAME totalPrice to price;