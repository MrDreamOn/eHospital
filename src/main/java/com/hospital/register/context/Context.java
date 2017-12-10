package com.hospital.register.context;

import java.util.concurrent.ConcurrentHashMap;

import com.hospital.register.bean.User;

/**
 * 
 * @author paul
 *
 */
public class Context {
	public static ConcurrentHashMap<Integer,User> USER_MAP = new ConcurrentHashMap<Integer,User>();
}
