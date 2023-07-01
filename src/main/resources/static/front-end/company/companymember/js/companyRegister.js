// AJAX連接
$(function () {
    $("#registerForm").submit(function () {
        $(".error-message").text('')
        // 先進行表單驗證
        if (!(
            checkAccount() &&
            checkPassword() &&
            checkCompanyName() &&
            checkTaxNum() &&
            checkEmail() &&
            checkTelephoneNumber() &&
            checkAddress()
        )) {
            // 驗證失敗，阻止表單提交
            return false;
        }

        // 發送數據到伺服器
        // 校驗通過，發送ajax請求(異步提交)，提交表單的數據(不是提交表單)
        var formData = new FormData($("#registerForm")[0]);
        // FormData物件可以處理包含文件的上傳數據

        console.log(formData);

        $.ajax({
            url: "/SeekerPool/CompanyRegister",
            type: "POST",
            data: formData,
            async: false,
            cache: false,
            contentType: false,  // 當form以multipart/form-data方式上傳檔案時，需要設定為false
            processData: false,  // 如果要傳送Dom樹資訊或其他不需要轉換的資訊(例如文字)，請設定為false
            success: function (data) {
                var data = JSON.parse(data);
                if (data.flag) {
                    // 註冊成功，跳轉到驗證碼頁面
                    location.href = "company_send_email.html";
                } else {
                    // 註冊失敗，給error_msg添加錯誤訊息
                    // alert(data.errorMsg)
                    swal("註冊失敗", `${data.errorMsg}`, "error");
                }
            },
            error: function (data) {
                // alert(data.errorMsg)
                swal("註冊失敗", `${data.errorMsg}`, "error");
            }
        });

        return false; // 返回true才會送出表單
    });
});

/*
※註冊表單檢查
1. 帳號: 長度8-30 正則表達式
2. 密碼: 長度8-30 正則表達式
3. 信箱: 信箱格式
4. 公司相關資訊: 不得為空
 */

function checkAccount() {
    const account = $("#account").val();
    const reg_account = /^\w{8,30}$/;
    if (!reg_account.test(account)) {
        // 使用 .text() 或 .html() 更新錯誤訊息
        $("#account-error").text('帳號格式錯誤');
        return false;
    } else {
        // 如果驗證成功，清除錯誤訊息
        $("#account-error").text('');
        return true;
    }
}

function checkPassword() {
    const password = $("#password").val();
    if (password.length < 8 || password.length > 30) {
        $("#password-error").text('');
        $("#password-error").text('密碼長度錯誤');
        return false;
    } else {
        $("#password-error").text('');
        return true;
    }
}

function checkCompanyName() {
    const companyName = $("#companyName").val();
    if (companyName.trim() === "") {
        $("#companyName-error").text('');
        $("#companyName-error").text('公司名稱不得為空');
        return false;
    } else {
        $("#companyName-error").text('');
        return true;
    }
}

function checkTaxNum() {
    const taxNumber = $("#taxNumber").val();
    const reg_taxNumber = /^[0-9]{8}$/;
    if (!reg_taxNumber.test(taxNumber)) {
        $("#taxNumber-error").text('')
        $("#taxNumber-error").text('統編號碼格式不正確');
        return false;
    } else {
        $("#taxNumber-error").text('');
        return true;
    }
}

function checkEmail() {
    const email = $("#companyEmail").val();
    const reg_email = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
    if (!reg_email.test(email)) {
        $("#companyEmail-error").text('')
        $("#companyEmail-error").text('e-mail格式不正確');
        return false;
    } else {
        $("#companyEmail-error").text('');
        return true;
    }
}

function checkTelephoneNumber() {
    const telNum = $("#companyTel").val();
    const reg_telNum = /^(0[2-9]\d)\d{6,8}$/;
    if (!reg_telNum.test(telNum)) {
        $("#companyTel-error").text('')
        $("#companyTel-error").text('電話號碼格式不正確');
        return false;
    } else {
        $("#companyTel-error").text('');
        return true;
    }
}

function checkAddress() {
    const companyAddress = $("#companyAddress").val();
    if (companyAddress.trim() === "") {
        $("#companyAddress-error").text('')
        $("#companyAddress-error").text('公司地址不得為空');
        return false;
    } else {
        $("#companyAddress-error").text('');
        return true;
    }
}