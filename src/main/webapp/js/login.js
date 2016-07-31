/**
 * Created by Naver on 2016-07-30.
 */
define([
],function () {

    function _init() {
        $("._login_btn").on('click', function (evt) {

            console.log($('.email').val());
            
            $.ajax({
                method: 'POST',
                url:'/login.do',
                data: {
                    email: $('.email').val(),
                    password: $('.password').val()
                }
            }).done(function (oData) {
                location.reload();
            })
        })
    }

    return {
        init : _init
    }
});