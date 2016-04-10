package kr.corearoad.bo;

import kr.corearoad.bean.User;
import kr.corearoad.dao.LoginUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * Created by Naver on 2016-04-07.
 */

@Service
public class LoginUserBO {

    @Autowired
    LoginUserDAO loginUserDAO;

    public User getTest(){
        User user = null;
        try {
            user =  loginUserDAO.test("hyos810@naver.com");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
