package main.java.org.problem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import main.java.org.problem.exception.AnalyseInputTextError;
import main.java.org.problem.inputanalyzer.InputAnalyzer;

/**
 * 根据NoteFile路径解析文件的每条指令并输出，并且是主程序入口
 * 
 */
public class NotesFileProcessor {
	/**
	 * 处理记录转换信息的note文件,返回解析后的InputAnalyzer对象
	 */
	public static InputAnalyzer processNoteFile(String filePath) throws IOException, AnalyseInputTextError {
		BufferedReader bufferedReader = null;
		InputAnalyzer ia = new InputAnalyzer();
		try {
			//未提供路径时，默认处理此类同级目录下的NotesFile.txt文件作为测试文件
			if (filePath == null){
				InputStream in = NotesFileProcessor.class.getResourceAsStream("NotesFile.txt");
				bufferedReader =new BufferedReader(new InputStreamReader(in));
			} else {
				FileReader fileReader = new FileReader(filePath);
				bufferedReader = new BufferedReader(fileReader);
			}
			
			String cmmLine = null;
			while ((cmmLine = bufferedReader.readLine()) != null) {
				System.out.println(cmmLine);
				ia.doAnalyse(cmmLine);
			}
		} finally {
			if (bufferedReader != null)
				bufferedReader.close();
		}
		
		return ia;
	}
}
