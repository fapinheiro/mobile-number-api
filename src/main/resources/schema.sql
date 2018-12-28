-- create sequence
create sequence if not exists SEQ_SUBSCRIBER start with 1 increment by 1 maxvalue 99999999;

-- create table
create table if not exists subscriber ( 
   id int auto_increment, 
   msisdn varchar(164) not null, 
   customer_id_owner int not null, 
   customer_id_user int not null,
   service_type enum('MOBILE_PREPAID', 'MOBILE_POSTPAID') not null,
   service_start_date bigint not null
);

-- adding constraint for msisdn
alter table subscriber add constraint msisdn_unique unique(msisdn);