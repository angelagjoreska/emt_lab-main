CREATE TABLE country
(
    id        BIGSERIAL PRIMARY KEY,
    name      VARCHAR(100) NOT NULL,
    continent VARCHAR(50)  NOT NULL
);

INSERT INTO country(name, continent)
VALUES ('North Macedonia', 'Europe'),
       ('USA', 'North America'),
       ('France', 'Europe');