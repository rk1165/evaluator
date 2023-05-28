DROP TABLE IF EXISTS "USER" CASCADE;

CREATE TABLE "user" (
    user_id varchar(50),
    plus_count integer,
    minus_count integer,
    multiply_count integer,
    division_count integer,
    CONSTRAINT pk_user PRIMARY KEY (user_id)
);