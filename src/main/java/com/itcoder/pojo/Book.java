package com.itcoder.pojo;


public class Book {

  private long bookId;
  private String bookName;
  private String bookIsbn;
  private String bookPress;
  private String bookAuthor;
  private long bookPagination;
  private double bookPrice;
  private String bookUploadtime;
  private String bookStatus;
  private String bookBrrower;
  private String bookBorrowtime;
  private String bookReturntime;


  public long getBookId() {
    return bookId;
  }

  public void setBookId(long bookId) {
    this.bookId = bookId;
  }


  public String getBookName() {
    return bookName;
  }

  public void setBookName(String bookName) {
    this.bookName = bookName;
  }


  public String getBookIsbn() {
    return bookIsbn;
  }

  public void setBookIsbn(String bookIsbn) {
    this.bookIsbn = bookIsbn;
  }


  public String getBookPress() {
    return bookPress;
  }

  public void setBookPress(String bookPress) {
    this.bookPress = bookPress;
  }


  public String getBookAuthor() {
    return bookAuthor;
  }

  public void setBookAuthor(String bookAuthor) {
    this.bookAuthor = bookAuthor;
  }


  public long getBookPagination() {
    return bookPagination;
  }

  public void setBookPagination(long bookPagination) {
    this.bookPagination = bookPagination;
  }


  public double getBookPrice() {
    return bookPrice;
  }

  public void setBookPrice(double bookPrice) {
    this.bookPrice = bookPrice;
  }


  public String getBookUploadtime() {
    return bookUploadtime;
  }

  public void setBookUploadtime(String bookUploadtime) {
    this.bookUploadtime = bookUploadtime;
  }


  public String getBookStatus() {
    return bookStatus;
  }

  public void setBookStatus(String bookStatus) {
    this.bookStatus = bookStatus;
  }


  public String getBookBrrower() {
    return bookBrrower;
  }

  public void setBookBrrower(String bookBrrower) {
    this.bookBrrower = bookBrrower;
  }


  public String getBookBorrowtime() {
    return bookBorrowtime;
  }

  public void setBookBorrowtime(String bookBorrowtime) {
    this.bookBorrowtime = bookBorrowtime;
  }


  public String getBookReturntime() {
    return bookReturntime;
  }

  public void setBookReturntime(String bookReturntime) {
    this.bookReturntime = bookReturntime;
  }

  @Override
  public String toString() {
    return "Book{" +
            "bookId=" + bookId +
            ", bookName='" + bookName + '\'' +
            ", bookIsbn='" + bookIsbn + '\'' +
            ", bookPress='" + bookPress + '\'' +
            ", bookAuthor='" + bookAuthor + '\'' +
            ", bookPagination=" + bookPagination +
            ", bookPrice=" + bookPrice +
            ", bookUploadtime='" + bookUploadtime + '\'' +
            ", bookStatus='" + bookStatus + '\'' +
            ", bookBrrower='" + bookBrrower + '\'' +
            ", bookBorrowtime='" + bookBorrowtime + '\'' +
            ", bookReturntime='" + bookReturntime + '\'' +
            '}';
  }
}
