package kr.corearoad.bo;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import jdk.nashorn.internal.parser.JSONParser;
import kr.corearoad.bean.ChatMessanger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

/**
 * Created by Naver on 2016-06-19.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:mvc-dispatcher-servlet.xml"})
public class ChatBOTest {
    public static final String SEND_USER_ID = "hyos810@naver.com";
    /*@Autowired
    ChatBO sut;*/


    /*@Test
    public void test() throws Exception {
        System.out.println(sut.getAllCharRoomByUserId(SEND_USER_ID));
    }

    @Test
    public void insertTest() throws Exception {
        ChatMessanger chatMessanger = new ChatMessanger();
        chatMessanger.setMessengerRoomId("1000");
        chatMessanger.setMessengerReadCount(Integer.toString(0));
        chatMessanger.setMessengerContent("Test");
        chatMessanger.setMessengerSendUserId(SEND_USER_ID);
    }*/

    @Test
    public void jsonTest() {
        String str = "{\"setId\":\"hyos810@naver.com\"}";

        Gson gson = new GsonBuilder().create();

        System.out.println(gson.fromJson(str, Map.class).get("setId"));
    }
}
