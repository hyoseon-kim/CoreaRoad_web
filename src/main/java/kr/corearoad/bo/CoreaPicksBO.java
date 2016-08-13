package kr.corearoad.bo;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import kr.corearoad.bean.CoreaPicks;
import kr.corearoad.dao.CoreaPicksDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by Naver on 2016-08-07.
 */
@Service
public class CoreaPicksBO {

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
}
