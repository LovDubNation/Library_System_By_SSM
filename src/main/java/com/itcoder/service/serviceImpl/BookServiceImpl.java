package com.itcoder.service.serviceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itcoder.mapper.BookMapper;
import com.itcoder.pojo.*;
import com.itcoder.service.BookService;
import com.itcoder.service.RecordService;
import com.itcoder.utils.DateConvertUtils;
import com.sun.istack.internal.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.PipedReader;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService {
    private final static Logger LOGGER = LoggerFactory.getLogger(BookServiceImpl.class);
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private RecordService recordService;

    @Override
    public PageResult getNewBooks(Integer pageNum, Integer pageSize) {
        //开启分页
        PageHelper.startPage(pageNum,pageSize);
        Page<Book> newBooks = bookMapper.getNewBook();
        PageResult pageResult = new PageResult();
        pageResult.setTotal(newBooks.getTotal());
        pageResult.setRows(newBooks.getResult());
        return pageResult;
    }

    @Override
    public Book findBookById(Integer id) {
        Book book = bookMapper.findBookById(id);
        return book;
    }

    @Override
    public Integer borrowBook(Book book, User user) {
        if (user != null && book != null){
            book.setBookStatus("1");
            //获取当前日期作为借阅时间
            String date = DateConvertUtils.getStringDate(new Date());
            book.setBookBrrower(user.getUserName());
            book.setBookBorrowtime(date);
            Integer result = bookMapper.updateBook(book);
            return result;
        }
        return 0;
    }

    @Override
    public PageResult listBooks(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Page<Book> books = bookMapper.listBooks();
        PageResult result = new PageResult();
        result.setTotal(books.getTotal());
        result.setRows(books.getResult());
        PageInfo info = new PageInfo<>(books);
        int pageNum1 = info.getPageNum();
        result.setPageNum(pageNum1);
        //设置总页面
        if (result.getTotal()% pageSize != 0){
            result.setPageTotalNum(result.getTotal()/pageSize + 1);
        }else {
            result.setPageTotalNum(result.getTotal()/pageSize);
        }
        return result;
    }

    @Override
    public PageResult searchBook(Book book,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Page<Book> books = bookMapper.getBooksByInfo(book);
        PageResult result = pageToPageResult(books.getTotal(),books.getResult(),new PageInfo<>(books.getResult()).getPageNum(),pageSize);
        return result;
    }

    @Override
    public Integer insertBook(Book book){
        Integer result;
        if (book == null){
            return 0;
        }
        //设置上传日期
        String nDate = DateConvertUtils.getStringDate(new Date());
        book.setBookUploadtime(nDate);
        book.setBookStatus("0");
        try {
            result = bookMapper.insertBook(book);
        }catch (Exception e){
            result = 0;
            LOGGER.error("插入图书时发生了错误"+e.getMessage());
        }
        return result;
    }

    @Override
    public Integer updateBook(Book book) {
        if (book == null){
            return 0;
        }
        Integer result = bookMapper.updateBook(book);
        return result;
    }

    @Override
    public PageResult searchBorrowed(Book book, @NotNull User user, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        book.setBookBrrower(user.getUserName());
        if ("admin".equals(user.getUserRole())){
            //执行管理员查询
            Page<Book> borrowedBook = bookMapper.getBorrowedBook(book);
            return pageToPageResult(borrowedBook.getTotal(), borrowedBook.getResult(), new PageInfo<>(borrowedBook.getResult()).getPageNum(), pageSize);
        }else {
            Page<Book> myBorrowed = bookMapper.getMyBorrowed(book);
            return pageToPageResult(myBorrowed.getTotal(), myBorrowed.getResult(), new PageInfo<>(myBorrowed.getResult()).getPageNum(), pageSize);
        }
    }

    /**
     * 用户归还图书
     * @param book
     * @return
     */
    @Override
    public Integer returnBook(@NotNull Book book) {
        book.setBookStatus("2");
        return bookMapper.updateBook(book);
    }

    @Override
    public Integer confirmReturn(@NotNull Book book) {
        try {
            //添加借阅记录
            Record record = new Record();
            record.setRecordBookname(book.getBookName());
            record.setRecordBookisbn(book.getBookIsbn());
            record.setRecordBorrower(book.getBookBrrower());
            record.setRecordBorrowtime(book.getBookBorrowtime());
            String nowDate = DateConvertUtils.getStringDate(new Date());
            record.setRecordRemandtime(nowDate);
            if (recordService.addRecord(record) == 1){
                //状态为0，清空借阅人信息
                book.setBookStatus("0");
                book.setBookBrrower("");
                book.setBookBorrowtime("");
                book.setBookReturntime("");
                return bookMapper.updateBook(book);
            }
        }catch (Exception e){
            LOGGER.error(e.getMessage());
        }
        return 0;
    }

    private PageResult pageToPageResult(long total , List<Book> data , Integer pageNum, Integer pageSize){
        PageResult pageResult = new PageResult();
        pageResult.setTotal(total);
        pageResult.setRows(data);
        pageResult.setPageNum(pageNum);
        if (total%pageSize != 0){
            pageResult.setPageTotalNum(total/pageSize + 1);
        }else {
            pageResult.setPageTotalNum(total/pageSize);
        }
        return pageResult;
    }
}
