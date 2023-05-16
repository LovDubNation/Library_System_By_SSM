<%--
  Created by IntelliJ IDEA.
  User: 黄元章
  Date: 2023-04-22
  Time: 18:12
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>借阅记录</title>
    <%@include file="head-common.jsp"%>
    <script src="${pageContext.request.contextPath}/js/my-js.js"></script>
</head>
<body>
<header id="header">
    <div id="title">
        借阅记录
    </div>
</header>
<main>
    <div class="container">
        <form class="form-inline form-row" action="${pageContext.request.contextPath}/Record/searchRecords" method="post">
            <c:if test="${sessionScope.currentUser.userRole == 'admin'}">
                <div class="form-group">
                    <label for="recordBorrower" class="col-md-3">借阅人</label>
                    <input id="recordBorrower"  type="text" name="recordBorrower" value="${book.recordBorrower}" />
                </div>
            </c:if>
            <div class="form-group">
                <label for="recordBookname" class="col-md-3">图书名称</label>
                <input id="recordBookname"  type="text" name="recordBookname" value="${book.recordBookname}"/>
            </div>
            <input type="submit" class="btn btn-info ml-2" value="查询"/>
        </form>
    </div>
    <div class="container">
        <table class="table table-striped ">
            <thead>
            <tr>
                <td>借阅人</td>
                <td>图书名称</td>
                <td>标准ISBN</td>
                <td>借阅时间</td>
                <td>归还时间</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${data}" var="record">
                <tr>
                    <td>${record.recordBorrower}</td>
                    <td>${record.recordBookname}</td>
                    <td>${record.recordBookisbn}</td>
                    <td>${record.recordBorrowtime}</td>
                    <td>${record.recordRemandtime}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        
        <nav aria-label="Page navigation example" style="margin: auto">
            <ul class="pagination">
                <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/Record/searchRecords?recordBookname=${book.recordBookname}&recordBorrower=${book.recordBorrower}&pageNum=1">首页</a></li>
                <c:forEach begin="1" end="${total_pageNum}" step="1" var="i">
                    <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/Record/searchRecords?recordBookname=${book.recordBookname}&recordBorrower=${book.recordBorrower}&pageNum=${i}">${i}</a></li>
                </c:forEach>
                <c:if test="${cur_pageNum != total_pageNum}">
                    <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/Record/searchRecords?recordBookname=${book.recordBookname}&recordBorrower=${book.recordBorrower}&pageNum=${cur_pageNum+1}">Next</a></li>
                </c:if>
                <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/Record/searchRecords?recordBookname=${book.recordBookname}&recordBorrower=${book.recordBorrower}&pageNum=${total_pageNum}">最后一页</a></li>
            </ul>
        </nav>
    </div>
</main>
</body>
</html>
