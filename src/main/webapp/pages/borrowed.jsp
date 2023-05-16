<%--
  Created by IntelliJ IDEA.
  User: 黄元章
  Date: 2023-04-21
  Time: 15:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@include file="head-common.jsp"%>
    <title>当前借阅</title>
    <script src="${pageContext.request.contextPath}/js/my-js.js" charset="UTF-8"></script>
    <style>
        *{
            margin: 0;
            padding: 0;
        }
    </style>
    <script>
        function returnBook(bid) {
            let conf = confirm("您确定您已经归还了该图书吗？");
            let is_success;
            if (conf == true){
                //发送请求
                let url = getProjectUrl()+"/Book/returnBook?bookId="+bid;
                $.get(url,function (response) {
                    alert(response.message);
                    window.location.reload();
                })
            }
        }
        function confirmReturn(bid) {
            let conf = confirm("您确定该书已经被归还了吗？")
            if (conf == true){
                let url = getProjectUrl()+"/Book/confirmReturn?bookId="+bid;
                $.get(url,function (response) {
                    alert(response.message);
                    window.location.reload();
                })
            }
        }
    </script>
</head>
<body>
    <header id="header">
        <div id="title">
            图书借阅
        </div>
    </header>
    <main>
        <div class="container">
            <form class="form-inline form-row" action="${pageContext.request.contextPath}/Book/searchBorrowed" method="post">
                <div class="form-group">
                    <label for="bookName" class="col-md-3">图书名称</label>
                    <input id="bookName"  type="text" name="bookName" value="${book.bookName}" />
                </div>
                <div class="form-group">
                    <label for="bookPress" class="col-md-3">出版商</label>
                    <input id="bookPress"  type="text" name="bookPress" value="${book.bookPress}" />
                </div>
                <div class="form-group">
                    <label for="bookAuthor" class="col-md-3">图书作者</label>
                    <input id="bookAuthor"  type="text" name="bookAuthor" value="${book.bookAuthor}"/>
                </div>
                <input type="submit" class="btn btn-info ml-2" value="提交">
            </form>
        </div>
        <div class="container">
            <table class="table table-striped ">
                <thead>
                    <tr>
                        <td>图书名称</td>
                        <td>图书作者</td>
                        <td>图书ISBN</td>
                        <td>借阅状态</td>
                        <td>出版商</td>
                        <td>借阅人</td>
                        <td>借阅时间</td>
                        <td>应归还时间</td>
                        <td>操作</td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${data}" var="book">
                        <tr>
                            <td>${book.bookName}</td>
                            <td>${book.bookAuthor}</td>
                            <td>${book.bookIsbn}</td>
                            <td>
                                <c:if test="${book.bookStatus == 0}">可借阅</c:if>
                                <c:if test="${book.bookStatus == 1}">借阅中</c:if>
                                <c:if test="${book.bookStatus == 2}">归还中</c:if>
                            </td>
                            <td>${book.bookPress}</td>
                            <td>
                                ${book.bookBrrower}
                            </td>
                            <td>${book.bookBorrowtime}</td>
                            <td>${book.bookReturntime}</td>
                            <td>
                                <c:if test="${sessionScope.currentUser.userRole == 'admin'}">
                                    <c:if test="${book.bookStatus == 2}">
                                        <button class="btn btn-info" disabled>归还中...</button>
                                        <button class="btn btn-info" id="conRe" onclick="confirmReturn(${book.bookId})">确认归还</button>
                                    </c:if>
                                    <c:if test="${book.bookStatus == 1}">
                                        <button class="btn btn-info" id="returnBk" onclick="returnBook(${book.bookId})">归还</button>
                                    </c:if>
                                </c:if>
                                <c:if test="${sessionScope.currentUser.userRole == 'user'}">
                                    <c:if test="${book.bookStatus == 2}">
                                        <button class="btn btn-info" disabled>归还中...</button>
                                    </c:if>
                                    <c:if test="${book.bookStatus == 1}">
                                        <button class="btn btn-info" id="returnBookStatus" onclick="returnBook(${book.bookId})">归还</button>
                                    </c:if>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div>
                <nav aria-label="Page navigation example" style="margin: auto">
                    <ul class="pagination">
                        <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/Book/searchBorrowed?bookName=${book.bookName}&bookPress=${book.bookPress}&bookAuthor=${book.bookAuthor}&pageNum=1">首页</a></li>
                        <c:forEach begin="1" end="${total_pageNum}" step="1" var="i">
                            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/Book/searchBorrowed?bookName=${book.bookName}&bookPress=${book.bookPress}&bookAuthor=${book.bookAuthor}&pageNum=${i}">${i}</a></li>
                        </c:forEach>
                        <c:if test="${cur_pageNum != total_pageNum}">
                            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/Book/searchBorrowed?bookName=${book.bookName}&bookPress=${book.bookPress}&bookAuthor=${book.bookAuthor}&pageNum=${cur_pageNum+1}">Next</a></li>
                        </c:if>
                        <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/Book/searchBorrowed?bookName=${book.bookName}&bookPress=${book.bookPress}&bookAuthor=${book.bookAuthor}&pageNum=${total_pageNum}">最后一页</a></li>
                    </ul>
                </nav>
            </div>
        </div>
    </main>
</body>
</html>
