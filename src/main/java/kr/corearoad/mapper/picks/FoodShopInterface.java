package kr.corearoad.mapper.picks;

import kr.corearoad.bean.ChatMessanger;
import kr.corearoad.bean.ChatRoom;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by Naver on 2016-04-07.
 */
public interface FoodShopInterface {

    List getFoodShopListBySearch(Map map) throws SQLException;
}
