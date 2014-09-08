package main.java.org.problem.inputanalyzer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.java.org.problem.SymbolCalculator;

/**
 * 描述性命令处理类，处理如“glob glob Silver is 34 Credits”的命令
 *
 */
public class DescriptionInputHandler extends InputHandler {

	public DescriptionInputHandler(InputAnalysisResult result) {
		super(result);
	}

	@Override
	public void handleCommand(String command) {
		Pattern patDescription = Pattern.compile("^(.+)\\s+is\\s+(\\d+)\\s+[C|c]redits$");
		Matcher mat = patDescription.matcher(command.trim());
		if (mat.find() && mat.groupCount() == 2) {
			String symbolStr = getSymbolsStrFromCommand(mat.group(1));
			int symbolsValue = 0;
			if (symbolStr != null && !symbolStr.isEmpty()) {
				symbolsValue = SymbolCalculator.getSymbolStrValue(symbolStr);
			}
			
			float totalPrice = Float.parseFloat(mat.group(2));
			float unitPrice = totalPrice / symbolsValue;
			
			String productName = getProductName(mat.group(1));
			if (!productName.isEmpty()) {
				analyseResult.addUnitPrice(productName, unitPrice);
			}
		} else if (nextHandler != null) {
			nextHandler.handleCommand(command);
		}
	}

}
