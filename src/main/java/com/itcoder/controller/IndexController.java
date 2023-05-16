package com.itcoder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class IndexController {
    @RequestMapping("/mainIndex")
    public String mainIndex(){
        return "forward:/pages/main.jsp";
    }

    @RequestMapping("/loginIndex")
    public String loginIndex(){
        return "forward:/pages/login.jsp";
    }

    @RequestMapping("/bookBorrowIndex")
    public String bookBorrowIndex(){
        return "book_borrow";
    }
}
