
drop table counties;
drop table states;

--create table states(id serial primary key, state varchar not null, lease_count integer);
create table states(id integer primary key, state varchar, lease_count integer);

--create table counties(id serial primary key, name varchar not null, state varchar not null, lease_count integer);
create table counties(id integer primary key, name varchar, state varchar, lease_count integer);
