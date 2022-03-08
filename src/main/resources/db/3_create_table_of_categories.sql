CREATE TABLE if not exists categories(
    id serial PRIMARY KEY,
    category VARCHAR(100) UNIQUE NOT NULL
);