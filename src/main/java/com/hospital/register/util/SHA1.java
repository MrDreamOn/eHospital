/**
 * 对公众平台发送给公众账号的消息加解密示例代码.
 * 
 * @copyright Copyright (c) 1998-2014 Tencent Inc.
 */

// ------------------------------------------------------------------------

package com.hospital.register.util;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.hospital.register.exception.AesException;

/**
 * SHA1 class
 *
 * 计算公众平台的消息签名接口.
 */
public class SHA1 {

	/**
	 * 用SHA1算法生成安全签名
	 * @param token 票据
	 * @param timestamp 时间戳
	 * @param nonce 随机字符串
	 * @param encrypt 密文
	 * @return 安全签名
	 * @throws AesException 
	 */
	public static String getSHA1(String token, String timestamp, String nonce, String encrypt) throws AesException
			  {
		try {
			String[] array = new String[] { token, timestamp, nonce, encrypt };
			StringBuffer sb = new StringBuffer();
			// 字符串排序
			Arrays.sort(array);
			for (int i = 0; i < 4; i++) {
				sb.append(array[i]);
			}
			String str = sb.toString();
			// SHA1签名生成
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(str.getBytes());
			byte[] digest = md.digest();

			StringBuffer hexstr = new StringBuffer();
			String shaHex = "";
			for (int i = 0; i < digest.length; i++) {
				shaHex = Integer.toHexString(digest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexstr.append(0);
				}
				hexstr.append(shaHex);
			}
			return hexstr.toString();
		} catch (Exception e) {
			throw new AesException(AesException.ComputeSignatureError);
		}
	}
	
	/**
	 * 用SHA1算法生成扫一扫签名
	 * @param ticket
	 * @param nonceStr
	 * @param timestamp
	 * @param url
	 * @return
	 * @throws AesException
	 */
	public static String getSHA2(Map<String, Object> map) throws AesException {
		try {
			String str = getOrderByLexicographic(map);
			// SHA1签名生成
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(str.getBytes());
			byte[] digest = md.digest();

			StringBuffer hexstr = new StringBuffer();
			String shaHex = "";
			for (int i = 0; i < digest.length; i++) {
				shaHex = Integer.toHexString(digest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexstr.append(0);
				}
				hexstr.append(shaHex);
			}
			return hexstr.toString();
		} catch (Exception e) {
			throw new AesException(AesException.ComputeSignatureError);
		}
	}
	
	/** 
     * 获取参数的字典排序 
     * @param maps 参数key-value map集合 
     * @return String 排序后的字符串 
     */  
    private static String getOrderByLexicographic(Map<String,Object> maps){  
        return splitParams(lexicographicOrder(getParamsName(maps)),maps);  
    }
    
    /** 
     * 获取参数名称 key 
     * @param maps 参数key-value map集合 
     * @return 
     */  
    private static List<String> getParamsName(Map<String,Object> maps){  
        List<String> paramNames = new ArrayList<String>();  
        for(Map.Entry<String,Object> entry : maps.entrySet()){  
            paramNames.add(entry.getKey());  
        }  
        return paramNames;  
    }
    
    /** 
     * 参数名称按字典排序 
     * @param paramNames 参数名称List集合 
     * @return 排序后的参数名称List集合 
     */  
    private static List<String> lexicographicOrder(List<String> paramNames){  
        Collections.sort(paramNames);  
        return paramNames;  
    }
    
    /** 
     * 拼接排序好的参数名称和参数值 
     * @param paramNames 排序后的参数名称集合 
     * @param maps 参数key-value map集合 
     * @return String 拼接后的字符串 
     */  
    private static String splitParams(List<String> paramNames,Map<String,Object> maps){  
        StringBuilder paramStr = new StringBuilder();
        int i=0;
        for(String paramName : paramNames){
        	if (i>0) {
				paramStr.append("&");
			}
            paramStr.append(paramName);  
            for(Map.Entry<String,Object> entry : maps.entrySet()){  
                if(paramName.equals(entry.getKey())){  
                    paramStr.append("="+String.valueOf(entry.getValue()));  
                }  
            }
            i++;
        }  
        return paramStr.toString();  
    }
}
