-- User table
create table if not exists user_account (
    id serial primary key,
    firstname varchar(255) not null,
    lastname varchar(255) not null,
    password varchar(255) not null,
    email varchar(255) unique,
    created_on date default current_date
);

-- Product category
--create table if not exists product_category (
--    id serial primary key,
--    name varchar(255) not null
--);

-- Product table
create table if not exists product (
    id serial primary key,
    title varchar(255) not null,
    description text not null,
    price numeric not null,
    category varchar(100)
--    category_id integer references product_category(id)
);

-- Product review table
create table if not exists review (
    content text not null,
    created_on date default current_date,
    updated_on date,
    user_id integer references user_account(id),
    product integer not null references product(id) on delete cascade
);

-- Order table
create table if not exists user_order (
    id serial,
    user_id integer,
    product_id integer,
    created_on date default current_date,
    primary key (id),
    foreign key (user_id) references user_account(id),
    foreign key (product_id) references product(id)
);

-- Cart table
create table if not exists cart (
    user_id integer,
    product_id integer,
    foreign key (user_id) references user_account(id) on delete cascade,
    foreign key (product_id) references product(id)
);
