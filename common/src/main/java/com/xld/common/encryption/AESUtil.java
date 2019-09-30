package com.xld.common.encryption;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

/**
 * AES加密工具类
 * @author XEX
 */
public class AESUtil {
	
	/**
	 * AES加密
	 * @param content 需要被加密的内容
	 * @param ecnodeRules 密钥
     * @return 加密成功：加密后的字符串；加密失败：null
	 */
	public static String aesEncode(String content, String ecnodeRules){
		try {
            //1.构造密钥生成器，指定为AES算法,不区分大小写
			KeyGenerator keygen = KeyGenerator.getInstance("AES");

            //linux环境下不能直接随机初始化密钥
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			
            //2.根据ENCODE_RULES规则初始化密钥生成器//生成一个128位的随机源,根据传入的字节数组
            secureRandom.setSeed(ecnodeRules.getBytes());
            keygen.init(128, secureRandom);
            
            //3.产生原始对称密钥
            SecretKey originalKey = keygen.generateKey();

            //4.获得原始对称密钥的字节数组
            byte [] raw = originalKey.getEncoded();

            //5.根据字节数组生成AES密钥
            SecretKey key = new SecretKeySpec(raw, "AES");
            
            //6.根据指定算法AES自成密码器
            Cipher cipher = Cipher.getInstance("AES");

            //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(Cipher.ENCRYPT_MODE, key);

            //8.获取加密内容的字节数组(这里要设置为utf-8)不然内容中如果有中文和英文混合中文就会解密为乱码
            byte [] contentByte = content.getBytes(StandardCharsets.UTF_8);

            //9.根据密码器的初始化方式--加密：将数据加密
            byte [] resultByte = cipher.doFinal(contentByte);

            //10.将加密后的数据转换为字符串
            BASE64Encoder base64encoder = new BASE64Encoder();			
            return base64encoder.encode(resultByte);						
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;         
    }
	
    /**
     * AES解密
     * @param content 需要被解密的内容
     * @param ecnodeRules 密钥
     * @return 解密成功：解密后的字符串 ；解密失败：null
     */
    public static String aesDecode(String content, String ecnodeRules){
        try {
            //1.构造密钥生成器，指定为AES算法,不区分大小写
        	KeyGenerator keygen = KeyGenerator.getInstance("AES");
        	
            //linux环境下不能直接随机初始化密钥
        	SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );

            //2.根据ecnodeRules规则初始化密钥生成器//生成一个128位的随机源,根据传入的字节数组
	        secureRandom.setSeed(ecnodeRules.getBytes()); 
            keygen.init(128, secureRandom);

            //3.产生原始对称密钥
            SecretKey originalKey = keygen.generateKey();
            
            //4.获得原始对称密钥的字节数组
            byte [] raw = originalKey.getEncoded();
           
            //5.根据字节数组生成AES密钥
            SecretKey key = new SecretKeySpec(raw, "AES");

            //6.根据指定算法AES自成密码器
            Cipher cipher = Cipher.getInstance("AES");
            
            //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(Cipher.DECRYPT_MODE, key);

            //8.将加密并编码后的内容解码成字节数组
            BASE64Decoder base64decoder = new BASE64Decoder();			
            byte[] contentByte = base64decoder.decodeBuffer(content);
            
            //9.根据密码器的初始化方式--解密密：将数据解密
            byte [] resultByte = cipher.doFinal(contentByte);

            //10.将解密后的数据转换为字符串
            return new String(resultByte,StandardCharsets.UTF_8);						
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;         
    }
}
