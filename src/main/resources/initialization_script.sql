insert into ers_reimbursment_status 
	values (default, 'pending'),
		(default, 'approved'),
		(default, 'denied');
		
insert into ers_reimbursment_type 
	values (default, 'lodging'),
		(default, 'travel'),
		(default, 'food'),
		(default, 'other');
		
insert into ers_user_roles 
	values (default, 'employee'),
		(default, 'manager');
		