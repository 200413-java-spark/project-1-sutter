
drop table states;
drop table employees;

create table employees(id integer primary key, name varchar);
insert into employees values(1, 'Will');
--select * from employees;

create table states(id integer primary key, state varchar, lease_count integer);
