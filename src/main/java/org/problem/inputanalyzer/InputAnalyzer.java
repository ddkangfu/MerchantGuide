package main.java.org.problem.inputanalyzer;

import main.java.org.problem.exception.AnalyseInputTextError;
import main.java.org.problem.exception.SymbolParseException;

/**
 * 分析从notes里读入的一行指令，并生成对提问问题的回答队列
 *
 */
public class InputAnalyzer {
	private InputAnalysisResult analyseResult = new InputAnalysisResult();
	
	/**
	 * 分析指令行文本
	 */
	public void doAnalyse(String command) throws AnalyseInputTextError {
		if (command == null || command.isEmpty())
			throw new AnalyseInputTextError("Input command text is null or blank.");
		
		try {
			//职责链模式分别处理各种指令
			InputHandler definitionHandler = new DefinitionInputHandler(analyseResult);
			InputHandler descriptionHandler = new DescriptionInputHandler(analyseResult);
			InputHandler howmuchHandler = new HowMuchInputHandler(analyseResult);
			InputHandler howmanyHandler = new HowManyInputHandler(analyseResult);
			InputHandler invalidHandler = new InvalidInputHandler(analyseResult);
			
			definitionHandler.setNextHandler(descriptionHandler);
			descriptionHandler.setNextHandler(howmuchHandler);
			howmuchHandler.setNextHandler(howmanyHandler);
			howmanyHandler.setNextHandler(invalidHandler);
			
			definitionHandler.handleCommand(command);
		} catch(SymbolParseException spe) {
			throw new AnalyseInputTextError("Parse symbol Error:" + spe.getMessage());
		} catch(Exception ex) {
			throw new AnalyseInputTextError("Analyse input command Error:" + ex.getMessage());
		}
	}
	
	public InputAnalysisResult getAnalyseResult() {
		return this.analyseResult;
	}
}
