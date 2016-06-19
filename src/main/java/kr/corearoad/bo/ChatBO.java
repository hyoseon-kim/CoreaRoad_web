package kr.corearoad.bo;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sun.xml.internal.ws.developer.Serialization;
import com.sun.xml.internal.ws.runtime.config.ObjectFactory;
import kr.corearoad.bean.ChatMessanger;
import kr.corearoad.bean.ChatRoom;
import kr.corearoad.dao.ChatDAO;
import kr.corearoad.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by Naver on 2016-06-19.
 */
@Service
public class ChatBO {
    public static final String ROOM_ID = "roomId";
    public static final String ROOM_NAME = "roomName";
    public static final String ROOM_COUNT = "roomUserCount";
    public static final String ROOM_USER_LIST = "roomUserList";
    public static final String ROOM_CREATE_DATE_TIME = "roomCreateDateTime";
    public static final String MESSENGER_ID = "messengerId";
    public static final String MEESENGER_SEND_TIME = "meesengerSendTime";
    public static final String MESSENGER_SEND_USER_ID = "messengerSendUserId";
    public static final String MESSENGER_CONTENT = "messengerContent";
    public static final String MESSENGER_READ_COUNT = "messengerReadCount";
    @Autowired
    ChatDAO chatDAO;

    private static Logger logger = LoggerFactory.getLogger(ChatBO.class);

    public Map<String,Object> getChatRoom(String id) {
        Map returnMap = Maps.newHashMap();
        ChatRoom chatRoom = new ChatRoom();

        try {
            chatRoom = chatDAO.getChatRoom(id);
        } catch (SQLException e) {
            logger.error("chat room select error: %s" , id);
            return returnMap;
        }

        getChatRoomMap(returnMap, chatRoom);

        return returnMap;
    }

    private void getChatRoomMap(Map returnMap, ChatRoom chatRoom) {
        returnMap.put(ROOM_ID, chatRoom.getRoomId());
        returnMap.put(ROOM_NAME, chatRoom.getRoomName());
        returnMap.put(ROOM_COUNT, chatRoom.getRoomUserCount());
        returnMap.put(ROOM_USER_LIST, CommonUtils.returnStringArrayFromString(chatRoom.getRoomUserList()));
        returnMap.put(ROOM_CREATE_DATE_TIME, chatRoom.getRoomCreateDateTime());
    }

    public Map<String, Object> getChatMessenger(String id) {
        Map returnMap = Maps.newHashMap();
        //지금 당장 필요없으므로 구현 Skip 하겠음.
        return returnMap;
    }

    public List<Map<String,Object>> getAllChatMessengerByRoomId(String id) {
        List<Map<String,Object>> returnList = Lists.newArrayList();
        List<ChatMessanger> getMessengerList = Lists.newArrayList();
        try {
            getMessengerList=  (List<ChatMessanger>)chatDAO.getAllChatMessengerByRoomId(id);
        } catch (SQLException e) {
            logger.error("get all chat messenger by room id error: %s", id);
        }

        for (ChatMessanger room : getMessengerList) {
            Map<String,Object> map = Maps.newHashMap();
            map.put(MESSENGER_ID, room.getMessengerId());
            map.put(MEESENGER_SEND_TIME, room.getMessengerSendTime());
            map.put(MESSENGER_SEND_USER_ID, room.getMessengerSendUserId());
            map.put(MESSENGER_CONTENT, room.getMessengerContent());
            map.put(MESSENGER_READ_COUNT, room.getMessengerReadCount());
            returnList.add(map);
        }

        return returnList;
    }

    public  List<Map<String,Object>> getAllCharRoomByUserId(String email) {
        List<Map<String,Object>> returnList = Lists.newArrayList();
        List<ChatRoom> chatRoomList = Lists.newArrayList();
        try {
            chatRoomList = chatDAO.getAllCharRoomByUserId(email);

        } catch (SQLException e) {
           logger.error("get All chat Room By userId error : %s" , email);
            return returnList;
        }

        for (ChatRoom chatRoomListMap : chatRoomList) {
            Map<String, Object> map = Maps.newHashMap();
            getChatRoomMap(map, chatRoomListMap);
            returnList.add(map);
        }

        return returnList;
    }
}
