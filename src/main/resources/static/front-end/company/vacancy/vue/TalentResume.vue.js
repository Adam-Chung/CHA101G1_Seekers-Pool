new Vue({
    el:'.talent-bio',
    data(){
        return{
            resume :[],
        }
    },
    mounted() {
        const id = localStorage.getItem('memId')
        axios.get(`/SeekerPool/talent/id/${id}`)
            .then(res => {
                if (res.data.code){
                    this.resume = res.data.data;
                }
            })
    },
    methods: {
        goBack() {
            //   退出人才履歷(x鍵) (ok)
            $(".bio-info > i").on("click", function () {
                $(".talent-bio").addClass("-none");
                $(".taletn-bio-container").addClass("-none");
            })
        },
    }
})