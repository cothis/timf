drop table if exists company CASCADE;
drop table if exists compensation CASCADE;
drop table if exists penalty CASCADE;
drop table if exists seller CASCADE;
drop table if exists shipping CASCADE;
drop table if exists voc CASCADE;

drop sequence if exists company_seq;
drop sequence if exists compensation_seq;
drop sequence if exists penalty_seq;
drop sequence if exists voc_seq;

create sequence company_seq increment by 50;
create sequence compensation_seq increment by 50;
create sequence penalty_seq increment by 50;
create sequence voc_seq increment by 50;

create table company
(
    dtype varchar(31) not null,
    id    bigint      not null,
    name  varchar(255),
    primary key (id)
);

create table compensation
(
    id     bigint not null,
    amount decimal(19, 2),
    voc_id bigint,
    primary key (id)
);

create table penalty
(
    id        bigint not null,
    amount    decimal(19, 2),
    confirmed boolean,
    content   varchar(255),
    objected  boolean,
    primary key (id)
);

create table seller
(
    manager_name  varchar(255),
    manager_phone varchar(255),
    id            bigint not null,
    primary key (id)
);

create table shipping
(
    driver varchar(255),
    id     bigint not null,
    primary key (id)
);

create table voc
(
    id         bigint not null,
    content    varchar(255),
    party      varchar(255),
    penalty_id bigint,
    primary key (id)
);

alter table compensation
    add constraint compensation_voc_fk
        foreign key (voc_id)
            references voc;

alter table seller
    add constraint seller_company_fk
        foreign key (id)
            references company;

alter table shipping
    add constraint shipping_company_fk
        foreign key (id)
            references company;

alter table voc
    add constraint voc_penalty_fk
        foreign key (penalty_id)
            references penalty;

create index confirmed_index
on penalty (confirmed);
create index objected_index
on penalty (objected);


