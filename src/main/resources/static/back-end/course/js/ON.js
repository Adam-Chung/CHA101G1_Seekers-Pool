// 付款方式
$(document).ready(function () {
    $('input[name="payment-method"]').on('change', function () {
        if ($(this).val() === 'bank-transfer') {
            $('#bank-account').show();
        } else {
            $('#bank-account').hide();
        }
    });
});
$(document).ready(function () {
    $('input[name="payment-method"]').on('change', function () {
        if ($(this).val() === 'credit-card') {
            $('#card-info').show();
        } else {
            $('#card-info').hide();
        }
    });
});

// 刪除訂單按鈕
$(document).ready(function () {
    $('.delete-order').click(function () {
        var confirmation = confirm("確定要刪除訂單嗎？");
        if (confirmation) {
            $(this).parent('.order-item').remove();
        }
    });
});
//  課程回覆
$(document).ready(function () {
    $('.reply-button').click(function () {
        var commentContent = $(this).closest('.comment-header').siblings('.comment-content');
        commentContent.slideToggle();
    });
});