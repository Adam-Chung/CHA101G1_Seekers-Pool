new Vue({
    el: '.main-content',
    data:{
        talentListData:[],
        keyword:'',
    },
    mounted() {
        const urlParams = new URLSearchParams(window.location.search);
        this.keyword = urlParams.get('keyword');
        axios.get(`/SeekerPool/talent/${this.keyword}`)
            .then(res => {
                if (res.data.code){
                    this.talentListData = res.data.data;
                    this.keyword =''; //將keyword清空讓給關鍵字搜尋使用
                }
            });
    },
    methods:{
        // 給關鍵字查詢用的
        search(){
            if(this.keyword ===''){
                return alert("查詢不得為空");
            } else {
                axios.get(`/SeekerPool/talent/${this.keyword}`)
                    .then(res => {
                        if (res.data.data.length >0){ // 用length來判斷是否有資料回傳
                            this.talentListData = res.data.data;
                        }else {
                            alert("查詢不到相關人才")
                        }
                    });

            }

        },
        // 打開詳細人才履歷頁面, 把memId存入localStorage裡
        openResume(id){
            $talentBio = $('.talent-bio').removeClass('-none');

            localStorage.setItem('memId', id);
        },
        // 把memId存入localStorage裡
    }
})