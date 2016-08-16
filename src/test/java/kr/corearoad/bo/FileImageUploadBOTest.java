package kr.corearoad.bo;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by Naver on 2016-08-15.
 */
public class FileImageUploadBOTest {


    FileImageUploadBO fileImageUploadBO;

    @Test
    public void upload() throws Exception {

        fileImageUploadBO = new FileImageUploadBO();

        fileImageUploadBO.init();
        fileImageUploadBO.upload("/img/", new File(path()));

    }

    public String path() {
        String path = new File(".").getAbsolutePath() + "\\src\\main\\webapp\\img\\logo_black_no_bg.png";
        return path;
    }

}