package com.hospital.register.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * 
 * @author paul
 *
 */
public class MyMD5 {
	
	// 全局数组
    private final static String[] strDigits = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
	
	
	// 返回形式为数字跟字符串
    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        // System.out.println("iRet="+iRet);
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return strDigits[iD1] + strDigits[iD2];
    }
	
	
	// 转换字节数组为16进制字串
    private static String byteToString(byte[] bByte) {
        StringBuffer sBuffer = new StringBuffer();
        for (int i = 0; i < bByte.length; i++) {
            sBuffer.append(byteToArrayString(bByte[i]));
        }
        return sBuffer.toString();
    }
	
	
	/**利用MD5进行加密
	 * 
	 * @param data
	 * @return
	 */
    public static String encoderByMd5(String data){
    	 String resultString = null;
         try {
             resultString = new String(data);
             MessageDigest md = MessageDigest.getInstance("MD5");
             // md.digest() 该函数返回值为存放哈希值结果的byte数组
             resultString = byteToString(md.digest(data.getBytes("utf-8")));
         } catch (NoSuchAlgorithmException ex) {
             ex.printStackTrace();
         } catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
         return resultString;
    }
    
    public static void main(String[] args) {
		String str = "a6f26ed0bd13232d9d77e49e20a85096";
		String encodedStr = encoderByMd5(str);
		System.out.println(encodedStr);
		Object obj = new SimpleHash("MD5", str, null, 2);
		System.out.println(obj);
	}

}
