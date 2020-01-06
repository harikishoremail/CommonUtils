package com.hrc.commons.utils;

public class ValidationUtils {

	public static boolean validatePanNo(String input) {
		String regex = "[A-Za-z]{5}\\d{4}[A-Za-z]{1}";
		return input != null ? input.matches(regex) : false;
	}
}
