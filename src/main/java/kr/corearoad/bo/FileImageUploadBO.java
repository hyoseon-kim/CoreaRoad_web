package kr.corearoad.bo;

import jdk.internal.util.xml.PropertiesDefaultHandler;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.springframework.expression.spel.ast.PropertyOrFieldReference;
import org.springframework.stereotype.Service;
import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpClientProvider;

import java.io.*;
import java.net.SocketException;

/**
 * Created by Naver on 2016-08-15.
 */
@Service
public class FileImageUploadBO{
    private FTPClient client = null;

    public void init() {
        client = new FTPClient();
        client.setControlEncoding("euc-kr"); // 한글 encoding....

        FTPClientConfig config = new FTPClientConfig();
        client.configure(config);
        try {
            client.connect("corearoad.kr", 21);
            client.login("corearoad", "cr20160323**");
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        /**
         * 하나의 파일을 업로드 한다.
         *
         * @param dir
         *            저장시킬 주소(서버)
         * @param file
         *            저장할 파일
         */
        public void upload(String dir, File file) {

            InputStream in = null;

            try {
                in = new FileInputStream(file);
                client.storeFile(dir + file.getName(), in);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

}
