//发送请求处理图书借阅
function Borrow(){
    let url = getProjectUrl() + "/Book/borrowBook";
    $.ajax({
        url:url,
        type:"post",
        data:$("#borrowBook").serialize(),
        success:function (response) {
            $("#exampleModal").modal('hide');
            alert(response.message);
            setTimeout(function () {
                if (response.success == true){
                    console.log("刷新页面");
                    window.location.reload();
                }
            },1000);
        }
    });
}

//根据图书id查找图书信息
function findBookById(id,do_name){
    resetStyle();
    let url = getProjectUrl()+"/Book/findBookById?id="+id;
    $.get(url,function (response) {
        if (do_name =='borrow'){
            //将数据填充到模态窗口
            $("#b_id").val(response.data.bookId)
            $("#b_name").val(response.data.bookName);
            $("#b_isbn").val(response.data.bookIsbn);
            $("#b_press").val(response.data.bookPress);
            $("#b_author").val(response.data.bookAuthor);
            $("#b_author").val(response.data.bookAuthor);
            $("#b_return_time").val("");
        }
        if (do_name=='edit'){
            //将数据填充到模态窗口
            $("#eid").val(response.data.bookId);
            $("#add_bookName").val(response.data.bookName);
            $("#add_bookIsbn").val(response.data.bookIsbn);
            $("#add_bookPress").val(response.data.bookPress);
            $("#add_bookAuthor").val(response.data.bookAuthor);
            $("#add_bookPrice").val(response.data.bookPrice);
            $("#add_bookPagination").val(response.data.bookPagination)
        }
    })
}

//重置表单中的内容和样式
function resetStyle(){
    $("#add").attr("disabled",false)
    var $vals=$("#add_form input");
    $vals.each(function(){
        $(this).attr("style","")
    });

}

//新增图书
function addOrEditBook(){
    let eid = $("#eid").val();
    if (eid >0){
        //编辑
        let url = getProjectUrl()+"/Book/editBook";
        $.ajax({
            url:url,
            type:'post',
            data:$("#add_form").serialize(),
            success:function (response) {
                if (response.success == true){
                    $("#addModal").modal('hide')
                    alert(response.message);
                    $("#iframe").contentWindow.location.reload();
                }
            }
        })
    }else{
        //添加
        let url = getProjectUrl()+"/Book/addBook";
        $.ajax({
            url:url,
            type: 'post',
            data: $("#add_form").serialize(),
            success:function (response){
                if (response.success == true){
                    alert(response.message)
                }else {
                    alert(response.message)
                    console.log("刷新页面")
                    $("#iframe").contentWindow.location.reload();
                }
            }
        });
    }

}

//检查图书信息的窗口中，图书信息填写是否完整
function checkval(){
    var $inputs=$("#add input")
    var count=0;
    $inputs.each(function () {
        if($(this).val()==''||$(this).val()=="不能为空！"){
            count+=1;
        }
    })
    //如果全部输入框都填写完整，解除确认按钮的禁用状态
    if(count==0){
        $("#add").attr("disabled",false)
    }
}


//获取根路径
function getProjectUrl(){
    //获取主机地址之后的目录，如： cloudlibrary/admin/books.jsp
    let pathName = window.document.location.pathname;
    //获取带"/"的项目名，如：/cloudlibrary
    let projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    return projectName;
}

//获取两个日期之间的天数
function getDaysBetweenDates(firstTime,lastTime) {
    let dateTime1 = firstTime.getTime();
    let dateTime2 = lastTime.getTime();
    let time_diff = Math.abs(dateTime2-dateTime1);
    let days = Math.ceil(time_diff/(24*60*60*1000));
    return days;
}