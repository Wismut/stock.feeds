CREATE TABLE IF NOT EXISTS tutorial
(
    id          SERIAL PRIMARY KEY,
    title       VARCHAR(255),
    description VARCHAR(255),
    published   BOOLEAN
);
