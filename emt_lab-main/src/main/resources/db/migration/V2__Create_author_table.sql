CREATE TABLE author
(
    id         BIGSERIAL PRIMARY KEY,
    created_at TIMESTAMP   NOT NULL,
    updated_at TIMESTAMP   NOT NULL,
    name       VARCHAR(50) NOT NULL,
    surname    VARCHAR(50) NOT NULL,
    country_id BIGINT      NOT NULL,
    CONSTRAINT fk_author_country FOREIGN KEY (country_id) REFERENCES country (id)
);

INSERT INTO author(created_at, updated_at, name, surname, country_id)
VALUES (NOW(), NOW(), 'Jane', 'Austen', 1),
       (NOW(), NOW(), 'Stephen', 'King', 2),
       (NOW(), NOW(), 'Victor', 'Hugo', 3);