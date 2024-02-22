
package com.chia.multienty.core.exception;

import com.chia.multienty.core.domain.enums.HttpResultEnum;
import com.chia.multienty.core.domain.enums.InternalExceptionEnum;
import org.redisson.api.RLock;

/**
 * 业务异常统一处理
 *
 */
public class KutaRuntimeException extends BaseException {

	public KutaRuntimeException(Integer errorCode) {

		super(errorCode, HttpResultEnum.parse(errorCode));
	}

	public KutaRuntimeException(String errorMessage) {
		super(500, errorMessage);
	}

	public KutaRuntimeException(InternalExceptionEnum expEnum) {
		super(expEnum.getCode(),expEnum.getMessage());
	}

	public KutaRuntimeException(HttpResultEnum resultEnum) {
		super(resultEnum.getCode(), resultEnum.getMessage());
	}

	public KutaRuntimeException(HttpResultEnum resultEnum, String format) {
		super(resultEnum.getCode(), String.format(resultEnum.getMessage(), format));
	}

	public KutaRuntimeException(Integer errorCode, RLock redissonLock) {
		super(errorCode, HttpResultEnum.parse(errorCode),redissonLock);
	}
	public KutaRuntimeException(HttpResultEnum resultEnum, RLock redissonLock) {
		super(resultEnum.getCode(), resultEnum.getMessage(),redissonLock);
	}

	public KutaRuntimeException(Integer errorCode, Object errorData) {
		super(errorCode, HttpResultEnum.parse(errorCode),errorData);
	}
	public KutaRuntimeException(HttpResultEnum resultEnum, Object errorData) {
		super(resultEnum.getCode(), resultEnum.getMessage(), errorData);
	}

	public KutaRuntimeException(Integer errorCode, Throwable t) {
		super(errorCode, HttpResultEnum.parse(errorCode), t);
	}

	public KutaRuntimeException(HttpResultEnum resultEnum, Throwable t) {
		super(resultEnum.getCode(), resultEnum.getMessage(), t);
	}

	public KutaRuntimeException(Integer errorCode, String message) {
		super(errorCode, message);
	}


	public KutaRuntimeException(Integer errorCode, String message, Object errorData) {
		super(errorCode, message, errorData);
	}

	/**
	 * 异常堆栈增加错误代码和绑定变量
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("业务异常，异常代码[").append(this.code).append("]\n");
		sb.append("异常信息:[").append(this.message).append("]\n");
		return sb.toString();
	}

}
