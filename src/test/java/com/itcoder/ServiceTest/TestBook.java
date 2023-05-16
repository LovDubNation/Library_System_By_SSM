package com.itcoder.ServiceTest;

import com.itcoder.config.SpringConfig;
import com.itcoder.pojo.Book;
import com.itcoder.pojo.PageResult;
import com.itcoder.pojo.User;
import com.itcoder.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class TestBook {
    @Autowired
    private BookService bookService;
    @Test
    public void getNewBooks(){
        PageResult<Book> newBooks = bookService.getNewBooks(1, 5);
        for (Book book: newBooks.getRows()) {
            System.out.println(book);
        }
    }

    @Test
    public void findBook(){
        Book book = bookService.findBookById(1);
        System.out.println(book);
    }

    @Test
    public void borrowBook(){
        Book book = new Book();
        book.setBookId(2);
        User user = new User();
        user.setUserName("张三");
        Integer integer = bookService.borrowBook(book, user);
        if (integer.intValue() != 0){
            System.out.println("借阅成功");
        }else System.out.println("借阅失败");
    }

    @Test
    public void listAll(){
        PageResult<Book> result = bookService.listBooks(1, 5);
        System.out.println(result.getTotal());
        System.out.println(result.getPageNum());
        System.out.println(result.getPageTotalNum());
        for (Book book:result.getRows()) {
            System.out.println(book);
        }
    }

    @Test
    public void addBook(){
        Book book = new Book();
        book.setBookName("活着");
        book.setBookAuthor("余华");
        book.setBookPress("人邮出版社");
        book.setBookIsbn("9787544280662");
        book.setBookPagination(300);
        book.setBookPrice(88.8);
        Integer result = bookService.insertBook(book);
        System.out.println(result);
    }
}
