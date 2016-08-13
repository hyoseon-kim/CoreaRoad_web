package kr.corearoad.dao;

import kr.corearoad.bean.CoreaPicks;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by Naver on 2016-08-02.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:mvc-dispatcher-servlet.xml"})
public class CoreaPicksDAOTest {

    @Autowired
    CoreaPicksDAO coreaPicksDAO;

    @Test
    public void selectTopCoreaPicks() throws Exception {
        System.out.println(coreaPicksDAO.selectTopCoreaPicks());
    }

    @Test
    public void selectCoreaPicksWithSearchCondition() throws Exception {
        CoreaPicks coreaPicks = new CoreaPicks();
        coreaPicks.setCategory("food");
        coreaPicks.setTagList("pork");
        coreaPicks.setStartPrice(15000);
        coreaPicks.setEndPrice(18000);
        System.out.println(coreaPicksDAO.selectCoreaPicksWithSearchCondition(coreaPicks));
    }

    @Test
    public void insertCoreaPicks() throws Exception {
        CoreaPicks coreaPicks = new CoreaPicks();
        coreaPicks.setCategory("activity");
        coreaPicks.setStartPrice(5000);
        coreaPicks.setEndPrice(10000);
        coreaPicks.setTagList("['activity','hangang','chimak']");
        coreaPicks.setTitle("Chimak & Hangang");
        coreaPicks.setContent("Are You Ready? <br> Jingermans first Post!");
        coreaPicks.setIsSponsor(false);
        coreaPicks.setMap("126.9753562/37.5599758");
        coreaPicks.setMainPicture("img/eat.jpg");
        coreaPicks.setWriterEmail("hyos810@naver.com");

        coreaPicksDAO.insertCoreaPicks(coreaPicks);
    }

}