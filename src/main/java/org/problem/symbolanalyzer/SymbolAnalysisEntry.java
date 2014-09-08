package main.java.org.problem.symbolanalyzer;

import java.util.Arrays;

import main.java.org.problem.Symbol;

/**
 * 符号字符串分析结果Entry类的抽象类
 *
 */
public abstract class SymbolAnalysisEntry {
	protected Symbol[] symbols;
	protected String desc;
	
	public SymbolAnalysisEntry(Symbol ...symbols) {
		this.symbols = Arrays.copyOf(symbols, symbols.length);
		this.desc = "Base";
	}
	
	/**
	 * 获取被分析到此Entry里的符号的个数
	 */
	public int getSymbolsCount() {
		return symbols.length;
	}
	
	/**
	 * 获取被分析到此Entry里的符号的数组
	 */
	public Symbol[] getSymbols() {
		return symbols;
	}
	
	/**
	 * 向符号数组中追加
	 */
	public void appendSymbol(Symbol symbol) {
		symbols = Arrays.copyOf(symbols, symbols.length+1);
		symbols[symbols.length-1] = symbol;
	}
	
	/**
	 * 得到符号Entry的对应的数值，由具体类根据自己的类型决定运算规则
	 */
	public abstract int getSymbolsEntryValue();
	
	/**
	 * 使用“<类型>符号”的方式打印对象，方便进行单元测试
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < symbols.length; i++) {
			sb.append(symbols[i]);
		}
		return "<" + desc + ">" + sb.toString();
	}
}
