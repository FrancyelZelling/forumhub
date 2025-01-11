create table users(

    id bigint not null auto_increment,
    username varchar(100) not null,
    email varchar(100) not null UNIQUE,
    password_hashed varchar(255) not null,

    primary key(id)

);