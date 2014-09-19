package main.java.org.problem.symbolanalyzer;

import java.util.LinkedList;
import java.util.Queue;

import main.java.org.problem.Symbol;
import main.java.org.problem.SymbolDefinition;
import main.java.org.problem.exception.InvalidSymbolException;
import main.java.org.problem.exception.SymbolParseException;

/**
 * 符号字符串分析类，用来分析如"MCLI"这样的字符串，并得到一个分析结果
 *
 */
public class SymbolAnalyzer {
	/**
	 * 符号字符串分析函数
	 */
	public static SymbolAnalysisResult doAnalyse(String text) throws SymbolParseException, InvalidSymbolException {
		SymbolDefinition.validateSymbolsStr(text);
		
		SymbolAnalysisResult result = new SymbolAnalysisResult();
		
		Queue<Symbol> symbolQueue = convertToQueue(text);
		if (symbolQueue == null || symbolQueue.size() == 0)
			return null;
		
		while(!symbolQueue.isEmpty()) {
			Symbol currentSymbol = symbolQueue.poll();
			Symbol nextSymbol = symbolQueue.peek();
			
			if (nextSymbol != null) { //有下一个元素
				if (currentSymbol.compareTo(nextSymbol) > 0) {
					greaterThanNextSymbol(result, currentSymbol);
				} else if (currentSymbol.equals(nextSymbol)) {
					equalToNextSymbol(result, symbolQueue, currentSymbol);
				} else {
					lesserThanNextSymbol(result, symbolQueue, currentSymbol,
							nextSymbol);
				}
			} else {
				handleLastSymbol(result, currentSymbol);
			}
		}
		return result;
	}
	
	/**
	 * 处理符号字符串中的最后一个字符
	 */
	private static void handleLastSymbol(SymbolAnalysisResult result, Symbol currentSymbol) {
		if (result.getResultList().size() > 0) {
			Symbol previousSymbol = result.getLastOne().getSymbols()[result.getLastOne().getSymbols().length - 1];
			if (currentSymbol.compareTo(previousSymbol) < 0) {
				greaterThanNextSymbol(result, currentSymbol);
			} else if (currentSymbol.equals(previousSymbol)) {
				result.getLastOne(RepeatSymbolAnalysisEntry.class).appendSymbol(currentSymbol);
			} else {
				SymbolAnalysisEntry lastEntry = result.getLastOne();
				if (lastEntry != null && isRepeatAnalysisEntry(lastEntry)) {//说明最后一个是重复性
					throw new SymbolParseException("'" + currentSymbol 
							+ "' can't be appeared after repeated '" + previousSymbol + "'");
				}
				
				throw new SymbolParseException("The '" + currentSymbol + "' is greater than previous one.");
			}
		} else {
			greaterThanNextSymbol(result, currentSymbol);
		}
	}
	
	/**
	 * 理当符号字符串的当前处理字符小于下一个字符的情况
	 */
	private static void lesserThanNextSymbol(SymbolAnalysisResult result,
			Queue<Symbol> symbolQueue, Symbol currentSymbol, Symbol nextSymbol) {
		if (!currentSymbol.isSubtractable()) {
			throw new SymbolParseException("'" + currentSymbol + "' can never be subtracted.");
		}
		
		if (!currentSymbol.canBeSubtracted(nextSymbol)) {
			throw new SymbolParseException("'" + currentSymbol + "' can be subtracted from '" + currentSymbol.getSubtractSymbolList()[0] 
					+ "' and '" + currentSymbol.getSubtractSymbolList()[1] + "' only.");
		}
		
		SymbolAnalysisEntry lastSubtractEntry = result.getLastOne(SubtractSymbolAnalysisEntry.class);
		//上一次相减操作的被减数小于此次相减操作的被减数，说明未按从大到小的排序，不作处理
		if (lastSubtractEntry != null && lastSubtractEntry.getSymbols().length == 2
				&& lastSubtractEntry.getSymbols()[1].compareTo(nextSymbol) < 0) {
			throw new SymbolParseException("The '" + nextSymbol
					+ "' is greater than '" + lastSubtractEntry.getSymbols()[1] + "'.");
		}
		
		result.add(new SubtractSymbolAnalysisEntry(currentSymbol, symbolQueue.poll()));
	}
	
	/**
	 * 处理当符号字符串的当前处理字符等于下一个字符的情况
	 */
	private static void equalToNextSymbol(SymbolAnalysisResult result, Queue<Symbol> symbolQueue, Symbol currentSymbol) {
		Symbol nextSymbol;
		if (!currentSymbol.isRepeatable())
			throw new SymbolParseException("'" + currentSymbol + "' can't be repeated.");
		
		result.add(new RepeatSymbolAnalysisEntry(currentSymbol, symbolQueue.poll())); //连续两个相等
		
		//判断连续三个相等的情况
		if ((nextSymbol = symbolQueue.peek()) != null) {
			if (currentSymbol.equals(nextSymbol)) {
				result.getLastOne().appendSymbol(symbolQueue.poll());
				
				//判断第四个元素
				if ((nextSymbol = symbolQueue.peek()) != null) {
					if (currentSymbol.compareTo(nextSymbol) > 0) {
						//三个连续字符后出现一个较小的字符，需要比较第五个字符是否相等，来判断是否为4个连续字符
						if (symbolQueue.size() > 1) {
							nextSymbol = ((LinkedList<Symbol>)symbolQueue).get(1);
							if (currentSymbol.equals(nextSymbol)){
								symbolQueue.poll();
								result.getLastOne().appendSymbol(symbolQueue.poll());
							}
						}
					} else if (currentSymbol.equals(nextSymbol)) {
						throw new SymbolParseException("'" + currentSymbol + "' is repeated more than " 
								+ SymbolDefinition.MAX_REPEAT_TIMES + " times.");
					} else {
						throw new SymbolParseException("'" + nextSymbol 
								+ "' can't be appeared after repeated '" + currentSymbol + "'");
					}
				}
			} else if (currentSymbol.compareTo(nextSymbol) < 0) {//两个连续字符后不能出一个比它更大的字符
				throw new SymbolParseException("'" + nextSymbol.getChar() 
						+ "' can't be appeared after repeated '" + currentSymbol.getChar() + "'");
			}
		}
	}

	/**
	 * 处理当符号字符串的当前处理字符大于下一个字符的情况
	 */
	private static void greaterThanNextSymbol(SymbolAnalysisResult result,
			Symbol currentSymbol) {
		result.add(new NormalSymbolAnalysisEntry(currentSymbol));
	}
	
	/**
	 * 判断分析后的Entry是否为可重复性的Entry，即RepeatAnalysisEntry
	 */
	private static boolean isRepeatAnalysisEntry(SymbolAnalysisEntry entry) {
		return entry.getClass().equals(RepeatSymbolAnalysisEntry.class);
	}
	
	/**
	 * 将要分析的符号字符串转换成Symbol对象的队列
	 */
	private static Queue<Symbol> convertToQueue(String symbolStr) {
		if (symbolStr == null || symbolStr.isEmpty())
			return null;
		
		char[] symbols = symbolStr.toCharArray();
		Queue<Symbol> result = new LinkedList<Symbol>();
		for (char symbol : symbols) {
			result.offer(new Symbol(symbol));
		}
		return result;
	}
}
