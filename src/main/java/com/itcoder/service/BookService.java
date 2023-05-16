package com.itcoder.service;

import com.google.protobuf.BoolValueOrBuilder;
import com.itcoder.mapper.BookMapper;
import com.itcoder.pojo.Book;
import com.itcoder.pojo.PageResult;
import com.itcoder.pojo.User;
import org.apache.ibatis.plugin.Interceptor;

public interface BookService {
    /**
     * 查询新书，
     * @param pageNum 页码
     * @param pageSize 数据条数
     * @return
     */
    PageResult getNewBooks(Integer pageNum , Integer pageSize);

    /**
     * 根据id查找图书
     * @param id
     * @return
     */
    Book findBookById(Integer id);

    Integer borrowBook(Book book, User user);

    PageResult listBooks(Integer pageNum,Integer pageSize);

    PageResult searchBook(Book book,Integer pageNum,Integer pageSize);

    Integer insertBook(Book book);

    Integer updateBook(Book book);

    PageResult searchBorrowed(Book book,User
                              user,Integer pageNum,Integer pageSize);

    Integer returnBook(Book book);

    Integer confirmReturn(Book book);
}
