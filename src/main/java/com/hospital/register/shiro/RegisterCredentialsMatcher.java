package com.hospital.register.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hospital.register.util.PasswordHelper;

public class RegisterCredentialsMatcher extends SimpleCredentialsMatcher {
	private static final Logger logger = LoggerFactory.getLogger(RegisterCredentialsMatcher.class);
	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		logger.info("doCredentialsMatch");
		UsernamePasswordToken utoken = (UsernamePasswordToken) token;
		// 获得用户输入的密码
		String inPassword = PasswordHelper.encryptPassword(utoken.getUsername(), new String(utoken.getPassword()));
		// 获得数据库中的密码
		String dbPassword = (String) info.getCredentials();
		// 进行密码的比对
		return this.equals(inPassword, dbPassword);
	}
}
