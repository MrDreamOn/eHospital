package com.hospital.register.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.hospital.register.bean.User;

public class PasswordHelper {
	// private RandomNumberGenerator randomNumberGenerator = new
	// SecureRandomNumberGenerator();
	private final static String algorithmName = "md5";
	private final static int hashIterations = 2;

	public static String encryptPassword(String salt, String password) {
		// 将用户的注册密码经过散列算法替换成一个不可逆的新密码保存进数据，散列过程使用了盐
		String newPassword = new SimpleHash(algorithmName, password.trim(), ByteSource.Util.bytes(salt.trim()),
				hashIterations).toHex();
		return newPassword;
	}
	
	public static void main(String[] args) {
		User user = new User();
		user.setUserName("admin");
		user.setPassword(PasswordHelper.encryptPassword(user.getUserName(),"!qazXsw2"));
		System.out.println("password:"+user.getPassword());
	}
}
