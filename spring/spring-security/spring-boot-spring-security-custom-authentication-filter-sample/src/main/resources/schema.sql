create table printer (id varchar(255) not null, is_detonated boolean, name varchar(255), primary key (id))
create table user (id varchar(255) not null, login varchar(255), password varchar(255), roles varchar(255), primary key (id))
