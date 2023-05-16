package com.itcoder.controller;

import com.itcoder.Exception.UnLoginException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionAdvice {
    private final static Logger LOGGER = LoggerFactory.getLogger(ExceptionAdvice.class);
    @ExceptionHandler(UnLoginException.class)
    public ModelAndView doUnLoginException(UnLoginException exception){
        LOGGER.warn(exception.getMessage());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("meg",exception.getMessage());
        modelAndView.setViewName("login");
        return modelAndView;
    }
}
