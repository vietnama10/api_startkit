CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

-- Chèn dữ liệu mẫu
INSERT INTO users (email, name, password) VALUES
('user1@example.com', 'User One', '$2a$10$7Qbdc...W5zNlfYplC'),
('user2@example.com', 'User Two', '$2a$10$7Qbdc...W5zNlfYplC'),
('user3@example.com', 'User Three', '$2a$10$7Qbdc...W5zNlfYplC'),
('user4@example.com', 'User Four', '$2a$10$7Qbdc...W5zNlfYplC'),
('user5@example.com', 'User Five', '$2a$10$7Qbdc...W5zNlfYplC'),
('user6@example.com', 'User Six', '$2a$10$7Qbdc...W5zNlfYplC'),
('user7@example.com', 'User Seven', '$2a$10$7Qbdc...W5zNlfYplC'),
('user8@example.com', 'User Eight', '$2a$10$7Qbdc...W5zNlfYplC'),
('user9@example.com', 'User Nine', '$2a$10$7Qbdc...W5zNlfYplC'),
('user10@example.com', 'User Ten', '$2a$10$7Qbdc...W5zNlfYplC');