
drop table counties;
drop table states;

create table states(id integer primary key, state varchar, lease_count integer);
create table counties(id integer primary key, name varchar, state varchar, lease_count integer);
