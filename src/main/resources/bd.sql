DROP TABLE IF EXISTS USERS;


CREATE TABLE IF NOT EXISTS USERS(
    
    id serial, 
    
    login varchar(10),
    password varchar(100),
    rule text,
    token text,
    token_refresh text,

    PRIMARY KEY(id)
);


INSERT INTO USERS(login, password, rule, token, token_refresh)VALUES('sam','1111','visitor',null,null);
INSERT INTO USERS(login, password, rule, token, token_refresh)VALUES('samy','2222','user',null,null);
INSERT INTO USERS(login, password, rule, token, token_refresh)VALUES('SamRus','3333','admin',null,null);