new Vue({
    el:'.search',
    data:{
        keyword:'', // 關鍵字查詢用
        url:'', // 關鍵字查詢用
    },
    methods:{
        search(){
            if(this.keyword ==='' && this.jobLocation === ''){
                Swal.fire({
                    title: '不能空白喔!',
                    text: '試試看加個關鍵字吧!',
                    icon: 'warning',
                    timer: 2000
                })
            } else {
                axios.get(`/SeekerPool/talent/${this.keyword}`,{
                    params:{
                        page: 1,
                        pageSize: 5
                    }
                })
                    .then(res => {
                        if (res.data.data.rows.length >0){ // 用length來判斷是否有資料回傳
                            // 動態生成 URL
                            this.url = `../html/TalentList.html?keyword=${encodeURIComponent(this.keyword)}`;
                            window.location.href = this.url;
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
    }
})