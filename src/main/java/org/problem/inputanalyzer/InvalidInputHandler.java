package main.java.org.problem.inputanalyzer;


/**
 * 处理无效的命令，如“how much wood could a woodchuck chuck if a woodchuck could chuck wood ?”
 *
 */
public class InvalidInputHandler extends InputHandler {

	public InvalidInputHandler(InputAnalysisResult result) {
		super(result);
	}

	@Override
	public void handleCommand(String command) {
		analyseResult.addAnswer("I have no idea what you are talking about");
	}

}
