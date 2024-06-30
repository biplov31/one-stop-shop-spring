alter table user_order
    drop column if exists product_id,
    drop column if exists quantity,
    add column total_cost numeric not null default 0;

-- Order table
--create table if not exists user_order (
--    id serial,
--    user_id integer not null,
--    created_at timestamp default current_timestamp,
--    primary key (id),
--    foreign key (user_id) references user_account(id)
--);

create table if not exists order_item (
    id serial primary key,
    order_id integer not null references user_order(id) on delete cascade,
    product_id integer not null references product(id),
    price numeric not null,
    quantity integer default 1 not null
);