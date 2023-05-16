package com.itcoder.service;

import com.github.pagehelper.Page;
import com.itcoder.pojo.PageResult;
import com.itcoder.pojo.Record;
import com.itcoder.pojo.User;
import com.sun.istack.internal.NotNull;

public interface RecordService {
    /**
     * 新增借阅记录
     * @return
     */
    Integer addRecord(@NotNull Record record);

    /**
     * 查询借阅记录
     * @return
     */
    PageResult searchRecords(Record record, User user, Integer pageNum, Integer pageSize);
}
