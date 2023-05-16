<%--
  Created by IntelliJ IDEA.
  User: 黄元章
  Date: 2023-04-14
  Time: 16:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>云借阅-图书管理系统</title>
  <script src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
  <script src="${pageContext.request.contextPath}/js/bootstrap.bundle.js"></script>
  <style>
    #box-center-father{
      display: flex;
      justify-content: center;
      align-items: center;
      width: 100%;
      height: 100%;
      background-image: url("${pageContext.request.contextPath}/img/pexels-pixabay-159711.jpg");
      background-size: cover;
      background-position: center;
      overflow: hidden;
    }
    .box-center-son{
      width: 430px;
      height: 400px;
    }
    .meg{
      color: red;
    }
  </style>
</head>
<body>
  <div id="box-center-father">
    <div class="box-center-son container">
      <div class="container">
        <div class="row text-left">
          <div class="col">
            <h2 style="color: white;">云借阅-图书管理系统</h2>
          </div>
        </div>
        <div class="row text-center mt-5" style="background: white">
          <div class="col">
            <h3>用户登录</h3>
          </div>
        </div>
        <%--     登录表单       --%>
        <div class="row " style="background: white">
          <form action="${pageContext.request.contextPath}/User/login">
            <div class="form-group ml-5 mt-4">
              <div class="input-group">
                <div class="input-group-prepend">
                  <div class="input-group-text">用户名</div>
                </div>
                <input type="text" class="form-control" placeholder="请输入邮箱" name="userEmail">
              </div>
              <div class="input-group mt-4">
                <div class="input-group-prepend">
                  <div class="input-group-text">密码</div>
                </div>
                <input type="password" class="form-control" placeholder="请输入密码" name="password">
              </div>
              <div class="meg mt-2">${requestScope.meg}</div>
              <div class="col mt-5 align-items-center">
                <input type="submit" class="btn btn-primary" value="提交" style="width: 150px;margin-left: 50px">
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</body>
</html>
