CREATE TABLE student (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(255),

    -- Embedded Address fields
    street VARCHAR(255),
    city VARCHAR(255),
    state VARCHAR(255),
    zip_code VARCHAR(20)
);

COMMIT;