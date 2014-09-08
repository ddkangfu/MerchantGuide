package test.java.org.problem.inputanalyzer;

import static org.junit.Assert.*;

import main.java.org.problem.inputanalyzer.InputAnalysisResult;

import org.junit.Test;

/**
 * 类InputAnalysisResult的单元测试类
 *
 */
public class TestInputAnalysisResult {
	/**
	 * 商品单价操作测试
	 */
	@Test
	public void testUnitPrice() {
		InputAnalysisResult rs = new InputAnalysisResult();
		rs.addUnitPrice("Gold", 14450);
		rs.addUnitPrice("Silver", 17);
		rs.addUnitPrice("Iron", 195.5f);
		
		assertEquals(14450, rs.getUnitPrice("Gold"), 0.1f);
		assertEquals(17, rs.getUnitPrice("Silver"), 0.1f);
		assertEquals(195.5f, rs.getUnitPrice("Iron"), 0.1f);
		assertEquals(null, rs.getUnitPrice(""));
		assertEquals(null, rs.getUnitPrice("ABCD"));
	}
	
	/**
	 * 星际符号定义操作测试
	 */
	@Test
	public void testSymbolDefiniton() {
		InputAnalysisResult rs = new InputAnalysisResult();
		rs.addDefinition("pish", 'I');
		rs.addDefinition("prok", 'L');
		
		assertEquals('I', rs.getRomanSymbol("pish").charValue());
		assertEquals('L', rs.getRomanSymbol("prok").charValue());
		assertEquals(null, rs.getRomanSymbol(""));
		assertEquals(null, rs.getRomanSymbol("ABC"));
	}
	
	/**
	 * 问题命令的答案操作测试
	 */
	@Test
	public void testAnswerOperation() {
		InputAnalysisResult rs = new InputAnalysisResult();
		rs.addAnswer("answer1");
		rs.addAnswer("answer2");
		
		assertEquals("answer1", rs.pollAnswer());
		assertEquals("answer2", rs.pollAnswer());
		assertEquals(null, rs.pollAnswer());
	}
}
