/**
 * Created by Naver on 2016-05-08.
 */
define([
    'text!/program.html',
    'text!/signUpForm.html',
    'text!/aboutUs.html'
],function (_welProgram, _welSignUp, _welAboutUs) {
    var _welContentArea = $('._corearoad_content');
    function init() {
        attachEvent();
    }

    function attachEvent() {
        //gallery open
        $('#menu_activities').on('click', function () {
            _welContentArea.html(_welProgram);
        });
        $('#menu_aboutUs').on('click', function () {
            _welContentArea.html(_welAboutUs);
        });

        $('._sign_up_btn').on('click', function () {
            $('#login').foundation('close');
            _welContentArea.html(_welSignUp);

            var elem = new Foundation.Abide($('._sign_up_form'),{});
            $('._sign_up_form').foundation('requiredCheck', $('_sign_up_form'));
            $('._add_lang').on('click', function (we) {
                if($('.capableLang2Display').css('display') == 'none') {
                    $('.capableLang2Display').show();
                } else {
                    $('.capableLang3Display').show();
                    $('._add_lang').hide();
                }
            });

            $('._check_id').on('click', function (we) {
                $.ajax({
                        method: 'GET',
                        url:'/checkId.do',
                        data: {email: $('#signup_email').val()}
                    })
                    .done(function (oData) {
                        if(oData == 'exist') {
                            alert('already existed Id!');
                        } else {
                            alert('You can use this id');
                        }
                    });
            });
        });
    }
    return {
        init: init
    };
});