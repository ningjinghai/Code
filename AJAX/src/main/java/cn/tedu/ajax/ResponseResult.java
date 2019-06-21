package cn.tedu.ajax;

public class ResponseResult {
	
	private Integer state;
	private String message;
	public ResponseResult(Integer state, String message) {
		super();
		this.state = state;
		this.message = message;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
