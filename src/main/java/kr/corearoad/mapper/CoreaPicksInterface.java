package kr.corearoad.mapper;

import kr.corearoad.bean.CoreaPicks;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Naver on 2016-08-02.
 */
public interface CoreaPicksInterface{

    List selectTopCoreaPicks() throws SQLException;

    List selectCoreaPicksWithSearchCondition(CoreaPicks coreaPicks) throws SQLException;

    void insertCoreaPicks(CoreaPicks coreaPicks) throws SQLException;
}
