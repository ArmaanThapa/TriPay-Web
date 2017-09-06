package com.tripayweb.app.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tripayweb.util.LogCat;
import com.tripayweb.util.SecurityUtil;

public class SecurityUtils {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static final String HASH_KEY = "C1B4246A70E8C22CE85CA472AB90A3DF";

	public static String getHash(String message) {
		try {
			LogCat.print("TESTING   :: "+message);
			return SecurityUtil.md5(message + HASH_KEY);
		} catch (Exception e) {
			return "";
		}
	}

	public static String generateKey(String str) {

		String hashKey = "";
		try {
			hashKey = md5(str);
		} catch (Exception e) {
			System.out.print("Key Not Generated");
		}
		return hashKey;

	}

	public static String md5(String str) throws Exception {

		MessageDigest m = MessageDigest.getInstance("MD5");

		byte[] data = str.getBytes();

		m.update(data, 0, data.length);

		BigInteger i = new BigInteger(1, m.digest());

		String hash = String.format("%1$032X", i);

		return hash;
	}

}
