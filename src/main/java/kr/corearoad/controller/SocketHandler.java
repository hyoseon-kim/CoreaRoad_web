
package kr.corearoad.controller;
import java.util.*;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import kr.corearoad.bean.ChatMessanger;
import kr.corearoad.bo.ChatBO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.servlet.http.HttpSession;



@Component
public class SocketHandler extends TextWebSocketHandler{

    @Autowired
    ChatBO chatBO;
    //세션을 모두 저장한다.
    //방법 1 :  1:1 채팅
//    private Map<String, WebSocketSession> sessions = new HashMap<String, WebSocketSession>();
    private Map<String, List<WebSocketSession>> chatSessionList = new HashMap<String, List<WebSocketSession>>();

    //방법 2 : 전체 채팅
    private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
    private static List<String> userOnlineList = new ArrayList<String>();


    private static Logger logger = LoggerFactory.getLogger(SocketHandler.class);

    /**
     * 클라이언트 연결 이후에 실행되는 메소드
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session)
            throws Exception {
        //맵을 쓸때 방법
        //chatSessionList.put(session.getId(), session);
        //List쓸때 방법
        //sessionList.add(session);
        //0번째 중괄호에 session.getId()을 넣으라는뜻
        logger.info("{} 연결됨", session.getId());

    }

    /**
     * 클라이언트가 웹소켓 서버로 메시지를 전송했을 때 실행되는 메소드
     */
    @Override
    protected void handleTextMessage(WebSocketSession session,
                                     TextMessage message) throws Exception {

        //0번째에 session.getId() 1번째에 message.getPayload() 넣음
        String payload = message.getPayload();

        logger.info("{}로 부터 {} 받음", session.getId(), payload);
        //    logger.info("{}로부터 {}받음", new String[]{session.getId(),message.getPayload()});

        Gson gson = new GsonBuilder().create();
        Map<String, String> map = gson.fromJson(payload, Map.class);

        String roomId = map.get("roomId");

        if(!chatSessionList.containsKey(roomId)) {
            chatSessionList.put(roomId, new ArrayList<WebSocketSession>());
        }

        if(!chatSessionList.get(roomId).contains(session)){
            chatSessionList.get(roomId).add(session);
        }

        if(map.containsKey("setId")) {

            for(WebSocketSession sess : chatSessionList.get(roomId)){
                sess.sendMessage(new TextMessage(String.format("{\"setId\": \"%s\", \"isOwner\": \"%s\"}",
                        map.get("setId"), sess.getId().equals(session.getId()))));
            }


        } else {

            //참석자들에게 메신저 내용 전파.
            for(WebSocketSession sess : chatSessionList.get(roomId)){
                sess.sendMessage(new TextMessage(String.format("{\"msg\": \"%s\", \"id\": \"%s\", \"isOwner\": \"%s\"}",
                        map.get("content"), map.get("id"), sess.getId().equals(session.getId()))));
            }
        }
        //연결된 모든 클라이언트에게 메시지 전송 : 리스트 방법


        // 맵 방법.
        /*Iterator<String> sessionIds = sessions.ketSet().iterator();
        String sessionId = "";
        while (sessionIds.hasNext()) {
            sessionId = sessionIds.next();
            sessions.get(sessionId).sendMessage(new TextMessage("echo:" + message.getPayload()));

        }*/

        //연결되어 있는 모든 클라이언트들에게 메시지를 전송한다.
//        session.sendMessage(new TextMessage("echo:" + message.getPayload()));
    }

    /**
     * 클라이언트 연결을 끊었을 때 실행되는 메소드
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session,
                                      CloseStatus status) throws Exception {
        //List 삭제
        sessionList.remove(session);

        //Map 삭제
//        sessions.remove(session.getId());

        logger.info("{} 연결 끊김.", session.getId());
        for(WebSocketSession sess : sessionList){
            sess.sendMessage(new TextMessage(String.format("{\"removeId\": \"%s\"}",
                    session.getId())));
        }
    }
}