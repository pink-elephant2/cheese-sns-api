package com.api.sns.cheese.aop;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.api.sns.cheese.exception.MultipartException;
import com.api.sns.cheese.exception.NotFoundException;

/**
 * 共通例外処理
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * バリデーションエラーを処理(400)
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<Object> handleValidationException(ValidationException ex, HttpServletResponse response) {
		return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	/**
	 * マルチパートファイルエラーを処理(400)
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MultipartException.class)
	public ResponseEntity<Object> handleMaltipartException(MultipartException ex, HttpServletResponse response) {
		return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	/**
	 * NotFoundを処理(404)
	 */
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Object> handleNotFoundException(NotFoundException ex, HttpServletResponse response) {
		return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

}
