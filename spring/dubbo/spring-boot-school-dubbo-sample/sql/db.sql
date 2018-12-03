create table tbl_course_type(
   type_id int primary key auto_increment,
   type_name varchar(30) not null
 );
 
INSERT INTO `tbl_course_type` VALUES (1, '专业必修');
INSERT INTO `tbl_course_type` VALUES (2, '专业任选');
INSERT INTO `tbl_course_type` VALUES (3, '校选课');
INSERT INTO `tbl_course_type` VALUES (4, '专家讲座');
INSERT INTO `tbl_course_type` VALUES (5, '公选课');
INSERT INTO `tbl_course_type` VALUES (6, '英语课');
INSERT INTO `tbl_course_type` VALUES (7, '教研课');


create table tbl_users(
   user_no    varchar(20) primary key,
   user_pwd   varchar(1000) not null,
   user_name  varchar(100) not null
);

insert into tbl_users values('000101','123456','王海涛');
insert into tbl_users values('000102','123456','张明');

