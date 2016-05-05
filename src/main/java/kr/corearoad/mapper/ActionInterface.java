package kr.corearoad.mapper;

import kr.corearoad.bean.Action;
import kr.corearoad.bean.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Naver on 2016-04-07.
 */
public interface ActionInterface {

    Action getAction(String no) throws SQLException;

    List getPictureList() throws SQLException;
}
