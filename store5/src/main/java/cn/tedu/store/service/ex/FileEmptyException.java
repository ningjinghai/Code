package cn.tedu.store.service.ex;

/**
 * 文件为空的异常
 * @author soft01
 *
 */
public class FileEmptyException extends FileUploadException {

	private static final long serialVersionUID = -106278002618373043L;

	public FileEmptyException() {
		super();
	}

	public FileEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FileEmptyException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileEmptyException(String message) {
		super(message);
	}

	public FileEmptyException(Throwable cause) {
		super(cause);
	}

}
