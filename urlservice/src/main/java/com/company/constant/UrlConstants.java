package com.company.constant;

/**
 * @author pushkar.chauhan@wissen.com
 */
public enum UrlConstants {
	APP_NAME,
	URL_KAFKA_GET_URL,
	URL_MONGO_GET_URL ,
	URL_MONGO_POST_URL ,
	URL_REDIS_GET_URL ,
	URL_REDIS_POST_URL,
	URL_ID,
	URL_NAME,
	EMAIL ,
	USERNAME ,
	PASSWORD ,
	PHONE_NUMBER;

	@Override
	public String toString() {
		switch(this) {
			case APP_NAME: return "UrlShortener";
			case URL_KAFKA_GET_URL: return "http://localhost:9021/api/v2/urls/";
			case URL_MONGO_GET_URL: return "/api/v1/urls/{id}";
			case URL_MONGO_POST_URL: return "/api/v1/urls";
			case URL_REDIS_GET_URL: return "/api/v2/urls/{id}";
			case URL_REDIS_POST_URL: return "/api/v2/urls";
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