create database batch;
create database db;
use db;
create table Transaction
(
    id          int AUTO_INCREMENT PRIMARY KEY,
    cpf         varchar(14),
    description varchar(255)
);
insert into Transaction
VALUES (1, "54145326059", "abc"),
       (2, "76736580030", "bca"),
       (3, "62443310019", "abb"),
       (4, "56964365010", "cba");
select * from db.`Transaction`;