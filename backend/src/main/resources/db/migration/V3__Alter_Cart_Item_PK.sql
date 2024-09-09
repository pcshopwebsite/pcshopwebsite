alter table public.cart_item
drop constraint if exists cart_item_pkey;

alter table public.cart_item
add constraint cart_product_pk primary key (cart_id, computer_id);

alter table public.cart_item
drop column if exists id;

alter table public.order_item
drop column if exists price;