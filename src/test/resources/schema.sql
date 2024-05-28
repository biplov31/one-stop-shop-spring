-- User table
create table if not exists "user_account" (
    id serial primary key,
    firstname varchar(255) not null,
    lastname varchar(255) not null,
    password varchar(255) not null,
    email varchar(255) unique,
    created_on date default current_date
);

-- Product table
create table if not exists "product" (
    id serial primary key,
    title varchar(255) not null,
    description text not null,
    price numeric not null,
    category varchar(100)
);

-- Product review table
create table if not exists "review" (
    id serial primary key,
    content text not null,
    created_on date default current_date,
    updated_on date,
    user_id integer references "user_account"(id),
    product integer not null references "product"(id) on delete cascade
);

-- Order table
create table if not exists "user_order" (
    id serial primary key,
    user_id integer references "user_account"(id),
    product_id integer references "product"(id),
    created_on date default current_date,
    quantity integer default 1 not null
);

-- Cart table
drop table if exists "cart";
create table if not exists "cart" (
    cart_item_id serial primary key,
    user_id integer not null references "user_account"(id) on delete cascade,
    product_id integer not null references "product"(id),
    quantity integer default 1 not null
);

