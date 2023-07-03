// ================================================  職務類別  =============================================================
new Vue({
    el:'.country-category-list',
    data(){
        return{
            citiesData: [], // 接收資料庫來的全部縣市資料
            districtsData: [],  // 根據點擊縣市的id來接收資料庫來的對應鄉鎮市資料
            selected: null, // 當被city點擊時，添加class 這樣才能套用到對應的css
            firstLayerCity: [], //右邊第一層city name
            selectedCityName: '', // 右邊第一層的checkbox的名子會跟隨被點擊的city而更換(預設是id=1的 台北市)
            multipleChecked : [], // 裝被選擇的city and district
            defaultDistrict : [],
            selectedCityId: null, // 當被city點擊時，保存對應的ID
            maxSelection: 4, // 最大選擇數量
            previousCitySelection: [], // 用於儲存上一次的城市選擇狀態
        }
    },
    mounted(){
        axios.all([
            axios.get('/SeekerPool/cities'),
            axios.get('/SeekerPool/districts'),
        ]).then(axios.spread((resCities , resDistricts) => {
            this.citiesData = resCities.data.data;
            this.districtsData = resDistricts.data.data;
            this.selected = 0;
            this.fetchDistricts(1)// 預設地一個(台北市 id = 1)
        } ))
    },
    methods: {
        toggleCity(id) {
            // 找對應id
            let index = this.citiesData.find(item => item.id === id)
            index = index.id;
            const selectDistrict = this.districtsData.filter(item => item.cityId === index); //要回傳整個districts資料要用filter
            // 检查已存在的區域名稱并移除
            this.multipleChecked = this.multipleChecked.filter(item => !selectDistrict.some(district => district.districtName === item));
            this.selectedCityId = index; // update the selected city ID

        },
        fetchDistricts(index, cityName){
            const selectDistrict = this.districtsData.filter(item => item.cityId === index); //要回傳整個districts資料要用filter
            this.defaultDistrict = selectDistrict;
            this.selected = index-1;
            this.selectedCityName = this.citiesData[index-1];
        },
        getClass(index) {  //yes
            return {
                '-chosen': this.selected === index,
                'original-class': true
            };
        },
        sendCountryData(){  //yes
            // 關閉地區表單
            $('.country-category-list').addClass('-none').css('z-index', '-1');
            $('.country-category-container').addClass('-none').css('z-index', '-1');

            // 儲存選中地區值到 localStorage
            localStorage.setItem('multipleChecked', this.multipleChecked);
            window.dispatchEvent(new CustomEvent('localstorage-changed', {
                detail: {
                    storage: localStorage.getItem('multipleChecked')
                }
            }));

        },
        removeSelected(index) {
            const cityName = this.multipleChecked[index];
            const cityIndex = this.firstLayerCity.indexOf(cityName);
            if (cityIndex > -1) {
                this.firstLayerCity.splice(cityIndex, 1); // 從 firstLayerCity 中移除對應的城市
            }
            this.multipleChecked.splice(index, 1); // 從 multipleChecked 中移除對應的城市
        },
        goBack(){ // yes
            var $countryCategoryList = $(".country-category-list");
            $countryCategoryList.addClass("-none");
            $countryCategoryList.css({ zIndex: -11 });
        }
    },
    computed: {
        isMaxSelectionReached() {
            return this.multipleChecked.length >= this.maxSelection;
        },
        isFirstLayerCityChecked() {
            return this.firstLayerCity.length >0
        },
        isDistrictRelatedToSelectedCity() {
            return (district) => {
                // 檢查區域是否與任一選中的城市相關
                return this.firstLayerCity.some(cityName => {
                    const cityId = this.citiesData.find(city => city.cityName === cityName).id;
                    return district.cityId === cityId;
                });
            };
        },


    },
    watch: {

        firstLayerCity(newVal) {
            // 判断是添加城市還是取消城市
            const isAddingCity = !this.previousCitySelection.includes(newVal);

            // 移除舊的城市名稱
            this.multipleChecked = this.multipleChecked.filter(item => !this.previousCitySelection.includes(item));

            if (isAddingCity) {
                // 添加城市
                if (this.multipleChecked.length < this.maxSelection) {
                    // 添加新的城市名稱
                    this.multipleChecked = [...this.multipleChecked, ...this.firstLayerCity];

                }
            } else {
                // 取消城市
                const cityIndex = this.multipleChecked.indexOf(newVal);
                if (cityIndex > -1) {
                    this.multipleChecked.splice(cityIndex, 1);
                }
            }

            // 更新上一次的城市選擇狀態
            this.previousCitySelection = [...this.firstLayerCity];
        },
    }


})
// ====================================================================================
// ====================================================================================

new Vue({
    el:'.talent-search-form',
    data:{
        keyword: '',
        jobLocation: '',
        jobLocationArray:[],
        url:'',
    },
    mounted() {
        // 从 localStorage 中获取数据
        window.addEventListener('localstorage-changed', (event) => {
            this.jobLocation = event.detail.storage;
        });

    },
    methods:{
        search(){
            if(this.keyword ==='' && this.jobLocation === ''){
                return Swal.fire({
                    title: '不能空白喔!',
                    text: '試試看加個關鍵字吧!',
                    icon: 'warning',
                    timer: 2000
                })
            } else if(this.keyword !=='' && this.jobLocation === '') {
                axios.get(`/SeekerPool/talent/${this.keyword}`)
                    .then(res => {
                        if (res.data.data.rows.length >0){ // 用length來判斷是否有資料回傳
                            // 動態生成 URL , 用 "?keyword=參數" 的方式把參數帶給../html/TalentList.html
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

            } else {
                this.jobLocationArray = this.jobLocation.split(",");
                axios.get(`/SeekerPool/talent/area/${this.jobLocationArray}`)
                    .then(res => {
                        if (res.data.data.rows.length >0){ // 用length來判斷是否有資料回傳
                            // 動態生成 URL
                            this.url = `../html/TalentList.html?areas=${encodeURIComponent(this.jobLocationArray)}`;
                            window.location.href = this.url;
                        }else {
                            Swal.fire({
                                title: '抱歉沒有相關的人才...',
                                text: '換個地點試試看吧!',
                                icon: 'warning',
                                timer: 2300
                            })
                        }
                    });
            }
        },
        reset(){
            this.keyword='';
            this.jobLocation='';
        },
        openCountryForm(){
            $('.country-category-list').removeClass('-none').css('z-index', '22');
            $('.country-category-container').removeClass('-none').css('z-index', '22');
        },
    },
    watch: {
        'jobLocation'(){
            if(this.jobLocation !== ''){
                this.keyword = '';
            }
        }
    },
})