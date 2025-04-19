SELECT * FROM `book_info`
# 添加图书
INSERT INTO book_info (book_name, author, count, price, publish)
VALUES
('图书1', '作者1', 20, 24.50, '出版社1'),
('图书1', '作者1', 20, 24.50, '出版社1'),
('图书1', '作者1', 20, 24.50, '出版社1'),
('图书1', '作者1', 20, 24.50, '出版社1'),
('图书1', '作者1', 20, 24.50, '出版社1'),
('图书1', '作者1', 20, 24.50, '出版社1'),
('图书1', '作者1', 20, 24.50, '出版社1'),
('图书1', '作者1', 20, 24.50, '出版社1'),
('图书1', '作者1', 20, 24.50, '出版社1'),
('图书1', '作者1', 20, 24.50, '出版社1'),
('图书1', '作者1', 20, 24.50, '出版社1');

SELECT * FROM book_info;

# 分页查询
# 分页LIMIT 0, 10 LIMIT offset, pageSize
# offset 代表这 起始页码
# limit 一次多少页数
SELECT * FROM book_info WHERE `status` <> 0 LIMIT 0, 10

SELECT * FROM book_info WHERE `status` <> 0 LIMIT 10, 10

SELECT * FROM book_info WHERE `status` <> 0 LIMIT 20, 10

SELECT COUNT(1) FROM book_info WHERE `status` <> 0

SELECT * FROM book_info WHERE `status` <> 0 AND id = 20

# 更新修改操作
UPDATE book_info SET book_name = '挪威的森林', author = '村上春花' WHERE id = 28;









