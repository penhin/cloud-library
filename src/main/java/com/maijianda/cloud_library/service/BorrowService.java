package com.maijianda.cloud_library.service;

import com.maijianda.cloud_library.entity.Borrow;

import java.util.List;

public interface BorrowService {

    void borrowBook(Long bookId, Long userId);

    void returnBook(Long borrowId);

    void confirmReturn(Long borrowId);

    List<Borrow> listMyBorrows(Long userId);

    List<Borrow> listAllBorrows();
}

