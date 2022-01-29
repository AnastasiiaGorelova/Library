
CREATE TABLE IF NOT EXISTS books (
    code_number BIGINT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    author VARCHAR(100) NOT NULL,
    user_id BIGINT DEFAULT -1
);

SELECT *
from books
