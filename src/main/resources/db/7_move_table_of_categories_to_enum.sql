ALTER TABLE products ADD COLUMN category VARCHAR(100) NOT NULL DEFAULT 'яйца';

UPDATE products
SET category = (
    SELECT category
    FROM categories
    WHERE products.category_id = categories.id
);

ALTER TABLE products DROP COLUMN category_id;

DROP TABLE categories;