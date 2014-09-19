package test.java.org.problem.symbolanalyzer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import main.java.org.problem.symbolanalyzer.SymbolAnalysisResult;
import main.java.org.problem.symbolanalyzer.SymbolAnalyzer;

import org.junit.Test;

public class TestSymbolAnalyzer {
	
	/**
	 * 被解析字符串无连续重复、无相减操作的普通测试
	 */
	@Test 
	public void testBlankSymbolString() {
		try {
			SymbolAnalyzer.doAnalyse("");
			fail("getSymbolStrValue() should throw an exception if symbolstr is blank string.");
		} catch(Exception ex) {
			assertEquals("Symbol string is null or blank.", ex.getMessage());
		}
	}
	
	@Test
	public void testInvalidSymbolString() {
		try {
			SymbolAnalyzer.doAnalyse("MBC");
			fail("getSymbolStrValue() should throw an exception if symbolstr include invalid symbol.");
		} catch(Exception ex) {
			assertEquals("'B' is invalid symbol.", ex.getMessage());
		}
	}
	
	@Test
	public void testNormalCase() {
		SymbolAnalysisResult result = SymbolAnalyzer.doAnalyse("V");
		assertEquals("<Normal>V", result.getEntry(0).toString());
		
		result = SymbolAnalyzer.doAnalyse("MVI");
		assertEquals("<Normal>M", result.getEntry(0).toString());
		assertEquals("<Normal>V", result.getEntry(1).toString());
		assertEquals("<Normal>I", result.getEntry(2).toString());
	}
	
	//should_return_repeated_entity_when_roman_letter_is_repeated_twice_and_both_of_the_at_the_end_of_lineø()
	
	/**
	 * 被解析字符串起始位置出现连续重复字符的测试
	 */
	@Test
	public void testMultipleRepeatAtBegining() {
		//不允许重复的字符不能重复
		try {
			SymbolAnalyzer.doAnalyse("LLVI");
			fail("getSymbolStrValue() should throw an exception if unrepeatable symbol is repeated.");
		} catch(Exception ex) {
			assertEquals("'L' can't be repeated.", ex.getMessage());
		}
		
		//开始位置连续重复两次
		SymbolAnalysisResult result = SymbolAnalyzer.doAnalyse("MMVI");
		assertEquals("<Repeat>MM", result.getEntry(0).toString());
		assertEquals("<Normal>V", result.getEntry(1).toString());
		assertEquals("<Normal>I", result.getEntry(2).toString());		
		
		//开始位置连续重复三次
		result = SymbolAnalyzer.doAnalyse("MMMVI");
		assertEquals("<Repeat>MMM", result.getEntry(0).toString());
		assertEquals("<Normal>V", result.getEntry(1).toString());
		assertEquals("<Normal>I", result.getEntry(2).toString());		
		
		//不允许连续重复四次
		try {
			SymbolAnalyzer.doAnalyse("MMMMVI");
			fail("getSymbolStrValue() should throw an exception if symbol is repeated more than 3 times.");
		} catch (Exception ex) {
			assertEquals("'M' is repeated more than 3 times.", ex.getMessage());
		}
		
		//起始位置使用较小符号分割后连续重复四次
		result = SymbolAnalyzer.doAnalyse("MMMIMVI");
		assertEquals("<Repeat>MMMM", result.getEntry(0).toString());
		assertEquals("<Normal>V", result.getEntry(1).toString());
		assertEquals("<Normal>I", result.getEntry(2).toString());		
		
		//相减操作前只能有一个较小的可相减符号
		try {
			SymbolAnalyzer.doAnalyse("XXXMVI");
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
			SymbolAnalyzer.doAnalyse("MCLLVI");
			fail("getSymbolStrValue() should throw an exception if 'L' be repeated.");
		} catch(Exception ex) {
			assertEquals("'L' can't be repeated.", ex.getMessage());
		}
		
		//中间位置连续重复两次
		SymbolAnalysisResult result = SymbolAnalyzer.doAnalyse("MCCXI");
		assertEquals("<Normal>M", result.getEntry(0).toString());
		assertEquals("<Repeat>CC", result.getEntry(1).toString());
		assertEquals("<Normal>X", result.getEntry(2).toString());	
		assertEquals("<Normal>I", result.getEntry(3).toString());
		
		//中间位置连续重复三次
		result = SymbolAnalyzer.doAnalyse("MCCCXI");
		assertEquals("<Normal>M", result.getEntry(0).toString());
		assertEquals("<Repeat>CCC", result.getEntry(1).toString());
		assertEquals("<Normal>X", result.getEntry(2).toString());	
		assertEquals("<Normal>I", result.getEntry(3).toString());
		
		//不允许连续重复四次
		try {
			SymbolAnalyzer.doAnalyse("MCCCCXI");
			fail("getSymbolStrValue() should throw an exception if 'C' is repeated more than 3 times.");
		} catch (Exception ex) {
			assertEquals("'C' is repeated more than 3 times.", ex.getMessage());
		}
		
		//中间位置使用较小符号分割后连续重复四次
		result = SymbolAnalyzer.doAnalyse("MCCCICXI");
		assertEquals("<Normal>M", result.getEntry(0).toString());
		assertEquals("<Repeat>CCCC", result.getEntry(1).toString());
		assertEquals("<Normal>X", result.getEntry(2).toString());	
		assertEquals("<Normal>I", result.getEntry(3).toString());
		
		//相减操作前不能有重复的较小符号
		try {
			SymbolAnalyzer.doAnalyse("MXXDVI");
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
			SymbolAnalyzer.doAnalyse("MCLL");
			fail("getSymbolStrValue() should throw an exception if 'L' be repeated.");
		} catch(Exception ex) {
			assertEquals("'L' can't be repeated.", ex.getMessage());
		}
		
		//结束位置连续重复两次
		SymbolAnalysisResult result = SymbolAnalyzer.doAnalyse("MCCII");
		assertEquals("<Normal>M", result.getEntry(0).toString());
		assertEquals("<Repeat>CC", result.getEntry(1).toString());
		assertEquals("<Repeat>II", result.getEntry(2).toString());	
		
		//结束位置连续重复三次
		result = SymbolAnalyzer.doAnalyse("MCCCIII");
		assertEquals("<Normal>M", result.getEntry(0).toString());
		assertEquals("<Repeat>CCC", result.getEntry(1).toString());
		assertEquals("<Repeat>III", result.getEntry(2).toString());
		
		//不允许连续重复四次
		try {
			SymbolAnalyzer.doAnalyse("MCCCC");
			fail("getSymbolStrValue() should throw an exception if 'C' is repeated more than 3 times.");
		} catch (Exception ex) {
			assertEquals("'C' is repeated more than 3 times.", ex.getMessage());
		}
		
		//不允许连续重复四次
		try {
			SymbolAnalyzer.doAnalyse("MMMM");
			fail("getSymbolStrValue() should throw an exception if 'M' is repeated more than 3 times.");
		} catch (Exception ex) {
			assertEquals("'M' is repeated more than 3 times.", ex.getMessage());
		}
		
		//中间位置使用较小符号分割后连续重复四次
		result = SymbolAnalyzer.doAnalyse("MCCCIC");
		assertEquals("<Normal>M", result.getEntry(0).toString());
		assertEquals("<Repeat>CCCC", result.getEntry(1).toString());
		
		//相减操作前不能有连续重复的较小符号
		try {
			SymbolAnalyzer.doAnalyse("MXXD");
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
			SymbolAnalyzer.doAnalyse("DMXI");
			fail("getSymbolStrValue() should throw an exception if 'D' is subtracted.");
		} catch(Exception ex) {
			assertEquals("'D' can never be subtracted.", ex.getMessage());
		}
		
		//只有IV,IX, XL, XC, CD, CM的情况下才能做相减操作
		try {
			SymbolAnalyzer.doAnalyse("IL");
			fail("getSymbolStrValue() should throw an exception if 'I' is subtracted from invaid symbols.");
		} catch(Exception ex) {
			assertEquals("'I' can be subtracted from 'V' and 'X' only.", ex.getMessage());
		}
		
		//每组相减操作都必须满足后一组的被减数小于前一组被减数的要求
		try {
			SymbolAnalyzer.doAnalyse("IXCM");
			fail("getSymbolStrValue() should throw an exception if 'M' is greater than 'X'.");
		} catch(Exception ex) {
			assertEquals("The 'M' is greater than 'X'.", ex.getMessage());
		}
		
		//中间位置相减测试
		SymbolAnalysisResult result = SymbolAnalyzer.doAnalyse("CMXIV");
		assertEquals("<Subtract>CM", result.getEntry(0).toString());
		assertEquals("<Normal>X", result.getEntry(1).toString());
		assertEquals("<Subtract>IV", result.getEntry(2).toString());
	}
	
