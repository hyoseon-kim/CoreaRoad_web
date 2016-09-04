package kr.corearoad.mapper;

import kr.corearoad.bean.CoreaPicks;
import kr.corearoad.bean.CoreaPicksImage;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Naver on 2016-08-02.
 */
public interface CoreaPicksInterface{

    List selectTopCoreaPicks() throws SQLException;

    List selectCoreaPicksWithSearchCondition(CoreaPicks coreaPicks) throws SQLException;

    int insertCoreaPicks(CoreaPicks coreaPicks) throws SQLException;

    int insertCoreaPicksImage(CoreaPicksImage coreaPicksImage) throws SQLException;
}
