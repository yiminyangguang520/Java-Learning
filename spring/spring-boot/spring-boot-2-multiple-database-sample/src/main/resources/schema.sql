CREATE TABLE booking(id BIGINT NOT NULL AUTO_INCREMENT, bookingAmount VARCHAR(255), createdBy BIGINT, dropAddress VARCHAR(255), pickupAddress  VARCHAR(255), PRIMARY KEY (id)) ENGINE=InnoDB;

INSERT INTO booking(bookingAmount,createdBy,dropAddress,pickupAddress)VALUES('1250',1,'Chennai','Bangalore');

INSERT INTO booking(bookingAmount,createdBy,dropAddress,pickupAddress)VALUES('2050',1,'Bangalore','Chennai');


create table user_details(id integer not null auto_increment, email varchar(255), first_Name varchar(255), last_Name varchar(255), password varchar(255), primary key (id)) ENGINE=InnoDB;

INSERT INTO user_details(email,first_Name,last_Name,password) VALUES ('abc@test.com','Abc','Test','password');
