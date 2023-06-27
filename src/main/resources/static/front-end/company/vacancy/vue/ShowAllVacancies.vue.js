
new Vue({
    el:'.all-vacancies-container',
    data(){
        return{
            msg1:'',
            msg2:'',
            citiesData: [], // 接收資料庫來的全部縣市類別
            positionTypeData: [], // 接收資料庫來的全部產業類別 ok
            selectedIndustry: '', // 用來存儲用戶選擇的產業，因為資料庫沒這欄位，所以放這
            selectedName: '',
            correspondingJobName : [], // 儲存找到選擇之產業對應的職務類別
            correspondingDistrict :[], // 儲存找到選擇之縣市對應的鄉鎮市區類別
            vacanciesData: [], // 接收資料庫來的全部職缺
            editData : [],
            vacancy: [], // 添加 vacancy 属性
            // 下面接收用戶編輯後的資料
            editedVacancy:{
                jobType : 0, // 全職、兼職
                jobNo : 0,  // 主鍵
                jobName : '', // 名稱(業主自己取)
                ptNo:0,
                ptType :'', // 產業編號 用來存儲用戶選擇的產業
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
    mounted() {
        axios.all([
            axios.get("/SeekerPool/cities"),
            axios.get("/SeekerPool/positionType"),
            axios.get("/SeekerPool/vacancy"),
        ]).then(axios.spread((resCities , resType, resVacancy) => {
            this.citiesData = resCities.data.data;
            this.positionTypeData = resType.data.data;
            this.vacanciesData = resVacancy.data.data;
            console.log("MOUNTED獲取資料成功")

        }))
    },
    methods:{
        // 保存編輯後的表單資料
        submitEditForm(){
            this.transformTypeNameToPtNo();
            // 檢查表單欄位是否填寫
            if (!this.checkFormFields()) {
                // 有未填寫的欄位，顯示錯誤訊息或其他處理方式
                this.showErrorMessage('請填寫所有必填欄位！');
                return;
            }
            // 發送 POST 請求
            axios.put('/SeekerPool/vacancy', this.editedVacancy)
                .then(response => {
                    // 處理成功回應
                    console.log(response.data);
                    this.fetchNewData();  // 編輯後要再一次更新列表的資料
                    // 例如顯示成功訊息或重新導向到其他頁面
                    this.showSuccessMessage('表單保存成功！');
                    this.goBack();
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
            if (!this.editedVacancy.jobName) {
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
        // 透過table.job 的 jobId 來編輯此職缺資料
        editVacancy(id){
            axios.get(`/SeekerPool/vacancy/${id}`).then(res => {
                if (res.data.code) {
                    this.editData = res.data.data;
                    // 目的是如果使用者沒有修改的話 還是吃的到原本的值
                    this.editedVacancy.jobNo = this.editData[0].jobNo;
                    this.editedVacancy.jobType = this.editData[0].jobType;
                    this.editedVacancy.ptNo = this.editData[0].ptNo;
                    this.editedVacancy.jobName = this.editData[0].jobName;
                    this.editedVacancy.cityName = this.editData[0].cityName;
                    this.editedVacancy.districtName = this.editData[0].districtName;
                    this.editedVacancy.jobAddress = this.editData[0].jobAddress;
                    this.editedVacancy.jobContent = this.editData[0].jobContent;
                    this.editedVacancy.jobOther = this.editData[0].jobOther;
                    this.editedVacancy.jobSalary = this.editData[0].jobSalary;
                    console.log(this.editData);
                }
            })
        },
        deleteVacancy(id){
            axios.delete(`/SeekerPool/vacancy/${id}`).then(res => {
                    // 删除请求成功后的处理逻辑
                    console.log('資料删除成功');
                    // 獲取最新數據
                    this.fetchNewData();  // 刪除後要再一次更新列表的資料
                }).catch(error => {
                        // 删除請求失敗
                        console.error('資料删除失败', error);
                    });
        },
        fetchNewData(){
            axios.get('/SeekerPool/vacancy')
                .then(response => {
                    // 獲取最新資料成功後的處理邏輯
                    console.log('獲取最新資料成功', response.data.data);
                    // 更新資料
                    this.vacanciesData = response.data.data;
                })
                .catch(error => {
                    // 獲取最新資料失敗後的處理邏輯
                    console.error('獲取最新資料失敗', error);
                });
        },

        // 用來將前端的職務名稱轉換成對應的id(job這table只能存id)
        editedVacancyPtNo() {
            const selectId = this.positionTypeData.find(item => item.ptType === this.editedVacancy.ptType);
            if (selectId) {
                return selectId.ptNo;
            }
            return '';
        },

         showInfo(id){
            const selectedType = this.positionTypeData.find(item => item.ptNo === id);
            if(selectedType){
                // 在找到指定的positionTypeData外， 一並將ptType賦值給this.editedVacancy.ptNo,
                // 因為ptNo要有值才能做watch監控,並回傳對應的職務名稱
                this.editedVacancy.ptType = selectedType.ptType;
                this.editedVacancy.ptName = selectedType.ptName;
                console.log("近來了嗎")
                return [selectedType]; // 回傳物件要加 []
            }
            return '';
        },
        transformTypeNameToPtNo(){
            const number = this.positionTypeData.find(item => item.ptType === this.selectedIndustry && item.ptName === this.selectedName)
            if (number) {
                console.log("我一定可以轉換" + number.ptNo);
                // 用來將前端的職務名稱轉換成對應的id(job這table只能存id)
                this.editedVacancy.ptNo = number.ptNo;
            }
        },
        goBack(){
            var $editVacancy = $(".edit-vacancy");
            $editVacancy.addClass("-none");
        }
    },

    computed:{
        // 讓從後端傳到這裡的position 的 ptType 裡的資料不重複
        uniqueTypes() {
            const typesSet = new Set();
            this.positionTypeData.forEach(job => {
                typesSet.add(job.ptType);
            });
            return Array.from(typesSet);
        },
    },

    watch:{
        selectedIndustry(value) {
            // 根據選擇的產業類別值發起請求或處理相應邏輯，並更新職務類別數據
            console.log("還沒動過this.editedVacancy.ptType的值是: " + this.editedVacancy.ptType)
            this.editedVacancy.ptType = value;
            console.log("那現在this.editedVacancy.ptType的值是: " + this.editedVacancy.ptType)
            axios.get(`/SeekerPool/positionType/${value}`)
                .then(res => {
                    if (res.data.code) {
                        this.msg1 = "disappear";
                        this.correspondingJobName = res.data.data;
                    }
                });
        },
        selectedName(value) {
            // 根據選擇的產業類別值發起請求或處理相應邏輯，並更新職務類別數據
            this.editedVacancy.ptName = value;
            console.log("那現在this.editedVacancy.ptName的值是: " + this.editedVacancy.ptName)
            console.log("最後看一下selectedName" + this.selectedName)
        },
        'editedVacancy.cityName'(cityName) {
            // 根據選擇的縣市發起請求或處理相應邏輯，並更新鄉鎮市區數據
            axios.get(`/SeekerPool/districts/${cityName}`)
                .then(res => {
                    if (res.data.code) {
                        this.msg2 = "disappear"; // 給其:value賦值，就可以讓他不出現在第一位
                        this.correspondingDistrict = res.data.data;
                        console.log("district success received")
                    }
                });
        },
    }

})