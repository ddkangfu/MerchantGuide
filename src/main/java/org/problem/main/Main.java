package main.java.org.problem.main;

import java.io.File;
import java.io.IOException;

import main.java.org.problem.NotesFileProcessor;
import main.java.org.problem.exception.AnalyseInputTextError;
import main.java.org.problem.inputanalyzer.InputAnalyzer;

/**
 * 主程序类
 *
 */
public class Main {
	public static void main(String[] args) {
		String filePath = null;
		if (args.length != 0) {
			filePath = args[0];
			File file = new File(filePath);
			if (!file.exists()) {
				System.out.println("File '" + filePath + "' can not be found !");
			}
		}
		
		try{
			InputAnalyzer ia = NotesFileProcessor.processNoteFile(filePath);
			String answer;
			System.out.println("");
			while ((answer = ia.getAnalyseResult().pollAnswer()) != null) {
				System.out.println(answer);
			}
		} catch(IOException ioExc){
			System.out.println("Read note file Error: " + ioExc.getMessage());
		} catch(AnalyseInputTextError analyseExc) {
			System.out.println("Analyse note file Error: " + analyseExc.getMessage());
		} catch(Exception ex) {
			System.out.print("Analyse note file get an unexcept error:" + ex.getMessage());
		}
	}
}
