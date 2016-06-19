package kr.corearoad.mapper;

import kr.corearoad.bean.Action;
import kr.corearoad.bean.ChatMessanger;
import kr.corearoad.bean.ChatRoom;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Naver on 2016-04-07.
 */
public interface ChatInterface {

    ChatRoom getChatRoom(String id) throws SQLException;

    ChatMessanger getChatMessnger(String id) throws SQLException;

    List getAllChatMessengerByRoomId(String id) throws SQLException;

    List getAllCharRoomByUserId(String email) throws SQLException;
}
