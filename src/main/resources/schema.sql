DROP TABLE IF EXISTS "user";

CREATE TABLE IF NOT EXISTS "user" (
    id identity,
    user_id varchar(50),
    plus_count integer,
    minus_count integer,
    multiply_count integer,
    division_count integer,
    power_count integer
);