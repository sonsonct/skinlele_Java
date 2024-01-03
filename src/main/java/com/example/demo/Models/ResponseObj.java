package com.example.demo.Models;

public class ResponseObj {
	private Boolean status;
    private String message;
    private Object data;
	public ResponseObj(Boolean status, String message, Object data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}
	public ResponseObj() {
		
	}
	@Override
	public String toString() {
		return "ResponseObj [status=" + status + ", message=" + message + ", data=" + data + "]";
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
    
}
