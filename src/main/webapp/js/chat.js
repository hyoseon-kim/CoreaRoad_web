define([],function () {
    
    function _init() {
        $.ajax({
            method: 'GET',
            url: '/getAllChatRoomByUserId.do',
            data: {email: 'hyos810@naver.com'}
        }).done(function (oData) {
            var result = $.parseJSON(oData);
            _makeChatRoomHtml(result);
            _attachEvent();
        });
    }

    function _makeChatRoomHtml(result) {
        var html = [];
        $.each(result, function (nIndex, data) {
            html.push('<a href="#" class="list-group-item">');
            html.push('<img src="http://bootdey.com/img/Content/avatar/avatar1.png" alt="User name">');
            html.push('<span class="roomName chat_font">'+data.roomName+'</span>');
            html.push('<span class="roomUserCount chat_font">('+data.roomUserCount+')</span>');
            html.push('<button type="button" class="close" data-dismiss="alert"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></button>');
            html.push('</a>');
        });
        
        $('.chat_room_instance').html(html.join(''));
    }
    
    function _attachEvent() {
        $('.list-group-item').on('click', function (oEvent) {
            alert('click');
            $('.chat_messenger_main').show();
        })
        
    }

    return {
        init: _init
    }
})
