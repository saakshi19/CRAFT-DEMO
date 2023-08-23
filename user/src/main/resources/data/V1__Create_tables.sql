CREATE TABLE users(
email_id VARCHAR(255) PRIMARY KEY,
name VARCHAR(255),
address_list TEXT[], -- This can store a list of Address objects
region VARCHAR(255)
)