package com.geniu.filetest;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

/**
 * @Author: zhongshibo
 * @Date: 2022/12/14 16:01
 */
@Slf4j
public class FileToBase64 {

	public static void main(String[] args) {
		String filePath = "C:\\Users\\shibo.zhong\\Pictures\\_20221102115552.jpg";
		String base64Str = imageToBase64(filePath);
		System.out.println(base64Str);

		base64ToImage(base64Str, "C:\\Users\\shibo.zhong\\Pictures\\_20221102115552.jpg_new.jpg");
	}


	public static String imageToBase64(String filePath) {
		byte[] fileContent = new byte[0];
		try {
			fileContent = FileUtils.readFileToByteArray(new File(filePath));
			String encodedString = Base64.getEncoder().encodeToString(fileContent);
			return encodedString;
		} catch (IOException e) {
			log.error("convert.error", e);
		}
		return null;
	}


	public static void base64ToImage(String encodedString, String outputFileName) {
		try {
			byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
			FileUtils.writeByteArrayToFile(new File(outputFileName), decodedBytes);
		} catch (IOException e) {
			log.error("convert.error", e);
		}
	}

}
