
drop table counties;
drop table states;
--drop table employees;

--create table employees(id integer primary key, name varchar);
--insert into employees values(1, 'Will');

--create table states(id serial primary key, state varchar not null, lease_count integer);
create table states(id integer primary key, state varchar, lease_count integer);

--create table counties(id serial primary key, name varchar not null, state varchar not null, lease_count integer);
create table counties(id integer primary key, name varchar, state varchar, lease_count integer);
