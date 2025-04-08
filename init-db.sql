CREATE TABLE IF NOT EXISTS users (
    user_id SERIAL PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

-- Chèn dữ liệu mẫu
INSERT INTO users (email, name, password) VALUES
('user1@example.com', 'User One', '$2a$10$7Qbdc...W5zNlfYplC'),
('user2@example.com', 'User Two', '$2a$10$7Qbdc...W5zNlfYplC');