package main.java.org.problem.inputanalyzer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 定义类型命令的处理类，对如“glob is I”的命令进行分析处理
 *
 */
public class DefinitionInputHandler extends InputHandler {

	public DefinitionInputHandler(InputAnalysisResult result) {
		super(result);
	}

	@Override
	public void handleCommand(String command) {
		Pattern patDefinition = Pattern.compile("^(.+)\\s+is\\s+([IVXLCDM])$");
		Matcher mat = patDefinition.matcher(command.trim());
		if (mat.find() && mat.groupCount() == 2) {
			analyseResult.addDefinition(mat.group(1), mat.group(2).charAt(0));
		} else if (nextHandler != null){
			nextHandler.handleCommand(command);
		}
	}

}
