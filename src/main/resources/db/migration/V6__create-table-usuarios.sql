CREATE TABLE IF NOT EXISTS usuarios (

    id bigint not null auto_increment,
    login varchar(100) not null,
    senha varchar(255) not null,

    primary key(id)
);