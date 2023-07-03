let allApplicants = [];  // 儲存所有應徵者的數據

// -------------------- 更新應徵者表格 --------------------
function updateApplicantsTable(applicants) {
    const tbody = document.querySelector('tbody');
    tbody.innerHTML = '';

    applicants.forEach(function (applicant) {
        let tr = document.createElement('tr');

        tr.innerHTML = `
    <td class="text-truncate" name="memName">${applicant.memName}</td>
    <td class="text-truncate" name="memCollege">${applicant.memCollege}</td>
    <td name="jobName">${applicant.jobName}</td>
    <td class="text-truncate" name="applyDate">${applicant.applyDate}</td>
    <td class="text-truncate" name="interDate">${applicant.interDate ? applicant.interDate : ''}</td>
    <td class="text-truncate" name="hireStatus">${applicant.hireStatusText}</td>
    <td class="text-center">
    <button class="btn btn-outline-primary btn-sm" type="button" onclick="handleDetailClick(${applicant.memId})">詳情</button>
    </td>
    `;

        tbody.appendChild(tr);
    });
}

// -------------------- 獲得和更新最新資料 --------------------
function fetchAndUpdateApplicants(status = '100') {
    axios.post('/SeekerPool/FrontEnd/InterviewerManage')
        .then(function (response) {
            allApplicants = response.data;
            updateApplicantsTable(allApplicants);
            document.getElementById('filter').value = status;

            // 監聽選單變化，過濾應徵者數據
            document.getElementById('filter').addEventListener('change', function () {
                // 清除已經顯示的履歷
                document.querySelector('.content-container').innerHTML = '';

                const status = parseInt(this.value, 10);

                if (status === 100) {  // 如果選擇"---請選擇目前狀態---"，則顯示所有數據
                    updateApplicantsTable(allApplicants);
                } else {  // 否則，根據選擇的狀態過濾數據
                    const filteredApplicants = allApplicants.filter(applicant => applicant.hireStatus === status);
                    updateApplicantsTable(filteredApplicants);
                }
            });

            // 將選單設為 "面試已完成，等待通知"，並觸發 "change" 事件以更新表格
            // document.getElementById('filter').value = '2';
            document.getElementById('filter').dispatchEvent(new Event('change'));
            document.querySelector('.content-container').innerHTML = '';
        })
        .catch(function (error) {
            console.log(error);
        });
}

window.onload = function () {
    fetchAndUpdateApplicants();
};

// -------------------- 搜尋框 --------------------
document.getElementById('search-btn').addEventListener('click', function () {
    applicantsSearch();
});

document.getElementById('search').addEventListener('keyup', function (e) {
    if (e.keyCode === 13) {  // 13 是 Enter 鍵的鍵碼
        applicantsSearch();
    }
});

function applicantsSearch() {
    let input, searchQuery, table, tr, td, i, txtValue;
    input = document.getElementById('search');
    searchQuery = input.value;
    table = document.getElementById('myTable');
    tr = table.getElementsByTagName('tr');

    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName('td');
        for (let j = 0; j < td.length; j++) {
            if (td[j]) {
                txtValue = td[j].textContent || td[j].innerText;
                if (txtValue.indexOf(searchQuery) > -1) {
                    tr[i].style.display = "";
                    break;
                } else {
                    tr[i].style.display = "none";
                }
            }
        }
    }
}


// -------------------- 點擊詳情顯示會員履歷 --------------------
let selectedApplicant = null;  // 儲存被選擇的求職者

function handleDetailClick(memId) {
    // 清除選擇的應徵者
    selectedApplicant = null;

    const container = document.querySelector('.content-container');
    container.innerHTML = '';

    axios.get('/SeekerPool/FrontEnd/InterviewerManage', {
        params: {
            detailId: memId
        }
    })
        .then(function (response) {
            const applicant = response.data;
            console.log(applicant)

            // 查找該應徵者在 allApplicants 中的訊息
            const applicantInAll = allApplicants.find(applicant => applicant.memId === memId);
            // 在 selectedApplicant 中添加 jobName 和 jobNo
            selectedApplicant = {
                ...applicant,
                jobName: applicantInAll.jobName,
                jobNo: applicantInAll.jobNo
            };

            console.log(selectedApplicant);

            container.innerHTML = `
      <section class="personal-info">
        <div class="avatar">
          <img class="avatar-img" src="${applicant.memPic}" alt="我的頭像">
        </div>
        <h1 class="name" name="memName">${applicant.memName}</h1>
        <div class="title">
          <span class="college" name="memCollege">${applicant.memCollege}</span>
          <span class="department" name="memDepartment">${applicant.memDepartment}</span><span> | </span>
          <span class="section" name="memAddress">${applicant.memAddress}</span><span> | </span>
          <span class="e-mail" name="memEmail">${applicant.memEmail}</span>
        </div>
        <p class="autobiography" name="memBio">${applicant.memBio}</p>
      </section>
      <section class="skill-section">
        <h2>專業技能</h2>
        <div class="skills">
          <div class="skill">
            <h3 class="skill-name" name="skNo">${applicant.skNo}</h3>
          </div>
        </div>
      </section>
      <section class="language-section">
        <h2>語文能力</h2>
        <div class="language">
          <h3 class="language-name" name="memLang">${applicant.memLang}</h3>
        </div>
      </section>
    `;
        })
        .catch(function (error) {
            console.log(error);
        });
}

