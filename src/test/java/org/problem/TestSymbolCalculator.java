package test.java.org.problem;

import static org.junit.Assert.*;


import main.java.org.problem.SymbolCalculator;

import org.junit.Test;

/**
 *SymbolCalculator类的单元测试类 
 *
 */
public class TestSymbolCalculator {
	@Test
	public void testNormal() {
		try {
			SymbolCalculator.getSymbolStrValue("");
			fail("getSymbolStrValue() should throw an exception if symbolstr is blank string.");
		} catch(Exception ex) {
			assertEquals("Symbol string is null or blank.", ex.getMessage());
		}
		
		//测试无效的符号
		try {
			SymbolCalculator.getSymbolStrValue("MBC");
			fail("getSymbolStrValue() should throw an exception if symbolstr include invalid symbol.");
		} catch(Exception ex) {
			assertEquals("'B' is invalid symbol.", ex.getMessage());
		}
		
		int result = SymbolCalculator.getSymbolStrValue("V");
		assertEquals(5, result);
		
		result = SymbolCalculator.getSymbolStrValue("MVI");
		assertEquals(1006, result);
	}
	
	/**
	 * 被解析字符串起始位置出现连续重复字符的测试
	 */
	@Test
	public void testMultipleRepeatAtBegining() {
		//不允许重复的字符不能重复
		try {
			SymbolCalculator.getSymbolStrValue("LLVI");
			fail("getSymbolStrValue() should throw an exception if unrepeatable symbol is repeated.");
		} catch(Exception ex) {
			assertEquals("'L' can't be repeated.", ex.getMessage());
		}
		
		//开始位置连续重复两次
		int result = SymbolCalculator.getSymbolStrValue("MMVI");
		assertEquals(2006, result);		
		
		//开始位置连续重复三次
		result = SymbolCalculator.getSymbolStrValue("MMMVI");
		assertEquals(3006, result);
		
		//不允许连续重复四次
		try {
			SymbolCalculator.getSymbolStrValue("MMMMVI");
			fail("getSymbolStrValue() should throw an exception if symbol is repeated more than 3 times.");
		} catch (Exception ex) {
			assertEquals("'M' is repeated more than 3 times.", ex.getMessage());
		}
		
		//起始位置使用较小符号分割后连续重复四次
		result = SymbolCalculator.getSymbolStrValue("MMMIMVI");
		assertEquals(4006, result);
		
		//相减操作前只能有一个较小的可相减符号
		try {
			SymbolCalculator.getSymbolStrValue("XXXMVI");
			fail("getSymbolStrValue() should throw an exception if 'X' appeared before 'M'.");
		} catch (Exception ex) {
			assertEquals("'M' can't be appeared after repeated 'X'", ex.getMessage());
		}
	}
	
	/**
	 * 被解析字符串中间位置出现连续重复字符的测试
	 */
	@Test
	public void testMultipleRepeatInTheMiddle() {
		//不允许重复的字符不能重复
		try {
			SymbolCalculator.getSymbolStrValue("MCLLVI");
			fail("getSymbolStrValue() should throw an exception if 'L' be repeated.");
		} catch(Exception ex) {
			assertEquals("'L' can't be repeated.", ex.getMessage());
		}
		
		//中间位置连续重复两次
		int result = SymbolCalculator.getSymbolStrValue("MCCXI");
		assertEquals(1211, result);
		
		//中间位置连续重复三次
		result = SymbolCalculator.getSymbolStrValue("MCCCXI");
		assertEquals(1311, result);
		
		//不允许连续重复四次
		try {
			SymbolCalculator.getSymbolStrValue("MCCCCXI");
			fail("getSymbolStrValue() should throw an exception if 'C' is repeated more than 3 times.");
		} catch (Exception ex) {
			assertEquals("'C' is repeated more than 3 times.", ex.getMessage());
		}
		
		//中间位置使用较小符号分割后连续重复四次
		result = SymbolCalculator.getSymbolStrValue("MCCCICXI");
		assertEquals(1411, result);
		
		//相减操作前不能有重复的较小符号
		try {
			SymbolCalculator.getSymbolStrValue("MXXDVI");
			fail("getSymbolStrValue() should throw an exception if 'X' is appeared before 'D'.");
		} catch (Exception ex) {
			assertEquals("'D' can't be appeared after repeated 'X'", ex.getMessage());
		}
	}
	
