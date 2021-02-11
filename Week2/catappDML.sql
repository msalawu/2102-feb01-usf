insert into breed values
	(1, 'Persian'),
	(2, 'Domestic Shorthair'),
	(3, 'Calico');
	
insert into status values
	(1, 'Available'),
	(2, 'Adopted');
	
insert into cat values
	(default, 'Fluffy', 3, 1, 1),
	(default, 'Lucky', 7, 1, 2),
	(default, 'Howard', 1, 1, 3),
	(default, 'Cat', 0, 1, 1);

insert into user_role values
	(1, 'Employee'),
	(2, 'User');

insert into person values (default, 'revature', 'pass', 1);

commit;