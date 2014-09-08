package main.java.org.problem.inputanalyzer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 输入命令的分析结果类
 *
 */
public class InputAnalysisResult {
	private Map<String, Character> definitionMap = new HashMap<String, Character>();//定义每个intergalactic符号和罗马符号的映射关系
	private Map<String, Float> unitPriceMap = new HashMap<String, Float>();//存储分析得取的intergalactic unit的价格
	private Queue<String> answerQueue = new LinkedList<String>();//回答提问的队列
	
	/**
	 * 添加产品名称对应的单价映射信息
	 */
	public void addUnitPrice(String productName, float price) {
		this.unitPriceMap.put(productName, price);
	}
	
	/**
	 * 添加外星符号定义信息
	 */
	public void addDefinition(String intergalacticSymbols, char symbolChar) {
		this.definitionMap.put(intergalacticSymbols, symbolChar);
	}
	
	/**
	 * 添加问题的答案到队列中
	 */
	public void addAnswer(String answerStr) {
		this.answerQueue.offer(answerStr);
	}
	
	/**
	 * 从问题答案队列中取回一条答案，并从队列中删除该答案
	 */
	public String pollAnswer() {
		if (!answerQueue.isEmpty())
			return answerQueue.poll();
		return null;
	}
	
	/**
	 * 使用产品名称获得商品的单价
	 */
	public Float getUnitPrice(String productName) {
		if (unitPriceMap.containsKey(productName))
			return unitPriceMap.get(productName);
		return null;
	}
	
	/**
	 * 使用外星符号取得对应的数字符号
	 */
	public Character getRomanSymbol(String intergalacticSymbols) {
		if (this.definitionMap.keySet().contains(intergalacticSymbols))
			return this.definitionMap.get(intergalacticSymbols);
		return null;
	}
}
