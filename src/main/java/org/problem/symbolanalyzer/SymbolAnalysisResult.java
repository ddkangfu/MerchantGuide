package main.java.org.problem.symbolanalyzer;

import java.util.ArrayList;
import java.util.List;

/**
 * 符号字符串分析结果类，类中使用例表存储一系列的结果entry。
 * 根据不同的操作，将字符串分析为普通Entry，重复Entry和相减的Entry，
 * 再对这些Entry求和，就可以得取被分析字符串的总值。
 */
public class SymbolAnalysisResult {
	private List<SymbolAnalysisEntry> resultList;
	
	public SymbolAnalysisResult() {
		this.resultList = new ArrayList<SymbolAnalysisEntry>();
	}
	
	/**
	 * 添加分析结果entry
	 */
	public void add(SymbolAnalysisEntry entry) {
		resultList.add(entry);
	}
	
	/**
	 * 获取最后一个分析结果entry
	 */
	public SymbolAnalysisEntry getLastOne() {
		if (resultList.isEmpty())
			return null;
		
		return resultList.get(resultList.size() - 1);
	}
	
	/**
	 * 获取指定类型的最后一个分析结果entry
	 */
	public SymbolAnalysisEntry getLastOne(Class<?> entryType) {
		if (resultList.isEmpty())
			return null;
		
		for (int i=resultList.size()-1; i >= 0; i--) {
			if (resultList.get(i).getClass().equals(entryType)) {
				return resultList.get(i);
			}
		}
		return null;
	}
	
	/**
	 * 获取指定索引的分析结果entry
	 */
	public SymbolAnalysisEntry getEntry(int index) {
		if (!resultList.isEmpty() && index >= 0 && index < resultList.size())
			return resultList.get(index);
		else 
			return null;
	}
	
	public List<SymbolAnalysisEntry> getResultList() {
		return this.resultList;
	}
	
	/**
	 * 获取所有结果Entry对应数值的总和
	 */
	public int getResultSumValue() {
		int value = 0;
		for (SymbolAnalysisEntry entry :resultList) {
			value += entry.getSymbolsEntryValue();
		}
		return value;
	}
}
