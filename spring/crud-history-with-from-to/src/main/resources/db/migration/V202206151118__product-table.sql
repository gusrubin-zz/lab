CREATE SEQUENCE product_id_seq;

CREATE TABLE product (
    id          oid CONSTRAINT product_primary_key PRIMARY KEY DEFAULT nextval('product_id_seq'),
    name        name NOT NULL,
    description name,
    price       numeric
);
