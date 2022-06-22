CREATE SEQUENCE purchase_id_seq;

CREATE TABLE purchase (
    id          oid CONSTRAINT purchase_primary_key PRIMARY KEY DEFAULT nextval('purchase_id_seq'),
    created_at   timestamp,
    customer_id int NOT NULL
);

CREATE SEQUENCE purchase_products_id_seq;

CREATE TABLE purchase_products (
    id          oid CONSTRAINT purchase_product_primary_key PRIMARY KEY DEFAULT nextval('purchase_products_id_seq'),
    product_id  int NOT NULL,
    CONSTRAINT fk_product_id FOREIGN KEY(product_id) REFERENCES product(id),
    price       numeric NOT NULL,
    purchase_id int NOT NULL,
    CONSTRAINT fk_purchase FOREIGN KEY(purchase_id) REFERENCES purchase(id) 
);