	/**
	 * 被解析字符串结束位置出现连续重复字符的测试
	 */
	@Test
	public void testMultipleRepeatAtTheEnd() {
		//不允许重复的字符不能重复
		try {
			SymbolCalculator.getSymbolStrValue("MCLL");
			fail("getSymbolStrValue() should throw an exception if 'L' be repeated.");
		} catch(Exception ex) {
			assertEquals("'L' can't be repeated.", ex.getMessage());
		}
		
		//结束位置连续重复两次
		int result = SymbolCalculator.getSymbolStrValue("MCCII");
		assertEquals(1202, result);
		
		//结束位置连续重复三次
		result = SymbolCalculator.getSymbolStrValue("MCCCIII");
		assertEquals(1303, result);
		
		//不允许连续重复四次
		try {
			SymbolCalculator.getSymbolStrValue("MCCCC");
			fail("getSymbolStrValue() should throw an exception if 'C' is repeated more than 3 times.");
		} catch (Exception ex) {
			assertEquals("'C' is repeated more than 3 times.", ex.getMessage());
		}
		
		//不允许连续重复四次
		try {
			SymbolCalculator.getSymbolStrValue("MMMM");
			fail("getSymbolStrValue() should throw an exception if 'M' is repeated more than 3 times.");
		} catch (Exception ex) {
			assertEquals("'M' is repeated more than 3 times.", ex.getMessage());
		}
		
		//中间位置使用较小符号分割后连续重复四次
		result = SymbolCalculator.getSymbolStrValue("MCCCIC");
		assertEquals(1400, result);
		
		//相减操作前不能有连续重复的较小符号
		try {
			SymbolCalculator.getSymbolStrValue("MXXD");
			fail("getSymbolStrValue() should throw an exception if 'X' is appeared before 'D'.");
		} catch (Exception ex) {
			assertEquals("'D' can't be appeared after repeated 'X'", ex.getMessage());
		}
	}
	
	/**
	 * 被解析字符串起始位置出现相减求值操作的测试
	 */
	@Test
	public void testSubtractAtBegining() {
		//不处理不能相减的字符
		try {
			SymbolCalculator.getSymbolStrValue("DMXI");
			fail("getSymbolStrValue() should throw an exception if 'D' is subtracted.");
		} catch(Exception ex) {
			assertEquals("'D' can never be subtracted.", ex.getMessage());
		}
		
		//只有IV,IX, XL, XC, CD, CM的情况下才能做相减操作
		try {
			SymbolCalculator.getSymbolStrValue("IL");
			fail("getSymbolStrValue() should throw an exception if 'I' is subtracted from invaid symbols.");
		} catch(Exception ex) {
			assertEquals("'I' can be subtracted from 'V' and 'X' only.", ex.getMessage());
		}
		
		//每组相减操作都必须满足后一组的被减数小于前一组被减数的要求
		try {
			SymbolCalculator.getSymbolStrValue("IXCM");
			fail("getSymbolStrValue() should throw an exception if 'M' is greater than 'X'.");
		} catch(Exception ex) {
			assertEquals("The 'M' is greater than 'X'.", ex.getMessage());
		}
		
		//中间位置相减测试
		int result = SymbolCalculator.getSymbolStrValue("CMXIV");
		assertEquals(914, result);
	}
	
	/**
	 * 被解析字符串中间位置出现相减求值操作的测试
	 */
	@Test
	public void testSubtractInMiddle() {
		//不处理不能相减的字符
		try {
			SymbolCalculator.getSymbolStrValue("MLCXI");
			fail("getSymbolStrValue() should throw an exception if 'L' is subtracted.");
		} catch(Exception ex) {
			assertEquals("'L' can never be subtracted.", ex.getMessage());
		}
		
		//只有IV,IX, XL, XC, CD, CM的情况下才能做相减操作
		try {
			SymbolCalculator.getSymbolStrValue("MIMV");
			fail("getSymbolStrValue() should throw an exception if 'I' is subtracted from invaid symbols.");
		} catch(Exception ex) {
			assertEquals("'I' can be subtracted from 'V' and 'X' only.", ex.getMessage());
		}
		
		//中间位置相减测试
		int result = SymbolCalculator.getSymbolStrValue("MXCVI");
		assertEquals(1096, result);
	}
	
	/**
	 * 被解析字符串结束位置出现相减求值操作的测试
	 */
	@Test
	public void testSubtractAtTheEnd() {
		//不处理不能相减的字符
		try {
			SymbolCalculator.getSymbolStrValue("MLC");
			fail("getSymbolStrValue() should throw an exception if 'L' is subtracted.");
		} catch(Exception ex) {
			assertEquals("'L' can never be subtracted.", ex.getMessage());
		}
		
		//只有IV,IX, XL, XC, CD, CM的情况下才能做相减操作
		try {
			SymbolCalculator.getSymbolStrValue("MIM");
			fail("getSymbolStrValue() should throw an exception if 'I' is subtracted from invaid symbols.");
		} catch(Exception ex) {
			assertEquals("'I' can be subtracted from 'V' and 'X' only.", ex.getMessage());
		}
		
		//结束位置相减测试
		int result = SymbolCalculator.getSymbolStrValue("MXCIX");
		assertEquals(1099, result);
	}
}
