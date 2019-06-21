package cn.tedu.store.service.ex;

public class FileStateException extends FileUploadException {

	private static final long serialVersionUID = 2061749938735791055L;

	public FileStateException() {
		super();
	}

	public FileStateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FileStateException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileStateException(String message) {
		super(message);
	}

	public FileStateException(Throwable cause) {
		super(cause);
	}

}
