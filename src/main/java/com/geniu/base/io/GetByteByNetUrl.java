package com.geniu.base.io;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * 网络图片转成字节流
 *
 */
public class GetByteByNetUrl {

    /**
     * 根据地址获得数据的字节流
     *
     * @param strUrl 网络连接地址
     * @return
     */
    public static byte[] getImageFromNetByUrl(String strUrl) {
        try {
            URL url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            InputStream inStream = conn.getInputStream();//通过输入流获取图片数据
            byte[] btImg = readInputStream(inStream);//得到图片的二进制数据    
            return btImg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从输入流中获取数据
     *
     * @param inStream 输入流
     * @return
     * @throws Exception
     */
    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }

    /**
     * 将byte数组转成string
     *
     * @param bytes
     * @return
     */
    public static String byteArrayToString(byte[] bytes) {
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) {
        String url = "https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLbcibrZ5AzangULxI9hribic0aV8mKPQZHaPH9v2n1IYoYIeUmxEkS3r7vPkXLDHtyABh8NyKHlPvdQ/0";
        byte[] imageFromNetByUrl = getImageFromNetByUrl(url);
        System.out.println(imageFromNetByUrl);
    }
}