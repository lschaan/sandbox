CREATE SCHEMA `petshop`;
CREATE TABLE `petshop`.`pet`
(
    `id`    INT NOT NULL,
    `name`  VARCHAR(45) NULL,
    `breed` VARCHAR(45) NULL,
    `age`   INT NULL,
    PRIMARY KEY (`id`)
);
