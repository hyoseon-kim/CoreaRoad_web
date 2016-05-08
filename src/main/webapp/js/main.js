/**
 * Created by Naver on 2016-03-14.
 */
'use strict';

require.config();
require(['jquery'], function ($) {
	require([
		'foundation',
		'header'
	], function(foundation, header){
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
			header.init();
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

			

			$("._login_btn").on('click', function () {
				$('#login').foundation('open');
			});

			$("._login_close").on('click', function () {
				$('#login').foundation('close');
			})
		}
	});
});

