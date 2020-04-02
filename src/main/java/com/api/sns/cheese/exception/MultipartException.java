package com.api.sns.cheese.exception;

/**
 * ファイルエラー例外(400)
 */
public class MultipartException extends RuntimeException {

	public MultipartException(String message) {
		super(message);
	}

}
