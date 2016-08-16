package kr.corearoad.dao.picks;

import com.google.common.collect.Maps;
import kr.corearoad.bo.picks.FoodShopBO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Naver on 2016-08-15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:mvc-dispatcher-servlet.xml"})
public class FoodShopDAOTest {
    
    @Autowired
    FoodShopBO foodShopBO;

    @Test
    public void name() throws Exception {
        Map map = Maps.newHashMap();
        map.put("lang","en");
        map.put("name", "해리");

        System.out.println(foodShopBO.getFoodShopListBySearch(map));

    }
}