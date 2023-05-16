package com.itcoder.pojo;

import java.io.Serializable;
import java.util.List;

public class  PageResult<T> implements Serializable {
    public static final Integer PAGE_SIZE = 5;
    private long total;
    private List<T> rows;
    private Integer pageNum;
    private Long PageTotalNum;

    public Long getPageTotalNum() {
        return PageTotalNum;
    }

    public void setPageTotalNum(Long pageTotalNum) {
        PageTotalNum = pageTotalNum;
    }


    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
