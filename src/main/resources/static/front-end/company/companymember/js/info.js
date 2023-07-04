// 定義變數
const editInfo = document.getElementById('edit-info');
const loadFile = document.getElementById('comPicture');
const previewZone = document.getElementById('preview');
const submitBtn = document.getElementById('submit-info');
const formElement = document.getElementById('memberInfoForm');
const inputs = document.querySelectorAll('input');

// --------------------- 初始化會員資料 ----------------------
const initMemberData = () => {
    axios.post('/SeekerPool/FrontEnd/ShowCompanyMemberInfo')
        .then(response => {
            const data = response.data;
            console.log("Received data: ", data);

            document.getElementById('logoImg').src = data.comPicture;
            document.getElementById('brand-name').innerHTML = data.comName;
            document.getElementById('account').value = data.comMemAccount;
            document.getElementById('password').value = data.comMemPassword;
            document.getElementById('name').value = data.comName;
            document.getElementById('taxNumber').value = data.taxNum;
            document.getElementById('email').value = data.comEmail;
            document.getElementById('phone').value = data.comTel;
            document.getElementById('address').value = data.comAddress;
            document.querySelector(`input[name="comStatus"][value="${data.comStatus}"]`).checked = true;
            document.getElementById('formattedTime').value = data.formattedTime || "N/A";
            document.getElementById('comRepNum').value = data.comRepNum;
        })
        .catch(error => {
            console.log('Error: ' + error);
        });
}

// -------------------- 修改圖片後可預覽 --------------------
const previewIMG = (file) => {
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.addEventListener('load', function () {
        const preIMG = `<img src="${reader.result}" class="preview_img">`;
        previewZone.innerHTML = preIMG;
    })
}

// ------------------- 修改資料後送出 -------------------
const submitForm = () => {
    let formData = new FormData(formElement);
    axios({
        method: 'post',
        url: '/SeekerPool/CompanyMemberUpdateInfo',
        data: formData,
        headers: { 'Content-Type': 'multipart/form-data' }
    })
        .then(response => {
            let resultInfo = response.data;
            if (resultInfo.flag) {
                swal("更新成功！", "", "success");
                // 更新頁面
                initMemberData();
                // 清空預覽圖
                previewZone.innerHTML = '<span class="text">預覽圖</span>';

                resetForm();
            } else {
                swal("更新失敗", `${data.errorMsg}`, "error");
            }
        })
        .catch(error => {
            console.error(error);
        });
}

// ----------------- 還原到原始狀態 -------------------
const resetForm = () => {
    editInfo.disabled = false;
    loadFile.setAttribute('disabled', 'disabled');
    for (let input of inputs) {
        if (input.getAttribute('type') === 'radio' ||
            input.getAttribute('type') === 'file' ||
            input.id === 'account' || input.id === 'formattedTime' || input.id === 'comRepNum') {
            continue;
        }
        input.setAttribute('readonly', 'readonly');
        input.classList.remove('border');
    }
}

// -------------------- 初始化設定 ---------------------
window.onload = function () {
    initMemberData();

    editInfo.addEventListener('click', function () {
        editInfo.disabled = true;
        loadFile.removeAttribute('disabled');
        for (let input of inputs) {
            if (input.getAttribute('type') === 'radio' ||
                input.getAttribute('type') === 'file' ||
                input.id === 'account' || input.id === 'formattedTime' || input.id === 'comRepNum') {
                continue;
            }
            input.removeAttribute('readonly');
            input.classList.add('border');
        }
    });

    loadFile.addEventListener('change', function (e) {
        if (this.files.length > 0) {
            previewIMG(this.files[0]);
        } else {
            previewZone.innerHTML = '<span class="text">預覽圖</span>';
        }
    });

    submitBtn.addEventListener('click', function (event) {
        event.preventDefault();
        submitForm();
    });
}