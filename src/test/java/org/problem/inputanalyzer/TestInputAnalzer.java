package test.java.org.problem.inputanalyzer;

import static org.junit.Assert.assertEquals;
import main.java.org.problem.inputanalyzer.InputAnalyzer;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestInputAnalzer {
	private static InputAnalyzer ia;
	
	@BeforeClass
	public static void beforeClass() {
		//定义和描述性的命令只初始化一次供后面回答问题和验证用即可
		ia  = new InputAnalyzer();
		
		ia.doAnalyse("glob is I");
		ia.doAnalyse("prok is V");
		ia.doAnalyse("pish is X");
		ia.doAnalyse("tegj is L");
		//ia.doAnalyse("1278 is D");
		ia.doAnalyse("hnga is C");
		ia.doAnalyse("mpor is D");
		ia.doAnalyse("atre is M");
		//ia.doAnalyse("#*&! is C");
		
		ia.doAnalyse("glob glob Silver is 34 Credits");
		ia.doAnalyse("glob prok Gold is 57800 Credits");
		ia.doAnalyse("pish pish Iron is 3910 Credits");
	}
	
	/**
	 * 测试定义型命令，如 glob is I
	 */
	@Test
	public void testDefinitionCommand() {
		assertEquals('I', ia.getAnalyseResult().getRomanSymbol("glob").charValue());
		assertEquals('V', ia.getAnalyseResult().getRomanSymbol("prok").charValue());
		assertEquals('X', ia.getAnalyseResult().getRomanSymbol("pish").charValue());
		assertEquals('L', ia.getAnalyseResult().getRomanSymbol("tegj").charValue());
		assertEquals('C', ia.getAnalyseResult().getRomanSymbol("hnga").charValue());
		assertEquals('D', ia.getAnalyseResult().getRomanSymbol("mpor").charValue());
		assertEquals('M', ia.getAnalyseResult().getRomanSymbol("atre").charValue());
	}
	
	/**
	 * 测试描述型命令，如 glob glob Silver is 34 Credits
	 */
	@Test
	public void testDescriptionCommand() {
		assertEquals(17.0, ia.getAnalyseResult().getUnitPrice("Silver").floatValue(), 0.1f);
		assertEquals(14450.0, ia.getAnalyseResult().getUnitPrice("Gold").floatValue(), 0.1f);
		assertEquals(195.5, ia.getAnalyseResult().getUnitPrice("Iron").floatValue(), 0.1f);
	}
	
	/**
	 * 测试How Much型命令， 如 how much is pish tegj glob glob ?
	 */
	@Test
	public void testHowMuchCommand() {
		ia.doAnalyse("how much is pish tegj glob glob ?");
		
		assertEquals("pish tegj glob glob is 42", ia.getAnalyseResult().pollAnswer());
	}
	
	/**
	 * 测试How many型命令，如 how many Credits is glob prok Silver ?
	 */
	@Test
	public void testHowManyCommand() {
		ia.doAnalyse("how many Credits is glob prok Silver ?");
		ia.doAnalyse("how many Credits is glob prok Gold ?");
		ia.doAnalyse("how many Credits is glob prok Iron ?");
		//ia.doAnalyse("how many Silver is glob Gold ?");
		
		assertEquals("glob prok Silver is 68 Credits", ia.getAnalyseResult().pollAnswer());
		assertEquals("glob prok Gold is 57800 Credits", ia.getAnalyseResult().pollAnswer());
		assertEquals("glob prok Iron is 782 Credits", ia.getAnalyseResult().pollAnswer());
		//assertEquals("glob Gold is 850 Silver", ia.getAnalyseResult().pollAnswer());
	}
	
	/**
	 * 测试不能实别的命令
	 */
	@Test
	public void testInvalidCommand() {
		ia.doAnalyse("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?");
		
		assertEquals("I have no idea what you are talking about", ia.getAnalyseResult().pollAnswer());
	}
}
