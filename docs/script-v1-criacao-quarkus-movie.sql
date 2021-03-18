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