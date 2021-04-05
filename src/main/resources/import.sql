CREATE TABLE movie (
  id serial PRIMARY KEY  -- implicit primary key constraint
, name    text NOT null
, imdb_id  text NOT null UNIQUE
, image_url text
, qualifier text
, rank numeric
, staff text
, year numeric
);

CREATE TABLE "users" (
  id  serial PRIMARY KEY
, name     text NOT null
, email     text NOT NULL
, password     text NOT null
);

CREATE TABLE user_movie (
  user_id    int REFERENCES "users" (id) ON UPDATE CASCADE ON DELETE CASCADE
, movie_id int REFERENCES movie (id) ON UPDATE CASCADE
, rate     numeric NOT NULL DEFAULT 1
,  watchlist boolean NOT NULL DEFAULT false
,  watched boolean NOT NULL DEFAULT false
, CONSTRAINT user_movie_pkey PRIMARY KEY (user_id, movie_id)  -- explicit pk
);

CREATE TABLE movie (
  id serial NOT NULL,
  name    text NOT null,
  imdb_id  text NOT null UNIQUE,
  image_url text,
  qualifier text,
  rank numeric,
  staff text,
  year numeric,
  constraint PK_MOVIE primary key (ID)
);

CREATE TABLE "users" (
  id  serial NOT NULL,
  name     text NOT null,
  email     text NOT NULL,
  password     text NOT null,
  constraint PK_USER primary key (ID)
);

CREATE TABLE user_movie (
  user_id    int REFERENCES "users" (id) ON UPDATE CASCADE ON DELETE CASCADE
, movie_id int REFERENCES movie (id) ON UPDATE CASCADE
, rate     numeric NOT NULL DEFAULT 1
,  watchlist boolean NOT NULL DEFAULT false
,  watched boolean NOT NULL DEFAULT false
, CONSTRAINT user_movie_pkey PRIMARY KEY (user_id, movie_id)  -- explicit pk
);


INSERT INTO "public"."users"("id","name","email","password") VALUES (12, 'marcus', 'mp@mp.com', '$2a$10$COJF6oWzOLMBYr/XU7pACevg7Km9jwzulojxmRUM3vwZ3cC43sZp.');


INSERT INTO "public"."movie"("id","name","imdb_id","image_url","qualifier","rank","staff","year")
VALUES
(1, '  o vento levou', '1233123', 'http://www.imagem.com', 'movie',5, 'Scarlet',NULL),
(2, 'Robocop', '12312323', 'http://', 'movie',1231, 'robocpo',NULL),
(3, 'Invisible City', 'tt8878862', 'https://m.media-amazon.com/images/M/MV5BYTM5MDg3MGMtODAyYy00MGQ0LWJhM2MtZDFmMjAzNTFhZGRmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_.jpg', 'TV series',1020,NULL,2021),
(4, 'Cidade Invisível', 'tt7733222', 'https://m.media-amazon.com/images/M/MV5BOWEzNTRiYTgtYmUxNC00YjhiLWFjNDktY2M5OWIwOTAxMjA3XkEyXkFqcGdeQXVyODMwNzE4MjU@._V1_.jpg', 'TV mini-series',46051,NULL,2017),
(5, 'Cidade invisível', 'tt9736024', 'https://m.media-amazon.com/images/M/MV5BNDdlODBkNWQtZTY3Ny00NTA3LWI0YjktMjM0MjY4NzgxYjM2XkEyXkFqcGdeQXVyNDMyOTY4MDc@._V1_.jpg', 'feature',215121,NULL,2018),
(7, 'White Chicks', 'tt0381707', 'https://m.media-amazon.com/images/M/MV5BMTY3OTg2OTM3OV5BMl5BanBnXkFtZTYwNzY5OTA3._V1_.jpg', 'feature',1320,NULL,2004);
