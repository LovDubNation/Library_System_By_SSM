<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 黄元章
  Date: 2023-04-17
  Time: 21:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图书借阅</title>
    <%@include file="head-common.jsp"%>
    <style>
        #borrowTable td{
            width: 50px;
            height: 20px;
            font-size: small;
        }
    </style>
    <script src="${pageContext.request.contextPath}/js/my-js.js"></script>
    <script>

        function checkForm(object){
            let isbn_regex = new RegExp("^[0-9]{13}$");
            $(object).blur(function () {
                if ($(object).val() == ''){
                    $("#add").attr("disabled",true);
                    $(object).attr("style","color:red").val("不能为空!");
                }else if($(object).attr('name') == 'bookIsbn'){
                    if (!isbn_regex.test($(object).val())){
                        console.log(isbn_regex.test($(object).val()))
                        console.log($(object).val())
                        console.log("isbn错误")
                        $("#add").attr("disabled",true);
                        alert('必须为标准的ISBN，请重新输入')
                        $(object).val("");
                    }
                }else {
                    checkval();
                }
            }).focus(function () {
                if ($(object).val() == '不能为空!'){
                    $(object).attr('style','').val('');
                }else{
                    $(object).attr('style','')
                }
            });
        }

        $(function() {
            let $add_input = $("#add_form input");
            $add_input.each(function () {
                checkForm(this);
            });
        });

        $(function () {
            let edit_input = $("#edit_form input");
            edit_input.each(function () {
                checkForm(this);
            })
        })

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
    <header id="header">
        <div id="title">
            图书借阅
        </div>
    </header>
    <main id="main" class="mt-3">
        <div class="container m-0">
            <div class="row p-0">
                <c:if test="${sessionScope.currentUser.userRole == 'admin'}">
                    <div class="col-md-2">
                        <button class="btn btn-info" data-target="#addModal" data-toggle="modal">
                            新增
                        </button>
                    </div>
                </c:if>
                <div class="col-md-10">
                    <form class="form-inline" action="${pageContext.request.contextPath}/Book/searchBooks" method="post">
                        <div class="form-group">
                            <label for="bookName" class="col-md-4">图书名称</label>
                            <input id="bookName"  type="text" name="bookName" value="${book.bookName}" />
                        </div>
                        <div class="form-group">
                            <label for="bookPress" class="col-md-3">出版商</label>
                            <input id="bookPress"  type="text" name="bookPress" value="${book.bookPress}" />
                        </div>
                        <div class="form-group">
                            <label for="bookAuthor" class="col-md-4">图书作者</label>
                            <input id="bookAuthor"  type="text" name="bookAuthor" value="${book.bookAuthor}"/>
                        </div>
                        <input type="submit" class="btn btn-info ml-2" value="提交">
                    </form>
                </div>
            </div>
            <div class="row mt-5">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <td>图书名称</td>
                            <td>图书作者</td>
                            <td>图书ISBN</td>
                            <td>图书页数</td>
                            <td>借阅状态</td>
                            <td>出版商</td>
                            <td></td>
                            <td></td>
                        </tr>
                        <c:forEach items="${data}" var="book">
                        <tr>
                            <td>${book.bookName}</td>
                            <td>${book.bookAuthor}</td>
                            <td>${book.bookIsbn}</td>
                            <td>${book.bookPagination}</td>
                            <td>
                                <c:if test="${book.bookStatus == 0}">可借阅</c:if>
                                <c:if test="${book.bookStatus == 1}">借阅中</c:if>
                                <c:if test="${book.bookStatus == 2}">归还中</c:if>
                            </td>
                            <td>${book.bookPress}</td>
                            <c:if test="${book.bookStatus ==0}">
                                <td>
                                    <button class="btn btn-info" data-toggle="modal" onclick="findBookById(${book.bookId},'borrow')" data-target="#exampleModal">借阅</button>
                                </td>
                                <td>
                                    <c:if test="${sessionScope.currentUser.userRole == 'admin'}">
                                        <button class="btn btn-info" data-toggle="modal" onclick="findBookById(${book.bookId},'edit')" data-target="#addModal" >编辑</button>
                                    </c:if>
                                    <c:if test="${sessionScope.currentUser.userRole != 'admin'}">
                                        <button type="button" disabled class="btn btn-info" data-toggle="popover" onclick="findBookById(${book.bookId},'edit')" title="您不是管理员">编辑</button>
                                    </c:if>
                                </td>
                            </c:if>
                            <c:if test="${book.bookStatus ==1 || book.bookStatus  ==2}">
                                <td>
                                    <button class="btn btn-info" disabled="disabled" data-toggle="modal" data-target="#exampleModal">借阅</button>
                                </td>
                                <td>
                                    <button type="button" disabled class="btn btn-info" data-toggle="popover" data-target = "#editModal" onclick="findBookById(${book.bookId},'edit')" title="图书借阅中，不可更改" >编辑</button>
                                </td>
                            </c:if>
                        </tr>
                        </c:forEach>
                    </thead>
                </table>
                <nav aria-label="Page navigation example" style="margin: auto">
                    <ul class="pagination">
                        <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/Book/searchBooks?bookName=${book.bookName}&bookPress=${book.bookPress}&bookAuthor=${book.bookAuthor}&pageNum=1">首页</a></li>
                        <c:forEach begin="1" end="${total_pageNum}" step="1" var="i">
                            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/Book/searchBooks?bookName=${book.bookName}&bookPress=${book.bookPress}&bookAuthor=${book.bookAuthor}&pageNum=${i}">${i}</a></li>
                        </c:forEach>
                        <c:if test="${cur_pageNum != total_pageNum}">
                            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/Book/searchBooks?bookName=${book.bookName}&bookPress=${book.bookPress}&bookAuthor=${book.bookAuthor}&pageNum=${cur_pageNum+1}">Next</a></li>
                        </c:if>
                        <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/Book/searchBooks?bookName=${book.bookName}&bookPress=${book.bookPress}&bookAuthor=${book.bookAuthor}&pageNum=${total_pageNum}">最后一页</a></li>
                    </ul>
                </nav>
            </div>
        </div>
    </main>

    <!-- 新增Modal -->
    <div class="modal fade" id="addModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">添加书籍</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form action="" id="add_form">
                        <div class="form-row">
                            <div class="col-6">
                                <div class="input-group">
                                    <input type="text" name="bookId" id="eid" hidden>
                                    <div class="input-group-prepend">
                                        <div class="input-group-text">图书名称</div>
                                    </div>
                                    <input type="text" name="bookName" placeholder="图书名称"  id="add_bookName">
                                    <span id="BookName_meg" style="color: red;font-size: 10px"></span>
                                </div>
                            </div>
                            <div class="col-6">
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <div class="input-group-text">图书ISBN</div>
                                    </div>
                                    <input type="text" placeholder="图书isbn" name="bookIsbn" id="add_bookIsbn">
                                    <span id="BookIsbn_meg" style="color: red;font-size: 10px"></span>
                                </div>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="col-6">
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <div class="input-group-text">图书作者</div>
                                    </div>
                                    <input type="text" name="bookAuthor"  placeholder="图书作者" id="add_bookAuthor">
                                    <span id="BookAuthor_meg" style="color: red;font-size: 10px"></span>
                                </div>
                            </div>
                            <div class="col-6">
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <div class="input-group-text">出版社&nbsp;</div>
                                    </div>
                                    <input type="text" placeholder="出版社"  name="bookPress" id="add_bookPress">
                                    <span id="BookPress_meg" style="color: red;font-size: 10px"></span>
                                </div>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="col-6">
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <div class="input-group-text">图书页数</div>
                                    </div>
                                    <input type="text" name="bookPagination" placeholder="图书页数" onblur="" id="add_bookPagination">
                                    <span id="BookPagination_meg" style="color: red;font-size: 10px"></span>
                                </div>
                            </div>
                            <div class="col-6">
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <div class="input-group-text">图书价格</div>
                                    </div>
                                    <input type="text" placeholder="图书价格"  name="bookPrice" id="add_bookPrice">
                                    <span id="BookPrice_meg" style="color: red;font-size: 10px"></span>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" id="add" onclick="addOrEditBook()">保存</button>
                </div>
            </div>
        </div>
    </div>

<%@include file="model.jsp"%>
</body>
</html>
