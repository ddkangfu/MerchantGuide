package main.java.org.problem.exception;

/**
 * 输入命令解析错误异常  
 *
 */
public class AnalyseInputTextError extends RuntimeException {

	private static final long serialVersionUID = -2494179447115376587L;

	public AnalyseInputTextError(String msg) {
		super(msg);
	}
}