// -------------------- 把相關資訊存在localStorage --------------------
document.getElementById('go-interview').addEventListener('click', function () {
    if (selectedApplicant) {
        const memId = selectedApplicant.memId;
        // const memName = selectedApplicant.memName;
        const jobTitle = selectedApplicant.jobName;
        const jobId = selectedApplicant.jobNo;
        // console.log(memName);
        console.log(memId);
        console.log(jobTitle);
        console.log(jobId);

        // localStorage.setItem('memName', memName);
        localStorage.setItem('memId', memId);
        localStorage.setItem('jobTitle', jobTitle);
        localStorage.setItem('jobId', jobId);
        window.open('interview_invite.html', '_blank');
        // window.location.href = 'interview_invite.html';
    } else {
        console.log('沒有儲存的會員資訊');
    }
});

// -------------------- 按鈕變化 --------------------
// 先將所有按鈕都隱藏
let buttons = document.querySelectorAll('.button');
buttons.forEach(button => button.style.display = 'none');

// 監聽選單變化
document.getElementById('filter').addEventListener('change', function () {
    // 每次變化都先將所有按鈕都隱藏
    buttons.forEach(button => button.style.display = 'none');

    const status = this.value;

    // 根據選擇的狀態控制按鈕的顯示
    switch (status) {
        case '0':
            document.getElementById('go-interview').style.display = 'block';
            break;
        case '1':
            document.getElementById('cancel-interview').style.display = 'block';
            document.getElementById('complete-interview').style.display = 'block';
            break;
        // case '2':
        //     document.getElementById('hire').style.display = 'block';
        //     document.getElementById('not-admitted').style.display = 'block';
        //     break;
        default:
            break;
    }
});

// -------------------- 完成面試發送轉換狀態請求 --------------------
document.getElementById('complete-interview').addEventListener('click', function () {
    // 檢查是否有選擇的會員
    if (selectedApplicant) {
        const memId = selectedApplicant.memId;
        const jobNo = selectedApplicant.jobNo;
        console.log(memId);
        console.log(jobNo);

        axios.post('/SeekerPool/FrontEnd/InterviewComplete', {
            memId: memId,
            jobNo: jobNo
        })
            .then(function (response) {
                console.log(response.data);
                // 在這裡處理成功的響應，例如彈出通知或更新前端頁面等
                // alert('更新狀態成功！');
                swal("更新成功", "面試狀態已更改為：完成面試", "success");
                fetchAndUpdateApplicants(2);
            })
            .catch(function (error) {
                console.error(error);
                swal("更新失敗", "請稍後再試", "error");
            });
    } else {
        // alert('請先選擇會員！')
        swal("請先選擇會員", "", "warning");
    }
});

// -------------------- 取消面試轉換狀態 & 寄信通知 --------------------
document.getElementById('cancel-interview').addEventListener('click', function () {
    // 檢查是否有選擇的會員
    if (selectedApplicant) {
        const memId = selectedApplicant.memId;
        const jobNo = selectedApplicant.jobNo;
        console.log(memId);
        console.log(jobNo);

        axios.get('/SeekerPool/CompanyCancelInterview', {
            params: {
                memId: memId,
                jobNo: jobNo
            }
        })
            .then(function (response) {
                console.log(response.data);
                // 在這裡處理成功的響應，例如彈出通知或更新前端頁面等
                // alert('取消面試成功！');
                swal("取消面試成功", "", "success");
                fetchAndUpdateApplicants(4);
            })
            .catch(function (error) {
                console.error(error);
                swal("無法進行此項動作", "請稍後再試", "error");
            });
    } else {
        // alert('請先選擇會員！')
        swal("請先選擇會員", "", "warning");
    }
});