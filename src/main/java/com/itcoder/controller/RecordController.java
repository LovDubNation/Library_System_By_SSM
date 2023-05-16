package com.itcoder.controller;

import com.itcoder.pojo.PageResult;
import com.itcoder.pojo.Record;
import com.itcoder.pojo.User;
import com.itcoder.service.RecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/Record")
public class RecordController {

    private final static Logger LOGGER = LoggerFactory.getLogger(RecordController.class);
    @Autowired
    private RecordService recordService;

    @RequestMapping ("/searchRecords")
    public ModelAndView searchRecords(Integer pageNum, Record record, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        if (pageNum == null){
            pageNum = 1;
        }
        try {
            User currentUser = (User) request.getSession().getAttribute("currentUser");
            PageResult result = recordService.searchRecords(record, currentUser, pageNum, PageResult.PAGE_SIZE);
            modelAndView.setViewName("record");
            BookController.addPageInfoToModal(modelAndView,result.getRows(),result.getTotal(),record,result.getPageNum(), result.getPageTotalNum());
            return modelAndView;
        }catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return modelAndView;
    }
}
