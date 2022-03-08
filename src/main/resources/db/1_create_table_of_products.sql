CREATE TABLE if not exists products (
    id serial primary key,
    name VARCHAR(100) NOT NULL unique,
    calories INTEGER NOT NULL
);