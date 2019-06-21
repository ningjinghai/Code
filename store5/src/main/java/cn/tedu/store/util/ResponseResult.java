package cn.tedu.store.util;

import java.io.Serializable;

public class ResponseResult<T> implements Serializable{
	
	/**
	 * 控制器向客户端响应结果的数据类型
	 * 
	 * @param <T> 如果控制器会向客户端响应某些数据，则表示响应的数据类型
	 */
	private static final long serialVersionUID = 1526000014731415966L;
	private Integer state;//代号
	private String message;//错误的提示信息
	private T data;
	
	
	
	
	public ResponseResult() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ResponseResult(Integer state) {
		super();
		this.state = state;
	}
	
	
	public ResponseResult(Integer state, String message) {
		super();
		this.state = state;
		this.message = message;
	}
	
	public ResponseResult(Integer state, T data) {
		super();
		this.state = state;
		this.data = data;
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
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "ResponseResult [state=" + state + ", message=" + message + ", data=" + data + "]";
	}
	

	
}
