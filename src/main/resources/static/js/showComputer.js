
$(document).ready(function () {
    $('.popup').click(function () {
        let src = $(this).attr('src')
        $('.modal-img').modal("show")
        $('#popup-img').attr('src', src)
    })

})