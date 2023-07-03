new Vue({
    el: '#app',
    data:{
        memId: null,
        resume :[],
        skillArray :[],
        langArray :[],
        phoneNumber :'',

        talentListData:[],
        keyword:'',
        tempKeyword:'',
        jobLocation: [],
        url:'',

        page:1, // 存當前的頁數
        pageSize: 5,
        total: '', // 給前端show 出共找到total位人才
        totalDataNumber: '', // 給pagination去算要有幾頁
        generatedNumber:[],
        activeIndex: 0, // 初始激活的索引
    },
    mounted() {
        const urlParams = new URLSearchParams(window.location.search);
        this.keyword = urlParams.get('keyword');
        this.jobLocation = urlParams.get('areas');
        if(this.keyword){
            axios.get(`/SeekerPool/talent/${this.keyword}`,{
                params:{
                    page: this.page,
                    pageSize: this.pageSize
                }
            })
                .then(res => {
                    if (res.data.code){
                        this.tempKeyword = this.keyword;
                        this.total = res.data.data.total;
                        this.totalDataNumber = res.data.data.total;
                        console.log("res.data.data.total: " + res.data.data.total + "page: " + this.page + "pageSize: " + this.pageSize + "total: " + this.totalDataNumber)
                        this.talentListData = res.data.data.rows;
                        this.keyword =''; //將keyword清空讓給關鍵字搜尋使用
                        this.roundNumber;
                    }
                });
        }else {
            axios.get(`/SeekerPool/talent/area/${this.jobLocation}`,{
                params:{
                    page: this.page,
                    pageSize: this.pageSize
                }
            })
                .then(res => {
                    if (res.data.code){

                        this.talentListData = res.data.data.rows;
                        this.total = res.data.data.total;
                        this.totalDataNumber = res.data.data.total;
                        this.keyword =''; //將keyword清空讓給關鍵字搜尋使用
                        this.roundNumber;
                    }
                });
        }
        // if(this.keyword){
        //     axios.get(`/SeekerPool/talent/${this.keyword}`)
        //         .then(res => {
        //             if (res.data.code){
        //                 this.talentListData = res.data.data;
        //                 this.keyword =''; //將keyword清空讓給關鍵字搜尋使用
        //             }
        //         });
        // }else {
        //     axios.get(`/SeekerPool/talent/area/${this.jobLocation}`)
        //         .then(res => {
        //             if (res.data.code){
        //                 this.talentListData = res.data.data;
        //                 this.keyword =''; //將keyword清空讓給關鍵字搜尋使用
        //             }
        //         });
        // }

    },
    methods:{
        // 給關鍵字查詢用的
        search(){
            if(this.keyword ===''){

                Swal.fire({
                    title: '不能空白喔!',
                    text: '試試看加個關鍵字吧!',
                    icon: 'warning',
                    timer: 2000
                })
            } else {
                this.tempKeyword = this.keyword;
                axios.get(`/SeekerPool/talent/${this.keyword}`,{
                    params:{
                        page: this.page,
                        pageSize: this.pageSize
                    }
                })
                    .then(res => {
                        if (res.data.data.rows.length >0){ // 用length來判斷是否有資料回傳

                            this.roundNumber;
                            this.total = res.data.data.total;
                            this.totalDataNumber = res.data.data.total;
                            this.talentListData = res.data.data.rows;
                        }else {
                            Swal.fire({
                                title: '抱歉沒有相關的人才...',
                                text: '換個關鍵字試試看吧!',
                                icon: 'warning',
                                timer: 2300
                            })
                        }
                    });

            }

        },
        // 打開詳細人才履歷頁面, 把memId存入localStorage裡
        openResume(id){
            this.memId = id;
            axios.get(`/SeekerPool/talent/id/${this.memId}`)
                .then(res => {
                    if (res.data.code) {
                        this.resume = res.data.data;
                        this.skillArray = this.resume[0].skNo.split('、');
                        this.langArray = this.resume[0].memLang.split(',');
                        this.phoneNumber = this.resume[0].memMobile.slice(0, 4) + '-' + this.resume[0].memMobile.slice(4, 7) + '-' + this.resume[0].memMobile.slice(7);
                    }
                });
            // localStorage.removeItem('memId');
            // $talentBio = $('.talent-bio').removeClass('-none');
            //
            // localStorage.setItem('memId', id);
            // const b = localStorage.getItem('memId')
            // alert("localStorage: " + b)
        },
        // 把memId存入localStorage裡
        sendInvitation(memId){
            this.url = `../../companymember/interview_invite.html?memId=${encodeURIComponent(memId)}`; // 問雯儀
            window.location.href = this.url;
        },
        setActive(index) {
            this.activeIndex = index;
            this.page = index+1; // 存當前頁面數
            this.fetchData(index+1,5)
        },
        goPrePage(){
            if(this.page !== 1){
                this.page--;
                this.activeIndex = this.page -1; // 讓下一頁增加 class="active"
                this.fetchData(this.page, 5) // 去抓下一頁的資料
            }
        },
        goNextPage(){
            if(this.page !== this.generatedNumber.length){
                this.activeIndex = this.page; // 讓下一頁增加 class="active"
                this.fetchData(this.page+1, 5) // 去抓下一頁的資料
                this.page++
            }
        },
        fetchData(page, pageSize) {
            axios.get(`/SeekerPool/talent/${this.tempKeyword}`, {
                params: {
                    page: page,
                    pageSize: pageSize
                }
            })
                .then(response => {
                    // 请求成功，处理响应数据
                    this.talentListData = response.data.data.rows; // 他回傳那包的結構裡面data裡面又有rows
                    this.total = res.data.data.total;
                    this.totalDataNumber = response.data.data.total;
                    this.roundNumber;
                    console.log(response.data);
                })
                .catch(error => {
                    // 请求失败，处理错误
                    console.error(error);
                });
        },
        goBack() {
            //   退出人才履歷(x鍵) (ok)
            $(".bio-info > i").on("click", function () {
                $(".talent-bio").addClass("-none");
                $(".taletn-bio-container").addClass("-none");
            })
        },
    },
    computed:{
        // 算頁數要有幾頁
        roundNumber(){
            this.totalDataNumber = Math.ceil(this.totalDataNumber/5);
            this.generatedNumber = []; // 要先清空要不然前端頁面的頁數會隨著刷新不斷重複
            let x = 1;
            while(x <= this.totalDataNumber){
                this.generatedNumber.push(x);
                x++;
            }
        }
    }
})