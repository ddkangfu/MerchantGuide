package main.java.org.problem;

import main.java.org.problem.exception.SymbolParseException;
import main.java.org.problem.symbolanalyzer.SymbolAnalysisResult;
import main.java.org.problem.symbolanalyzer.SymbolAnalyzer;

/**
 * 符号字符串数值计算类
 *
 */
public class SymbolCalculator {
	/**
	 * 分析符号字符串并得到分析结果，最后返回分析结果的总值ֵ
	 */
	public static int getSymbolStrValue(String SymbolStr) throws SymbolParseException{
		SymbolAnalysisResult result = SymbolAnalyzer.doAnalyse(SymbolStr);
		if (result != null)
			return result.getResultSumValue();
		return 0;
	}
}
