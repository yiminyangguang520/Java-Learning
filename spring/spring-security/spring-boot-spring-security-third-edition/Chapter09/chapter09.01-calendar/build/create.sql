create sequence hibernate_sequence start with 1 increment by 1
create table calendar_users (id integer not null, email varchar(255), first_name varchar(255), last_name varchar(255), password varchar(255), primary key (id))
create table events (id integer not null, description varchar(255) not null, summary varchar(255) not null, when timestamp not null, attendee integer, owner integer not null, primary key (id))
create table persistent_logins (series varchar(255) not null, last_used timestamp, token varchar(255), username varchar(255), primary key (series))
create table role (id integer not null, name varchar(255), primary key (id))
create table user_role (user_id integer not null, role_id integer not null, primary key (user_id, role_id))
alter table events add constraint FKlaq5j62pjejbqud229wxlo3fl foreign key (attendee) references calendar_users
alter table events add constraint FKq6wxpkh9gqbuv84078vcpk3hb foreign key (owner) references calendar_users
alter table user_role add constraint FKa68196081fvovjhkek5m97n3y foreign key (role_id) references role
alter table user_role add constraint FKk4h0gth5yu5wecm1u27cmff1b foreign key (user_id) references calendar_users
create sequence hibernate_sequence start with 1 increment by 1
create table calendar_users (id integer not null, email varchar(255), first_name varchar(255), last_name varchar(255), password varchar(255), primary key (id))
create table events (id integer not null, description varchar(255) not null, summary varchar(255) not null, when timestamp not null, attendee integer, owner integer not null, primary key (id))
create table persistent_logins (series varchar(255) not null, last_used timestamp, token varchar(255), username varchar(255), primary key (series))
create table role (id integer not null, name varchar(255), primary key (id))
create table user_role (user_id integer not null, role_id integer not null, primary key (user_id, role_id))
alter table events add constraint FKlaq5j62pjejbqud229wxlo3fl foreign key (attendee) references calendar_users
alter table events add constraint FKq6wxpkh9gqbuv84078vcpk3hb foreign key (owner) references calendar_users
alter table user_role add constraint FKa68196081fvovjhkek5m97n3y foreign key (role_id) references role
alter table user_role add constraint FKk4h0gth5yu5wecm1u27cmff1b foreign key (user_id) references calendar_users
create sequence hibernate_sequence start with 1 increment by 1
create table calendar_users (id integer not null, email varchar(255), first_name varchar(255), last_name varchar(255), password varchar(255), primary key (id))
create table events (id integer not null, description varchar(255) not null, summary varchar(255) not null, when timestamp not null, attendee integer, owner integer not null, primary key (id))
create table persistent_logins (series varchar(255) not null, last_used timestamp, token varchar(255), username varchar(255), primary key (series))
create table role (id integer not null, name varchar(255), primary key (id))
create table user_role (user_id integer not null, role_id integer not null, primary key (user_id, role_id))
alter table events add constraint FKlaq5j62pjejbqud229wxlo3fl foreign key (attendee) references calendar_users
alter table events add constraint FKq6wxpkh9gqbuv84078vcpk3hb foreign key (owner) references calendar_users
alter table user_role add constraint FKa68196081fvovjhkek5m97n3y foreign key (role_id) references role
alter table user_role add constraint FKk4h0gth5yu5wecm1u27cmff1b foreign key (user_id) references calendar_users
create sequence hibernate_sequence start with 1 increment by 1
create table calendar_users (id integer not null, email varchar(255), first_name varchar(255), last_name varchar(255), password varchar(255), primary key (id))
create table events (id integer not null, description varchar(255) not null, summary varchar(255) not null, when timestamp not null, attendee integer, owner integer not null, primary key (id))
create table persistent_logins (series varchar(255) not null, last_used timestamp, token varchar(255), username varchar(255), primary key (series))
create table role (id integer not null, name varchar(255), primary key (id))
create table user_role (user_id integer not null, role_id integer not null, primary key (user_id, role_id))
alter table events add constraint FKlaq5j62pjejbqud229wxlo3fl foreign key (attendee) references calendar_users
alter table events add constraint FKq6wxpkh9gqbuv84078vcpk3hb foreign key (owner) references calendar_users
alter table user_role add constraint FKa68196081fvovjhkek5m97n3y foreign key (role_id) references role
alter table user_role add constraint FKk4h0gth5yu5wecm1u27cmff1b foreign key (user_id) references calendar_users
create sequence hibernate_sequence start with 1 increment by 1
create table calendar_users (id integer not null, email varchar(255), first_name varchar(255), last_name varchar(255), password varchar(255), primary key (id))
create table events (id integer not null, description varchar(255) not null, summary varchar(255) not null, when timestamp not null, attendee integer, owner integer not null, primary key (id))
create table persistent_logins (series varchar(255) not null, last_used timestamp, token varchar(255), username varchar(255), primary key (series))
create table role (id integer not null, name varchar(255), primary key (id))
create table user_role (user_id integer not null, role_id integer not null, primary key (user_id, role_id))
alter table events add constraint FKlaq5j62pjejbqud229wxlo3fl foreign key (attendee) references calendar_users
alter table events add constraint FKq6wxpkh9gqbuv84078vcpk3hb foreign key (owner) references calendar_users
alter table user_role add constraint FKa68196081fvovjhkek5m97n3y foreign key (role_id) references role
alter table user_role add constraint FKk4h0gth5yu5wecm1u27cmff1b foreign key (user_id) references calendar_users
create sequence hibernate_sequence start with 1 increment by 1
create table calendar_users (id integer not null, email varchar(255), first_name varchar(255), last_name varchar(255), password varchar(255), primary key (id))
create table events (id integer not null, description varchar(255) not null, summary varchar(255) not null, when timestamp not null, attendee integer, owner integer not null, primary key (id))
create table persistent_logins (series varchar(255) not null, last_used timestamp, token varchar(255), username varchar(255), primary key (series))
create table role (id integer not null, name varchar(255), primary key (id))
create table user_role (user_id integer not null, role_id integer not null, primary key (user_id, role_id))
alter table events add constraint FKlaq5j62pjejbqud229wxlo3fl foreign key (attendee) references calendar_users
alter table events add constraint FKq6wxpkh9gqbuv84078vcpk3hb foreign key (owner) references calendar_users
alter table user_role add constraint FKa68196081fvovjhkek5m97n3y foreign key (role_id) references role
alter table user_role add constraint FKk4h0gth5yu5wecm1u27cmff1b foreign key (user_id) references calendar_users
create sequence hibernate_sequence start with 1 increment by 1
create table calendar_users (id integer not null, email varchar(255), first_name varchar(255), last_name varchar(255), password varchar(255), primary key (id))
create table events (id integer not null, description varchar(255) not null, summary varchar(255) not null, when timestamp not null, attendee integer, owner integer not null, primary key (id))
create table persistent_logins (series varchar(255) not null, last_used timestamp, token varchar(255), username varchar(255), primary key (series))
create table role (id integer not null, name varchar(255), primary key (id))
create table user_role (user_id integer not null, role_id integer not null, primary key (user_id, role_id))
alter table events add constraint FKlaq5j62pjejbqud229wxlo3fl foreign key (attendee) references calendar_users
alter table events add constraint FKq6wxpkh9gqbuv84078vcpk3hb foreign key (owner) references calendar_users
alter table user_role add constraint FKa68196081fvovjhkek5m97n3y foreign key (role_id) references role
alter table user_role add constraint FKk4h0gth5yu5wecm1u27cmff1b foreign key (user_id) references calendar_users
create sequence hibernate_sequence start with 1 increment by 1
create table calendar_users (id integer not null, email varchar(255), first_name varchar(255), last_name varchar(255), password varchar(255), primary key (id))
create table events (id integer not null, description varchar(255) not null, summary varchar(255) not null, when timestamp not null, attendee integer, owner integer not null, primary key (id))
create table persistent_logins (series varchar(255) not null, last_used timestamp, token varchar(255), username varchar(255), primary key (series))
create table role (id integer not null, name varchar(255), primary key (id))
create table user_role (user_id integer not null, role_id integer not null, primary key (user_id, role_id))
alter table events add constraint FKlaq5j62pjejbqud229wxlo3fl foreign key (attendee) references calendar_users
alter table events add constraint FKq6wxpkh9gqbuv84078vcpk3hb foreign key (owner) references calendar_users
alter table user_role add constraint FKa68196081fvovjhkek5m97n3y foreign key (role_id) references role
alter table user_role add constraint FKk4h0gth5yu5wecm1u27cmff1b foreign key (user_id) references calendar_users
create sequence hibernate_sequence start with 1 increment by 1
create table calendar_users (id integer not null, email varchar(255), first_name varchar(255), last_name varchar(255), password varchar(255), primary key (id))
create table events (id integer not null, description varchar(255) not null, summary varchar(255) not null, when timestamp not null, attendee integer, owner integer not null, primary key (id))
create table persistent_logins (series varchar(255) not null, last_used timestamp, token varchar(255), username varchar(255), primary key (series))
create table role (id integer not null, name varchar(255), primary key (id))
create table user_role (user_id integer not null, role_id integer not null, primary key (user_id, role_id))
alter table events add constraint FKlaq5j62pjejbqud229wxlo3fl foreign key (attendee) references calendar_users
alter table events add constraint FKq6wxpkh9gqbuv84078vcpk3hb foreign key (owner) references calendar_users
alter table user_role add constraint FKa68196081fvovjhkek5m97n3y foreign key (role_id) references role
alter table user_role add constraint FKk4h0gth5yu5wecm1u27cmff1b foreign key (user_id) references calendar_users
