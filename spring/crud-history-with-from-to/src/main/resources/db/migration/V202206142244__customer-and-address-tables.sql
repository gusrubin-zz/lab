CREATE SEQUENCE customer_id_seq;

CREATE TABLE customer (
    id          oid CONSTRAINT customer_primary_key PRIMARY KEY DEFAULT nextval('customer_id_seq'),
    name        name NOT NULL,
    address_id  oid
);

CREATE SEQUENCE customer_address_id_seq;

CREATE TABLE customer_address (
    id          oid CONSTRAINT address_primary_key PRIMARY KEY DEFAULT nextval('customer_address_id_seq'),
    street      name NOT NULL,
    city        name NOT NULL,
    country     name NOT NULL
);