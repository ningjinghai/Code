package cn.tedu.store.service.ex;

public class UpdateException extends ServiceException {

	private static final long serialVersionUID = 7526554852199537812L;

	public UpdateException() {
		super();
	}

	public UpdateException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UpdateException(String message, Throwable cause) {
		super(message, cause);
	}

	public UpdateException(String message) {
		super(message);

	}

	public UpdateException(Throwable cause) {
		super(cause);

	}

}
