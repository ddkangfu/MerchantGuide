package test.java.org.problem.symbolanalyzer;

import static org.junit.Assert.*;
import main.java.org.problem.Symbol;
import main.java.org.problem.symbolanalyzer.NormalSymbolAnalysisEntry;
import main.java.org.problem.symbolanalyzer.RepeatSymbolAnalysisEntry;
import main.java.org.problem.symbolanalyzer.SubtractSymbolAnalysisEntry;
import main.java.org.problem.symbolanalyzer.SymbolAnalysisEntry;
import main.java.org.problem.symbolanalyzer.SymbolAnalysisResult;

import org.junit.Test;

/**
 * 类SymbolAnalysisResult的单元测试类
 *
 */
public class TestSymbolAnalysisResult {
	
	/**
	 * 测试求整组Entry的总和的操作
	 */
	@Test
	public void testGetResultSumValue() {
		SymbolAnalysisResult result = new SymbolAnalysisResult();
		
		assertEquals(0, result.getResultSumValue());
		
		result.add(new NormalSymbolAnalysisEntry(new Symbol('M'), new Symbol('D')));
		assertEquals(1500, result.getResultSumValue());
		
		result.add(new SubtractSymbolAnalysisEntry(new Symbol('X'), new Symbol('L')));
		assertEquals(1540, result.getResultSumValue());
		
		result.add(new RepeatSymbolAnalysisEntry(new Symbol('I'), new Symbol('I'), new Symbol('I')));
		assertEquals(1543, result.getResultSumValue());
	}
	
	/**
	 * 测试访问最后一个Entry元素的操作
	 */
	@Test
	public void testGetLastOne() {
		SymbolAnalysisResult result = new SymbolAnalysisResult();
		
		SymbolAnalysisEntry entry = result.getLastOne();
		assertTrue(entry == null);
		
		entry = result.getLastOne(SubtractSymbolAnalysisEntry.class);
		assertTrue(entry == null);
		
		result.add(new NormalSymbolAnalysisEntry(new Symbol('M'), new Symbol('D')));
		entry = result.getLastOne();
		assertTrue(entry != null);
		assertEquals("<Normal>MD", entry.toString());
		
		result.add(new SubtractSymbolAnalysisEntry(new Symbol('X'), new Symbol('L')));
		entry = result.getLastOne();
		assertTrue(entry != null);
		assertEquals("<Subtract>XL", entry.toString());
		
		result.add(new RepeatSymbolAnalysisEntry(new Symbol('I'), new Symbol('I'), new Symbol('I')));
		entry = result.getLastOne();
		assertTrue(entry != null);
		assertEquals("<Repeat>III", entry.toString());
		
		entry = result.getLastOne(SubtractSymbolAnalysisEntry.class);
		assertTrue(entry != null);
		assertEquals("<Subtract>XL", entry.toString());
	}
	
	/**
	 * 测试使用索引值访问结果Entry元素
	 */
	@Test
	public void testGetEntry() {
		SymbolAnalysisResult result = new SymbolAnalysisResult();
		
		SymbolAnalysisEntry entry = result.getEntry(1);
		assertTrue(entry == null);
		
		entry = result.getEntry(-1);
		assertTrue(entry == null);
		
		result.add(new NormalSymbolAnalysisEntry(new Symbol('M'), new Symbol('D')));
		result.add(new SubtractSymbolAnalysisEntry(new Symbol('X'), new Symbol('L')));
		result.add(new RepeatSymbolAnalysisEntry(new Symbol('I'), new Symbol('I'), new Symbol('I')));
		
		entry = result.getEntry(1);
		assertTrue(entry != null);
		assertEquals("<Subtract>XL", entry.toString());
		
		entry = result.getEntry(2);
		assertTrue(entry != null);
		assertEquals("<Repeat>III", entry.toString());
	}
}
