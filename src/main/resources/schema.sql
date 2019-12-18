-- create schema exp collate utf8_general_ci;

create table roles
(
	id bigint auto_increment
		primary key,
	name varchar(255) null
);

create table user
(
	id bigint auto_increment
		primary key,
	created_date datetime not null,
	updated_date datetime not null,
	nick_name varchar(255) null,
	password varchar(255) null,
	username varchar(255) null
);

create table user_roles
(
	user_id bigint not null,
	roles_id bigint not null,
	constraint UK_amwlmdeik2qdnksxgd566knop
		unique (roles_id),
	constraint FK55itppkw3i07do3h7qoclqd4k
		foreign key (user_id) references user (id),
	constraint FKdbv8tdyltxa1qjmfnj9oboxse
		foreign key (roles_id) references roles (id)
);

