package com.geniu.tool.files;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

/**
 * 将Map数据转成文件
 *
 * @Auther: prepared
 * @Date: 2021/12/16 11:36
 */
public class MapToFileUtil {

    private static Logger logger = LoggerFactory.getLogger(MapToFileUtil.class);

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
        content.put("key1", "test");
        content.put("key2", "131********");
        content.put("key3", "2010-10-12 14:04:03");
        content.put("key4", "wwww.baidu.com");
        content.put("key5", "1231456@qq.com");
        content.put("key6", "是");
        mapToFile(content, "2.txt");
    }
}
