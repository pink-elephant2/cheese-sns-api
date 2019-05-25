package com.api.sns.cheese.exception;

/**
 * リソースが存在しない例外(404)
 */
public class NotFoundException extends RuntimeException {

	public NotFoundException(String message) {
		super(message);
	}

}
