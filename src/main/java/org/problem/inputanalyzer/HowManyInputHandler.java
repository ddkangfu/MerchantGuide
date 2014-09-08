package main.java.org.problem.inputanalyzer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.java.org.problem.SymbolCalculator;

/**
 * 以how many开头类型命令的处理类，对如“how many Credits is glob prok Silver ?”的命令进行分析处理
 *
 */
public class HowManyInputHandler extends InputHandler {

	public HowManyInputHandler(InputAnalysisResult result) {
		super(result);
	}

	@Override
	public void handleCommand(String command) {
		Pattern patHowMany = Pattern.compile("^how\\s+many\\s+[C|c]redits\\s+is\\s+(.+)\\s+\\?$");
		Matcher mat = patHowMany.matcher(command.trim());
		if (mat.find() && mat.groupCount() == 1) {
			String symbolStr = getSymbolsStrFromCommand(mat.group(1));
			int symbolsValue = 0;
			if (!symbolStr.isEmpty()) {
				symbolsValue = SymbolCalculator.getSymbolStrValue(symbolStr);
			}
			
			String productName = getProductName(mat.group(1));
			float unitPrice = 0.0f;
			if (!productName.isEmpty()) {
				unitPrice = analyseResult.getUnitPrice(productName);
			}

			analyseResult.addAnswer(mat.group(1) + " is " + (int)(symbolsValue * unitPrice) + " Credits");
		} else if (nextHandler != null) {
			nextHandler.handleCommand(command);
		}
	}

}
