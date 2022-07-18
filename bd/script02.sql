-- Mostrar todos os registros da tabela de filmes.
select * 
from movies;

-- Mostrar o nome, sobrenome e classificação de todos os atores.
select first_name, last_name, rating 
from actors;

-- Mostrar o título de todas as séries e use alias para que tanto o nome da tabela 
-- quanto o campo estejam em Português.
select séries.title as Título
from series as séries;

-- Mostrar o nome e sobrenome dos atores cuja classificação é superior a 7,5.
select first_name, last_name, rating 
from actors
where rating > 7.5;

-- Mostrar o título dos filmes, a classificação e os prêmios dos filmes com classificação
-- superior a 7,5 e com mais de dois prêmios.
SELECT title, rating, awards
FROM movies
WHERE rating > 7.5 AND awards > 2;

-- Mostrar o título dos filmes e a classificação ordenados por classificação em ordem crescente.
SELECT title, rating
FROM movies
ORDER BY rating desc;

--  Mostrar os títulos dos três primeiros filmes no banco de dados.
select title
from movies
limit 3;

-- Mostrar os 5 melhores filmes com a classificação mais alta.
select title, rating
from movies
order by rating desc
limit 5;

-- Listar os 10 primeiros atores.
select first_name, last_name
from actors
limit 10;

-- Mostrar o título e a classificação de todos os filmes cujo título é Toy Story
select title, rating
from movies
where title like '%Toy Story%';

-- Mostrar todos os atores cujos nomes começam com Sam.
select first_name, last_name
from actors
where first_name like 'Sam%';

-- Mostrar o título dos filmes que saíram entre 2004 e 2008.
select title, year(release_date)
from movies
-- where year(release_date) >= 2004 and year(release_date) <= 2008;
where year(release_date) between 2004 and 2008;


-- Mostrar o título dos filmes com classificação superior a 3, com mais de 1 prêmio e
-- com data de lançamento entre 1988 e 2009. Ordenar os resultados por
-- classificação.

SELECT title, rating, awards
FROM movies
WHERE rating > 3 AND awards > 1 AND YEAR(release_date) BETWEEN 1988 AND 2009
ORDER BY rating;

--

-- insere um novo registro no BD
insert into actors values (null, null, null, 'Armando', 'Santos', 5.7, 1);

--
-- atualiza o sobrenome do ator com id 50
update actors 
set last_name = 'Ferreira'
where id = 50;

--
-- apaga o registro com id 50
delete from actors 
where id = 50;
