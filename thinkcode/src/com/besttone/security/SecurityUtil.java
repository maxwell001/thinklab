package src.com.besttone.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import org.apache.commons.codec.binary.Base64;

/**
 *鐎瑰鍙忛惄绋垮彠瀹搞儱鍙跨猾锟� */
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
     * 閸旂姴鐦�
     * @param src
     * @return
     */
    public static byte[] encryptMode(String algorithm,String secretKey,String padadWay,byte[] src) {
        try {
        	 Cipher c1 = Cipher.getInstance(padadWay);
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
     * 鐟欙絽鐦�
     * @param src
     * @return
     */
    public static byte[] decryptMode(String algorithm,String padadWay,String secretKey,byte[] src) {  
        try {
        	Cipher c1 = Cipher.getInstance(padadWay);
        	//閸掓繂顫愰崠鏍﹁礋鐟欙絽鐦戝Ο鈥崇础
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
     * 閻㈢喐鍨氱�鍡涙寽
     * @param secretKey
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static SecretKey generateKey(String algorithm,String secretKey) throws NoSuchAlgorithmException{  
    	SecureRandom secureRandom = new SecureRandom(secretKey.getBytes());  
    	// 娑撶儤鍨滄禒顒勶拷閹封晝娈慏ES缁犳纭堕悽鐔稿灇娑擄拷閲淜eyGenerator鐎电钖� 
    	KeyGenerator kg = null;  
    	try {  
    	    kg = KeyGenerator.getInstance(algorithm);  
    	} catch (NoSuchAlgorithmException e) { 
    		e.printStackTrace();
    	}  
    	kg.init(secureRandom);  
    	// 閻㈢喐鍨氱�鍡涙寽  
    	return kg.generateKey();  
    }
    
    /**
     * 娴ｈ法鏁ase64閺傜懓绱＄亸鍝箉te鏉烆剚宕叉稉绨妕ring
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
     * 娴ｈ法鏁ase64閺傜懓绱＄亸鍝爐ring鏉炵悿yte[]
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
