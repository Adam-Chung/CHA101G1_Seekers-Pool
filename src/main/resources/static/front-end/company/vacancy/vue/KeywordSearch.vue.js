new Vue({
    el:'.search',
    data:{
        keyword:'', // 關鍵字查詢用
        url:'', // 關鍵字查詢用
    },
    methods:{
        search(){
            if(this.keyword ==='' && this.jobLocation === ''){
                return alert("查詢不得為空");
            } else {
                axios.get(`/SeekerPool/talent/${this.keyword}`)
                    .then(res => {
                        if (res.data.data.length >0){ // 用length來判斷是否有資料回傳
                            // 動態生成 URL
                            this.url = `../html/TalentList.html?keyword=${encodeURIComponent(this.keyword)}`;
                            window.location.href = this.url;
                        }else {
                            alert("關鍵字找不到喔!")
                        }
                    });

            }
        },
    }
})