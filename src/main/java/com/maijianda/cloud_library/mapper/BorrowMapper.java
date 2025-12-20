package com.maijianda.cloud_library.mapper;

import com.maijianda.cloud_library.entity.Borrow;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BorrowMapper {

    int insert(Borrow borrow);

    int updateStatus(@Param("borrowId") Long borrowId,
                     @Param("status") String status);
                     
    Borrow selectById(Long borrowId);
    
    List<Borrow> selectByUserId(Long userId);

    List<Borrow> selectAll();
} 