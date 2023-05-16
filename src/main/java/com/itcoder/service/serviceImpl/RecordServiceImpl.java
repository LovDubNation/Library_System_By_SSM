package com.itcoder.service.serviceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itcoder.mapper.RecordMapper;
import com.itcoder.pojo.PageResult;
import com.itcoder.pojo.Record;
import com.itcoder.pojo.User;
import com.itcoder.service.RecordService;
import com.sun.istack.internal.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RecordServiceImpl implements RecordService {
    private final static Logger LOGGER = LoggerFactory.getLogger(RecordServiceImpl.class);
    @Autowired
    private RecordMapper recordMapper;

    @Override
    public Integer addRecord(@NotNull Record record) {
        return  recordMapper.addRecord(record);
    }

    @Override
    public PageResult searchRecords(Record record , @NotNull User user,@NotNull Integer pageNum,@NotNull Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        try {
            Page<Record> page;
            PageResult pageResult = new PageResult();
            if ("admin".equalsIgnoreCase(user.getUserRole())){
                page = recordMapper.getRecords(record);
            }else {
                record.setRecordBorrower(user.getUserName());
                page = recordMapper.getMyRecords(record);
            }
            pageResult.setTotal(page.getTotal());
            pageResult.setRows(page.getResult());
            PageInfo pageInfo = new PageInfo(page.getResult());
            pageResult.setPageNum(pageInfo.getPageNum());
            pageResult.setPageTotalNum(page.getTotal()%pageSize != 0 ?page.getTotal()/pageSize + 1:page.getTotal()/pageSize);
            return pageResult;
        }catch (Exception e){
            LOGGER.error(e.getMessage());
        }
        return null;
    }
}
