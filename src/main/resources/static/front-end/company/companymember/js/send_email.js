$(function () {
    // 寄驗證信
    axios.post('/SeekerPool/RegisterVertification')
        .then(function (response) {
            console.log(response.data);  // 印出從後端回傳的數據("驗證碼已發送")
        })
        .catch(function (error) {
            console.log(error);
        });

    $("#confirmBtn").click(function (e) {
        e.preventDefault();
        let formData = $("#checkCodeForm").serialize();  // 收集表單數據
        console.log(formData);  // 印出表單數據
        axios.post('/SeekerPool/RegisterVertification', formData)
            .then(function (response) {
                // console.log(response.data);  // 印出從後端回傳的數據
                let data = response.data;
                if (data.flag) {
                    Swal.fire({
                        title: '恭喜註冊成功!!',
                        timer: 2000,
                        icon: 'success',
                        width: 400,
                        padding: '3em',
                        color: '#817be0',
                        background: '#fff',
                        backdrop: `
                                rgba(0,0,023,0.3)
                                url(https://sweetalert2.github.io/images/nyan-cat.gif)
                                right bottom
                                repeat
                              `
                    }).then(() => {
                        setTimeout(() => {
                            location.href = "companyIndex.html";
                        }, 2000)
                    })
                } else {
                    Swal.fire(
                        '驗證碼錯誤',
                        `${data.errorMsg}`,
                        'error'
                    )
                }
            })
            .catch(function (error) {
                console.log(error);
            });
    });
});

// 重新寄驗證碼設定
function startCountdown() {
    let timer = 30;
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