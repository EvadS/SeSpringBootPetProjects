drop table if exists products CASCADE;


drop sequence if exists hibernate_sequence;
 create sequence hibernate_sequence start with 1 increment by 1;


create table products (
                          id bigint not null,
                          count integer not null,
                          STATUS varchar(120),
                          price double,
                          primary key (id)
)