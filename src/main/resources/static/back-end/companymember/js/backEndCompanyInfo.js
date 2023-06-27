document.addEventListener('DOMContentLoaded', function () {
    // 讀取URL中的參數
    let urlParams = new URLSearchParams(window.location.search);
    let id = urlParams.get('id');
    console.log(id);

    // --------------------- 顯示該企業的資訊 ---------------------
    axios.post('/SeekerPool/BackEnd/ShowCompanyMemberInfo', {
        id: id
    })
        .then(response => {
            const data = response.data;
            console.log("Received data: ", data);

            document.getElementById('logoImg').src = data.comPicture;
            document.getElementById('comName').value = data.comName;
            document.getElementById('comMemId').value = data.comMemId;
            document.getElementById('account').value = data.comMemAccount;
            document.getElementById('comEmail').value = data.comEmail;
            document.getElementById('comTel').value = data.comTel;
            document.getElementById('comAddress').value = data.comAddress;
            document.querySelector(`input[name="comStatus"][value="${data.comStatus}"]`).checked = true;
            /* 如果data.formattedTime的資料存在，返回該值；
               如果不存在，將其值設定為N/A */
            document.getElementById('formattedTime').value = data.formattedTime || "N/A";
            document.getElementById('comRepNum').value = data.comRepNum;
        })
        .catch(error => {
            console.log('Error: ' + error);
        });


    // --------------------- 把企業狀態的值傳送到後端 ---------------------
    const submitBtn = document.getElementById('submit-info');

    submitBtn.addEventListener('click', function (event) {
        event.preventDefault();

        const radios = document.getElementsByName('comStatus');
        let value;

        for (let i = 0; i < radios.length; i++) {
            if (radios[i].checked) {
                value = radios[i].value;
                console.log('Value:', value);
                break;
            }
        }

        axios.get('/SeekerPool/BackEnd/ChangeCompanyStatus', {
            params: {
                id: id,
                comStatus: value
            }
        })
            .then(response => {
                const data = response.data;
                // alert('更新成功')
                swal("更新成功", "", "success");
            })
            .catch(error => {
                console.log('Error: ' + error);
                // alert('更新失敗')
                swal("更新失敗", "請稍後再試", "error");
            })

    })
})