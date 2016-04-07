package kr.corearoad.dao;

import kr.corearoad.mapper.LoginUserInterface;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.SQLException;

/**
 * Created by Naver on 2016-04-07.
 */
@Repository
public class LoginUserDAO implements LoginUserInterface{

    @Autowired
    @Resource(name = "sqlSessionTemplate")
    private SqlSession sqlSession;

    @Override
    public void test() throws SQLException {
        sqlSession.selectOne("query.test");
    }

}
