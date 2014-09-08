package test.java.org.problem.inputanalyzer;

import static org.junit.Assert.*;

import java.lang.reflect.Method;

import main.java.org.problem.inputanalyzer.DefinitionInputHandler;
import main.java.org.problem.inputanalyzer.DescriptionInputHandler;
import main.java.org.problem.inputanalyzer.HowManyInputHandler;
import main.java.org.problem.inputanalyzer.HowMuchInputHandler;
import main.java.org.problem.inputanalyzer.InputAnalysisResult;
import main.java.org.problem.inputanalyzer.InputHandler;
import main.java.org.problem.inputanalyzer.InvalidInputHandler;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 类InputHandler及其子类的单元测试类
 *
 */
public class TestInputHandler {
	private static InputAnalysisResult analyseResult = new InputAnalysisResult();
	
	private static InputHandler definitionHandler = new DefinitionInputHandler(analyseResult);
	private static InputHandler descriptionHandler = new DescriptionInputHandler(analyseResult);
	private static InputHandler howmuchHandler = new HowMuchInputHandler(analyseResult);
	private static InputHandler howmanyHandler = new HowManyInputHandler(analyseResult);
	private static InputHandler invalidHandler = new InvalidInputHandler(analyseResult);
	
	@BeforeClass
	public static void beforeClass() {
		definitionHandler.setNextHandler(descriptionHandler);
		descriptionHandler.setNextHandler(howmuchHandler);
		howmuchHandler.setNextHandler(howmanyHandler);
		howmanyHandler.setNextHandler(invalidHandler);
		
		definitionHandler.handleCommand("glob is I");
		definitionHandler.handleCommand("prok is V");
		definitionHandler.handleCommand("pish is X");
		definitionHandler.handleCommand("tegj is L");
	}
	/**
	 * 测试基类的方法
	 */
	@Test
	public void testBaseMehtod() {
		try {
			Method method = InputHandler.class.getDeclaredMethod("getSymbolsStrFromCommand", new Class[]{String.class});
			method.setAccessible(true);
			Object result = method.invoke(definitionHandler, new Object[]{"pish tegj glob glob"});
			assertEquals("XLII", result);
			
			result = method.invoke(definitionHandler, new Object[]{"bbbb tegj glob glob"});
			assertEquals("LII", result);
			
			result = method.invoke(definitionHandler, new Object[]{""});
			assertEquals("", result);
			
			
			method = InputHandler.class.getDeclaredMethod("getProductName", new Class[]{String.class});
			method.setAccessible(true);
			result = method.invoke(definitionHandler, new Object[]{"glob glob Silver"});
			assertEquals("Silver", result);
			
			result = method.invoke(definitionHandler, new Object[]{"pish tegj glob glob"});
			assertEquals("", result);
			
			result = method.invoke(definitionHandler, new Object[]{""});
			assertEquals("", result);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * 测试DefinitionHandler的操作
	 */
	@Test
	public void testDefinitionHandler() {
		assertTrue(analyseResult.getRomanSymbol("glob") != null);
		assertEquals('I', analyseResult.getRomanSymbol("glob").charValue());
		
		assertTrue(analyseResult.getRomanSymbol("prok") != null);
		assertEquals('V', analyseResult.getRomanSymbol("prok").charValue());
		
		assertTrue(analyseResult.getRomanSymbol("pish") != null);
		assertEquals('X', analyseResult.getRomanSymbol("pish").charValue());
		
		assertTrue(analyseResult.getRomanSymbol("tegj") != null);
		assertEquals('L', analyseResult.getRomanSymbol("tegj").charValue());
		
		assertTrue(analyseResult.getRomanSymbol("abcd") == null);
		assertTrue(analyseResult.getRomanSymbol("") == null);
	}
	
	/**
	 * 测试DescriptionHandler的操作
	 */
	@Test
	public void testDescriptionHandler() {
		definitionHandler.handleCommand("glob glob Silver is 34 Credits");
		definitionHandler.handleCommand("glob prok Gold is 57800 Credits");
		definitionHandler.handleCommand("pish pish Iron is 3910 Credits");
		
		assertTrue(analyseResult.getUnitPrice("Silver") != null);
		assertEquals(17.0f, analyseResult.getUnitPrice("Silver").floatValue(), 0.01f);
		
		assertTrue(analyseResult.getUnitPrice("Gold") != null);
		assertEquals(14450.0f, analyseResult.getUnitPrice("Gold").floatValue(), 0.01f);
		
		assertTrue(analyseResult.getUnitPrice("Iron") != null);
		assertEquals(195.5f, analyseResult.getUnitPrice("Iron").floatValue(), 0.01f);
		
		assertTrue(analyseResult.getUnitPrice("Other") == null);
		assertTrue(analyseResult.getUnitPrice("") == null);
	}

	/**
	 * 测试HowMuchInputHandler的操作
	 */	
	@Test
	public void testHowMuchInputHandler() {
		definitionHandler.handleCommand("how much is pish tegj glob glob ?");
		definitionHandler.handleCommand("how much is tegj glob ?");
		definitionHandler.handleCommand("how much is tegj prok glob ?");
		
		definitionHandler.handleCommand("how much is ?");
		
		String answer = analyseResult.pollAnswer();
		assertTrue(answer != null);
		assertEquals("pish tegj glob glob is 42", answer);
		
		answer = analyseResult.pollAnswer();
		assertTrue(answer != null);
		assertEquals("tegj glob is 51", answer);
		
		answer = analyseResult.pollAnswer();
		assertTrue(answer != null);
		assertEquals("tegj prok glob is 56", answer);
		
		answer = analyseResult.pollAnswer();
		assertTrue(answer != null);
		assertEquals("I have no idea what you are talking about", answer);
	}
	
	/**
	 * 测试HowManyInputHandler的操作
	 */
	@Test
	public void testHowManyInputHandler() {
		definitionHandler.handleCommand("how many Credits is glob prok Silver ?");
		definitionHandler.handleCommand("how many Credits is glob prok Gold ?");
		definitionHandler.handleCommand("how many Credits is glob prok Iron ?");
		
		definitionHandler.handleCommand("how many Credits is ?");
		
		String answer = analyseResult.pollAnswer();
		assertTrue(answer != null);
		assertEquals("glob prok Silver is 68 Credits", answer);
		
		answer = analyseResult.pollAnswer();
		assertTrue(answer != null);
		assertEquals("glob prok Gold is 57800 Credits", answer);
		
		answer = analyseResult.pollAnswer();
		assertTrue(answer != null);
		assertEquals("glob prok Iron is 782 Credits", answer);
		
		answer = analyseResult.pollAnswer();
		assertTrue(answer != null);
		assertEquals("I have no idea what you are talking about", answer);
	}
	
	/**
	 * 测试testInvalidInputHandler的操作
	 */
	@Test
	public void testInvalidInputHandler() {
		definitionHandler.handleCommand("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?");
		
		String	answer = analyseResult.pollAnswer();
		assertTrue(answer != null);
		assertEquals("I have no idea what you are talking about", answer);
	}
}
