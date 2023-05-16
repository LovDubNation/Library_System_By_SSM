<%--
  Created by IntelliJ IDEA.
  User: 黄元章
  Date: 2023-04-15
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=utf-8" charset="UTF-8">
    <%@include file="head-common.jsp"%>
    <script src="${pageContext.request.contextPath}/js/my-js.js">

    </script>
    <style>
        *{
            margin: 0;
            padding: 0;
        }
        header{
            background: #b1dfbb;
        }
        .aside{
            width: 300px;
            height: 100%;
            background: #0f6674;
            display: inline-block;
        }
        .content{
            width: calc(100% - 300px);
            float: right;
        }
    </style>
    <script type="text/javascript">
        function SetIFrameHeight() {
            let iframeid = document.getElementById("iframe");
            if (document.getElementById) {
                /*设置 内容展示区的高度等于页面可视区的高度*/
                iframeid.height = document.documentElement.clientHeight;
            }
        }

        $(function () {
            $("li a").click(function () {
                let a = $(event.target);
                let parentElement = a.parent();
                $("ul li").attr("style",'')
                parentElement.attr("style",'background: #0dcaf0')
            });
        });
    </script>
</head>
<body>
    <%----%>
    <header class="m-0 p-0">
        <div class="container m-0" style="width: 100%">
            <div class="row justify-content-around m-0">
                <div class="col-md-10 m-0 mr-auto">
                    <strong style="font-size: xx-large">云借阅-图书管理系统</strong>
                </div>
                <div class="col-md-2 ">
                    <i class="bi bi-github" style="font-size: 2rem;"></i>
                    <span>${sessionScope.currentUser.userName}</span>
                    <a href="${pageContext.request.contextPath}/User/logout" class="ml-3">退出登录</a>
                </div>
            </div>
        </div>
    </header>
    <div class="aside" onload="SetIFrameHeight">
        <ul class="nav flex-column font-weight-bolder">
            <li class="nav-item" style="background: #0dcaf0">
                <a class="nav-link active" id="main" href="${pageContext.request.contextPath}/Book/getNewBooks?pageNum=1" target="iframe">首页</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" id="borrow-books" href="${pageContext.request.contextPath}/Book/searchBooks?pageNum=1" target="iframe">图书借阅</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" id="current-books" href="${pageContext.request.contextPath}/Book/searchBorrowed?pageNum=1" target="iframe">当前借阅</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" id="record" href="${pageContext.request.contextPath}/Record/searchRecords" target="iframe">借阅记录</a>
            </li>
        </ul>
    </div>
    <!--内容-->
    <div class="content">
        <iframe width="100%" id="iframe" name="iframe" style="height: 100%"
                src="${pageContext.request.contextPath}/Book/getNewBooks?pageNum=1"></iframe>
    </div>
</body>
</html>
