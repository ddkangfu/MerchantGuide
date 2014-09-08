package main.java.org.problem.symbolanalyzer;

import main.java.org.problem.Symbol;

/**
 * 保存需要相减操作的分析结果Entry，如"MDCCCIV",其中的"IV"可以保存到此Entry中，
 * 保存后为"<Repeat>IV",减数为第1个元素，被减数为第2个元素
 */
public class SubtractSymbolAnalysisEntry extends SymbolAnalysisEntry {
	public SubtractSymbolAnalysisEntry(Symbol ...symbols) {
		super(symbols);
		this.desc = "Subtract";
	}
	
	@Override
	public int getSymbolsEntryValue() {
		if (this.symbols.length == 0 || this.symbols.length != 2)
			return 0;
		
		int minValue = this.symbols[0].getSymbolValue();
		int maxValue = this.symbols[1].getSymbolValue();
		return maxValue - minValue;
	}
}
