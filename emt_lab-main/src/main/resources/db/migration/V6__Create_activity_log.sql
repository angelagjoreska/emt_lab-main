CREATE TABLE activity_log (
                              id BIGSERIAL PRIMARY KEY,
                              book_title VARCHAR(255) NOT NULL,
                              event_time TIMESTAMP NOT NULL,
                              event_type VARCHAR(50) NOT NULL
);