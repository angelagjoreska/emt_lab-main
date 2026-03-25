CREATE MATERIALIZED VIEW library_category_stats AS
SELECT
    category AS category,
    COUNT(*) AS total_books,
    SUM(available_copies) AS total_available_copies,
    COUNT(*) FILTER (WHERE state != 'GOOD') AS books_not_good_condition
FROM book
GROUP BY category;