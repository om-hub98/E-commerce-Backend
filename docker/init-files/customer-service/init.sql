CREATE DATABASE IF NOT EXISTS ecomm_customer_db;
USE ecomm_customer_db;

CREATE TABLE customer (
    customer_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    email VARCHAR(100),
    phone_number VARCHAR(15)
);

CREATE TABLE address (
    address_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    street VARCHAR(100),
    city VARCHAR(50),
    pincode VARCHAR(10)
);

CREATE TABLE customer_address (
    customer_fk BIGINT,
    address_fk BIGINT,
    PRIMARY KEY (customer_fk, address_fk),
    FOREIGN KEY (customer_fk) REFERENCES customer(customer_id) ON DELETE CASCADE,
    FOREIGN KEY (address_fk) REFERENCES address(address_id) ON DELETE CASCADE
);
