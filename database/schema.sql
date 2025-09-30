DROP DATABASE IF EXISTS mobilnet;
CREATE DATABASE mobilnet;
USE mobilnet;

CREATE TABLE roles (
    id INT PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(50) NOT NULL
);

CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    role_id INT NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE clients (
    dni VARCHAR(8) PRIMARY KEY,
    ruc VARCHAR(11) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    phone VARCHAR(9) NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE plans (
    id INT PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(255) NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE promotions (
    id INT PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(255) NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE districts (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE sectors (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE record_states (
    id INT PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(30) NOT NULL
);

CREATE TABLE main_contacts (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL,
    dni VARCHAR(8) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(9) NOT NULL
);

CREATE TABLE secondary_contacts (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL,
    dni VARCHAR(8) NOT NULL,
    email VARCHAR(30) NOT NULL,
    phone VARCHAR(9) NOT NULL
);

CREATE TABLE schedules (
    id INT PRIMARY KEY AUTO_INCREMENT,
    installation_location VARCHAR(100),
    installation_range VARCHAR(100),
    registration_date DATE,
    installation_date DATE
);

CREATE TABLE ruc10_records (
    id INT PRIMARY KEY AUTO_INCREMENT,
    consultant_user_id INT NOT NULL,
    supervisor_user_id INT NOT NULL,
    client_dni VARCHAR(8) NOT NULL,
    main_contact_id INT NOT NULL,
    secondary_contact_id INT NOT NULL,
    plan_id INT NOT NULL,
    promotion_id INT,
    schedule_id INT NOT NULL,
    state_id INT DEFAULT 1,
    district_id INT,
    request_id VARCHAR(255) DEFAULT 'ninguno',
    installation_id VARCHAR(255) DEFAULT 'ninguno',
    cart_id VARCHAR(255) DEFAULT 'ninguno',
    observation VARCHAR(255),
    FOREIGN KEY (consultant_user_id) REFERENCES users(id),
    FOREIGN KEY (supervisor_user_id) REFERENCES users(id),
    FOREIGN KEY (client_dni) REFERENCES clients(dni),
    FOREIGN KEY (main_contact_id) REFERENCES main_contacts(id),
    FOREIGN KEY (secondary_contact_id) REFERENCES secondary_contacts(id),
    FOREIGN KEY (plan_id) REFERENCES plans(id),
    FOREIGN KEY (promotion_id) REFERENCES promotions(id),
    FOREIGN KEY (schedule_id) REFERENCES schedules(id),
    FOREIGN KEY (state_id) REFERENCES record_states(id),
    FOREIGN KEY (district_id) REFERENCES districts(id)
);