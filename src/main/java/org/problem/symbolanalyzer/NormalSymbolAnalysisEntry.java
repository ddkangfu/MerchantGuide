package main.java.org.problem.symbolanalyzer;

import main.java.org.problem.Symbol;

/**
 * 普通的分析结果Entry，除重复和相减操作以外的，都可以使用此Entry来保存结果
 * 如"MDCCCIV",其中的"MD"可以保存到此Entry中，保存后为"<Normal>MD"
 */
public class NormalSymbolAnalysisEntry extends SymbolAnalysisEntry {
	
	public NormalSymbolAnalysisEntry(Symbol ...symbols){
		super(symbols);
		this.desc = "Normal";
	}

	@Override
	public int getSymbolsEntryValue() {
		int result = 0;
		
		for (Symbol symbol : this.symbols) {
			result += symbol.getSymbolValue();
		}
		
		return result;
	}

}
