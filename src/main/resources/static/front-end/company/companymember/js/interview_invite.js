let projectName = window.location.pathname.split('/')[1]; // SeekerPool
// 讀取URL中的參數
let urlParams = new URLSearchParams(window.location.search);
let id = urlParams.get('memId');

// ========================== 初始化日期為當天 ==========================
$(function () {
    var date = new Date();
    var day = date.getDate();
    var month = date.getMonth() + 1;
    var year = date.getFullYear();
    if (month < 10) month = "0" + month;
    if (day < 10) day = "0" + day;
    var today = year + "-" + month + "-" + day;
    $("#datetime").attr("value", today);
});

// ========================== 取出localStorage的東東 ==========================
// let memName = localStorage.getItem('memName');
let jobTitle = localStorage.getItem('jobTitle');
console.log(jobTitle);

// ========================== 企業方找應徵者面試 ==========================
// 如果jobTitle存在，則顯示這些資料
if (jobTitle) {
    // 創建span元素，並設置其ID和innerText
    let jobTitleSpan = document.createElement('span');
    // jobTitleSpan.id = 'job-title';
    jobTitleSpan.innerText = jobTitle;

    // 獲取p元素，並插入新創建的span元素
    let jobP = document.querySelector('.job > p');
    jobP.appendChild(jobTitleSpan);

    // 取出後刪除localStorage的資訊
    // localStorage.removeItem('memName');
    localStorage.removeItem('jobTitle');
} else {
    // ========================== 企業方自己找人才面試 ==========================
    // 代表memName和jobTitle不存在，則發送請求獲取資訊
    axios.post('/SeekerPool/FrontEnd/InterviewInvite')
        .then(function (response) {
            const jobs = response.data;
            console.log(jobs)
            const select = document.getElementById('job-title');
            select.style.display = "block";  // 讓 select 選單顯示出來
            select.innerHTML = '';  // 清空元素內容

            // 動態生成選單
            jobs.forEach(function (job) {
                let option = document.createElement('option');
                option.value = job.jobNo;
                option.text = job.jobName;

                select.appendChild(option);
            });
        })
        .catch(function (error) {
            console.log(error);
        });
}

// ========================== 新增面試時間 ==========================
let dateCounter = 0;
// 儲存已被選擇的時間段
let selectedDateTimes = [];

$("#add-time").click(function () {

    if (dateCounter < 3) {

        const dateInput = $("#datetime").val();
        const timeZone = $("#time-zone").val();

        // 轉換日期格式: "YYYY-MM-DD" - "MM月DD日"
        const dateParts = dateInput.split("-");
        let month = parseInt(dateParts[1], 10);  // Parse the month to integer to remove leading zero.
        let day = parseInt(dateParts[2], 10);    // Parse the day to integer to remove leading zero.
        const formattedDate = month + '月' + day + '日';

        // 選擇的日期及時間轉換成新字串
        const newDateTime = formattedDate + ' ' + timeZone;

        // 時間段已被選擇則不加入
        if (selectedDateTimes.includes(newDateTime)) {
            // 將警告訊息顯示出來
            $("#warning").css("visibility", "visible");
            return;
        }

        dateCounter += 1;
        $("#warning").css("visibility", "hidden");

        selectedDateTimes.push(newDateTime);
        const newP = $('<p></p>').text(newDateTime);
        newP.addClass('date-' + dateCounter);

        $("#chosenDateTime").append(newP);
    } else {
        // alert("最多可選擇三個時段");
        swal("最多可選擇三個時段", "", "warning");
    }
});

// ========================== 按下按鈕後送出邀請 ==========================
function interviewSubmit() {
    if (dateCounter != 0) {
        let date1 = $(".date-1").html();
        let date2 = $(".date-2").html();
        let date3 = $(".date-3").html();

        // 人才的ID
        let memId = localStorage.getItem('memId');
        // 選擇哪一職缺"id"
        let jobId = localStorage.getItem('jobId');

        if (!jobId) {
            // = Sam的查詢人才
            let select = document.getElementById('job-title');
            jobId = select.options[select.selectedIndex].value;  // 獲得當前選擇的選項的 jobId
            memId = id;  // 最上面第3行讀的參數
        } else {
            localStorage.removeItem('jobId');
        }
        localStorage.removeItem('memId');

        $.ajax({
            url: "/" + projectName + "/ComInterviewInviteServlet",
            type: 'POST',
            data: { memId: memId, date1: date1, date2: date2, date3: date3, "jobNo": jobId },
            async: false,//同步請求
            cache: false,//不快取頁面
            beforeSend: function () {  //都無法比success先執行?!??
                // $("#interview-invite").html("請稍後...");
                // $("#interview-invite").attr({ disabled: "disabled" });
                // $('#loading_img').html('<img style="max-width: 100px; display:block; margin:auto;" src="./images/loading.gif">');
            },
            success: function (data) {
                var data = JSON.parse(data);
                if (data.flag) {
                    //郵件寄送成功
                    alert("已郵件通知該位人才");
                } else {
                    //郵件寄送失敗
                    alert("郵件寄送失敗，請稍後再試");
                }
            },
            error: function () {
                location.href = "membererror.html";
            }
        });

    } else {
        alert("請至少選擇一個時段");
    }
}