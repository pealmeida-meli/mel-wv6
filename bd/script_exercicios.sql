-- Adicione um filme à tabela de filmes.
insert into movies values(
	null, now(), null, 'Star Wars IV', 10, 3, '1977-11-18', 144, 5
);

-- Adicione um gênero à tabela de gêneros.
insert into genres values (null, now(), null, 'Classico', 13, 1);

-- Associe o filme inserido no item  1. com o gênero criado item 2.
update movies 
set genre_id = 13, updated_at = now()
where movies.title = 'Star Wars IV';
-- where movies.id = 22;

-- Modifique a tabela de atores para que pelo menos um ator tenha o filme adicionado item 1 como favorito.

-- Crie uma tabela temporária da tabela filmes.
create temporary table movies_tmp
select * from movies;

-- Elimine da tabela temporária do item 5 todos os filmes que ganharam menos de 5 prêmios.
delete from movies_tmp where awards < 5;
select * from movies_tmp;

-- Obtenha a lista de todos os gêneros que possuem pelo menos um filme.
select distinct name
from genres inner join movies on genres.id = movies.genre_id;

-- Obtenha a lista de atores cujo filme favorito ganhou mais de 3 prêmios.
select first_name, last_name
from actors inner join movies on actors.favorite_movie_id = movies.id
where movies.awards > 3;

-- Crie um índice no título na tabela de filmes. Verifique se o índice foi criado corretamente.
create index indx_movies_title on movies(title);
