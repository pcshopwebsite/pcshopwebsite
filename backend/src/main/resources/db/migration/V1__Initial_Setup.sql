-- Create user table if not exists
CREATE TABLE IF NOT EXISTS public.user (
  id UUID PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  created_at TIMESTAMP,
  updated_at TIMESTAMP,
  created_by VARCHAR(255),
  updated_by VARCHAR(255)
);

-- Create index on user table if not exists
CREATE INDEX IF NOT EXISTS idx_user_email ON public.user (email);

-- Create category table if not exists
CREATE TABLE IF NOT EXISTS category (
  id UUID PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
                              created_at TIMESTAMP,
                              updated_at TIMESTAMP,
                              created_by VARCHAR(255),
                              updated_by VARCHAR(255)
);

-- Create index on category table if not exists
CREATE INDEX IF NOT EXISTS idx_category_name ON category (name);

-- Create brand table if not exists
CREATE TABLE IF NOT EXISTS brand (
  id UUID PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
                              created_at TIMESTAMP,
                              updated_at TIMESTAMP,
                              created_by VARCHAR(255),
                              updated_by VARCHAR(255)
);

-- Create index on brand table if not exists
CREATE INDEX IF NOT EXISTS idx_brand_name ON brand (name);

-- Create computer table if not exists
CREATE TABLE IF NOT EXISTS computer (
  id UUID PRIMARY KEY,
  name VARCHAR(255) NOT NULL UNIQUE,
  description TEXT,
  price DECIMAL(10, 2) NOT NULL,
  rating FLOAT,
  thumbnail VARCHAR(255),
  category_id UUID NOT NULL,
  brand_id UUID NOT NULL,
  specification TEXT,
                      created_at TIMESTAMP,
                      updated_at TIMESTAMP,
                      created_by VARCHAR(255),
                      updated_by VARCHAR(255),
  FOREIGN KEY (category_id) REFERENCES category (id),
  FOREIGN KEY (brand_id) REFERENCES brand (id)
);

-- Create indexes on computer table if not exists
CREATE INDEX IF NOT EXISTS idx_computer_category_id ON computer (category_id);
CREATE INDEX IF NOT EXISTS idx_computer_brand_id ON computer (brand_id);

-- Create cart table if not exists
CREATE TABLE IF NOT EXISTS cart (
  id UUID PRIMARY KEY,
  FOREIGN KEY (id) REFERENCES public.user (id)
);

-- Create cart_item table if not exists
CREATE TABLE IF NOT EXISTS cart_item (
  id UUID PRIMARY KEY,
  cart_id UUID NOT NULL,
  computer_id UUID NOT NULL,
  quantity INT,
                created_at TIMESTAMP,
                updated_at TIMESTAMP,
                created_by VARCHAR(255),
                updated_by VARCHAR(255),
  FOREIGN KEY (cart_id) REFERENCES cart (id),
  FOREIGN KEY (computer_id) REFERENCES computer (id)
);

-- Create indexes on cart_item table if not exists
CREATE INDEX IF NOT EXISTS idx_cart_item_cart_id ON cart_item (cart_id);
CREATE INDEX IF NOT EXISTS idx_cart_item_computer_id ON cart_item (computer_id);

-- Create order table if not exists
CREATE TABLE IF NOT EXISTS public.order (
  id UUID PRIMARY KEY,
  user_id UUID NOT NULL,
  shipping_address VARCHAR(255),
  shipping_city VARCHAR(100),
  shipping_postal_code VARCHAR(10),
  receiver_name VARCHAR(100),
  receiver_phone VARCHAR(20),
  additional_note TEXT,
  total_amount DECIMAL(10, 2),
  order_status VARCHAR(20),
                            created_at TIMESTAMP,
                            updated_at TIMESTAMP,
                            created_by VARCHAR(255),
                            updated_by VARCHAR(255),
  FOREIGN KEY (user_id) REFERENCES public.user (id)
);

-- Create order_item table if not exists
CREATE TABLE IF NOT EXISTS order_item (
  id UUID PRIMARY KEY,
  order_id UUID NOT NULL,
  computer_id UUID NOT NULL,
  quantity INT,
  price DECIMAL(10, 2),
                        created_at TIMESTAMP,
                        updated_at TIMESTAMP,
                        created_by VARCHAR(255),
                        updated_by VARCHAR(255),
  FOREIGN KEY (order_id) REFERENCES public.order (id),
  FOREIGN KEY (computer_id) REFERENCES computer (id)
);

-- Create media table if not exists
CREATE TABLE IF NOT EXISTS media (
  id UUID PRIMARY KEY,
  computer_id UUID,
  file_path VARCHAR(255),
  file_type VARCHAR(50),
                         created_at TIMESTAMP,
                         updated_at TIMESTAMP,
                         created_by VARCHAR(255),
                         updated_by VARCHAR(255),
  FOREIGN KEY (computer_id) REFERENCES computer (id)
);