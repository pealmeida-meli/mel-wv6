use movies_db_meli;

select * from genres; -- 12
select * from movies; -- 21

select count(*) from genres; -- 12
select count(*) from movies; -- 21

select * from movies, genres;

select title from movies where genre_id is null;

select * 
from movies, genres
where movies.genre_id = genres.id;

-- liste os filmes (que possuem classificação) e seus nomes de classidicação  
select * 
from movies inner join genres on movies.genre_id = genres.id;

-- liste todos os filmes  e seus nomes de classidicação, inclusive sem classificação  
select * 
from movies left join genres on movies.genre_id = genres.id;

-- liste todas as classificações e os filmes correspondentes  
select * 
from movies right join genres on movies.genre_id = genres.id;

