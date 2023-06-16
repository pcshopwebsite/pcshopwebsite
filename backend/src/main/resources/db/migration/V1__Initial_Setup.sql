-- Create user table if not exists
CREATE TABLE IF NOT EXISTS public.user (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  created_at TIMESTAMP NOT NULL
);

-- Create index on user table if not exists
CREATE INDEX IF NOT EXISTS idx_user_email ON public.user (email);

-- Create category table if not exists
CREATE TABLE IF NOT EXISTS category (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL
);

-- Create index on category table if not exists
CREATE INDEX IF NOT EXISTS idx_category_name ON category (name);

-- Create brand table if not exists
CREATE TABLE IF NOT EXISTS brand (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL
);

-- Create index on brand table if not exists
CREATE INDEX IF NOT EXISTS idx_brand_name ON brand (name);

-- Create computer table if not exists
CREATE TABLE IF NOT EXISTS computer (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL UNIQUE,
  description TEXT,
  price DECIMAL(10, 2) NOT NULL,
  rating FLOAT,
  thumbnail VARCHAR(255),
  category_id INT NOT NULL,
  brand_id INT NOT NULL,
  specification TEXT,
  created_at TIMESTAMP NOT NULL,
  updated_at TIMESTAMP NOT NULL,
  FOREIGN KEY (category_id) REFERENCES category (id),
  FOREIGN KEY (brand_id) REFERENCES brand (id)
);

-- Create indexes on computer table if not exists
CREATE INDEX IF NOT EXISTS idx_computer_category_id ON computer (category_id);
CREATE INDEX IF NOT EXISTS idx_computer_brand_id ON computer (brand_id);

-- Create cart table if not exists
CREATE TABLE IF NOT EXISTS cart (
  id SERIAL PRIMARY KEY,
  user_id INT NOT NULL,
  created_at TIMESTAMP NOT NULL,
  FOREIGN KEY (user_id) REFERENCES public.user (id)
);

-- Create index on cart table if not exists
CREATE INDEX IF NOT EXISTS idx_cart_user_id ON cart (user_id);

-- Create cart_item table if not exists
CREATE TABLE IF NOT EXISTS cart_item (
  id SERIAL PRIMARY KEY,
  cart_id INT NOT NULL,
  computer_id INT NOT NULL,
  quantity INT,
  created_at TIMESTAMP NOT NULL,
  FOREIGN KEY (cart_id) REFERENCES cart (id),
  FOREIGN KEY (computer_id) REFERENCES computer (id)
);

-- Create indexes on cart_item table if not exists
CREATE INDEX IF NOT EXISTS idx_cart_item_cart_id ON cart_item (cart_id);
CREATE INDEX IF NOT EXISTS idx_cart_item_computer_id ON cart_item (computer_id);

-- Create order table if not exists
CREATE TABLE IF NOT EXISTS public.order (
  id SERIAL PRIMARY KEY,
  user_id INT NOT NULL,
  shipping_address VARCHAR(255),
  shipping_city VARCHAR(100),
  shipping_postal_code VARCHAR(10),
  receiver_name VARCHAR(100),
  receiver_phone VARCHAR(20),
  additional_note TEXT,
  total_amount DECIMAL(10, 2),
  order_status VARCHAR(20),
  created_at TIMESTAMP NOT NULL,
  FOREIGN KEY (user_id) REFERENCES public.user (id)
);

-- Create order_item table if not exists
CREATE TABLE IF NOT EXISTS order_item (
  id SERIAL PRIMARY KEY,
  order_id INT NOT NULL,
  computer_id INT NOT NULL,
  quantity INT,
  price DECIMAL(10, 2),
  FOREIGN KEY (order_id) REFERENCES public.order (id),
  FOREIGN KEY (computer_id) REFERENCES computer (id)
);

-- Create media table if not exists
CREATE TABLE IF NOT EXISTS media (
  id SERIAL PRIMARY KEY,
  computer_id INT,
  file_path VARCHAR(255),
  file_type VARCHAR(50),
  created_at TIMESTAMP NOT NULL,
  FOREIGN KEY (computer_id) REFERENCES computer (id)
);