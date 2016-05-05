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
        List<Map<String,String>> retList = new ArrayList<Map<String,String>>();
        List<Map<String,String>> picList = actionBO.getPictureList();
        for(Map<String,String> pic : picList) {
            Map<String,String> map = new HashMap<String, String>();
            String strPicture = pic.get("act_pictureList").split("\\[")[1].split("\\]")[0];
            String[] tempPictureList = strPicture.split(",");
            map.put("url", tempPictureList[0].trim().replaceAll("'",""));
            map.put("no", pic.get("act_no"));
            retList.add(map);
        }
        System.out.println(retList);
    }

}