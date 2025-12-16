-- Active: 1758127399777@@127.0.0.1@3306@cloud_library
CREATE DATABASE IF NOT EXISTS cloud_library
DEFAULT CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;
USE cloud_library;

CREATE TABLE IF NOT EXISTS users (
    user_id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '用户ID',
    username VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名',
    password VARCHAR(100) NOT NULL COMMENT '密码',
    role VARCHAR(20) NOT NULL COMMENT '身份组',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) COMMENT='用户表';

CREATE TABLE IF NOT EXISTS books (
    book_id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '图书ID',
    title VARCHAR(100) NOT NULL COMMENT '书名',
    author VARCHAR(100) NOT NULL COMMENT '作者',
    publisher VARCHAR(100) COMMENT '出版社',
    total_stock INT NOT NULL COMMENT '总库存',
    available_stock INT NOT NULL COMMENT '可用库存',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) COMMENT='图书表';

CREATE TABLE IF NOT EXISTS borrows (
    borrow_id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '借阅记录ID',
    user_id BIGINT NOT NULL COMMENT '用户ID',
    book_id BIGINT NOT NULL COMMENT '图书ID',
    borrow_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '借阅日期',
    return_time TIMESTAMP NULL COMMENT '归还日期',
    status VARCHAR(20) NOT NULL COMMENT '状态: BORROWED, RETURNED, CONFIRMED',
    CONSTRAINT fk_borrow_user FOREIGN KEY (user_id) REFERENCES users(user_id),
    CONSTRAINT fk_borrow_book FOREIGN KEY (book_id) REFERENCES books(book_id)
) COMMENT='借阅记录表';

CREATE INDEX idx_borrow_user ON borrows(user_id);
CREATE INDEX idx_borrow_book ON borrows(book_id);
CREATE INDEX idx_borrow_status ON borrows(status);

INSERT INTO users (username, password, role) VALUES
('admin', '123456', 'ADMIN'),
('alice', '123456', 'USER'),
('bob', '123456', 'USER');

INSERT INTO books (title, author, publisher, total_stock, available_stock) VALUES
('Java 程序设计', '张三', '清华大学出版社', 10, 8),
('Spring Boot 实战', 'Craig Walls', '人民邮电出版社', 5, 5),
('数据库系统概论', '王珊', '高等教育出版社', 12, 10),
('计算机网络', '谢希仁', '电子工业出版社', 7, 6),
('操作系统原理', '汤子瀛', '西安电子科技大学出版社', 6, 6);

INSERT INTO borrows (user_id, book_id, status) VALUES
(2, 1, 'BORROWED'),
(2, 2, 'RETURNED'),
(2, 3, 'CONFIRMED');
