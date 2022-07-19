SELECT * FROM movies_db_meli.actors;

create temporary table actors_names
select first_name, last_name
from actors
where rating > 5;

select * from actors_names;