CREATE SCHEMA `db`;
USE `db`;
CREATE TABLE `messagejob`
(
    `name`    VARCHAR(100) NOT NULL,
    `message` VARCHAR(45) NULL,
    `cron`    VARCHAR(45) NULL,
    PRIMARY KEY (`name`)
);

INSERT INTO `messagejob`
VALUES ("JOB-001", "Job 01 Message", "0 0/1 * 1/1 * ? *"),
       ("JOB-002", "Job 02 Message", "0 0/3 * 1/1 * ? *");
SELECT * FROM `messagejob`;