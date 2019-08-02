package com.zile.beetlsql.common.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

public class MD5Utils {

	private static String salt = "szsti1019";
	
	/**
	 * 加密字符串
	 * @param password 要加密的明文
	 * @param isAddSalt 是否加默认盐
	 * @return 加密之后的结果
	 */
	public static String Encrypt(String password, boolean isAddSalt){
		if (StringUtils.isNotEmpty(password)){
			if (isAddSalt){
				return DigestUtils.md5Hex(password + salt);
				//return DigestUtils.md5Hex(DigestUtils.md5(password + salt));
			} else {
				return DigestUtils.md5Hex(password);
				//return DigestUtils.md5Hex(DigestUtils.md5(password));
			}
		}
		return null;		
	}
	
	/**
	 * 
	 * @param bytes
	 * @return
	 */
	public static String Encrypt(byte[] bytes){
		if (ArrayUtils.isNotEmpty(bytes)){
			return DigestUtils.md5Hex(DigestUtils.md5(bytes));
		}
		return null;
	}
	
	/**
	 * MD5加盐加密
	 * @param password 要加密的明文
	 * @param salt 盐
	 * @return 加密之后的结果
	 */
	public static String Encrypt(String password, String salt){
		if (StringUtils.isNotEmpty(password)){
			return DigestUtils.md5Hex(DigestUtils.md5(password + salt));
		}
		return null;
	}
	
	public static void main(String[] args){

		System.out.println(MD5Utils.Encrypt("risk@2019", true));
		System.out.println(MD5Utils.Encrypt("123456", true));
		//System.out.println(MD5Utils.Encrypt("123456", true));
	}
}
