package main.java.org.problem.inputanalyzer;

import main.java.org.problem.exception.SymbolParseException;

/**
 * 输入指令的处理类的抽象类
 *
 */
public abstract class InputHandler {
	InputHandler nextHandler;
	
	InputAnalysisResult analyseResult;
	
	public InputHandler(InputAnalysisResult result) {
		this.analyseResult = result;
	}
	
	/**
	 * 设置处理链的下一个处理者
	 */
	public void setNextHandler(InputHandler nextHandler) {
		this.nextHandler = nextHandler;
	}
	
	public abstract void handleCommand(String command) throws SymbolParseException;
	
	/**
	 * 从给定的字符串中分解出定义类型命令中定义的字符串，如使用“tegj is L”命令中定义的tegi得到L，
	 * 并与其它解析得到的符号拼接成符号字符串
	 */
	protected String getSymbolsStrFromCommand(String command) {
		String[] symbols = command.split("\\s+");
		if (symbols.length <= 0)
			return null;
		
		String result = "";
		for (String s : symbols) {
			Character symbolChar = analyseResult.getRomanSymbol(s);
			if (symbolChar != null) {
				result += symbolChar;
			}
		}
		
		return result;
	}
	
	/**
	 * 分析出命令行里的Silver、Gold、Iron这样的产品名称
	 */
	protected String getProductName(String command) {
		String[] symbols = command.split("\\s+");
		if (symbols.length > 0) {
			for (String s : symbols) {
				Character symbolChar = analyseResult.getRomanSymbol(s);
				if (symbolChar == null) {
					return s;
				}
			}
		}
		return "";
	}
}
