package com.besttone.data.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *properties文件操作工具类
 */
public class PropertiesUtil {
	/**
	 * 加载properties文件
	 * @param fileName
	 * @return
	 */
	public static Properties loadProperties(String fileName){
		Properties properties = new Properties();
		try {
			InputStream is = PropertiesUtil.class.getResourceAsStream(fileName);
			properties.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
	/**
	 * 获取properties对应key的值
	 * @param fileName
	 * @param key
	 * @return
	 */
	public static String getValue(String fileName,String key){
		String value = "";
		Properties properties = new Properties();
		try {
			InputStream is = PropertiesUtil.class.getResourceAsStream(fileName);
			properties.load(is);
			value = properties.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}
}
