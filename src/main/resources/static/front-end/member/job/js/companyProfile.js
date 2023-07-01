// 讀取URL中的參數
let urlParams = new URLSearchParams(window.location.search);
let id = urlParams.get('comMemId');
// let id = 5;
// console.log(id);

axios.post('/SeekerPool/ShowCompanyInfoAndJoblist', {
    id: id
})
    .then(response => {
        const companyInfo = response.data.companyInfo;
        const jobList = response.data.jobList;
        console.log("Received data of companyInfo: ", companyInfo);
        console.log("Received data of jobList: ", jobList);

        document.getElementById('comPicture').src = companyInfo.comPicture;
        document.getElementById('companyName').textContent = companyInfo.comName;
        document.getElementById('address').textContent = companyInfo.comAddress;
        document.getElementById('phone').textContent = companyInfo.comTel;

        // 加入企業有上架的職缺
        const jobContainer = document.querySelector('.panel-body');
        jobContainer.innerHTML = '';

        jobList.forEach(function (job) {
            const jobBlock = document.createElement('div');
            jobBlock.className = 'job-block';

            const h3 = document.createElement('h3');
            h3.className = 'job-title';

            const a = document.createElement('a');
            a.href = '/SeekerPool/front-end/member/job/JobContent.html?jobNo=' + job.jobNo;
            a.textContent = job.jobName;

            h3.appendChild(a);
            jobBlock.appendChild(h3);
            jobContainer.appendChild(jobBlock);
        });
    })
    .catch(error => {
        console.log('Error: ' + error);
    });


// 點擊檢舉按鈕到檢舉表單
document.querySelector('.report-botton').addEventListener('click', function () {
    let comMemId = id;  // Replace this with the actual value
    window.location.href = "/SeekerPool/front-end/member/report/ReportForm.html?comMemId=" + comMemId;
});
