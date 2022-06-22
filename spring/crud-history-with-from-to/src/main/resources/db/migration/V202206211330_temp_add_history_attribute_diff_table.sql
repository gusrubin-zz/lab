CREATE SEQUENCE history_attribute_diff_id_seq;

CREATE TABLE history_attribute_diff (
    id             oid CONSTRAINT history_attribute_diff_primary_key PRIMARY KEY DEFAULT nextval('history_attribute_diff_id_seq'),
    history_id     int NOT NULL,
    CONSTRAINT fk_history_id FOREIGN KEY(history_id) REFERENCES history(id),
    attribute_name name,
    attributevalue name
);