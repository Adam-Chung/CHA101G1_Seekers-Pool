new Vue({
    el:'.add-vacancy-form',

    data(){
        return{
            citiesData: [], // 接收資料庫來的全部縣市類別 ok
            positionTypeData: [], // 接收資料庫來的全部產業類別 ok

            correspondingJobName : [], // 儲存找到選擇之產業對應的職務類別
            correspondingDistrict :[], // 儲存找到選擇之縣市對應的鄉鎮市區類別
            // 存儲addVacancyForm表單
            vacancy:{
                jobType : 0, // 全職、兼職
                jobNo : 0,  // 主鍵
                jobName : '', // 名稱(業主自己取)
                ptNo :'', // 產業編號 用來存儲用戶選擇的產業
                ptName : '', // 職務類別名稱
                cityName : '', // 用來存儲用戶選擇的縣市
                districtName : '',
                jobAddress : '',
                jobContent : '',
                jobOther: '', // 其他條件
                jobSalary: 0
            },
        }
    },

    mounted(){
        axios.all([
                    axios.get("/SeekerPool/cities"),
                    axios.get("/SeekerPool/positionType/"),
            ]).then(axios.spread((resCities , resType) => {
              this.citiesData = resCities.data.data;
              this.positionTypeData = resType.data.data;
              console.log("其實有成功")
            }))
    },

    methods: {
        // 提交form表單
        submitAddForm() {
            // 檢查表單欄位是否填寫
            if (!this.checkFormFields()) {
                // 有未填寫的欄位，顯示錯誤訊息或其他處理方式
                return;
            }else {
                // 用來將前端的職務名稱轉換成對應的id(job這table只能存id)
                this.vacancy.ptNo = this.vacancyPtNo;
                // 發送 POST 請求
                axios.post('/SeekerPool/vacancy', this.vacancy)
                    .then(response => {
                        // 處理成功回應
                        console.log(response.data);
                        Swal.fire({
                            title: '新增成功!!',
                            icon: 'success',
                            width: 400,
                            padding: '3em',
                            color: '#817be0',
                            background: '#fff',
                            backdrop: `
                                        rgba(0,0,123,0.3)
                                        url("https://sweetalert2.github.io/images/nyan-cat.gif")
                                        left top
                                        no-repeat
                                      `
                        });
                        setTimeout(() => {
                            this.refreshPage();
                        }, 1500);
                    })
                    .catch(error => {
                        // 處理錯誤回應
                        console.error(error);
                        // 顯示錯誤訊息
                        Swal.fire({
                            title: '有錯誤!',
                            text: '表單提交失敗!',
                            icon: 'error',
                            timer: 1500
                        })
                    });
            }
        },
        checkFormFields() {
            // 檢查表單欄位是否填寫
            if (this.vacancy.jobName === null || this.vacancy.jobName === '') {
                this.sweetalert('注意!', '職務名稱幫我填一下!!', 'warning','關閉' )
                return false;
            }else if(this.vacancy.ptNo === '' || this.vacancy.ptNo === null){
                this.sweetalert('注意!', '產業類別幫我填一下!', 'warning', '關閉');
                return false;
            }else if(this.vacancy.ptName === '' || this.vacancy.ptName === null){
                this.sweetalert('注意!', '職缺類別幫我填一下!', 'warning', '關閉');
                return false;
            }else if(this.vacancy.cityName === '' || this.vacancy.cityName === null){
                this.sweetalert('注意!', '地區還沒選擇喔!', 'warning', '關閉');
                return false;
            }else if(this.vacancy.districtName=== '' || this.vacancy.districtName === null){
                this.sweetalert('注意!', '地區似乎還沒選擇完喔!', 'warning', '關閉');
                return false;
            }else if(this.vacancy.jobAddress === '' || this.vacancy.jobAddress === null){
                this.sweetalert('注意!', '剩餘地址幫我補上謝謝!!', 'warning', '關閉');
                return false;
            }else if(this.vacancy.jobContent === '' || this.vacancy.jobContent === null){
                this.sweetalert('注意!', '方便幫我介紹一下工作內容嗎?', 'warning', '關閉');
                return false;
            }else if(this.vacancy.jobSalary === 0 || this.vacancy.jobSalary === null){
                this.sweetalert('注意!', '請填入給付薪水金額!', 'warning', '關閉');
                return false;
            }else if(this.vacancy.jobOther === '' || this.vacancy.jobOther === null){
                this.vacancy.jobOther = '無';
                return true;
            }else {
                return true;
            }
        },
        sweetalert(title1, text2, icon3, confirmButtonText4, timer5){
            Swal.fire({
                title: title1,
                text: text2,
                icon: icon3,
                confirmButtonText: confirmButtonText4,
                timer: timer5,
            })
        },
        //刷新頁面讓新資料show出來
        refreshPage() {
            location.reload(); // 刷新頁面
        }
    },
    computed: {
        // 讓從後端傳到這裡的position 的 ptType 裡的資料不重複
        uniqueTypes() {
            const typesSet = new Set();
            this.positionTypeData.forEach(job => {
                typesSet.add(job.ptType);
            });
            return Array.from(typesSet);
        },
        vacancyPtNo() {
            const selectId = this.positionTypeData.find(item => item.ptType === this.vacancy.ptNo);
            if (selectId) {
                return selectId.ptNo;
            }
            return '';
        }
    },
    watch:{
        'vacancy.ptNo'(type) {
            // 根據選擇的產業類別值發起請求或處理相應邏輯，並更新職務類別數據
            axios.get(`/SeekerPool/positionType/${type}`)
                .then(res => {
                    if (res.data.code) {
                        console.log("我看一下")
                        this.correspondingJobName = res.data.data;
                    }
                });
        },
        'vacancy.cityName'(cityName) {
            // 根據選擇的縣市發起請求或處理相應邏輯，並更新鄉鎮市區數據
            axios.get(`/SeekerPool/districts/${cityName}`)
                .then(res => {
                    if (res.data.code) {
                        this.correspondingDistrict = res.data.data;
                        console.log("district success received")
                    }
                });
        }
    }
})