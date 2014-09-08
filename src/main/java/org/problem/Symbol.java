package main.java.org.problem;

import main.java.org.problem.utils.ArrayUtils;

/**
 * 符号类，用来定义符号"I, V, X, L, C, D, M"的特性和操作
 *
 */
public class Symbol implements Comparable<Symbol> {
	private char symbolChar;
	private int value;
	private boolean canRepeat;
	private boolean canSubtract;
	private char[] subtractSymbolList;
			
	public Symbol(char symbolsChar) {
		this.symbolChar = symbolsChar;
		this.value = SymbolDefinition.getSymbolsValue(symbolsChar);
		this.canRepeat = SymbolDefinition.isRepeatSymbols(symbolsChar);
		this.canSubtract = SymbolDefinition.isSubtractableSymbols(symbolsChar);
		this.subtractSymbolList = SymbolDefinition.getSubtractSymbols(symbolsChar);
	}
	
	/**
	 * 得到符号对应的数值ֵ
	 */
	public int getSymbolValue() {
		return this.value;
	}
	
	/**
	 * 符号是否可以连续重复
	 */
	public boolean isRepeatable() {
		return this.canRepeat;
	}
	
	/**
	 * 符号是否能作相减操作
	 */
	public boolean isSubtractable() {
		return this.canSubtract;
	}
	
	/**
	 * 符号对应的字符 
	 */
	public char getChar() {
		return this.symbolChar;
	}
	
	/**
	 * 得到可与当前符号相减的字符列表 
	 */
	public char[] getSubtractSymbolList() {
		return subtractSymbolList;
	}
	
	/**
	 * 判断符号是否能与当前的符号进行相减操作
	 */
	public boolean canBeSubtracted(Symbol symbol) {
		if (subtractSymbolList != null) {
			return (ArrayUtils.search(subtractSymbolList, symbol.getChar()) >= 0);
		}
		return false;
	}

	@Override
	public int compareTo(Symbol other) {
		if (other != null) {
			if (this.value == other.value)
				return 0;
			
			if (this.value > other.value) 
				return 1;
		}
		return -1;
	}
	
	@Override
	public boolean equals(Object other) {
		if (this == other)
			return true;
		
		if (other !=  null && other.getClass() == this.getClass()) {
			Symbol symbol = (Symbol) other;
            return symbol.value == this.value;
        }
        return false;
	}
	
	@Override 
	public String toString() {
		return symbolChar + "";
	}
}
