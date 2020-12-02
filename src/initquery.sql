DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF exists lots CASCADE;
DROP TABLE IF EXISTS spots CASCADE;
DROP TABLE IF EXISTS reservation CASCADE;
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
	lot_id int NOT null,
	reserved_by char(20),
	available bool,
	primary key (spot_num, lot_id),
	foreign key (lot_id) references lots(lot_id)
);
create table reservation (
	member bool, 
	drive_in bool,
	online bool,
	spot_num int not null, 
	lot_id int NOT null,
	start_time timestamp,
	end_time timestamp,
	res_num int not null, 
	primary key (res_num),
	foreign key (spot_num, lot_id) REFERENCES spots(spot_num, lot_id)
);
create role a_member;
create role non_member;
create role staff;
create role admin;
ALTER ROLE admin WITH superuser;
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO admin;
CREATE USER joe WITH password 1234;
GRANT admin TO joe; 

