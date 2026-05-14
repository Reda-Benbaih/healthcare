create table user(
    id int auto_increment primary key,
    user_name varchar(255) not null,
    email varchar(255) not null unique,
    password varchar(255) not null,
);