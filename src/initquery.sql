create table users (
	username char (20),
	is_role char(20), 
	plate_num char(7), 
	temp_plate char(7), 
	primary key (username)
);
create table lots (
	lot_id int not null, 
	member_fee int, 
	primary key (lot_id)
);
create table spots (
	spot_num int not null, 
	lot_id int,
	reserved_by char(20),
	primary key (spot_num),
	foreign key (lot_id) references lots(lot_id),
	foreign key (reserved_by) references users(username)
);
create table reservation (
	member bool, 
	drive_in bool,
	online bool,
	start_time timestamp,
	end_time timestamp,
	res_num int not null, 
	primary key (res_num)
);
create role a_member;
create role non_member;
create role staff;
create role admin;
ALTER ROLE admin WITH superuser;
CREATE USER joe WITH password 1234;
GRANT admin TO joe; 

