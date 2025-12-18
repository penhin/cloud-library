package com.maijianda.cloud_library.mapper;

import com.maijianda.cloud_library.entity.Book;
import java.util.List;

public interface BookMapper {
       
    int insert(Book book);

    int update(Book book);

    int deleteById(Long bookId);

    Book selectById(Long bookId);

    List<Book> selectByConditions(Book book);
}
