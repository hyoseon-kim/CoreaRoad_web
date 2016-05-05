package kr.corearoad.dao;

import kr.corearoad.bean.User;
import kr.corearoad.mapper.LoginUserInterface;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public User getUser(String email) throws SQLException {
        return (User)sqlSession.selectOne("kr.corearoad.mapper.LoginUserInterface.getUser", email);
    }

    @Override
    @Transactional
    public void join(User user) throws SQLException {
        sqlSession.insert("kr.corearoad.mapper.LoginUserInterface.joinUser", user);
    }
}
