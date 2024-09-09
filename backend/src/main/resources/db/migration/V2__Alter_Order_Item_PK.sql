alter table public.order_item
drop constraint if exists order_item_pkey;

alter table public.order_item
add constraint order_product_pk primary key (order_id, computer_id);

alter table public.order_item
drop column if exists id;