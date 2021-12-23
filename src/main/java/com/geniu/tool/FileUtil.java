package com.geniu.tool;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import java.io.*;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * 文件工具类
 *
 * @Auther: shibo.zhong
 * @Date: 2021/12/16 12:02
 */
public class FileUtil {

    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 将Map存入文件
     *
     * @param fileContent 要存入文件的内容
     * @param filePath 要存入的文件地址
     */
    public static void mapToFile(Map<String, Object> fileContent, String filePath) {
        try {
            // 2个key、value之间换行
            String line = "\n";
            StringBuffer str = new StringBuffer();
            FileWriter fw = new FileWriter(filePath, false);
            Set set = fileContent.entrySet();
            Iterator iter = set.iterator();
            while (iter.hasNext()) {
                Map.Entry<String, String> entry = (Map.Entry<String, String>) iter.next();
                str.append(entry.getKey() + " : " + entry.getValue()).append(line);
            }
            fw.write(str.toString());
            fw.close();
        } catch (FileNotFoundException e) {
            logger.error("file not found error, errMsg: {}", e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            logger.error("map存入文件异常, errMsg: {}", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Map 转成file
     *
     * @param fileContent 文件内容Map格式
     * @return
     */
    public static File contentToFile(Map<String, Object> fileContent, String fileName) {
        FileUtil.mapToFile(fileContent, fileName);
        return new File(fileName);
    }

    /**
     * Map 转成file
     *
     * @param fileContent 文件内容Map格式
     * @return
     */
    public static MimeMultipart contentToMultipart(Map<String, Object> fileContent, String fileName) {
        File attachment = contentToFile(fileContent, fileName);
        // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
        MimeMultipart multipart = new MimeMultipart();
        // 添加附件的内容
        if (attachment != null) {
            try {
                BodyPart attachmentBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(attachment);
                attachmentBodyPart.setDataHandler(new DataHandler(source));
                //MimeUtility.encodeWord可以避免文件名乱码
                attachmentBodyPart.setFileName(MimeUtility.encodeWord(attachment.getName()));
                multipart.addBodyPart(attachmentBodyPart);
                return multipart;
            } catch (MessagingException e) {
                logger.error("contentToMultipart.error.errorMsg:{}", e.getMessage());
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                logger.error("contentToMultipart.error.errorMsg:{}", e.getMessage());
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 删除文件
     *
     * @param filePath
     */
    public static void deleteFile(String filePath) {
        if(StringUtils.isEmpty(filePath)) {
            logger.warn("filePath is empty.");
            return;
        }
        try {
            Files.delete(new File(filePath).toPath());
        } catch (IOException e) {
            logger.error("删除文件异常, filePath: {}, errMsg: {}", filePath, e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Map<String, Object> content = new LinkedHashMap<>();
        content.put("昵称", "test");
        content.put("手机号", "131********");
        content.put("注册时间", "2010-10-12 14:04:03");
        content.put("头像", "wwww.baidu.com");
        content.put("邮箱", "1231456@qq.com");
        content.put("实名状态", "是");
        mapToFile(content, "2.txt");
    }
}