package kr.corearoad.mapper;

import kr.corearoad.bean.User;
import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;

/**
 * Created by Naver on 2016-04-07.
 */
public interface LoginUserInterface {

    User test(String email) throws SQLException;
}
