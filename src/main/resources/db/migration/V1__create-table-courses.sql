CREATE TABLE courses (
    id serial PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    image_url VARCHAR(255),
    video_url VARCHAR(255),
    active BOOLEAN
);
