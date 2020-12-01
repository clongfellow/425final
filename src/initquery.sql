create table customers (
	isMember bool, 
	plate_num char(7) not null, 
	temp_plate char(7), 
	primary key (plate_num)
);
create table lots (
	lot_id int not null, 
	member_fee int, 
	primary key (lot_id)
);
create table spots (
	spot_num int not null, 
	lot_id int,
	reserved_by char(7),
	primary key (spot_num),
	foreign key (lot_id) references lots(lot_id),
	foreign key (reserved_by) references customers(plate_num)
);
create table reservation (
	member bool, 
	drive_in bool,
	online bool,
	res_num int not null, 
	primary key (res_num)
);
create role a_member;
create role non_member;
create role staff;
create role administrator;
