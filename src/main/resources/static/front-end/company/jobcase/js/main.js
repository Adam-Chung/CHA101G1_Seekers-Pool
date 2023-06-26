var express = require("express");
var mysql = require('mysql');

var app = express();
var con = mysql.createConnection({
    host: "localhost",
    user: "root",
    password: "2322302519",
    database: "db01" // 替換為您的資料庫名稱
});

con.connect(function (err) {
    if (err) {
        console.log("連線失敗: " + err.message);
    } else {
        console.log("已成功連線到資料庫");
        // 在這裡可以執行其他相關操作
    }
});

con.on('error', function (err) {
    console.error("資料庫錯誤: " + err.message);
    // 在這裡可以處理資料庫錯誤事件
});


// 其他應用程式邏輯

app.listen(3000, function () {
    console.log("應用程式正在監聽 3306 連接埠");
});
