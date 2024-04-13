create table licences(
                           id serial primary key ,
                           number varchar(255) unique not null
);
CREATE TABLE authors(
                       id SERIAL PRIMARY KEY ,
                       name VARCHAR(255)unique not null,
                        licence_id INT UNIQUE NOT NULL ,
                       FOREIGN KEY (licence_id) REFERENCES licences(id) ON DELETE SET NULL
);
CREATE TABLE books(
                     id SERIAL PRIMARY KEY ,
                     name VARCHAR(255) UNIQUE NOT NULL ,
                     author_id INT NOT NULL ,
                     FOREIGN KEY (author_id) REFERENCES authors(id) ON DELETE SET NULL

);
CREATE TABLE authors_books(
                              authors_id int,
                              FOREIGN KEY (authors_id) REFERENCES authors(id),
                              books_id INT,
                              FOREIGN KEY (books_id) REFERENCES books(id)
);

INSERT INTO authors_books
(authors_id, books_id)
SELECT a.id,b.id
FROM authors a
         CROSS JOIN books b
WHERE a.id = b.author_id;