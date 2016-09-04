package kr.corearoad.bo;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import kr.corearoad.bean.CoreaPicks;
import kr.corearoad.bean.CoreaPicksImage;
import kr.corearoad.dao.CoreaPicksDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Naver on 2016-08-07.
 */
@Service
public class CoreaPicksBO {

    private static Logger logger = LoggerFactory.getLogger(CoreaPicksBO.class);

    @Autowired
    CoreaPicksDAO coreaPicksDAO;

    public List<CoreaPicks> getAllCoreaPicksList() {
        List<CoreaPicks> list = Lists.newArrayList();
        try {
            coreaPicksDAO.selectTopCoreaPicks().stream().forEach(c -> {
                ((CoreaPicks)c).setProcessedTagList();
                list.add((CoreaPicks)c);
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<CoreaPicks> searchCoreaPicks(CoreaPicks coreaPicks) {
        List<CoreaPicks> list = Lists.newArrayList();
        try {
            coreaPicksDAO.selectCoreaPicksWithSearchCondition(coreaPicks).stream().forEach(c -> {
                ((CoreaPicks)c).setProcessedTagList();
                list.add((CoreaPicks)c);
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int insertCorearPicks(CoreaPicks coreaPicks) {
        try {
            return coreaPicksDAO.insertCoreaPicks(coreaPicks);
        } catch (SQLException e) {
            logger.error("CoreaPicks 데이터 삽입에 실패함!! 디버깅 필요 :{}", coreaPicks, e);
            return 0;
        }
    }

    public int insertCoreaPicksImage(String randomKey, String img) {
        try {
            return coreaPicksDAO.insertCoreaPicksImage(new CoreaPicksImage(randomKey, img));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
