package main.java.org.problem.inputanalyzer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.java.org.problem.SymbolCalculator;
import main.java.org.problem.exception.SymbolParseException;

/**
 * 以how many开头类型命令的处理类，对如“how many Credits is glob prok Silver ?”的命令进行分析处理
 *
 */
public class HowManyInputHandler extends InputHandler {

	public HowManyInputHandler(InputAnalysisResult result) {
		super(result);
	}
	
	@Override
	public void handleCommand(String command) throws SymbolParseException {
		Pattern patHowMany = Pattern.compile("^how\\s+many\\s+((?![C|c]redits).+)\\s+is\\s+(.+)\\s+\\?$");
		Matcher mat = patHowMany.matcher(command.trim());
		if (mat.find() && mat.groupCount() == 2) {
			float product1Price = getTotalPrice(mat.group(1));
			float product2Price = getTotalPrice(mat.group(2));
			
			if (product1Price > 0 && product2Price > 0)
				analyseResult.addAnswer(mat.group(2) + " is " + (int)(product2Price/product1Price)
						+ " " + mat.group(1));
			else
				analyseResult.addAnswer(mat.group(2) + " is 0 " + mat.group(1));
		} else if (nextHandler != null) {
			nextHandler.handleCommand(command);
		}
	}
	
	private float getTotalPrice(String desc) {
		//一般的字符串使用空格分隔，最后一个为产品名称，前几个为数量
		if (desc == null || desc.isEmpty())
			return 0.0f;
		
		String str[] = desc.split("\\s+");
		if (str.length == 1) 
			return getProductPrice(str[0]);
		
		String quantity = "";
		for (int i = 0; i < str.length - 1; i++)
			quantity += str[i] + " ";
		
		quantity = getSymbolsStrFromCommand(quantity);
		int quantityNum = 0;
		if (quantity != null && !quantity.isEmpty())
			quantityNum = SymbolCalculator.getSymbolStrValue(quantity);
		
		return quantityNum * getProductPrice(str[str.length - 1]);
	}
	
	private float getProductPrice(String productName) {
		if (!productName.isEmpty()) {
			return analyseResult.getUnitPrice(productName);
		}		
		return 0.0f;
	}
}
