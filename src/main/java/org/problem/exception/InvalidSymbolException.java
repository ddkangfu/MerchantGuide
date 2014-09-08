package main.java.org.problem.exception;

/**
 * 无效符号异常，当被解析的符号不在"I, V, X, L, C, D, M"中时，触发此异常
 *
 */
public class InvalidSymbolException extends RuntimeException {

	private static final long serialVersionUID = -4514197742961542560L;

	public InvalidSymbolException(String msg) {
		super(msg);
	}

}
