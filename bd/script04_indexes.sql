create database bd_meli01;

use bd_meli01;

SELECT * FROM user;

-- apagar toda a tabela e seus dados
-- drop table user;

create table user(
id integer,
nome varchar(100)
);

insert into user values(1, 'Marcos');

alter table user
add constraint primary key (id);

insert into user values(1, 'Fabiana');

alter table user
add email varchar(50);

update user set email = 'marcos@email.com' where id = 1;

insert into user values(2, 'Fabiana', 'marcos@email.com');

alter table user
add constraint unique (email);

update user set email = 'fabiana@email.com' where id = 1;

select * from user where id = 1;

select * from user where nome = 'Marcos';




