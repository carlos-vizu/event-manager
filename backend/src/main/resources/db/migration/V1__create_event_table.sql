CREATE TABLE event (
                       id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                       title VARCHAR(100) NOT NULL,
                       description VARCHAR(1000),
                       date_time TIMESTAMP,
                       location VARCHAR(200),
                       deleted BOOLEAN DEFAULT FALSE NOT NULL,
                       created_at TIMESTAMP,
                       updated_at TIMESTAMP
);