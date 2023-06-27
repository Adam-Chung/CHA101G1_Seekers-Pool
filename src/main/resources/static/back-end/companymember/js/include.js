// 引入頁首頁尾方法https://blog.csdn.net/weixin_47883684/article/details/114372920

$(function () {
    // ========方法一===========
    $.get("buildPage/header.html", function (data) {
        $("#header").html(data);  //將header.html 頁面加到 id 是 header 的標籤體中
    });
    //======== 方法二===========
    // $("#header").load('header.html');

    $.get("buildPage/list.html", function (data) {
        $(".list").html(data);
    })
    $.get("buildPage/footer.html", function (data) {
        $("#footer").html(data);
    });

    $.get("buildPage/QandTop.html", function (data) {
        $("#QandTop").html(data);
    });
});