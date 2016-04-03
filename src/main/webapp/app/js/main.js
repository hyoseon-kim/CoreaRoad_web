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
        $('.menu_title').on('click',function (e) {
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
            $('.top-bar').removeClass("_scroll");
        }
    });
});


function openLayer(targetID, options){
	var $layer = $('#'+targetID);
	var $close = $layer.find('.close');
	var width = $layer.outerWidth();
	var ypos = options.top;
	var xpos = options.left;
	var scrollTop = $(window).scrollTop();
	var marginLeft = 0;
	var maskHeight = $(document).height();  
    var maskWidth = $(window).width();
	
	if(xpos==undefined){
		xpos = '50%';
		marginLeft = -(width/2);
	}

	if(!$layer.is(':visible')){
		$layer.css({'top':ypos+scrollTop+'px','left':xpos,'margin-left':marginLeft,'z-index':1000,'margin':'10%'}).show();	
		$('.popup_background').css({'width':maskWidth,'height':maskHeight}).show();  
		$(document).on("mousewheel.disableScroll DOMMouseScroll.disableScroll touchmove.disableScroll", function(e) {
	        e.preventDefault();
	        return;
	    });
	    $(document).on("keydown.disableScroll", function(e) {
	        var eventKeyArray = [32, 33, 34, 35, 36, 37, 38, 39, 40];
	        for (var i = 0; i < eventKeyArray.length; i++) {
	            if (e.keyCode === eventKeyArray [i]) {
	                e.preventDefault();
	                return;
	            }
	        }
	    });		
		console.log("팝업창이 뜹니다.");
	}

	$close.bind('click',function(){
		if($layer.is(':visible')){
			$layer.hide();
			$('.popup_background').hide();
			$(document).off(".disableScroll");
		}
		return false;
	});
}


