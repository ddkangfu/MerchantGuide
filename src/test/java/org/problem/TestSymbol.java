package test.java.org.problem;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import main.java.org.problem.Symbol;

import org.junit.Test;

/**
 * Symbol类的单元测试
 *
 */
public class TestSymbol {
	static Symbol symbolI = new Symbol('I');
	static Symbol symbolV = new Symbol('V');
	static Symbol symbolX = new Symbol('X');
	static Symbol symbolL = new Symbol('L');
	static Symbol symbolC = new Symbol('C');
	static Symbol symbolD = new Symbol('D');
	static Symbol symbolM = new Symbol('M');
	
	/**
	 * 普通操作测试
	 */
	@Test
	public void operationTest() {
		assertTrue(symbolI.equals(symbolI));
		assertFalse(symbolI.equals(symbolM));
		
		assertEquals(1, symbolM.compareTo(symbolI));
		assertEquals(0, symbolV.compareTo(symbolV));
		assertEquals(-1, symbolI.compareTo(symbolM));
		
		assertEquals("I", symbolI.toString());
		assertEquals("V", symbolV.toString());
	}
	
	/**
	 * 符号对应值字符的测试
	 */
	@Test
	public void testSymbolChar() {
		assertEquals('I', symbolI.getChar());
		assertEquals('V', symbolV.getChar());
		assertEquals('X', symbolX.getChar());
		assertEquals('L', symbolL.getChar());
		assertEquals('C', symbolC.getChar());
		assertEquals('D', symbolD.getChar());
		assertEquals('M', symbolM.getChar());
	}
	
	/**
	 * 符号对应数值的测试
	 */
	@Test
	public void testSymbolValue() {
		assertEquals(1, symbolI.getSymbolValue());
		assertEquals(5, symbolV.getSymbolValue());
		assertEquals(10, symbolX.getSymbolValue());
		assertEquals(50, symbolL.getSymbolValue());
		assertEquals(100, symbolC.getSymbolValue());
		assertEquals(500, symbolD.getSymbolValue());
		assertEquals(1000, symbolM.getSymbolValue());
	}
	
	/**
	 * 符号是否可以连续重复的测试
	 */
	@Test
	public void testSymbolIsRepeatable() {
		assertTrue(symbolI.isRepeatable());
		assertFalse(symbolV.isRepeatable());
		assertTrue(symbolX.isRepeatable());
		assertFalse(symbolL.isRepeatable());
		assertTrue(symbolC.isRepeatable());
		assertFalse(symbolD.isRepeatable());
		assertTrue(symbolM.isRepeatable());
	}
	
	/**
	 * 符号是否可以被减性的测试
	 */
	@Test
	public void testSymbolSubtractable() {
		assertTrue(symbolI.isSubtractable());
		assertFalse(symbolV.isSubtractable());
		assertTrue(symbolX.isSubtractable());
		assertFalse(symbolL.isSubtractable());
		assertTrue(symbolC.isSubtractable());
		assertFalse(symbolD.isSubtractable());
		assertFalse(symbolM.isSubtractable());
	}
	
	/**
	 * 可以作为被减数的符号列表的测试
	 */
	@Test
	public void testSymboleSubtractList() {
		char[] listI = symbolI.getSubtractSymbolList();
		assertTrue(listI != null);
		assertEquals('V', listI[0]);
		assertEquals('X', listI[1]);
		
		char[] listV = symbolV.getSubtractSymbolList();
		assertTrue(listV == null);
		
		char[] listX = symbolX.getSubtractSymbolList();
		assertTrue(listX != null);
		assertEquals('L', listX[0]);
		assertEquals('C', listX[1]);
		
		char[] listL = symbolL.getSubtractSymbolList();
		assertTrue(listL == null);
		
		char[] listC = symbolC.getSubtractSymbolList();
		assertTrue(listC != null);
		assertEquals('D', listC[0]);
		assertEquals('M', listC[1]);
		
		char[] listD = symbolD.getSubtractSymbolList();
		assertTrue(listD == null);
		
		char[] listM = symbolM.getSubtractSymbolList();
		assertTrue(listM == null);
	}
	
	/**
	 * 符号是否可以作为减数的测试
	 */
	@Test
	public void testSymbolCanBeSubtracted() {
		assertTrue(symbolI.canBeSubtracted(symbolV));
		assertFalse(symbolI.canBeSubtracted(symbolM));
		
		assertFalse(symbolV.canBeSubtracted(symbolD));
		
		assertTrue(symbolX.canBeSubtracted(symbolL));
		assertFalse(symbolX.canBeSubtracted(symbolM));
		
		assertFalse(symbolL.canBeSubtracted(symbolD));
		
		assertTrue(symbolC.canBeSubtracted(symbolD));
		assertFalse(symbolC.canBeSubtracted(symbolI));
	}
}
