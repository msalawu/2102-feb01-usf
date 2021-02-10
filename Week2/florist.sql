-- create schema florist;

/*
	multi-line comment
*/

-- DDL
create table flower (
	flower_id serial primary key,
	name varchar(30) unique not null,
	current_price money check(current_price > '0.0')
);

create table purchase (
	purchase_id serial primary key,
	flower_id int references flower
);

alter table purchase add column price money not null;

--drop table flower cascade;
--drop table purchase cascade;

-- composite key
create table example (
	column_1 int,
	column_2 int,
	primary key(column_1, column_2)
);

-- DML
insert into flower values
	(default, 'rose', 5.99),
	(default, 'daisy', 5.25),
	(default, 'sunflower', 6.99);
insert into flower values
	(default, 'hibiscus', 9.89),
	(default, 'peony', 5.85),
	(default, 'echinacea', 8.05);
	
insert into purchase values
	(default, 1, 6.99),
	(default, 1, 4.99),
	(default, 3, 8.99),
	(default, 3, 9.59),
	(default, 3, 8.49),
	(default, 6, 10.12);

insert into purchase values (default, 10, 1000);

update flower set current_price = 6.00 where name = 'peony';
update flower set name = 'red rose', current_price = 6.49 where name = 'rose';
update flower set current_price = 7.49 where flower_id > 4;

delete from purchase where flower_id = 6;
delete from flower where flower_id = 6;

update flower set flower_id = 6 where flower_id = 1;
update flower set current_price = 4.99 where flower_id = (select flower_id from purchase where price > '9.00');
update flower set current_price = 5.25 where flower_id = 3;

-- DQL
select * from flower;
select name, current_price from flower;
select name from flower;
select * from flower order by current_price, name desc;
select * from flower where name like 'r%';

-- joins
-- inner join
select * from flower join purchase on flower.flower_id = purchase.flower_id;
-- outer joins
select * from flower left outer join purchase on flower.flower_id = purchase.flower_id;
select * from flower right outer join purchase on flower.flower_id = purchase.flower_id;
select * from flower full outer join purchase on flower.flower_id = purchase.flower_id;
-- cross join
select * from flower cross join purchase;
-- thetajoin
select * from flower join purchase on current_price < price;

select a.* from
		(select flower.flower_id as id, name, current_price, price, purchase.flower_id, purchase_id
		from flower join purchase on current_price < price) as a
	join purchase as b on a.id = b.flower_id;
	
select * from flower join purchase on current_price < price and flower.flower_id = purchase.flower_id;

-- scalar functions
-- examples: round, trim, substr, length, upper, lower, concat, cast
select upper(name) from flower;
select (round(price::numeric))::money from purchase;
select cast(price as numeric) from purchase;
select concat(name, ' costs ', current_price) as "Flower Cost" from flower;

-- aggregate functions
-- examples: count, avg, sum, max, min
select count(purchase_id) from purchase where flower_id = (select flower_id from flower where name = 'red rose');
select avg(price::numeric) from purchase;

-- group by
select flower_id, name, count(price) from flower join purchase using (flower_id)
	group by flower_id;
	
-- having
select flower_id, name, count(price) from flower join purchase using (flower_id)
	group by flower_id having name = 'red rose';