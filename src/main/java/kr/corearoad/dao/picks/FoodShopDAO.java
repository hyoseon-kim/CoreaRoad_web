package kr.corearoad.dao.picks;

import kr.corearoad.bean.Action;
import kr.corearoad.mapper.ActionInterface;
import kr.corearoad.mapper.picks.FoodShopInterface;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by Naver on 2016-08-15.
 */
@Repository
public class FoodShopDAO implements FoodShopInterface {

    @Autowired
    @Resource(name = "sqlSessionTemplate")
    private SqlSession sqlSession;

    @Override
    @Transactional
    public List getFoodShopListBySearch(Map map) throws SQLException {
        return sqlSession.selectList("kr.corearoad.mapper.picks.FoodShopInterface.getFoodShopListBySearch", map);
    }
}
