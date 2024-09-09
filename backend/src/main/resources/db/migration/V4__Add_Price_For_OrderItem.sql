alter table public.order_item
add column if not exists price DECIMAL(10, 2);
update public.order_item as oi
set price = (select price from public.computer where id=oi.computer_id );
alter table public.order_item
alter column price set not null;
alter table public.order_item
alter column quantity set not null;