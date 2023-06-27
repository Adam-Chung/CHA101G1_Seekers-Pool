// 顯示所有企業的資訊
axios.post('/SeekerPool/BackEnd/ShowAllCompany')
    .then(function (response) {
        let data = response.data;
        let tbody = document.querySelector("tbody");

        data.forEach(member => {
            let rowHTML = `
        <tr>
          <td style="text-align: center">${member.comMemId}</td>
          <td style="text-align: center">${member.comName}</td>
          <td style="text-align: center">${member.comMemAccount}</td>
          <td style="text-align: center">${member.comStatusText}</td>
          <td class="text-center">
            <a href="backEndCompanyInfo.html?id=${member.comMemId}" class="cancleA">
              <button class="btn btn-outline-primary btn-sm detailsButton" type="button" data-id="${member.comMemId}">詳情</button>
            </a>
          </td>
        </tr>`;
            tbody.innerHTML += rowHTML;
        });
    })
    .catch(function (error) {
        console.log(error);
    });


// 搜尋框
// 過濾企業會員 & 更新列表
function searchMembers() {
    const searchInput = document.getElementById('formGroupExampleInput');
    const searchQuery = searchInput.value;
    const rows = document.querySelectorAll("tbody tr");

    // 沒有符合搜尋結果時就隱藏該資料
    rows.forEach(row => {
        const name = row.children[1].textContent;  // <td style="text-align: center">${member.comName}</td>
        if (!name.includes(searchQuery)) {
            row.style.display = "none";
        } else {
            row.style.display = "";
        }
    });

    searchInput.value = "";  // 搜尋完就清空關鍵字
}


document.getElementById('searchBtn').addEventListener('click', searchMembers);
document.getElementById('formGroupExampleInput').addEventListener('keydown', function (event) {
    if (event.key === 'Enter') {
        searchMembers();
    }
});


// 點擊詳情後可以進入每個企業會員的資訊頁
document.addEventListener('DOMCotentLoaded', function () {
    // 選取所有的詳情按鈕
    const buttons = document.querySelectorAll('.detailsButton');
    // 所有的詳情按鈕都綁定點擊事件、並根據id發送post請求
    buttons.forEach(button => {
        button.addEventListener('click', function (e) {
            const id = e.target.getAttribute("data-id");

            axios.post('/SeekerPool/CompanyMemberShowInfo', {
                id: id
            })
                .then(function (response) {
                    console.log(response.data);
                })
                .catch(function (error) {
                    console.log(error);
                });
        });
    });
});