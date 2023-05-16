package com.itcoder.pojo;


import javax.servlet.ServletRequest;
import java.io.Serializable;
import java.util.List;

public class Record implements Serializable {

  private long recordId;
  private String recordBookname;
  private String recordBookisbn;
  private String recordBorrower;
  private String recordBorrowtime;
  private String recordRemandtime;

  @Override
  public String toString() {
    return "Record{" +
            "recordId=" + recordId +
            ", recordBookname='" + recordBookname + '\'' +
            ", recordBookisbn='" + recordBookisbn + '\'' +
            ", recordBorrower='" + recordBorrower + '\'' +
            ", recordBorrowtime='" + recordBorrowtime + '\'' +
            ", recordRemandtime='" + recordRemandtime + '\'' +
            '}';
  }

  public long getRecordId() {
    return recordId;
  }

  public void setRecordId(long recordId) {
    this.recordId = recordId;
  }


  public String getRecordBookname() {
    return recordBookname;
  }

  public void setRecordBookname(String recordBookname) {
    this.recordBookname = recordBookname;
  }


  public String getRecordBookisbn() {
    return recordBookisbn;
  }

  public void setRecordBookisbn(String recordBookisbn) {
    this.recordBookisbn = recordBookisbn;
  }


  public String getRecordBorrower() {
    return recordBorrower;
  }

  public void setRecordBorrower(String recordBorrower) {
    this.recordBorrower = recordBorrower;
  }


  public String getRecordBorrowtime() {
    return recordBorrowtime;
  }

  public void setRecordBorrowtime(String recordBorrowtime) {
    this.recordBorrowtime = recordBorrowtime;
  }


  public String getRecordRemandtime() {
    return recordRemandtime;
  }

  public void setRecordRemandtime(String recordRemandtime) {
    this.recordRemandtime = recordRemandtime;
  }

}
