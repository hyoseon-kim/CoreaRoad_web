package kr.corearoad.bo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Naver on 2016-06-19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:mvc-dispatcher-servlet.xml"})
public class ChatBOTest {
    @Autowired
    ChatBO sut;

    @Test
    public void test() throws Exception {
        System.out.println(sut.getAllCharRoomByUserId("hyos810@naver.com"));
    }
}
