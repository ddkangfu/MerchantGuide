package main.java.org.problem;

import java.util.HashMap;
import java.util.Map;

import main.java.org.problem.exception.InvalidSymbolException;
import main.java.org.problem.exception.SymbolParseException;
import main.java.org.problem.utils.ArrayUtils;

/**
 * 符号处理规则定义类
 *
 */
public class SymbolDefinition {
	public final static char[] NO_REPEAT_SYMBOLS = new char[] {'D', 'L', 'V'}; //不能连续重复的符号
	public final static char[] REPEAT_SYMBOLS = new char[] {'I', 'X', 'C', 'M'}; //允许连续重复的符号

	private static Map<Character, Integer> symbolValueDict = new HashMap<Character, Integer>(); //符号与数字对应的字典
	private static Map<Character, char[]> subtractableSymbolDict = new HashMap<Character, char[]>(); //可相减的符号字，列出了相减时可组合的情况
	
	public final static int MAX_REPEAT_TIMES = 3;
	
	static {
		symbolValueDict.put('I', 1);
		symbolValueDict.put('V', 5);
		symbolValueDict.put('X', 10);
		symbolValueDict.put('L', 50);
		symbolValueDict.put('C', 100);
		symbolValueDict.put('D', 500);
		symbolValueDict.put('M', 1000);
		
		subtractableSymbolDict.put('I', new char[]{'V', 'X'});
		subtractableSymbolDict.put('X', new char[]{'L', 'C'});
		subtractableSymbolDict.put('C', new char[]{'D', 'M'});
	}
	
	/**
	 * 判断符号是否可连续重复
	 */
	public static boolean isRepeatSymbols(char symbols) {
		return (ArrayUtils.search(REPEAT_SYMBOLS, symbols) >= 0);
	}
	
	/**
	 * 判断符号是否可以作为减数
	 */
	public static boolean isSubtractableSymbols(char symbols) {
		return subtractableSymbolDict.keySet().contains(symbols);
	}
	
	/**
	 *  查询符号对应的数字
	 */
	public static int getSymbolsValue(Character symbols) {
		if (symbolValueDict.containsKey(symbols))
			return symbolValueDict.get(symbols).intValue();
		return 0;
	}
	
	/**
	 * 查询符号对应的可以作为被减数的符号
	 */
	public static char[] getSubtractSymbols(char symbols) {
		return subtractableSymbolDict.get(symbols);
	}
	
	/**
	 * 验证符号字符串是否为空或包含不合法的符号  
	 */
	public static void validateSymbolsStr(String symbolsStr) throws InvalidSymbolException, SymbolParseException {
		if (symbolsStr == null || symbolsStr.isEmpty())
			throw new SymbolParseException("Symbol string is null or blank.");
		
		char[] symbols = symbolsStr.toCharArray();
		for (char c : symbols) {
			if (!symbolValueDict.keySet().contains(c)) {
				throw new InvalidSymbolException("'" + c + "' is invalid symbol.");
			}
		}
	}
}
