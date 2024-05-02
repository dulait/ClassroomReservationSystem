DROP DATABASE IF EXISTS classroom_reservation_system;
DROP USER IF EXISTS `crsadmin`@`%`;

CREATE DATABASE IF NOT EXISTS classroom_reservation_system CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER IF NOT EXISTS `crsadmin`@`%` IDENTIFIED WITH mysql_native_password BY 'crsadmin';
GRANT ALL PRIVILEGES ON `classroom_reservation_system`.* TO `crsadmin`@`%`;