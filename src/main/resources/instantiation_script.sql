create table ers_reimbursment_status (
	reimb_status_id serial not null primary key,
	reimb_status varchar(10) not null
);

create table ers_reimbursment_type (
	reimb_type_id serial not null primary key,
	reimb_type varchar(10) not null
);

create table ers_user_roles(
	ers_user_role_id serial not null primary key,
	user_role varchar(10) not null
);

create table ers_users(
	ers_users_id serial not null primary key,
	ers_username varchar(50) unique not null,
	ers_password varchar(50) not null,
	user_first_name varchar(100) not null,
	user_last_name varchar(100) not null,
	user_email varchar(150) unique not null,
	user_role_id int not null,
	foreign key (user_role_id) references ers_user_roles(ers_user_role_id)
);

create table ers_reimbursement(
	reimb_id serial not null primary key,
	reimb_amount real not null,
	reimb_submitted timestamp not null,
	reimb_resolved timestamp,
	reimb_description varchar(250),
	reimb_receipt varchar(250),
	reimb_author int not null,
	reimb_resolver int,
	reimb_status_id int not null,
	reimb_type_id int not null,
	foreign key (reimb_author) references ers_users(ers_users_id),
	foreign key (reimb_resolver) references ers_users(ers_users_id),
	foreign key (reimb_status_id) references ers_reimbursment_status(reimb_status_id),
	foreign key (reimb_type_id) references ers_reimbursment_type(reimb_type_id)	
);

