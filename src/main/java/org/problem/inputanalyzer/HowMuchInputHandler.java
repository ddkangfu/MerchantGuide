package main.java.org.problem.inputanalyzer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.java.org.problem.SymbolCalculator;

/**
 * 以how much开头类型命令的处理类，对如“how much is pish tegj glob glob ?”的命令进行分析处理 
 *
 */
public class HowMuchInputHandler extends InputHandler {

	public HowMuchInputHandler(InputAnalysisResult result) {
		super(result);
	}

	@Override
	public void handleCommand(String command) {
		Pattern patHowMuch = Pattern.compile("^how\\s+much\\s+is\\s+(.+)\\s+\\?$");
		Matcher mat = patHowMuch.matcher(command.trim());
		if (mat.find() && mat.groupCount() == 1) {
			String symbolStr = getSymbolsStrFromCommand(mat.group(1));
			int symbolsValue = 0;
			if (!symbolStr.isEmpty()) {
				symbolsValue = SymbolCalculator.getSymbolStrValue(symbolStr);
			}
			
			analyseResult.addAnswer(mat.group(1) + " is " + symbolsValue);
		} else if (nextHandler != null) {
			nextHandler.handleCommand(command);
		}
	}

}
