package main.java.org.problem.symbolanalyzer;

import main.java.org.problem.Symbol;

/**
 * 保存连续重复的分析结果Entry，如"MDCCCIV",其中的"CCC"可以保存到此Entry中，保存后为"<Repeat>CCC"
 *
 */
public class RepeatSymbolAnalysisEntry extends SymbolAnalysisEntry {
	public RepeatSymbolAnalysisEntry(Symbol ...symbols) {
		super(symbols);
		this.desc = "Repeat";
	}
	
	@Override
	public int getSymbolsEntryValue() {
		if (this.symbols.length == 0)
			return 0;
		
		return symbols[0].getSymbolValue() * this.symbols.length;
	}
}
