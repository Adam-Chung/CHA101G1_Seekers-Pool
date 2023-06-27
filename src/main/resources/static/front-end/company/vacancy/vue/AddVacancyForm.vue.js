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
            }
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

    // 提交form表單
    methods: {
        // 驗證還沒實現!!!!!!!!!!!!!!!!!!
        submitAddForm() {
            // 檢查表單欄位是否填寫
            if (!this.checkFormFields()) {
                // 有未填寫的欄位，顯示錯誤訊息或其他處理方式
                this.showErrorMessage('請填寫所有必填欄位！');
                return;
            }
            // 用來將前端的職務名稱轉換成對應的id(job這table只能存id)
            this.vacancy.ptNo = this.vacancyPtNo;
            // 發送 POST 請求
            axios.post('/SeekerPool/vacancy', this.vacancy)
                .then(response => {
                    // 處理成功回應
                    console.log(response.data);
                    // 例如顯示成功訊息或重新導向到其他頁面
                    this.showSuccessMessage('表單提交成功！');
                })
                .catch(error => {
                    // 處理錯誤回應
                    console.error(error);
                    // 例如顯示錯誤訊息
                    this.showErrorMessage('表單提交失敗，請稍後再試！');
                });
        },
        checkFormFields() {
            // 檢查表單欄位是否填寫
            if (!this.vacancy.jobName) {
                // 職務名稱未填寫
                this.showErrorMessage('職務名稱不能為空！');
                return false;
            }
            // 檢查其他必填欄位...
            return true;
        },

        // 跳出警示
        showErrorMessage(msg) {
            alert(msg);
        },
        showSuccessMessage(msg) {
            alert(msg);
        },

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