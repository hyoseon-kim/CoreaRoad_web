package kr.corearoad.dao;

import kr.corearoad.bean.Action;
import kr.corearoad.bean.User;
import kr.corearoad.mapper.ActionInterface;
import kr.corearoad.mapper.LoginUserInterface;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Naver on 2016-04-07.
 */
@Repository
public class ActionDAO implements ActionInterface{

    @Autowired
    @Resource(name = "sqlSessionTemplate")
    private SqlSession sqlSession;

    @Override
    @Transactional
    public Action getAction(String no) throws SQLException {
        return (Action) sqlSession.selectOne("kr.corearoad.mapper.ActionInterface.getAction", no);
    }

    @Override
    @Transactional
    public List getPictureList() throws SQLException {
        return sqlSession.selectList("kr.corearoad.mapper.ActionInterface.getPictureList");
    }
}
