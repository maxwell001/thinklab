package src.com.besttone.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
//			InputStream is = PropertiesUtil.class.getResourceAsStream(fileName);
			InputStreamReader isr = new InputStreamReader(PropertiesUtil.class.getResourceAsStream(fileName), "UTF-8");
			properties.load(isr);
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
//			InputStream is = PropertiesUtil.class.getResourceAsStream(fileName);
			//防止中文乱码
			InputStreamReader isr = new InputStreamReader(PropertiesUtil.class.getResourceAsStream(fileName), "UTF-8");
			properties.load(isr);
			value = properties.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}
	/**
	 * properties中文乱码
	 * @param fileName
	 * @return
	 */
	public static Properties loadProperties2(String fileName){
		Properties properties = new Properties();
		try {
			InputStreamReader is = new InputStreamReader(PropertiesUtil.class.getResourceAsStream("/labelexelinfo.properties"), "UTF-8");
			properties.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
	
}
