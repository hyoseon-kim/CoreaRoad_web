/**
 * Created by Naver on 2016-03-14.
 */
'use strict';

require.config({
    baseUrl:'./js',
    shim: {
    },
    paths: {
        jquery: 'vendor/jquery.min',
        foundation: 'vendor/foundation.min',
    }
});

require([
    'jquery',
    'foundation',
    'vision'
], function($, foundation, vision){
    $(document).foundation();
    $(document).ready(function(){
        $('a[href^="#"]').on('click',function (e) {
            e.preventDefault();

            var target = this.hash;
            var $target = $(target);

            $('html, body').stop().animate({
                'scrollTop': $target.offset().top - $('.top-bar').height()
            }, 900, 'swing', function () {
                window.location.hash = target;
            });
        });
    });
    $('.vision').on('click', function(we){
        $('.menu').animate({
            scrollTop: $( $.attr(this, 'href') ).offset().top
        }, 500);
        return false;
    });

    $(window).scroll(function(){
        if ($(this).scrollTop() > 125) {
            $('.top-bar').addClass("_scroll");
        } else {
            $('top-bar').removeClass("_scroll");
        }
    });
});