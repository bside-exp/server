-- create schema exp collate utf8_general_ci;

create table roles
(
	id bigint auto_increment
		primary key,
	name varchar(255) null,
	constraint roles_uk_1 unique (name)
);

create table user
(
	id bigint auto_increment
		primary key,
	created_date datetime not null,
	updated_date datetime not null,
	nick_name varchar(255) null,
	password varchar(255) null,
	username varchar(255) null,
	constraint user_uk_1 unique(nick_name),
	constraint user_uk_2 unique(username)
);

create table user_roles
(
	user_id bigint not null,
	roles_id bigint not null,
	constraint FK55itppkw3i07do3h7qoclqd4k
		foreign key (user_id) references user (id),
	constraint FKdbv8tdyltxa1qjmfnj9oboxse
		foreign key (roles_id) references roles (id),
	primary key (user_id, roles_id)
);

