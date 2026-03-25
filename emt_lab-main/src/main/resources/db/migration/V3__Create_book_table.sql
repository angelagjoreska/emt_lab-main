
CREATE TABLE book
(
    id               BIGSERIAL PRIMARY KEY,
    created_at       TIMESTAMP    NOT NULL,
    updated_at       TIMESTAMP    NOT NULL,
    name             VARCHAR(100) NOT NULL,
    category         VARCHAR(20)  NOT NULL,
    author_id        BIGINT       NOT NULL,
    state            VARCHAR(10)  NOT NULL,
    available_copies INT          NOT NULL,
    CONSTRAINT fk_book_author FOREIGN KEY (author_id) REFERENCES author (id)
);

INSERT INTO book(created_at, updated_at, name, category, author_id, state, available_copies)
VALUES
    (NOW(), NOW(), 'Pride and Prejudice', 'NOVEL', 1, 'GOOD', 5),
    (NOW(), NOW(), 'It', 'THRILLER', 2, 'GOOD', 3),
    (NOW(), NOW(), 'Les Misérables', 'DRAMA', 3, 'GOOD', 2);