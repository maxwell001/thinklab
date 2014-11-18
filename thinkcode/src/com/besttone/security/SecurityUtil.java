package com.besttone.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import org.apache.commons.codec.binary.Base64;

/**
 *瀹夊叏鐩稿叧宸ュ叿绫�
 */
public class SecurityUtil {
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
     * 鍔犲瘑
     * @param src
     * @return
     */
    public static byte[] encryptMode(String algorithm,String secretKey,String padadWay,byte[] src) {
        try {
        	 //瀹炰緥鍖栬礋璐ｅ姞瀵�瑙ｅ瘑鐨凜ipher宸ュ叿绫�
             Cipher c1 = Cipher.getInstance(padadWay);
             //鍒濆鍖栦负鍔犲瘑妯″紡
             c1.init(Cipher.ENCRYPT_MODE, generateKey(algorithm,secretKey));
             return c1.doFinal(src);
         } catch (java.security.NoSuchAlgorithmException e1) {
             e1.printStackTrace();
         } catch (javax.crypto.NoSuchPaddingException e2) {
             e2.printStackTrace();
         } catch (java.lang.Exception e3) {
             e3.printStackTrace();
         }
        return null;
     }
    
    /**
     * 瑙ｅ瘑
     * @param src
     * @return
     */
    public static byte[] decryptMode(String algorithm,String padadWay,String secretKey,byte[] src) {  
        try {
        	Cipher c1 = Cipher.getInstance(padadWay);
        	//鍒濆鍖栦负瑙ｅ瘑妯″紡
            c1.init(Cipher.DECRYPT_MODE, generateKey(algorithm,secretKey));
            return c1.doFinal(src);
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (java.lang.Exception e3) {
            e3.printStackTrace();
        }
        return null;
     }
    
    /**
     * 鐢熸垚瀵嗛挜
     * @param secretKey
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static SecretKey generateKey(String algorithm,String secretKey) throws NoSuchAlgorithmException{  
    	SecureRandom secureRandom = new SecureRandom(secretKey.getBytes());  
    	// 涓烘垜浠�鎷╃殑DES绠楁硶鐢熸垚涓�釜KeyGenerator瀵硅薄  
    	KeyGenerator kg = null;  
    	try {  
    	    kg = KeyGenerator.getInstance(algorithm);  
    	} catch (NoSuchAlgorithmException e) { 
    		e.printStackTrace();
    	}  
    	kg.init(secureRandom);  
    	// 鐢熸垚瀵嗛挜  
    	return kg.generateKey();  
    }
    
    /**
     * 浣跨敤Base64鏂瑰紡灏哹yte杞崲涓簊tring
     * @param src
     * @return
     */
    public static String byteToStrByBase64(byte[] src){
    	String result = "";
    	try{
    		Base64 base64Encoder = new Base64();
	    	result = base64Encoder.encodeAsString(src);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return result;
    }
    
    /**
     * 浣跨敤Base64鏂瑰紡灏哠tring杞琤yte[]
     * @param src
     * @return
     */
    public static byte[] strToByteByBase64(String src){
    	byte[] result = new byte[0];
    	try{
    		Base64 base64Decoder = new Base64();
	    	result = base64Decoder.decode(src);
    	}catch(Exception e){
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
