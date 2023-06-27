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
            previousCitySelection: [], // 用于存储上一次的城市选择状态
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
            // 检查已存在的区域名称并移除
            this.multipleChecked = this.multipleChecked.filter(item => !selectDistrict.some(district => district.districtName === item));
            this.selectedCityId = index; // update the selected city ID

        },
        fetchDistricts(index, cityName){
            const selectDistrict = this.districtsData.filter(item => item.cityId === index); //要回傳整個districts資料要用filter
            this.defaultDistrict = selectDistrict;
            this.selected = index-1;
            this.selectedCityName = this.citiesData[index-1];
        },
        // fetchDistricts(index, cityName){
        //     const selectDistrict = this.districtsData.filter(item => item.cityId === index); //要回傳整個districts資料要用filter
        //     this.defaultDistrict = selectDistrict;
        //     this.selectedCityName = cityName;
        //
        //     if (this.multipleChecked.length < this.maxSelection) {
        //         this.multipleChecked.push(cityName);
        //     }
        // },
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
        // removeSelected(index) {
        //     this.multipleChecked.splice(index, 1); // 从 multipleChecked 中移除指定索引的元素
        // },
        removeSelected(index) {
            const cityName = this.multipleChecked[index];
            const cityIndex = this.firstLayerCity.indexOf(cityName);
            if (cityIndex > -1) {
                this.firstLayerCity.splice(cityIndex, 1); // 从 firstLayerCity 中移除对应的城市
            }
            this.multipleChecked.splice(index, 1); // 从 multipleChecked 中移除对应的城市
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
            // // 检查第一层城市是否被选中
            // const isCityChecked = this.firstLayerCity.length > 0;
            // alert("ischecked:" + isCityChecked)
            // // 检查是否有与选定城市不相关的区域复选框被选中
            // const isRelatedDistrictChecked = this.multipleChecked.some(item => {
            //     const city = this.defaultDistrict.find(district => district.districtName === item);
            //     alert(city && city.cityName !== this.firstLayerCityIndex)
            //     return city && city.cityName !== this.firstLayerCityIndex;
            // });
            //
            // // 返回两个条件的逻辑与运算结果
            // return isCityChecked && !isRelatedDistrictChecked;
        },
        // isDistrictRelatedToSelectedCity() {
        //     return (district) => {
        //         // 首先確保有一個被選中的城市
        //         if (!this.selectedCityName) return false;
        //
        //         // 找到城市對應的 id
        //         const cityId = this.citiesData.find(city => city.cityName === this.selectedCityName).id;
        //
        //         // 檢查區域是否與城市相關
        //         return district.cityId === cityId;
        //     };
        // },
        isDistrictRelatedToSelectedCity() {
            return (district) => {
                // 首先确保有一个被选中的城市
                // if (!this.firstLayerCity.length) return false;

                // 检查区域是否与任一选中的城市相相关
                alert("come?")
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

            // 移除旧的城市名称
            this.multipleChecked = this.multipleChecked.filter(item => !this.previousCitySelection.includes(item));

            if (isAddingCity) {
                // 添加城市
                if (this.multipleChecked.length < this.maxSelection) {
                    // 添加新的城市名称
                    this.multipleChecked = [...this.multipleChecked, ...this.firstLayerCity];

                }
            } else {
                // 取消城市
                const cityIndex = this.multipleChecked.indexOf(newVal);
                if (cityIndex > -1) {
                    this.multipleChecked.splice(cityIndex, 1);
                }
            }

            // 更新上一次的城市选择状态
            this.previousCitySelection = [...this.firstLayerCity];
        },
    }


})
// ====================================================================================
// ====================================================================================
// ====================================================================================
// new Vue({
//     el:'.job-category-body',
//     data() {
//         return{
//             positionTypeData: [],
//             namesData:[],
//             selected: null,
//             chosenType: '',  // 將選中的category.name 渲染給右邊第一層中
//         }
//     },
//     mounted(){
//         axios.get('/SeekerPool/positionType')
//             .then(res => {
//                 if(res.data.code){
//                     this.positionTypeData = res.data.data;
//                     this.selected = 0;
//                     this.fetchName(this.positionTypeData[0].ptNo) //查第一個來顯示右邊那排對應的職務名稱
//                 }
//             } )
//     },
//     methods:{
//         fetchName: function (id) {
//             axios.get(`/SeekerPool/positionType/${id}`)
//                 .then(response => {
//                     console.log(response);
//                     this.namesData = response.data.data;
//                 }).catch(error => {
//                 console.error(error);
//             });
//         },
//         getClass(index) {
//             return {
//                 '-chosen': this.selected === index,
//                 'original-class': true
//             };
//         },
//         selectType(index) { // 將選中的city.name 渲染給右邊第一層中
//             this.selected = index;
//             this.chosenType = this.positionTypeData[index];
//         },
//     },
//     computed:{
//         // 讓從後端傳到這裡的position 的 ptType 裡的資料不重複
//         uniqueTypes() {
//             const typesSet = new Set();
//             this.positionTypeData.forEach(job => {
//                 typesSet.add(job.ptType);
//             });
//             return Array.from(typesSet);
//         },
//     },
//
// })

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
                return alert("查詢不得為空");
            } else if(this.keyword !=='' && this.jobLocation === '') {
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

            } else {
                this.jobLocationArray = this.jobLocation.split(",");
                axios.get(`/SeekerPool/talent/area/${this.jobLocationArray}`)
                    .then(res => {
                        if (res.data.data.length >0){ // 用length來判斷是否有資料回傳
                            // 動態生成 URL
                            this.url = `../html/TalentList.html?areas=${encodeURIComponent(this.jobLocationArray)}`;
                            window.location.href = this.url;
                        }else {
                            alert("關鍵字找不到喔!")
                        }
                    });
            }
        },

        openCountryForm(){
            $('.country-category-list').removeClass('-none').css('z-index', '22');
            $('.country-category-container').removeClass('-none').css('z-index', '22');
        },
    },
    watch: {

    },
})