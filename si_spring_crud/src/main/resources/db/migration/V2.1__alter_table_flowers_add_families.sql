alter table flowers add column families_id int not null;
alter table flowers
    add constraint fk_flowers_families
    foreign key (families_id) references families (id);