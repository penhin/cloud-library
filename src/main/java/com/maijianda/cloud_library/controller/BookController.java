package com.maijianda.cloud_library.controller;

import com.maijianda.cloud_library.common.Result;
import com.maijianda.cloud_library.entity.Book;
import com.maijianda.cloud_library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public Result<Void> add(@RequestBody Book book) {
        bookService.addBook(book);
        return Result.success("新增图书成功", null);
    }

    @PutMapping
    public Result<Void> update(@RequestBody Book book) {
        bookService.updateBook(book);
        return Result.success("更新图书成功", null);
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        bookService.deleteBook(id);
        return Result.success("删除图书成功", null);
    }

    @GetMapping("/{id}")
    public Result<Book> get(@PathVariable Long id) {
        return Result.success(bookService.getBookById(id));
    }

    @GetMapping
    public Result<List<Book>> list(Book condition) {
        return Result.success(bookService.listBooks(condition));
    }
}

