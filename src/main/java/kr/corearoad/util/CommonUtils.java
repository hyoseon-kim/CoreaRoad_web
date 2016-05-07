package kr.corearoad.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Naver on 2016-05-07.
 */
public class CommonUtils {
    public static List<String> returnStringArrayFromString(String str) {
        if(str.isEmpty()) {
            return null;
        }
        String removedStr = str.split("\\[")[1].split("\\]")[0].replaceAll("'","").trim();
        String[] returnArray = removedStr.split(",");
        if(returnArray.length != 0) {
            return new ArrayList<String>(Arrays.asList(returnArray));
        } else {
            return null;
        }

    }
}
