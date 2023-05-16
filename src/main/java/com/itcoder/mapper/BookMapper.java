package com.itcoder.mapper;

import com.github.pagehelper.Page;
import com.itcoder.pojo.Book;
import org.apache.ibatis.annotations.*;
import org.apache.logging.log4j.core.tools.picocli.CommandLine;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Repository
public interface BookMapper {
    @Select("select * from book where book_status != '3' order by book_uploadtime desc")
    @Results(id = "bookMap",value = {
            @Result(column = "book_id",property = "bookId"),
            @Result(column = "book_name", property = "bookName"),
            @Result(column = "book_isbn",property = "bookIsbn"),
            @Result(column = "book_press",property = "bookPress"),
            @Result(column = "book_author",property = "bookAuthor"),
            @Result(column = "book_pagination",property = "bookPagination"),
            @Result(column = "book_price",property = "bookPrice"),
            @Result(column = "book_uploadtime",property = "bookUploadtime"),
            @Result(column = "book_status",property = "bookStatus"),
            @Result(column = "book_brrower",property = "bookBrrower"),
            @Result(column = "book_borrowtime",property = "bookBorrowtime"),
            @Result(column = "book_returntime",property = "bookReturntime")
    })
    Page<Book> getNewBook();

    @Select("select * from book where book_id = #{id}")
    @ResultMap(value = "bookMap")
    Book findBookById(@Param("id") int id);

    //更新图书
    Integer updateBook(Book book);


    @Select("select * from book")
    @ResultMap("bookMap")
    Page<Book> listBooks();

    @ResultMap("bookMap")
    Page<Book> getBooksByInfo(Book book);


    Integer insertBook(Book book);


    Page<Book> getBorrowedBook(Book book);

    @ResultMap("bookMap")
    Page<Book> getMyBorrowed(Book book);
}
