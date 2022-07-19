-- group by

select count(*) from series;

-- quantas temporadas estão cadastradas
select count(*) from seasons;

-- quantas temporadas existem para cada série
select series.title, count(*) as temporadas
from seasons inner join series on series.id = seasons.serie_id
group by series.id;

-- quantas temporadas existem para a série "The Walking Dead"
select series.title, count(*) as temporadas
from seasons inner join series on series.id = seasons.serie_id
where series.title = 'The Walking Dead'
group by series.id;

-- quais séries tem mais que 5 temporadas
select series.title, count(*) as temporadas
from seasons inner join series on series.id = seasons.serie_id
group by series.id
having temporadas > 5;

-- quais as 3 séries com maior número de temporadas
select series.title, count(*) as temporadas
from seasons inner join series on series.id = seasons.serie_id
group by series.id
order by temporadas desc
limit 3;

-- subconsultas

-- Quais os nomes dos atores que trabalham em filmes com avaliação maior que 9.1
-- 1. consulta interna: selecionar os id's dos atores que trabalharam em filmes com avaliação maior que 9.1
-- 2. a partir da resposta anterior, listas os atores cujo id está na lista gerada no passo 1

select * 
from actors
where id in

(select actor_movie.actor_id
from movies inner join actor_movie on movies.id = actor_movie.movie_id
where rating > 9.1)

order by first_name;

-- outra forma de resolver usando join
select first_name, last_name
from movies inner join actor_movie on movies.id = actor_movie.movie_id
			inner join actors on actors.id = actor_movie.actor_id
where movies.rating > 9.1
order by first_name;


-- outra forma de resolver usando join, agora ordenado por avaliação do ator
select first_name, last_name, actors.rating as 'Nota do ator' 
from movies inner join actor_movie on movies.id = actor_movie.movie_id
			inner join actors on actors.id = actor_movie.actor_id
where movies.rating > 9.1
order by actors.rating desc;

-- quais são os filmes que não é o filme preferido de nenhum ator
select movies.title
from movies
where not exists
( select actors.first_name from actors where favorite_movie_id = movies.id );

