/**
 * Created by preme on 2016-06-19.
 */
define([],function () {

    function _init() {
        _attachEvent();



        /*card 공유 버튼*/
        $('.card__share > a').on('click', function(e){
            e.preventDefault() // prevent default action - hash doesn't appear in url
            $(this).parent().find( 'div' ).toggleClass( 'card__social--active' );
            $(this).toggleClass('share-expanded');
        });

    }
    
    function _attachEvent() {
        $('.coreaPicks_search_button').on('click', function (oEvent) {
        picksButtonClickEvent(oEvent);
        });
        
    }
    /*searchbox on/off*/
    function picksButtonClickEvent(we){
        if($(we.target).hasClass('on')){
            $(we.target).removeClass('on');
            $(we.target).addClass('off');

            //이미 off일때는 class > on
        } else {
            $(we.target).removeClass('off');
            $(we.target).addClass('on');
        }
    }
    /*back to top*/
    function backToTop() {
        if ($('#back-to-top').length) {
            var scrollTrigger = 100, // px
                backToTop = function () {
                    var scrollTop = $(window).scrollTop();
                    if (scrollTop > scrollTrigger) {
                        $('#back-to-top').addClass('show');
                    } else {
                        $('#back-to-top').removeClass('show');
                    }
                };
            backToTop();
            $(window).on('scroll', function () {
                backToTop();
            });
            $('#back-to-top').on('click', function (e) {
                e.preventDefault();
                $('html,body').animate({
                    scrollTop: 0
                }, 700);
            });
        }
    }

    return {
        init: _init
    }



});

