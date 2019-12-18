package cn.licoy.encryptbody.util;

import org.springframework.util.Base64Utils;

/**
 * Base64工具类
 */
public class Base64Util {
    public static String encodeByte2String(byte[] data) {
        return Base64Utils.encodeToString(data);
    }

    public static byte[] decodeString2byte(String data) {
        return Base64Utils.decodeFromString(data);
    }
}
