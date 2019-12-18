package cn.licoy.encryptbody.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * <p>AES加密处理工具类</p>
 * @author licoy.cn
 * @version 2018/9/5
 */
public class AESEncryptUtil {

    /**
     * AES加密
     * @param content  字符串内容
     * @param password 密钥
     */
    public static String encrypt(String content, String password,String iv){
        return aes(content,password,iv,Cipher.ENCRYPT_MODE);
    }


    /**
     * AES解密
     * @param content  字符串内容
     * @param password 密钥
     */
    public static String decrypt(String content, String password,String iv){
        return aes(content,password,iv,Cipher.DECRYPT_MODE);
    }

    /**
     * AES加密/解密 公共方法
     * @param data  字符串
     * @param password 密钥
     * @param type     加密：{@link Cipher#ENCRYPT_MODE}，解密：{@link Cipher#DECRYPT_MODE}
     */
    private static String aes(String data, String password,String iv, int type) {
        try {
//            KeyGenerator generator = KeyGenerator.getInstance("AES");
//            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
//            random.setSeed(password.getBytes());
//            generator.init(128, random);
//            SecretKey secretKey = generator.generateKey();
//            byte[] enCodeFormat = secretKey.getEncoded();

            SecretKeySpec key = new SecretKeySpec(password.getBytes(), "AES");

            //算法
            //算法/模式/补码方式
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            //使用CBC模式
            //需要一个向量iv
            //可增加加密算法的强度
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());

            //初始化并传达key和iv
            cipher.init(type, key, ivParameterSpec);

            if (type == Cipher.ENCRYPT_MODE) {
                //加密
                byte[] result = cipher.doFinal(data.getBytes());

                //base64编码后返回
                return Base64Util.encodeByte2String(result);
            } else {
                //先使用base64解码
                byte[] dataByte = Base64Util.decodeString2byte(data);

                //解密
                byte[] result = cipher.doFinal(dataByte);

                //创建为字符串返回
                return new String(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
