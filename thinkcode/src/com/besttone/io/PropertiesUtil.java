package com.besttone.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *properties
 */
public class PropertiesUtil {
	/**
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
