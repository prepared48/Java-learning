package com.geniu.file;

import org.apache.http.entity.ContentType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * file 转成 MultipartFile（spring-web）
 *
 * @Author: zhongshibo
 * @Date: 2020/11/18 14:44
 */
public class TestMultipartFile {

    private static final String ROOT_FOLDER = new File("").getAbsolutePath();

    public static void main(String[] args) {
        String fileName = ROOT_FOLDER + "/file/电子签名-e签宝.jpg";
        File file = new File(fileName);
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            MultipartFile multipartFile = new MockMultipartFile(file.getName(), file.getName(),
                    ContentType.APPLICATION_OCTET_STREAM.toString(), inputStream);
            System.out.println(multipartFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
