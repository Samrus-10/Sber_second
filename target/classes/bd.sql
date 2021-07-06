DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS access_user;


CREATE TABLE  users (
    id serial,

    login TEXT not null,
    password TEXT not null,
    rule TEXT,

    Primary key(id)
);

CREATE TABLE access_user(
    id_user int,
    token_refresh text,
    token_access text,

    FOREIGN KEY (id_user) REFERENCES users (id) ON DELETE CASCADE
);


INSERT INTO users(login, password, rule)VALUES('sam', '1111', 'visitor');
INSERT INTO users(login, password, rule)VALUES('samy', '2222', 'user');
INSERT INTO users(login, password, rule)VALUES('SamRus', '3333', 'admin');
