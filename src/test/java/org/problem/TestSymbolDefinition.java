package test.java.org.problem;

import static org.junit.Assert.*;
import main.java.org.problem.SymbolDefinition;

import org.junit.Test;

/**
 * 类SymbolDefinition的单元测试类
 *
 */
public class TestSymbolDefinition {
	
	/**
	 * 符号可连续重复性测试
	 */
	@Test
	public void testIsRepeatSymbols() {
		//Only 'I', 'X', 'C', 'M' can be repeated.
		boolean isRepeatSymI = SymbolDefinition.isRepeatSymbols('I');
		assertTrue(isRepeatSymI);
		
		boolean isRepeatSymV = SymbolDefinition.isRepeatSymbols('V');
		assertFalse(isRepeatSymV);
		
		boolean isRepeatSymX = SymbolDefinition.isRepeatSymbols('X');
		assertTrue(isRepeatSymX);
		
		boolean isRepeatSymL = SymbolDefinition.isRepeatSymbols('L');
		assertFalse(isRepeatSymL);
		
		boolean isRepeatSymC = SymbolDefinition.isRepeatSymbols('C');
		assertTrue(isRepeatSymC);
		
		boolean isRepeatSymD = SymbolDefinition.isRepeatSymbols('D');
		assertFalse(isRepeatSymD);
		
		boolean isRepeatSymM = SymbolDefinition.isRepeatSymbols('M');
		assertTrue(isRepeatSymM);
	}
	
	/**
	 * 符号是否能作为减数的测试
	 */
	@Test
	public void testIsSubtractSymbols() {
		//Only 'I', 'X', 'C'can be subtracted.
		boolean isSubtractSymI = SymbolDefinition.isSubtractableSymbols('I');
		assertTrue(isSubtractSymI);
		
		boolean isSubtractSymV = SymbolDefinition.isSubtractableSymbols('V');
		assertFalse(isSubtractSymV);
		
		boolean isSubtractSymX = SymbolDefinition.isSubtractableSymbols('X');
		assertTrue(isSubtractSymX);
		
		boolean isSubtractSymL = SymbolDefinition.isSubtractableSymbols('L');
		assertFalse(isSubtractSymL);
		
		boolean isSubtractSymC = SymbolDefinition.isSubtractableSymbols('C');
		assertTrue(isSubtractSymC);
		
		boolean isSubtractSymD = SymbolDefinition.isSubtractableSymbols('D');
		assertFalse(isSubtractSymD);
		
		boolean isSubtractSymM = SymbolDefinition.isSubtractableSymbols('M');
		assertFalse(isSubtractSymM);
		
		boolean isSubtractSymA = SymbolDefinition.isSubtractableSymbols('A');
		assertFalse(isSubtractSymA);
	}
	
	/**
	 * 符号对应值测试
	 */
	@Test 
	public void testSymbolsValue() {
		assertEquals(1, SymbolDefinition.getSymbolsValue('I'));
		assertEquals(5, SymbolDefinition.getSymbolsValue('V'));
		assertEquals(10, SymbolDefinition.getSymbolsValue('X'));
		assertEquals(50, SymbolDefinition.getSymbolsValue('L'));
		assertEquals(100, SymbolDefinition.getSymbolsValue('C'));
		assertEquals(500, SymbolDefinition.getSymbolsValue('D'));
		assertEquals(1000, SymbolDefinition.getSymbolsValue('M'));
		
		assertEquals(0, SymbolDefinition.getSymbolsValue('B'));
	}
	
	/**
	 * 指定符号的被减数列表测试
	 */
	@Test
	public void testGetSubtractSymbols() {
		char[] symbolsI = SymbolDefinition.getSubtractSymbols('I');
		assertTrue(symbolsI != null);
		assertEquals('V', symbolsI[0]);
		assertEquals('X', symbolsI[1]);
		
		char[] symbolsX = SymbolDefinition.getSubtractSymbols('X');
		assertTrue(symbolsX != null);
		assertEquals('L', symbolsX[0]);
		assertEquals('C', symbolsX[1]);
		
		char[] symbolsC = SymbolDefinition.getSubtractSymbols('C');
		assertTrue(symbolsC != null);
		assertEquals('D', symbolsC[0]);
		assertEquals('M', symbolsC[1]);
		
		for (char c : new char[]{'V', 'L', 'D', 'M', 'A'}) {
			char[] symbols = SymbolDefinition.getSubtractSymbols(c);
			assertTrue(symbols == null);
		}
	}
	
	/**
	 * 无效符号字符串验证测试
	 */
	@Test 
	public void testValidateSymbolsStr() {
		try {
			SymbolDefinition.validateSymbolsStr("");
			fail("validateSymbolsStr() should throw an exception if Symbol string is null or blank.");
		} catch(Exception ex) {
			assertEquals("Symbol string is null or blank.", ex.getMessage());
		}
		
		try {
			SymbolDefinition.validateSymbolsStr("XBV");
			fail("validateSymbolsStr() should throw an exception if 'B' is included in symbolstr.");
		} catch(Exception ex) {
			assertEquals("'B' is invalid symbol.", ex.getMessage());
		}
	}
}
