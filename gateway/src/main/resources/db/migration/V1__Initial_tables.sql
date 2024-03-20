CREATE TABLE working_hour(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `employee_id` VARCHAR(255) NOT NULL,
    `registry_date_time` DATETIME NOT NULL,
    CONSTRAINT `pk_id` PRIMARY KEY (id),
    INDEX ix_employee_id (employee_id),
    INDEX registry_date_time (registry_date_time)
);