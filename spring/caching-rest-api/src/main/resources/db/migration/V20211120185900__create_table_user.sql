CREATE TABLE IF NOT EXISTS users
(
    id				SERIAL PRIMARY KEY,
    username		VARCHAR,
    password_hash	VARCHAR
);