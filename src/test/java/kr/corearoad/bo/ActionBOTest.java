package kr.corearoad.bo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Naver on 2016-05-05.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:mvc-dispatcher-servlet.xml"})
public class ActionBOTest {

    @Autowired
    ActionBO actionBO;

    @Test
    public void getAction() throws Exception {
        System.out.println(actionBO.getAction("1"));
    }

    @Test
    public void getPictureList() throws Exception {
        List<Map<String,String>> retList = actionBO.getMainPictureList();
        System.out.println(retList);
    }

}