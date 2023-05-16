<%--
  Created by IntelliJ IDEA.
  User: 黄元章
  Date: 2023-04-18
  Time: 15:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">图书信息</h5>
      </div>
      <div class="modal-body">
        <form action="" id="borrowBook">
          <table class="table table-striped" id="borrowTable">
            <%--     不可见       --%>
            <input type="text" hidden name="bId" id="b_id">
            <tr>
              <td>图书名称</td>
              <td>
                <input type="text" id="b_name" name="bookName">
              </td>
              <td>标准ISBN</td>
              <td>
                <input type="text" id="b_isbn" name="bookIsbn">
              </td>
            </tr>
            <tr>
              <td>出版社</td>
              <td>
                <input type="text" id="b_press" name="bookPress">
              </td>
              <td>作者</td>
              <td>
                <input type="text" id="b_author" name="bookAuthor">
              </td>
            </tr>
            <tr>
              <td>归还时间</td>
              <td>
                <input type="date" id="b_return_time" name="bookReturntime" onchange="return_time_correct()">
              </td>
            </tr>
            <tr>
              <span style="color: red" id="errorMeg"></span>
            </tr>
          </table>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary" onclick="Borrow()" id="saveMeg">借阅</button>
      </div>
    </div>
  </div>
</div>