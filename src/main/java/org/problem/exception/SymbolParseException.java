package main.java.org.problem.exception;

/**
 * 解析符号字符串时发生的异常
 *
 */
public class SymbolParseException extends RuntimeException {
	
	private static final long serialVersionUID = -1389172694029070899L;
	
	public SymbolParseException(String msg) {
		super(msg);
	}

}
