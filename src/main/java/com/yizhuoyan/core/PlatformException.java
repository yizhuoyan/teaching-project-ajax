package com.yizhuoyan.core;

/**
 * 全局异常类
 * @author yizhuoyan@hotmail.com
 *
 */
public class PlatformException extends RuntimeException {

	public PlatformException(String message, Throwable cause) {
		super(message, cause);
	}

	public PlatformException(String message) {
		super(message);
	}
	
	public static void throwWithMessage(String message,Object... args) {
		for (int i = 0; i < args.length; i++) {
			message=message.replaceFirst("\\?",String.valueOf(args[0]));
		}
		throw new PlatformException(message);
	}
	
	
}
