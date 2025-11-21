CREATE TABLE users (
    id UUID PRIMARY KEY,
    username VARCHAR(30) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(20) NOT NULL,
    phone VARCHAR(50),
    avatar_url VARCHAR(255),
    card_number_id VARCHAR(50),
    role VARCHAR(20) NOT NULL
);