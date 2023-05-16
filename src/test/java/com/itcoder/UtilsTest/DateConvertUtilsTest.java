package com.itcoder.UtilsTest;

import com.itcoder.utils.DateConvertUtils;
import org.junit.Test;

import java.util.Date;

public class DateConvertUtilsTest {
    @Test
    public void getDateString(){
        System.out.println(DateConvertUtils.getStringDate(new Date()));
    }
}
