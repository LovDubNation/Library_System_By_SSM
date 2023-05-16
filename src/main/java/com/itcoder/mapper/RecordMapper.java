package com.itcoder.mapper;

import com.github.pagehelper.Page;
import com.itcoder.pojo.Record;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordMapper {
    Page<Record> getRecords(Record record);

    Page<Record> getMyRecords(Record record);

    @Insert("insert into record(record_id,record_bookname,record_bookisbn,record_borrower,record_borrowtime,record_remandtime)" +
            " values(null,#{recordBookname},#{recordBookisbn},#{recordBorrower},#{recordBorrowtime},#{recordRemandtime})")
    Integer addRecord(Record record);


}
