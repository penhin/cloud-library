package com.maijianda.cloud_library.service;

import com.maijianda.cloud_library.entity.Book;
import java.util.List;

public interface BookService {

    void addBook(Book book);

    void updateBook(Book book);

    void deleteBook(Long bookId);

    Book getBookById(Long bookId);

    List<Book> listBooks(Book condition);
}

