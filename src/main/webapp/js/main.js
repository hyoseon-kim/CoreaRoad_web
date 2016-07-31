/**
 * Created by Naver on 2016-03-14.
 */
'use strict';

require.config();
	require([
		'header'
	], function(header){

		var _welLoginBtnArea = null,
			_h1UserId = null,
			_welHeaderArea = $('._corearoad_header'),
			_welFooterArea = $('._corearoad_footer'),
			_welContentArea = $('._corearoad_content'),
			userEmail = null;

		//top bar animation initialize setting
		$(document).ready(function(){
			_includeTemplate();
			_getLoginSession();
			_attachEvent();
		});

		function _includeTemplate() {
			header.init();
			_welLoginBtnArea = $('._login_btn');
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
						$("#menu_login").hide();
						$("#menu_logout").show();
						$("#menu_signup").hide();
						//_h1UserId.html(result.email);
						userEmail = result;
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
			$("._login_btn").on('click', function () {
				//$('#login').foundation('open');
			});

			$("._side_bar").on('click', function () {
				$('.foreground_black').show();
				$('#sidebar').show();
			});
			
			$('._sidebar_close').on('click', function (evt) {
				$('.foreground_black').hide();
				$('#sidebar').hide();
			});
		}
	});


