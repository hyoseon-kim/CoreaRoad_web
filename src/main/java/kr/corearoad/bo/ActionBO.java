package kr.corearoad.bo;

import kr.corearoad.bean.Action;
import kr.corearoad.dao.ActionDAO;
import kr.corearoad.util.CommonUtils;
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

    public Map<String,Object> getAction(String no) {
        Map<String,Object> retMap = new HashMap<String, Object>();
        try {
            Action action = actionDAO.getAction(no);
            retMap.put("no", action.getNo());
            retMap.put("name", action.getName());
            retMap.put("city", action.getCity());
            retMap.put("text", action.getText());
            retMap.put("totalTime", action.getTotalTime());
            retMap.put("meetPlace", action.getMeetPlace());
            retMap.put("exception", action.getException());
            retMap.put("tagList", CommonUtils.returnStringArrayFromString(action.getTagList()));
            retMap.put("thumbList", CommonUtils.returnStringArrayFromString(action.getThumbList()));
            retMap.put("cartList", CommonUtils.returnStringArrayFromString(action.getCartList()));
            retMap.put("bookList", CommonUtils.returnStringArrayFromString(action.getBookList()));
            retMap.put("pictureList", CommonUtils.returnStringArrayFromString(action.getPictureList()));
            retMap.put("jingermanList", CommonUtils.returnStringArrayFromString(action.getJingermanList()));
            return retMap;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
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
                map.put("name", pic.get("act_name"));
                retList.add(map);
            }
            return retList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
