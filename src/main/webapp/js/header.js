/**
 * Created by Naver on 2016-05-08.
 */
define([
    'text!/program.html',
    'text!/signUpForm.html',
    'text!/aboutUs.html',
    'text!/monthly.html',
    'text!/chatting.html',
    'text!/coreaPicks.html',
    'text!/login.html',
    'chat',
    'coreaPicks',
    'monthly',
    'program',
    'login'
],function (_welProgram, _welSignUp, _welAboutUs, _welMonthly, _welChatHtml, _welCoreaPicks, _welLogin, chat, coreaPicks, monthly, program, login) {
    var _welContentArea = $('._corearoad_content');
    function init() {
        attachEvent();
    }

    function attachEvent() {
        //gallery open
        $('#menu_activities').on('click', function (we, index) {
            closeMenu();

            //이미 on일 때는 class > off
            if($(we).hasClass('on')){
                $(we).removeClass('on');
                $(we).addClass('off');

                //이미 off일때는 class > on
            } else {
                $(we).removeClass('off');
                $(we).addClass('on');
            }
            $(we).addClass('on');
            _welContentArea.html(_welProgram);
            program.init(index);
        });
        $('#menu_aboutUs').on('click', function () {
            closeMenu();
            _welContentArea.html(_welAboutUs);
        });
        $('#menu_monthly').on('click', function () {
            closeMenu();
            _welContentArea.html(_welMonthly);
            monthly.init();
        });
        $('#menu_chat').on('click', function () {
            closeMenu();
            _welContentArea.html(_welChatHtml);
            chat.init();
        });
        $('#menu_coreaPicks').on('click', function () {
            closeMenu();
            _welContentArea.html(_welCoreaPicks);
            coreaPicks.init();
        });

        $("#menu_login").on('click', function () {
            closeMenu();
            _welContentArea.html(_welLogin);
            login.init();
        });

        $("#menu_logout").on("click", function () {
            closeMenu();
            $.ajax("/logout.do").done(function () {
                location.reload();
            })
        })

        $('#menu_signup').on('click', function () {
            closeMenu();
            //$('#login').foundation('close');
            _welContentArea.html(_welSignUp);

           //var elem = new Foundation.Abide($('._sign_up_form'),{});
            //$('._sign_up_form').foundation('requiredCheck', $('._sign_up_form'));
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
    
    function closeMenu() {
        var nav = $('.navbar-toggle');

        if(nav.css('display') !== 'none') {
            nav.click();
        }
    }
    return {
        init: init
    };
});