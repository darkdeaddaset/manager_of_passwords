package org.admin.hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public interface Hash {
	static String HashString(final String str) throws NoSuchAlgorithmException {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		byte[] bytes = md5.digest(str.getBytes());
		StringBuilder builder = new StringBuilder();
		
		for (byte b : bytes) {
			builder.append(String.format("%02X", b));
		}
		return builder.toString();
	}
}
