create table user_role (
	id serial primary key,
	name varchar(10) unique not null
);

create table person (
	id serial primary key,
	username varchar(30) unique not null,
	passwd varchar(30) not null,
	user_role_id integer references user_role
);

create table breed (
	id serial primary key,
	name varchar(40) unique not null
);

create table status (
	id serial primary key,
	name varchar(40) unique not null
);

create table cat (
	id serial primary key,
	name varchar(30),
	age integer not null,
	status_id integer references status,
	breed_id integer references breed
);

create table person_cat (
	person_id integer references person,
	cat_id integer references cat
);

create table special_need (
	id serial primary key,
	name varchar(40) unique not null
);

create table cat_special_need (
	cat_id integer references cat,
	special_need_id integer references special_need
);