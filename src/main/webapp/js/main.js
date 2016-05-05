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
		text:'vendor/text',
		least: 'vendor/least'
    }
});

require(['jquery'], function($){
	require([
		'foundation',
		'least',
		'text!/header.html',
		'text!/footer.html',
		'text!/content.html',
		'text!/program.html',
		'text!/signUpForm.html'
	], function(foundation, least, _welheader, _welFooter, _welContent, _welProgram, _welSignUp){
		var _welLoginBtnArea = null,
			_h1UserId = null,
			_welHeaderArea = $('._corearoad_header'),
			_welFooterArea = $('._corearoad_footer'),
			_welContentArea = $('._corearoad_content');

		//top bar animation initialize setting
		$(document).ready(function(){
			_includeTemplate();
			$(document).foundation();
			_getLoginSession();
			_attachEvent();
		});

		function _includeTemplate() {
			_welHeaderArea.html(_welheader);
			_welFooterArea.html(_welFooter);
			_welContentArea.html(_welContent);

			_welLoginBtnArea = $('._login_btn');
			_h1UserId = $('._user_id');
		}

		function _getLoginSession() {
			$.ajax('/getSession.do')
				.done(function (oData) {
					//login 시도했으나 실패
					if(oData == 'login fail') {
						_welLoginBtnArea.show();
						alert('Login Failed! please sign up.')

						//login시도 안하고 첫페이지로 진입
					} else if (oData == 'not a member') {
						_welLoginBtnArea.show();

						//login시도 후 성공
					} else {
						var result = $.parseJSON(oData);
						_welLoginBtnArea.hide();
						_h1UserId.html(result.name)
					}

				})
		}

		function _attachEvent() {
			$(window).scroll(function(){
				if ($(this).scrollTop() > 125) {
					$('.top-bar').addClass("_scroll");
				} else {
					$('.top-bar').removeClass("_scroll");
				}
			});

			//gallery open
			$('#menu_activities').on('click', function () {
				_welContentArea.html(_welProgram);
			});

			$("._login_btn").on('click', function () {
				$('#login').foundation('open');
			});
			
			$("._login_close").on('click', function () {
				$('#login').foundation('close');
			})

			$('._sign_up_btn').on('click', function () {
				$('#login').foundation('close');
				_welContentArea.html(_welSignUp);
				
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
			})
		}
	});
});


