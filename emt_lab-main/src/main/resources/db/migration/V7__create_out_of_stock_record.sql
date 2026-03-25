CREATE TABLE out_of_stock_event_record (
                                           id BIGSERIAL PRIMARY KEY,
                                           book_id BIGINT NOT NULL,
                                           book_title VARCHAR(255) NOT NULL,
                                           event_timestamp TIMESTAMP NOT NULL
);