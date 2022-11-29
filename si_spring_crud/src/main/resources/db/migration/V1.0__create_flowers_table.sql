create table flowers (
    id serial,
    name varchar(50) not null,
    scientific_name varchar(50) not null,
    color varchar(50) not null,
    constraint pk_flowers primary key(id),
    constraint un_scientific_name unique(scientific_name)
);