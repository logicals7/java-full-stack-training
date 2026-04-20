Drop Table if exists springbootdb;
Drop database if exists springbootdb;
create database springbootdb;
use springbootdb;
drop table if exists employee;
create table if not exists employee(
    employeeId int(11) unsigned not null auto_increment,
    employeeName varchar(20) default null,
    salary double default null,
    departmentCode varchar(220) default null,
    primary key (employeeId)
) ENGINE = InnoDB AUTO_INCREMENT=22 DEFAULT charset = utf8;
insert into employee(employeeId, employeeName, salary, departmentCode)
values
    (1001, 'MSD', 200000, '101'),
    (1002, 'ABD', 300000, '102'),
    (1003, 'RP', 400000, '103');
commit;