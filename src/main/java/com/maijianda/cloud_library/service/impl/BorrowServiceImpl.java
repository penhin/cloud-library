package com.maijianda.cloud_library.service.impl;

import com.maijianda.cloud_library.entity.Book;
import com.maijianda.cloud_library.entity.Borrow;
import com.maijianda.cloud_library.mapper.BookMapper;
import com.maijianda.cloud_library.mapper.BorrowMapper;
import com.maijianda.cloud_library.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BorrowServiceImpl implements BorrowService {
    
    @Autowired
    private BorrowMapper borrowMapper;

    @Autowired
    private BookMapper bookMapper;

    @Override
    @Transactional
    public void borrowBook(Long bookId, Long userId) {
        
        Book book = bookMapper.selectById(bookId);
        if (book == null || book.getAvailableStock() <= 0) {
            throw new RuntimeException("借阅图书库存不足");
        }
        
        Borrow borrow = new Borrow();
        borrow.setBookId(bookId);
        borrow.setUserId(userId);
        borrow.setStatus("BORROWED");
        borrowMapper.insert(borrow);

        book.setAvailableStock(book.getAvailableStock() - 1);
        bookMapper.update(book);
    }
    
    @Override
    public void returnBook(Long borrowId) {
        Borrow borrow = borrowMapper.selectById(borrowId);

        if (borrow == null || "RETURNED".equals(borrow.getStatus()) || "CONFIRMED".equals(borrow.getStatus())) {
            throw new RuntimeException("非法操作");
        }

        borrowMapper.updateStatus(borrowId, "RETURNED");
    }

    @Override
    @Transactional
    public void confirmReturn(Long borrowId) {
        Borrow borrow = borrowMapper.selectById(borrowId);

        if (borrow == null || !"RETURNED".equals(borrow.getStatus())) {
            throw new RuntimeException("非法操作");
        }

        borrowMapper.updateStatus(borrowId, "CONFIRMED");

        Book book = bookMapper.selectById(borrow.getBookId());
        book.setAvailableStock(book.getAvailableStock() + 1);
        bookMapper.update(book);
    }
    
    @Override
    public List<Borrow> listMyBorrows(Long userId) {
        return borrowMapper.selectByUserId(userId);
    }
    
    @Override
    public List<Borrow> listAllBorrows() {
        return borrowMapper.selectAll();
    }
}
 