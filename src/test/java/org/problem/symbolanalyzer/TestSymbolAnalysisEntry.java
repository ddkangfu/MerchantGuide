package test.java.org.problem.symbolanalyzer;

import static org.junit.Assert.assertEquals;
import main.java.org.problem.Symbol;
import main.java.org.problem.symbolanalyzer.NormalSymbolAnalysisEntry;
import main.java.org.problem.symbolanalyzer.RepeatSymbolAnalysisEntry;
import main.java.org.problem.symbolanalyzer.SubtractSymbolAnalysisEntry;
import main.java.org.problem.symbolanalyzer.SymbolAnalysisEntry;

import org.junit.Test;

public class TestSymbolAnalysisEntry {
	
	/**
	 * 测试基类的一些方法
	 */
	@Test
	public void testBaseClassMethod() {
		SymbolAnalysisEntry entry = new NormalSymbolAnalysisEntry(new Symbol('M'), new Symbol('D'));
		assertEquals("<Normal>MD", entry.toString());
		
		assertEquals(2, entry.getSymbolsCount());
		assertEquals(new Symbol('M'), entry.getSymbols()[0]);
		assertEquals(new Symbol('D'), entry.getSymbols()[1]);
		
		entry.appendSymbol(new Symbol('C'));
		assertEquals(3, entry.getSymbolsCount());
		assertEquals(new Symbol('C'), entry.getSymbols()[2]);
	}
	
	/**
	 * NormalSymbolAnalysisEntry类的求总值操作
	 */
	@Test
	public void testNormalSymbolAnalysisEntry() {
		SymbolAnalysisEntry entry = new NormalSymbolAnalysisEntry(new Symbol('M'));
		assertEquals(1000, entry.getSymbolsEntryValue());
		
		entry.appendSymbol(new Symbol('D'));
		assertEquals(1500, entry.getSymbolsEntryValue());
		
		entry.appendSymbol(new Symbol('C'));
		assertEquals(1600, entry.getSymbolsEntryValue());
		
		entry.appendSymbol(new Symbol('I'));
		assertEquals(1601, entry.getSymbolsEntryValue());
	}
	
	/**
	 * RepeatSymbolAnalysisEntry类的求总值操作
	 */
	@Test
	public void testRepeatSymbolAnalysisEntry() {
		SymbolAnalysisEntry entry = new RepeatSymbolAnalysisEntry(new Symbol('M'), new Symbol('M'));
		assertEquals(2000, entry.getSymbolsEntryValue());
		
		entry.appendSymbol(new Symbol('C'));
		assertEquals(3000, entry.getSymbolsEntryValue());
		
		entry.appendSymbol(new Symbol('C'));
		assertEquals(4000, entry.getSymbolsEntryValue());
	}
	
	/**
	 * SubtractSymbolAnalysisEntry类的求总值操作
	 */
	@Test
	public void testSubstractSymbolAnalysisEntry() {
		SymbolAnalysisEntry entry = new SubtractSymbolAnalysisEntry(new Symbol('C'), new Symbol('D'));
		assertEquals(400, entry.getSymbolsEntryValue());
	}
}
