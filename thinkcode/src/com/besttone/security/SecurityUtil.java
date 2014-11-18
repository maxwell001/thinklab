package com.besttone.data.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

/**
 *安全相关工具类
 */
public class SecurityUtil {
	private static final Logger log = Logger.getLogger(SecurityUtil.class);
//	private static String encryptAlgorithm;
//	private static String secretKey;
//	private static String padadWay;
//	
//    static{
//    	Properties prop = PropertiesUtil.loadProperties("/init.properties");
//    	encryptAlgorithm = prop.getProperty("encryptKey");
//    	secretKey = prop.getProperty("secretKey");
//    	padadWay = prop.getProperty("padadWay");
//    }
    
    /**
     * 加密
     * @param src
     * @return
     */
    public static byte[] encryptMode(String algorithm,String secretKey,String padadWay,byte[] src) {
    	log.info("开始加密...");
        try {
        	 //实例化负责加密/解密的Cipher工具类
             Cipher c1 = Cipher.getInstance(padadWay);
             //初始化为加密模式
             c1.init(Cipher.ENCRYPT_MODE, generateKey(algorithm,secretKey));
             log.info("结束加密...");
             return c1.doFinal(src);
         } catch (java.security.NoSuchAlgorithmException e1) {
        	 log.error("加密报错："+e1);
             e1.printStackTrace();
         } catch (javax.crypto.NoSuchPaddingException e2) {
        	 log.error("加密报错："+e2);
             e2.printStackTrace();
         } catch (java.lang.Exception e3) {
        	 log.error("加密报错："+e3);
             e3.printStackTrace();
         }
        log.info("结束加密...");
        return null;
     }
    
    /**
     * 解密
     * @param src
     * @return
     */
    public static byte[] decryptMode(String algorithm,String padadWay,String secretKey,byte[] src) {  
    	log.info("开始解密...");
        try {
        	Cipher c1 = Cipher.getInstance(padadWay);
        	//初始化为解密模式
            c1.init(Cipher.DECRYPT_MODE, generateKey(algorithm,secretKey));
            log.info("结束解密...");
            return c1.doFinal(src);
        } catch (java.security.NoSuchAlgorithmException e1) {
        	log.error("解密报错:"+e1);
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
        	log.error("解密报错:"+e2);
            e2.printStackTrace();
        } catch (java.lang.Exception e3) {
        	log.error("解密报错:"+e3);
            e3.printStackTrace();
        }
        log.info("结束解密...");
        return null;
     }
    
    /**
     * 生成密钥
     * @param secretKey
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static SecretKey generateKey(String algorithm,String secretKey) throws NoSuchAlgorithmException{  
    	SecureRandom secureRandom = new SecureRandom(secretKey.getBytes());  
    	// 为我们选择的DES算法生成一个KeyGenerator对象  
    	KeyGenerator kg = null;  
    	try {  
    	    kg = KeyGenerator.getInstance(algorithm);  
    	} catch (NoSuchAlgorithmException e) { 
    		log.error("密钥生成报错:"+e);
    		e.printStackTrace();
    	}  
    	kg.init(secureRandom);  
    	// 生成密钥  
    	return kg.generateKey();  
    }
    
    /**
     * 使用Base64方式将byte转换为string
     * @param src
     * @return
     */
    public static String byteToStrByBase64(byte[] src){
    	String result = "";
    	try{
    		Base64 base64Encoder = new Base64();
	    	result = base64Encoder.encodeAsString(src);
    	}catch(Exception e){
    		log.error("byte[]转String报错："+e);
    		e.printStackTrace();
    	}
    	return result;
    }
    
    /**
     * 使用Base64方式将String转byte[]
     * @param src
     * @return
     */
    public static byte[] strToByteByBase64(String src){
    	byte[] result = new byte[0];
    	try{
    		Base64 base64Decoder = new Base64();
	    	result = base64Decoder.decode(src);
    	}catch(Exception e){
    		log.error("String转byte[]报错："+e);
    		e.printStackTrace();
    	}
    	return result;
    }
    
    public static byte[] messageDigest(byte[] src,String algorithm){
    	MessageDigest md = null;
    	byte[] res = null;
    	try{
    		md = MessageDigest.getInstance(algorithm);  
    		md.update(src);  
    		res = md.digest(); 
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return res;
    }
}
