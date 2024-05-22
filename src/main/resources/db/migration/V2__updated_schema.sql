-- User table
--create table if not exists user_account (
--    id serial primary key,
--    firstname varchar(255) not null,
--    lastname varchar(255) not null,
--    password varchar(255) not null,
--    email varchar(255) unique,
--    created_on date default current_date
--);

-- Product category
--create table if not exists product_category (
--    id serial primary key,
--    name varchar(255) not null
--);

-- Product table
--create table if not exists product (
--    id serial primary key,
--    title varchar(255) not null,
--    description text not null,
--    price numeric not null,
--    category varchar(100)
----    category_id integer references product_category(id)
--);

alter table review
    add column id serial primary key;

-- Product review table
--create table if not exists review (
--    id serial primary key,
--    content text not null,
--    created_on date default current_date,
--    updated_on date,
--    user_id integer references user_account(id),
--    product integer not null references product(id) on delete cascade
--);

alter table user_order
    add column quantity integer default 1 not null;

-- Order table
--create table if not exists user_order (
--    id serial,
--    user_id integer not null,
--    product_id integer not null,
--    quantity integer not null,
--    created_on date default current_date,
--    primary key (id), -- we may need to identify orders separately for similar orders made on different dates
--    foreign key (user_id) references user_account(id),
--    foreign key (product_id) references product(id)
--);

alter table cart
    add column cart_item_id serial primary key,
    alter column user_id set not null,
    alter column product_id set not null,
    add column quantity integer default 1 not null;

-- Cart table
--create table if not exists cart (
--    cart_item_id serial,
--    user_id integer not null,
--    product_id integer not null,
--    quantity integer not null,
--    primary key (cart_item_id),
--    -- primary key (user_id, product_id), -- Spring Data JDBC doesn't have the best support for composite keys
--    foreign key (user_id) references user_account(id) on delete cascade,
--    foreign key (product_id) references product(id)
--);
