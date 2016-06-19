package kr.corearoad.dao;

import kr.corearoad.bean.Action;
import kr.corearoad.bean.ChatMessanger;
import kr.corearoad.bean.ChatRoom;
import kr.corearoad.controller.SocketHandler;
import kr.corearoad.mapper.ActionInterface;
import kr.corearoad.mapper.ChatInterface;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Naver on 2016-06-19.
 */
@Repository
public class ChatDAO implements ChatInterface{

    private static Logger logger = LoggerFactory.getLogger(ChatDAO.class);

    @Autowired
    @Resource(name = "sqlSessionTemplate")
    private SqlSession sqlSession;

    @Override
    @Transactional
    public ChatRoom getChatRoom(String id) throws SQLException {
        return (ChatRoom) sqlSession.selectOne("kr.corearoad.mapper.ChatInterface.getChatRoom", id);
    }

    @Override
    @Transactional
    public ChatMessanger getChatMessnger(String id) throws SQLException {
        return (ChatMessanger) sqlSession.selectOne("kr.corearoad.mapper.ChatInterface.getChatMessnger", id);
    }

    @Override
    @Transactional
    public List getAllChatMessengerByRoomId(String id) throws SQLException {
        return sqlSession.selectList("kr.corearoad.mapper.ChatInterface.getAllChatMessengerByRoomId", id);
    }

    @Override
    @Transactional
    public List getAllCharRoomByUserId(String email) throws SQLException {
        return sqlSession.selectList("kr.corearoad.mapper.ChatInterface.getAllChatRoomByUserId", email);
    }

}
