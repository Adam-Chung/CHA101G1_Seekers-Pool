$(function () {
    // 寄驗證信
    $.ajax({
        type: 'POST',
        url: '/SeekerPool/RegisterVertification',
        success: function (data) {
            console.log(data);  // 印出從後端回傳的數據("驗證碼已發送")
        },
        dataType: 'json'
    });

    $("#confirmBtn").click(function (e) {
        e.preventDefault();
        console.log($("#checkCodeForm").serialize());  // 印出表單數據
        $.ajax({
            type: 'POST',
            url: '/SeekerPool/RegisterVertification',
            data: $("#checkCodeForm").serialize(),
            success: function (data) {
                console.log(data);  // 印出從後端回傳的數據
                if (data.flag) {
                    location.href = "info.html";
                } else {
                    alert(data.errorMsg);
                }
            },
            dataType: 'json'
        });
    });
});

// 重新寄驗證碼設定
function startCountdown() {
    let timer = 15;
    const resendBtn = document.getElementById('resendButton');
    const buttonText = resendBtn.innerText;

    const countdownTimer = setInterval(() => {
        if (timer >= 0) {
            resendBtn.innerText = `${buttonText}(${timer})`;
            // 禁用按鈕
            resendBtn.disabled = true;
            timer--;
        } else {
            // 停止倒數計時器
            clearInterval(countdownTimer);
            // 重設按鈕文字並重新啟用
            resendBtn.innerText = buttonText;
            resendBtn.disabled = false;
        }

    }, 1000);  // 每秒執行一次前面的函式
}

document.addEventListener('DOMContentLoaded', function () {
    const resendButton = document.getElementById('resendButton');
    resendButton.addEventListener('click', startCountdown);
    startCountdown();  // 在網頁載入後自動開始倒數計時
});