create table licences
(
    id     serial primary key,
    number varchar(255) unique not null
);
CREATE TABLE authors
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(255) unique not null,
    licence_id INT UNIQUE          NOT NULL,
    FOREIGN KEY (licence_id) REFERENCES licences (id) ON DELETE SET NULL
);
CREATE TABLE books
(
    id        SERIAL PRIMARY KEY,
    name      VARCHAR(255) UNIQUE NOT NULL,
    author_id INT,
    FOREIGN KEY (author_id) REFERENCES authors (id) ON DELETE SET NULL

);
