create table users (
    id          serial primary key,
    name        varchar(50),
    email       varchar(150),
    password    varchar(300),
    role        varchar(20)
);


create table exhibitions (
    id              serial primary key,
    title            varchar(300),
    ticket_price    numeric(18, 2),
    opening_date    date,
    closing_date    date,
    status          varchar(30)
);


create table exhibitions_halls (
    hall_id         int,
    exhibition_id   int,
    foreign key (exhibition_id) references exhibitions (id)
);

create table tickets (
    id              serial primary key,
    user_id         int,
    exhibition_id   int,
    price           numeric(18, 2),
    purchase_time    timestamp,
    foreign key (user_id) references users (id),
    foreign key (exhibition_id) references exhibitions (id)
);