package com.maijianda.cloud_library.controller;

import com.maijianda.cloud_library.common.Result;
import com.maijianda.cloud_library.entity.Borrow;
import com.maijianda.cloud_library.entity.User;
import com.maijianda.cloud_library.service.BorrowService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/borrow")
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

    @PostMapping("/{bookId}")
    public Result<Void> borrow(@PathVariable Long bookId, HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        borrowService.borrowBook(bookId, user.getUserId());
        return Result.success("借阅成功", null);
    }
    
    @PostMapping("/return/{borrowId}")
    public Result<Void> returnBook(@PathVariable Long borrowId) {
        borrowService.returnBook(borrowId);
        return Result.success("已申请归还", null);
    }
    
    @PostMapping("/confirm/{borrowId}")
    public Result<Void> confirm(@PathVariable Long borrowId) {
        borrowService.confirmReturn(borrowId);
        return Result.success("确认归还成功", null);   
    }
    
    @GetMapping("/my")
    public Result<List<Borrow>> myBorrows(HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        return Result.success(borrowService.listMyBorrows(user.getUserId()));
    }

    @GetMapping("/all")
    public Result<List<Borrow>> allBorrows() {
        return Result.success(borrowService.listAllBorrows());
    }  
}
