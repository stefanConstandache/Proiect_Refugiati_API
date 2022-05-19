create sequence hibernate_sequence start 1 increment 1;
create table Refugee (email varchar(255), first_name varchar(255), last_name varchar(255), telephone varchar(255), primary key (email));
create table Request (id numeric(7), refugee_email varchar(255), request_type varchar(255), user_notes varchar(500), creation_date date, request_status varchar(255), accepted_by varchar(255), primary key (id));
