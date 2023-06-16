-- Insert Brands
INSERT INTO brand (name) VALUES ('Apple'), ('Lenovo'), ('Dell'), ('HP');

-- Insert Categories
INSERT INTO category (name) VALUES ('PC'), ('Laptop'), ('Macbook');

-- Insert Computers
WITH computer_data AS (
  SELECT
    'Apple' AS brand_name,
    'PC' AS category_name
  UNION ALL
  SELECT
    'Lenovo' AS brand_name,
    'PC' AS category_name
  UNION ALL
  SELECT
    'Dell' AS brand_name,
    'Laptop' AS category_name
  UNION ALL
  SELECT
    'HP' AS brand_name,
    'Macbook' AS category_name
)
INSERT INTO computer (name, description, price, rating, thumbnail, brand_id, category_id, specification, created_at, updated_at)
SELECT
  CONCAT(cd.brand_name, ' ', c.num),
  'Description of ' || cd.brand_name || ' ' || c.num,
  ROUND((RANDOM() * 1000 + 500)::INT, 2),
  ROUND((RANDOM() * 5)::INT, 1),
  'https://stevenguyendev.com/thumbnail-url-' || c.num || '.jpg',
  b.id,
  cat.id,
  'Specification of ' || cd.brand_name || ' ' || c.num,
  NOW(),
  NOW()
FROM
  generate_series(1, 100) AS c(num)
  JOIN computer_data AS cd ON true
  JOIN brand AS b ON b.name = cd.brand_name
  JOIN category AS cat ON cat.name = cd.category_name;

-- Insert Media for each Computer
WITH media_data AS (
  SELECT
    'https://stevenguyendev.com/media-url-1.jpg' AS file_path,
    'image/jpeg' AS file_type
  UNION ALL
  SELECT
    'https://stevenguyendev.com/media-url-2.jpg' AS file_path,
    'image/jpeg' AS file_type
  UNION ALL
  SELECT
    'https://stevenguyendev.com/media-url-3.jpg' AS file_path,
    'image/jpeg' AS file_type
  UNION ALL
  SELECT
    'https://stevenguyendev.com/media-url-4.jpg' AS file_path,
    'image/jpeg' AS file_type
)
INSERT INTO media (computer_id, file_path, file_type, created_at)
SELECT
  c.id,
  md.file_path,
  md.file_type,
  NOW()
FROM
  computer AS c
  CROSS JOIN media_data AS md
ORDER BY
  c.id, md.file_path;
