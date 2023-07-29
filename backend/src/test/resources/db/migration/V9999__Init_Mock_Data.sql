-- Insert fixed data into category table
INSERT INTO category (name)
VALUES ('Laptop'), ('Desktop'), ('Macbook');

-- Insert fixed data into brand table
INSERT INTO brand (name)
VALUES ('Apple'), ('Dell'), ('Lenovo');

-- Insert fixed data into computer table
INSERT INTO computer (name, description, price, rating, thumbnail, category_id, brand_id, specification, created_at, updated_at)
VALUES
  ('Macbook Pro 13", Intel i5, 8GB RAM, 256GB SSD', '13-inch Macbook Pro with Retina Display', 1299.99, 4.5, 'macbook-pro-13.jpg', 3, 1, 'Specs for Macbook Pro 13-inch', NOW(), NOW()),
  ('Dell XPS 15, Intel i7, 16GB RAM, 512GB SSD', '15-inch Dell XPS laptop', 1799.99, 4.7, 'dell-xps-15.jpg', 1, 2, 'Specs for Dell XPS 15', NOW(), NOW()),
  ('Lenovo ThinkPad X1 Carbon, Intel i5, 16GB RAM, 512GB SSD', '14-inch Lenovo ThinkPad X1 Carbon', 1399.99, 4.6, 'thinkpad-x1-carbon.jpg', 1, 3, 'Specs for ThinkPad X1 Carbon', NOW(), NOW()),
  ('Apple MacBook Air 13", Intel i3, 8GB RAM, 256GB SSD', '13-inch MacBook Air', 1099.99, 4.4, 'macbook-air-13.jpg', 3, 1, 'Specs for MacBook Air 13-inch', NOW(), NOW()),
  ('Dell Inspiron 14, Intel i5, 8GB RAM, 256GB SSD', '14-inch Dell Inspiron laptop', 799.99, 4.2, 'dell-inspiron-14.jpg', 1, 2, 'Specs for Dell Inspiron 14', NOW(), NOW()),
  ('Dell XPS 13, Intel i7, 16GB RAM, 1TB SSD', '13-inch Dell XPS laptop', 2099.99, 4.8, 'dell-xps-13.jpg', 1, 2, 'Specs for Dell XPS 13', NOW(), NOW()),
  ('Dell Latitude 15, Intel i5, 8GB RAM, 512GB SSD', '15-inch Dell Latitude laptop', 1099.99, 4.3, 'dell-latitude-15.jpg', 1, 2, 'Specs for Dell Latitude 15', NOW(), NOW()),
  ('Apple iMac 21.5-inch, Intel i5, 8GB RAM, 512GB SSD', '21.5-inch Apple iMac', 1299.99, 4.6, 'apple-imac-21.jpg', 2, 1, 'Specs for Apple iMac 21.5-inch', NOW(), NOW()),
  ('Apple Mac Mini, Intel i3, 8GB RAM, 256GB SSD', 'Apple Mac Mini desktop', 799.99, 4.4, 'apple-mac-mini.jpg', 2, 1, 'Specs for Apple Mac Mini', NOW(), NOW()),
  ('Apple Mac Pro, Intel Xeon, 32GB RAM, 1TB SSD', 'Apple Mac Pro workstation', 4999.99, 4.9, 'apple-mac-pro.jpg', 2, 1, 'Specs for Apple Mac Pro', NOW(), NOW()),
  ('Lenovo ThinkPad E14, Intel i5, 8GB RAM, 256GB SSD', '14-inch Lenovo ThinkPad', 999.99, 4.5, 'thinkpad-e14.jpg', 3, 3, 'Specs for ThinkPad E14', NOW(), NOW()),
  ('Lenovo IdeaPad 15, Intel i3, 4GB RAM, 1TB HDD', '15-inch Lenovo IdeaPad laptop', 599.99, 4.1, 'ideapad-15.jpg', 3, 3, 'Specs for Lenovo IdeaPad 15', NOW(), NOW()),
  ('Lenovo Legion 5, AMD Ryzen 7, 16GB RAM, 512GB SSD', '15-inch Lenovo gaming laptop', 1399.99, 4.7, 'legion-5.jpg', 3, 3, 'Specs for Lenovo Legion 5', NOW(), NOW()),
  ('Apple MacBook Pro 16", Intel i7, 16GB RAM, 512GB SSD', '16-inch MacBook Pro', 2399.99, 4.9, 'macbook-pro-16.jpg', 1, 1, 'Specs for MacBook Pro 16-inch', NOW(), NOW()),
  ('Apple MacBook Air 15", Intel i3, 8GB RAM, 256GB SSD', '15-inch MacBook Air', 1099.99, 4.4, 'macbook-air-15.jpg', 1, 1, 'Specs for MacBook Air 15-inch', NOW(), NOW()),
  ('Dell OptiPlex 3070, Intel i5, 8GB RAM, 256GB SSD', 'Dell OptiPlex desktop', 899.99, 4.3, 'dell-optiplex-3070.jpg', 2, 2, 'Specs for Dell OptiPlex 3070', NOW(), NOW()),
  ('Dell Precision 5750, Intel i7, 32GB RAM, 1TB SSD', 'Dell Precision workstation', 2799.99, 4.8, 'dell-precision-5750.jpg', 2, 2, 'Specs for Dell Precision 5750', NOW(), NOW()),
  ('Apple MacBook Pro 13", M1, 8GB RAM, 256GB SSD', '13-inch MacBook Pro', 1499.99, 4.7, 'macbook-pro-13-m1.jpg', 1, 1, 'Specs for MacBook Pro 13-inch M1', NOW(), NOW()),
  ('Apple MacBook Air 11", Intel Core 2 Duo, 4GB RAM, 128GB SSD', '11-inch MacBook Air', 799.99, 4.1, 'macbook-air-11.jpg', 1, 1, 'Specs for MacBook Air 11-inch', NOW(), NOW()),
  ('Lenovo ThinkPad X1 Carbon, Intel i7, 16GB RAM, 1TB SSD', '14-inch Lenovo ThinkPad', 1899.99, 4.9, 'thinkpad-x1-carbon.jpg', 2, 3, 'Specs for ThinkPad X1 Carbon', NOW(), NOW()),
  ('Lenovo IdeaPad 14, Intel i3, 8GB RAM, 512GB SSD', '14-inch Lenovo IdeaPad laptop', 799.99, 4.3, 'ideapad-14.jpg', 2, 3, 'Specs for Lenovo IdeaPad 14', NOW(), NOW()),
  ('Lenovo ThinkCentre M90n, Intel i5, 8GB RAM, 512GB SSD', 'Lenovo ThinkCentre desktop', 999.99, 4.6, 'thinkcentre-m90n.jpg', 2, 3, 'Specs for ThinkCentre M90n', NOW(), NOW()),
  ('Lenovo Legion Tower 5, Intel i7, 16GB RAM, 1TB SSD', 'Lenovo Legion gaming desktop', 1599.99, 4.8, 'legion-tower-5.jpg', 2, 3, 'Specs for Legion Tower 5', NOW(), NOW());
