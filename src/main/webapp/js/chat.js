define([],function () {

    var chatList = {};
    
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
            html.push('<a href="#chat/'+data.roomId+'" class="list-group-item" data-value="'+data.roomId+'">');
            html.push('<img src="http://bootdey.com/img/Content/avatar/avatar1.png" alt="User name">');
            html.push('<span class="roomName chat_font">'+data.roomName+'</span>');
            html.push('<span class="roomUserCount chat_font">('+data.roomUserCount+')</span>');
            html.push('<button type="button" class="close" data-dismiss="alert"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></button>');
            html.push('</a>');
            chatList[data.roomId] = data;
        });
        
        $('.chat_room_instance').html(html.join(''));
    }
    
    function _attachEvent() {
        $('.list-group-item').on('click', function (oEvent) {
            var target = oEvent.target;
            var roomId = $(target).attr('data-value');

            $('.title').html(chatList[roomId].roomName);
            $('.chat_room_list').hide();
            $('.chat_messenger_main').show();
            _setChatMessengerMainView(roomId);
        })
    }
    
    function _setChatMessengerMainView(roomId) {

        $.each(chatList[roomId].roomUserList, function (nIndex, data) {
            $("#chatUserList").append(' <div class="user" id="'+data+'"><div class="avatar"><img src="http://bootdey.com/img/Content/avatar/avatar1.png" alt="User name"><div class="status off"></div> </div> <div class="name">'+data+'</div> <div class="mood"></div> </div>');
        });

        $.ajax({
            method: 'GET',
            url: '/getAllMessengersByRoomId.do',
            data: {roomId: roomId}
        }).done(function (oData) {
            var result = $.parseJSON(oData);
            var userEmail = $("#userEmail");

            $.each(result, function (nIndex, data) {
                if(data.messengerSenderUserId != userEmail) {
                    $("#chatMsgList").append('<div class="answer left"><div class="avatar"><img src="http://bootdey.com/img/Content/avatar/avatar1.png" alt="User name"><div class="status offline"></div></div><div class="name">'+data.messengerSendUserId+'</div><div class="text">'+data.messengerContent+'</div><div class="time">'+data.meesengerSendTime+'</div></div>');
                } else {
                    $("#chatMsgList").append('<div class="answer right"><div class="avatar"><img src="http://bootdey.com/img/Content/avatar/avatar1.png" alt="User name"><div class="status offline"></div></div><div class="name">'+data.messengerSendUserId+'</div><div class="text">'+data.messengerContent+'</div><div class="time">'+data.meesengerSendTime+'</div></div>');
                }
            });
        });
    }

    return {
        init: _init
    }
})
