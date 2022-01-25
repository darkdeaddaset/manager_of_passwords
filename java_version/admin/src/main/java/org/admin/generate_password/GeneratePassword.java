package org.admin.generate_password;

import java.util.Locale;
import java.util.Random;

public interface GeneratePassword {
	static String GeneratePassword(final int length) {
		 final String word_small = "abcdefghijklmnopqrstuvwsyz";
	     final String word_large = word_small.toUpperCase(Locale.ROOT);
	     final String symbols = "!@#$%^&*()_-+=?;:'[]{}";
	     final String numbers = "1234567890";

	     Random random = new Random();
	     StringBuilder result = new StringBuilder(length);
	     String temp = "";

	     temp += word_small + word_large + symbols + numbers;
	     for (int i = 0; i<length; i++){
	         result.append(temp.charAt(random.nextInt(0, temp.length())));
	     }
	     return result.toString();
	}
}