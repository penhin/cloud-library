package com.maijianda.cloud_library.service.impl;

import com.maijianda.cloud_library.entity.Book;
import com.maijianda.cloud_library.mapper.BookMapper;
import com.maijianda.cloud_library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public void addBook(Book book) {
        book.setAvailableStock(book.getTotalStock());
        bookMapper.insert(book);
    }

    @Override
    public void updateBook(Book book) {
        bookMapper.update(book);
    }

    @Override
    public void deleteBook(Long bookId) {
        bookMapper.deleteById(bookId);
    }

    @Override
    public Book getBookById(Long bookId) {
        return bookMapper.selectById(bookId);
    }

    @Override
    public List<Book> listBooks(Book condition) {
        return bookMapper.selectByConditions(condition);
    }
}

