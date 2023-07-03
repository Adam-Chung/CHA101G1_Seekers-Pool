// 引入頁首頁尾方法https://blog.csdn.net/weixin_47883684/article/details/114372920

$(function () {
    // ========方法一===========
    $.get("header.html", function (data) {
        $("#header").html(data);  //將header.html 頁面加到 id 是 header 的標籤體中
    });
    //======== 方法二===========
    // $("#header").load('header.html');
    
    $.get("list.html", function (data){
        $(".list").html(data);
    })
    $.get("footer.html", function (data) {
        $("#footer").html(data);
    });
    
    $.get("QandTop.html", function (data) {
        $("#QandTop").html(data);
    });
});