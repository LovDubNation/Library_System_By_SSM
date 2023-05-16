<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 黄元章
  Date: 2023-04-16
  Time: 17:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
  <%@include file="head-common.jsp"%>
  <meta http-equiv='Content-Type' content='text/html; charset=utf-8' charset="UTF-8">
  <script src="${pageContext.request.contextPath}/js/my-js.js" type="text/javascript" charset="UTF-8"></script>
  <style>
    #borrowTable td{
      width: 50px;
      height: 20px;
      font-size: small;
    }
  </style>
  <script>
    //当时间改变时
    function return_time_correct(){
      let time = $("#b_return_time").val().split("-");
      let saveMeg = $("#saveMeg");
      let meg = "归还日期不能早于今天";
      $("#errorMeg").text("");
      //按钮可用
      saveMeg.attr("disabled",false);
      let nDate = new Date();
      let ny = nDate.getFullYear();
      let nm = nDate.getMonth()+1;
      let nd = nDate.getDate();
      let bet_days = getDaysBetweenDates(nDate, new Date($("#b_return_time").val()))
      if (time[0] < ny){
        saveMeg.attr("disabled",true);
        alert(meg);
      }else if(time[0] == ny){
        if (time[1] < nm){
          saveMeg.attr("disabled",true);
          alert(meg);
        }else if (time[1] == nm){
          if (time[2] < nd){
            saveMeg.attr("disabled",true);
            alert(meg);
          }
        }
      }
      if (bet_days > 180){
        saveMeg.attr("disabled",true);
        alert("您必须在180天内归还书籍");
      }
    }
  </script>
</head>
<body>
  <div class="container">
    <table class="table table-striped">
      <thead>
        <tr>
          <th>图书名称</th>
          <th>图书作者</th>
          <th>出版社</th>
          <th>时标ISBN</th>
          <th>书籍状态</th>
          <th>借阅人</th>
          <th>借阅时间</th>
          <th>预计归还时间</th>
          <th>操作</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${newBooks.rows}" var="book">
          <tr>
            <td>${book.bookName}</td>
            <td>${book.bookAuthor}</td>
            <td>${book.bookPress}</td>
            <td>${book.bookIsbn}</td>
            <td>
                <c:choose>
                  <c:when test="${book.bookStatus ==0}">
                    可借阅
                  </c:when>
                  <c:when test="${book.bookStatus ==1}">
                    借阅中
                  </c:when>
                  <c:otherwise>
                    归还中
                  </c:otherwise>
                </c:choose>
            </td>
            <td>${book.bookBrrower}</td>
            <td>${book.bookBorrowtime}</td>
            <td>${book.bookReturntime}</td>
            <td class="text-center">
              <c:if test="${book.bookStatus ==1 || book.bookStatus  ==2}">
                <button class="btn btn-info" disabled="disabled" data-toggle="modal" data-target="#exampleModal">借阅</button>
              </c:if>
              <c:if test="${book.bookStatus ==0}">
                <button class="btn btn-info" data-toggle="modal" onclick="findBookById(${book.bookId},'borrow')" data-target="#exampleModal">借阅</button>
              </c:if>
            </td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </div>
<%@include file="model.jsp"%>
</body>
</html>
