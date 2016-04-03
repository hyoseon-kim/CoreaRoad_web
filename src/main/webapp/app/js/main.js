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
            $('.top-bar').removeClass("_scroll");
        }
    });
});

<!--popuplayer contents-->

function openLayer(targetID, options){
	var $layer = $('#'+targetID);
	var $close = $layer.find('.close');
	var width = $layer.outerWidth();
	var ypos = options.top;
	var xpos = options.left;
	var marginLeft = 0;
	
	if(xpos==undefined){
		xpos = '50%';
		marginLeft = -(width/2);
		console.log("ddddd");
	}

	if(!$layer.is(':visible')){
		$layer.css({'top':ypos+'px','left':xpos,'margin-left':marginLeft})
			.show();
	}

	$close.bind('click',function(){
		if($layer.is(':visible')){
			$layer.hide();
		}
		return false;
	});
}


