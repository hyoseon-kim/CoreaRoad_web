/**
*** least.js 2
*** Author: Kamil Czujowski - @kamilczujowski
*** Version: 2.2.0
*** Made with ♥	in Hamburg, Germany.
*** http://kamilczujowski.de
**/

(function($){ 
	$.fn.least = function(options) {
		var settings = $.extend({
			'random': true,
			'scrollToGallery': true,
			'HiDPI': false
		}, options);

		return this.each(function() {

			/* Open Images */
			function intipreview(object, path, text, caption) {
				console.log('call');
				/!*var *!/
				var img = $('<img src="' + path + '"/>'),
					close=$('<figure class="close"></figure>'),
					thumb = $('li a'),
					imgTemplate = $('<div class="carouselGallery-modal"><span class="carouselGallery-left"><img src="/img/layer/left.png"/></span><span class="carouselGallery-right"><img src="/img/layer/right.png"/></span><div class="container"><span class="icons iconscircle-cross close-icon"></span><div class="carouselGallery-scrollbox" style="max-height: 871px;"><div class="carouselGallery-modal-image"><img src="" alt="carouselGallery image" class="_color_preview_img"></div><div class="carouselGallery-modal-text"><span class="carouselGallery-modal-username"><a href="https://instagram.com/p/92tWKsQVUN/">visitsweden</a> </span><span class="carouselGallery-modal-location"></span><span class="carouselGallery-item-modal-likes"><span class="icons icon-heart"></span><a href="https://instagram.com/p/92tWKsQVUN/">3939</a></span><span class="carouselGallery-modal-imagetext"><p>Photographer: @tannerstedtphotography Location: Resm A perfect place for stargazing.Tag #visitsweden and #swedishmoments for a chance to get featured. //@deskriptiv</p></span></div></div></div></div>');


				img.load(
					function() {
						if ( caption.length ) {
							object.html('')
						} else {
							object.html('');
						}


						object
							.prepend(imgTemplate)
							.append(close)
							thumb.removeClass('load');
						$('._color_preview_img').attr('src',path);
						object.slideDown('slow');
					}
				);
				
				/* Close Fullscreen */		
				close.on(
					'click',
					function() {
						$('.least-preview').slideToggle('slow');
						thumb.removeClass('active');
					}
				);
			}

			/* Thumbnail */
			$(this).find('li a').click(
				function(e) {

					/* var */
					var $$ = $(this),
						path = $$.attr('href'),
						text = $$.attr('data-text'),
						preview = $('.least-preview'),
						previewImg = preview.children('img'),
						caption = $$.attr('data-caption') || '';

					/* Same Image */
					if ( /*previewImg.length && path === previewImg.attr('src')*/false ) {
						preview.slideToggle('slow');

						$$.toggleClass('active');
							
						return;
					}

					/* Other Image */
					if ( /*previewImg.length*/true ) {

						$$.addClass('active');
						$('.least-gallery li a.active').removeClass('active');

						preview.slideUp(
							'slow',
							function() {
								intipreview(
									preview,
									path,
									text,
									caption
								);
							}
						);

					/* First Image */
					} else {
						intipreview(
							preview,
							path,
							caption
						);
					}

					/* Add Loading bar */
					$$.addClass('load active');
				}
			);
			
			/* Random Images - looked up from jquery forum */
			if(settings.random) {
				$('.least-gallery').each(function(){
					var ul = $(this),
						li = ul.children('li');
						
						li.sort(function() {
							var	temp = parseInt( Math.random()*8, null ),
								OddEven = temp%4,
								PosNeg = temp>2 ? 1 : -1;
								
								return ( OddEven*PosNeg );
						})
						.appendTo(ul);
				});
			}

			/* Scroll to Top */
			if(settings.scrollToGallery) {
				$(this).find('li a').click(
					function(e) {
						e.preventDefault();

						$('html, body').animate(
							{ 
								scrollTop: $('#least').offset().top
							}, 500 
						);
					}
				);
			}

			/* Support Retina Image - Inspiration https://bensmann.no */
			if(settings.HiDPI) {
				if(window.devicePixelRatio > 1) {
					/* var's */
					var image_thumb = $('#least img'),
						image_big = $('#least a');

					/* Replace images with @2x */
					for(var i = 0; i < image_thumb.length && image_big.length; i++) {
						var src = image_thumb[i].src,
							href = image_big[i].href,
							j = src.lastIndexOf('.'),
							k = href.lastIndexOf('.');

							src = src.substr(0,j) + '@2x' + src.substr(j);
							href = href.substr(0,k) + '@2x' + href.substr(k);

							image_thumb[i].src = src;
							image_big[i].href = href;
					}
				}
			}
		});
	};
})(jQuery);