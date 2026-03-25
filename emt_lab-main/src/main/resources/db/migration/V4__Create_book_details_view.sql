CREATE VIEW book_details_view AS
SELECT
    b.id AS id,
    b.name AS title,
    b.category AS category,
    b.state AS state,
    b.available_copies AS available_copies,
    CONCAT(a.name, ' ', a.surname) AS author_full_name,
    c.name AS country_name
FROM book b
         JOIN author a ON b.author_id = a.id
         JOIN country c ON a.country_id = c.id;