package kr.corearoad.dao;

import kr.corearoad.bean.ChatRoom;
import kr.corearoad.bean.CoreaPicks;
import kr.corearoad.mapper.CoreaPicksInterface;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Naver on 2016-08-02.
 */
@Repository
public class CoreaPicksDAO implements CoreaPicksInterface{
    private static Logger logger = LoggerFactory.getLogger(CoreaPicksDAO.class);

    @Autowired
    @Resource(name = "sqlSessionTemplate")
    private SqlSession sqlSession;


    @Override
    @Transactional
    public List selectTopCoreaPicks() throws SQLException {
        return  sqlSession.selectList("kr.corearoad.mapper.CoreaPicksInterface.selectTopCoreaPicks");
    }

    @Override
    @Transactional
    public List selectCoreaPicksWithSearchCondition(CoreaPicks coreaPicks) throws SQLException {
        return  sqlSession.selectList("kr.corearoad.mapper.CoreaPicksInterface.selectCoreaPicksWithSearchCondition", coreaPicks);
    }

    @Override
    @Transactional
    public void insertCoreaPicks(CoreaPicks coreaPicks) throws  SQLException {
        sqlSession.insert("kr.corearoad.mapper.CoreaPicksInterface.insertCoreaPicks", coreaPicks);
    }
}
