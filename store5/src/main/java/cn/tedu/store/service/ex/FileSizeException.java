package cn.tedu.store.service.ex;

public class FileSizeException extends FileUploadException {

	private static final long serialVersionUID = -5085341119910729153L;

	public FileSizeException() {
		super();
	}

	public FileSizeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FileSizeException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileSizeException(String message) {
		super(message);
	}

	public FileSizeException(Throwable cause) {
		super(cause);
	}

}
