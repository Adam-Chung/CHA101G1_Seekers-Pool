
        // 使用AJAX從服務器獲取資料
        $.ajax({
            url: '/newsletter', 
            method: 'GET',
            dataType: 'json',
            success: function (response) {
                // 成功獲取數據後更新頁面內容
                $('#nlTitle').text(response.NL_TITLE);
                $('#nlContent').text(response.NL_CONTENT);
            },
            error: function () {
                // 錯誤處理
                console.log('Failed to fetch data.');
            }
        });