	/**
	 * 被解析字符串中间位置出现相减求值操作的测试
	 */
	@Test
	public void testSubtractInMiddle() {
		//不处理不能相减的字符
		try {
			SymbolAnalyzer.doAnalyse("MLCXI");
			fail("getSymbolStrValue() should throw an exception if 'L' is subtracted.");
		} catch(Exception ex) {
			assertEquals("'L' can never be subtracted.", ex.getMessage());
		}
		
		//只有IV,IX, XL, XC, CD, CM的情况下才能做相减操作
		try {
			SymbolAnalyzer.doAnalyse("MIMV");
			fail("getSymbolStrValue() should throw an exception if 'I' is subtracted from invaid symbols.");
		} catch(Exception ex) {
			assertEquals("'I' can be subtracted from 'V' and 'X' only.", ex.getMessage());
		}
		
		//中间位置相减测试
		SymbolAnalysisResult result = SymbolAnalyzer.doAnalyse("MXCVI");
		assertEquals("<Normal>M", result.getEntry(0).toString());
		assertEquals("<Subtract>XC", result.getEntry(1).toString());
		assertEquals("<Normal>V", result.getEntry(2).toString());
		assertEquals("<Normal>I", result.getEntry(3).toString());
	}
	
	/**
	 * 被解析字符串结束位置出现相减求值操作的测试
	 */
	@Test
	public void testSubtractAtTheEnd() {
		//不处理不能相减的字符
		try {
			SymbolAnalyzer.doAnalyse("MLC");
			fail("getSymbolStrValue() should throw an exception if 'L' is subtracted.");
		} catch(Exception ex) {
			assertEquals("'L' can never be subtracted.", ex.getMessage());
		}
		
		//只有IV,IX, XL, XC, CD, CM的情况下才能做相减操作
		try {
			SymbolAnalyzer.doAnalyse("MIM");
			fail("getSymbolStrValue() should throw an exception if 'I' is subtracted from invaid symbols.");
		} catch(Exception ex) {
			assertEquals("'I' can be subtracted from 'V' and 'X' only.", ex.getMessage());
		}
		
		//结束位置相减测试
		SymbolAnalysisResult result = SymbolAnalyzer.doAnalyse("MXCIX");
		assertEquals("<Normal>M", result.getEntry(0).toString());
		assertEquals("<Subtract>XC", result.getEntry(1).toString());
		assertEquals("<Subtract>IX", result.getEntry(2).toString());
	}
}
