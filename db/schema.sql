CREATE TABLE IF NOT EXISTS user
(
    id        varchar(30) not null unique,
    password  char(60)    not null,
    email     varchar(60) not null,
    role char(60)    not null,
    primary key (id)
) default character set utf8
  collate utf8_general_ci;