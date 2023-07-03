
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
            initialDistrict: '',

            page:1, // 存當前的頁數
            pageSize: 10,
            totalDataNumber: null,
            generatedNumber:[],
            activeIndex: 0, // 初始激活的索引

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
        this.fetchData(1,this.pageSize)
        axios.all([
            axios.get("/SeekerPool/cities"),
            axios.get("/SeekerPool/positionType"),
        ]).then(axios.spread((resCities , resType) => {
            this.citiesData = resCities.data.data;
            this.positionTypeData = resType.data.data;

            console.log("MOUNTED獲取資料成功")

        }))
    },

    methods:{
        fetchData(page, pageSize) {
            axios.get('/SeekerPool/vacancy/page', {
                params: {
                    page: page,
                    pageSize: pageSize
                }
            })
                .then(response => {
                    // 请求成功，处理响应数据
                    this.vacanciesData = response.data.data.rows; // 他回傳那包的結構裡面data裡面又有rows
                    this.totalDataNumber = response.data.data.total;
                    this.roundNumber;
                    console.log(response.data);
                })
                .catch(error => {
                    // 请求失败，处理错误
                    console.error(error);
                });
        },
        // 保存編輯後的表單資料
        submitEditForm(){
            this.transformTypeNameToPtNo();
            // 檢查表單欄位是否填寫
            if (!this.checkFormFields()) {
                // 有未填寫的欄位，顯示錯誤訊息或其他處理方式
                return;
            }else {
                // 發送 POST 請求
                axios.put('/SeekerPool/vacancy', this.editedVacancy)
                    .then(response => {
                        // 處理成功回應
                        console.log(response.data);
                        this.fetchData(this.page, 10);  // 編輯後要再一次更新列表的資料
                        // 例如顯示成功訊息或重新導向到其他頁面
                        Swal.fire({
                            title: '已成功保存!!',
                            icon: 'success',
                            width: 400,
                            padding: '3em',
                            color: '#817be0',
                            background: '#fff',
                            backdrop: `
                                        rgba(0,0,023,0.3)
                                        url(https://sweetalert2.github.io/images/nyan-cat.gif)
                                        right bottom
                                        repeat
                                      `
                        });
                        this.goBack();
                    })
                    .catch(error => {
                        // 處理錯誤回應
                        console.error(error);
                        // 顯示錯誤訊息
                        Swal.fire({
                            title: '有錯誤!',
                            text: '表單提交失敗!',
                            icon: 'error',
                            timer: 2000
                        })
                    });
            }

        },
        checkFormFields() {
            // 檢查表單欄位是否填寫
            if (this.editedVacancy.jobName === null || this.editedVacancy.jobName === '') {
                this.sweetalert('注意!', '職務名稱幫我填一下!!', 'warning','關閉' )
                return false;
            }else if(this.editedVacancy.ptName === '' || this.editedVacancy.ptName === null){
                this.sweetalert('注意!', '職缺類別幫我填一下!', 'warning', '關閉');
                return false;
            }else if(this.editedVacancy.districtName=== '' || this.editedVacancy.districtName === null){
                this.sweetalert('注意!', '地區還沒選擇喔!', 'warning', '關閉');
                return false;
            }else if(this.editedVacancy.jobAddress === '' || this.editedVacancy.jobAddress === null){
                this.sweetalert('注意!', '剩餘地址幫我補上喔!!', 'warning', '關閉');
                return false;
            }else if(this.editedVacancy.jobContent === '' || this.editedVacancy.jobContent === null){
                this.sweetalert('注意!', '方便幫我介紹一下工作內容嗎?', 'warning', '關閉');
                return false;
            }else if(this.editedVacancy.jobSalary === '' ||this.editedVacancy.jobSalary === 0 || this.editedVacancy.jobSalary === null){
                this.sweetalert('注意!', '請填入給付薪水金額!', 'warning', '關閉');
                return false;
            }else if(this.editedVacancy.jobOther === '' || this.editedVacancy.jobOther === null){
                this.editedVacancy.jobOther = '無';
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
                }
            })
        },
        deleteVacancy(id) {
            Swal.fire({
                title: '這是真的嗎?',
                text: "刪除職缺可是沒有回頭路的!",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#209318',
                cancelButtonColor: '#d33',
                confirmButtonText: '沒錯，刪了吧!',
                cancelButtonText: '好啦等等，我再想想...'
            }).then((result) => {
                if (result.isConfirmed) {
                    // 用户确认删除
                    axios.delete(`/SeekerPool/vacancy/${id}`)
                        .then(res => {
                            // 删除成功通知
                            Swal.fire(
                                '已刪除!',
                                '此職缺已被刪除!',
                                'success'
                            ).then(() => {
                                // 获取最新数据
                                this.fetchData(this.page , 10);
                            });
                        })
                        .catch(error => {
                            // 删除请求失败
                            console.error('職缺刪除失敗', error);
                            Swal.fire(
                                '刪除失敗',
                                '無法刪除職缺。',
                                'error'
                            );
                        });
                }
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
        },
        //被點擊到的分頁加上class="active"

        setActive(index) {
            this.activeIndex = index;
            this.page = index+1; // 存當前頁面數
            this.fetchData(index+1,10)
        },
        goPrePage(){
            if(this.page !== 1){
                this.page--;
                this.activeIndex = this.page -1; // 讓下一頁增加 class="active"
                this.fetchData(this.page, 10) // 去抓下一頁的資料
            }
        },
        goNextPage(){
            if(this.page !== this.generatedNumber.length){
                this.activeIndex = this.page; // 讓下一頁增加 class="active"
                this.fetchData(this.page+1, 10) // 去抓下一頁的資料
                this.page++
            }
        },
        clearDistrict(){
            if (this.editedVacancy.districtName !== this.initialDistrict) {
                this.editedVacancy.districtName = '';
            }
        },
        clearPtName(){
            if(this.editedVacancy.ptName){

            }
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
        // 算頁數要有幾頁
        roundNumber(){
            this.totalDataNumber = Math.ceil(this.totalDataNumber/10);
            this.generatedNumber = []; // 要先清空要不然前端頁面的頁數會隨著刷新不斷重複
            let x = 1;
            while(x <= this.totalDataNumber){
                this.generatedNumber.push(x);
                x++;
            }
        }
    },

    watch:{
        selectedIndustry(value) {
            // 根據選擇的產業類別值發起請求或處理相應邏輯，並更新職務類別數據
            this.editedVacancy.ptType = value;
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