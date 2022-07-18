-- cria um novo banco de dados chamado bd_meli01
create database bd_meli01;

-- colca o BD bd_meli01 como o bando default (padrão)
use bd_meli01;

-- criar uma tabela chamada 'user'
create table user (
	id integer,
    name varchar(100)
);

-- insere um novo registro na tabela 'user'
insert into user values(1, 'João Silva');

-- consulta os dados da tabela user
select *  from user;

