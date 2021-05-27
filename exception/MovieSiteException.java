package com.louise.movieSiteHibernate.service.exception;

/**
 * Root of our exception hierarchy
 * @author louise
 *
 */
@SuppressWarnings("serial")
public class MovieSiteException extends RuntimeException {
	private ErrorCode errorCode;

	public MovieSiteException(ErrorCode code, String message, Throwable throwable) {
		super(message, throwable);
		this.errorCode = code;
	}
	
	public MovieSiteException(ErrorCode code, String message) {
		super(message);
		this.errorCode = code;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

}
