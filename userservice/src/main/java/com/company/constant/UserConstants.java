package com.company.constant;

/**
 * @author pushkar.chauhan@wissen.com
 */
public enum UserConstants {
	APP_NAME,
	URL_ID,
	URL_NAME,
	EMAIL ,
	USERNAME ,
	PASSWORD ,
	PHONE_NUMBER;

	@Override
	public String toString() {
		switch(this) {
			case APP_NAME: return "userservice";
			case URL_ID: return "50328aa4";
			case URL_NAME: return "https://www.google.com";
			case EMAIL: return "pushkarchauhan91@gmail.com";
			case USERNAME: return "John";
			case PASSWORD: return "Welcome@123";
			case PHONE_NUMBER: return "9826396465";
			default: throw new IllegalArgumentException();
		}
	}
}