package kr.corearoad.bo;

import kr.corearoad.bean.Action;
import kr.corearoad.dao.ActionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Naver on 2016-05-05.
 */
@Service
public class ActionBO {

    @Autowired
    ActionDAO actionDAO;

    public Action getAction(String no) {
        Action action = null;
        try {
            action = actionDAO.getAction(no);
            return action;
        } catch (SQLException e) {
            e.printStackTrace();
            return action;
        }
    }

    public List<Map<String,String>> getPictureList() {
        try {
            return actionDAO.getPictureList();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Map<String,String>> getMainPictureList() {
        List<Map<String,String>> retList = new ArrayList<Map<String,String>>();
        try {
            List<Map<String,String>> picList = actionDAO.getPictureList();
            for(Map<String,String> pic : picList) {
                Map<String,String> map = new HashMap<String, String>();
                String strPicture = pic.get("act_pictureList").split("\\[")[1].split("\\]")[0];
                String[] tempPictureList = strPicture.split(",");
                map.put("url", tempPictureList[0].trim().replaceAll("'",""));
                map.put("no", pic.get("act_no"));
                retList.add(map);
            }
            return retList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
