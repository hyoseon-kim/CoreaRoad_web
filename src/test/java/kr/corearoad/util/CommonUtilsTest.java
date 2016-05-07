package kr.corearoad.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by Naver on 2016-05-07.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:mvc-dispatcher-servlet.xml"})
public class CommonUtilsTest {

    @Test
    public void test() {
        String str = "['chicken1.jpg', 'chicken2.PNG', 'chicken3.jpg']";
        System.out.println(CommonUtils.returnStringArrayFromString(str));
    }


}