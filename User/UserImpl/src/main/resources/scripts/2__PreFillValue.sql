insert into rs_customer values(nextval('id_sequence'), 'Dhaya', 'Dakshina',true, 32, 'Male',now(),null,1);
insert into rs_customer values(nextval('id_sequence'), 'Anshul', 'Patni',true, 38, 'Male',now(),null,1);
insert into rs_customer values(nextval('id_sequence'), 'James', 'Norah',true, 33, 'Female',now(),null,1);

insert into rs_custom_tag('c_id','c_name') values (nextval('id_sequence'), 'Nationality');