package cn.itcast.exception;

public class CustomException extends Exception {
	private static final long serialVersionUID = 1L;
	//异常信息
	private String exceptionMsg;

	public String getExceptionMsg() {
		return exceptionMsg;
	}

	public void setExceptionMsg(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}

	//构造方法
	public CustomException(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}
	
	
}
