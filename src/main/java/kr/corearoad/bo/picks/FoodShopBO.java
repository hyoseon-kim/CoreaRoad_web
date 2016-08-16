package kr.corearoad.bo.picks;

import com.google.common.collect.Lists;
import kr.corearoad.bean.picks.FoodShop;
import kr.corearoad.dao.picks.FoodShopDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Naver on 2016-08-15.
 */
@Service
public class FoodShopBO {
    @Autowired
    FoodShopDAO foodShopDAO;

    public List<FoodShop> getFoodShopListBySearch(Map map) {
        List<FoodShop> foodShops = Lists.newArrayList();
        try {
            foodShopDAO.getFoodShopListBySearch(map).stream().forEach(c -> {
                foodShops.add((FoodShop)c);
            });
        } catch (SQLException e) {
            e.printStackTrace();
            return  null;
        }
        return foodShops;
    }
}
