package com.itcoder.controller;

import com.github.pagehelper.PageHelper;
import com.itcoder.pojo.Book;
import com.itcoder.pojo.PageResult;
import com.itcoder.pojo.Result;
import com.itcoder.pojo.User;
import com.itcoder.service.BookService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/Book")
public class BookController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);
    @Autowired
    private BookService bookService;

    @RequestMapping("/getNewBooks")
    public ModelAndView getNewBooks(Integer pageNum){
        PageResult newBooks = bookService.getNewBooks(pageNum, PageResult.PAGE_SIZE);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("books_new");
        modelAndView.addObject("newBooks",newBooks);
        return modelAndView;
    }

    @RequestMapping("/findBookById")
    @ResponseBody
    public Result<Book> findBookById(Integer id){
        try {
            Book book = bookService.findBookById(id);
            if (book == null){
                return new Result(false,"查询失败");
            }
            return new Result<>(true,"查询成功",book);
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            return new Result(false,"查询失败");
        }
    }

    @RequestMapping("/borrowBook")
    @ResponseBody
    public Result borrowBook(@RequestParam(value = "bId") Integer bId, @RequestParam(value = "bookReturntime") String bookReturnTime, HttpServletRequest request){
        Book book = bookService.findBookById(bId);
        //设置预计归还时间
        book.setBookReturntime(bookReturnTime);
        User user = (User)request.getSession().getAttribute("currentUser");
        Integer result = bookService.borrowBook(book, user);
        if (result.intValue() !=  0){
            LOGGER.info("用户借阅成功");
            return new Result(true,"借阅成功，请前往大厅取书");
        }
        return new Result(false,"借阅失败");
    }

    @RequestMapping("/listBooks")
    public ModelAndView listBooks(Integer pageNum,HttpServletRequest request){
        try {
            PageResult result = bookService.listBooks(pageNum,  PageResult.PAGE_SIZE);
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("book_borrow");
            modelAndView.addObject("result",result);
            return modelAndView;
        }catch (Exception e){
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @RequestMapping("/searchBooks")
    public ModelAndView searchBooks(Book book,Integer pageNum){
        if (pageNum == null){
            pageNum =1;
        }
        PageResult result = bookService.searchBook(book, pageNum,  PageResult.PAGE_SIZE);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("book_borrow");
        addPageInfoToModal(modelAndView,result.getRows(),result.getTotal(),book,result.getPageNum(),result.getPageTotalNum());
        return modelAndView;
    }

    @RequestMapping("/addBook")
    @ResponseBody
    public Result<Book> addBook(Book book){
        Integer integer = bookService.insertBook(book);
        if (integer == 0){
            LOGGER.info("增加书籍失败");
            return new Result<Book>(false,"增加书籍失败,是否已有该书在库中!ISBN表示唯一的图书");
        }
        LOGGER.info("增加书籍成功");
        return new Result<Book>(true,"增加图书成功!");
    }

    @RequestMapping("/editBook")
    @ResponseBody
    public Result<Book> editBook(Book book){
        Integer result = bookService.updateBook(book);
        if (result >0){
            LOGGER.info("更新成功");
            return new Result<>(true,"更新图书成功！");
        }
        return new Result<>(false,"更新图书失败！");
    }

    @RequestMapping("/searchBorrowed")
    public ModelAndView searchBorrowed(Book book,Integer pageNum,HttpServletRequest request){
        if (pageNum == null){
            pageNum =1;
        }
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        PageResult result = bookService.searchBorrowed(book, currentUser, pageNum,  PageResult.PAGE_SIZE);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("borrowed");
        addPageInfoToModal(modelAndView,result.getRows(),result.getTotal(),book,result.getPageNum(),result.getPageTotalNum());
        return modelAndView;
    }

    public  static <T> void  addPageInfoToModal(@NonNull ModelAndView modelAndView, List<T> data, long totalRow, T book, Integer cur_pageNum, long total_pageNum){
        modelAndView.addObject(modelAndView.addObject("data",data));
        modelAndView.addObject("totalRow",totalRow);
        modelAndView.addObject("book",book);
        modelAndView.addObject("page_size", PageResult.PAGE_SIZE);
        modelAndView.addObject("cur_pageNum",cur_pageNum);
        modelAndView.addObject("total_pageNum",total_pageNum);
    }

    @RequestMapping("/returnBook")
    @ResponseBody
    public Result returnBook(Book book){
        try {
            Book the_book = bookService.findBookById(Integer.valueOf((int) book.getBookId()));
            Integer integer = bookService.returnBook(the_book);
            if (integer == 1){
                LOGGER.info("用户归图书还成功");
                return new Result(true,"归还成功");
            }
        }catch (Exception e){
            LOGGER.error(e.getMessage());
        }
        return new Result(false,"归还失败");
    }

    @RequestMapping("/confirmReturn")
    @ResponseBody
    public Result confirmReturn(Book book){
        Book theBook = bookService.findBookById(Integer.valueOf((int) book.getBookId()));
        try {
            Integer integer = bookService.confirmReturn(theBook);
            if (integer == 1){
                LOGGER.info("管理员已经确认归还图书+",book.getBookId());
                return new Result(true,"归还成功!");
            }
        }catch (Exception e){
            LOGGER.error(e.getMessage());
        }
        return new Result(false,"归还失败!");
    }
}
