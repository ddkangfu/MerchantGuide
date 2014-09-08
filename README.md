Merchant Guide

========================

环境说明：
	开发语言：Java
	JDK版本：V1.6.0_65-b14-462-11M4609
	开发环境： MyEclipse 10
	单元测试版本：junit4

依赖jar包：
	junit4.jar

程序说明：
	入口程序：main.java.org.problem.main.Main
	程序路径：src/main/java/org/problem/
	单元测试程序路径：src/test/java/org/problem/

使用说明：
	执行时可以在第一个参数指定要解析的文件，如果不指定，将默认执行src/main/java/org/problem/NotesFile.txt文件。
	
解决方法简单说明：
	1、使用NotesFileProcessor类读入指定文件的每条指令，然后依次使用InputAnalyzer类对每条指令进行解析；
	2、InputAnalyzer类对每条指令需要解析的外星字符串[pish tegj glob glob]解析成数字符号字符串[XLII]后，交给SymbolAnalyzer进行解析，并生成SymbolAnalysisResult的解析结果；
	3、使用SymbolCalculator类计算得到的SymbolAnalysisResult对应的数值[42]；
	4、将解析结果放到InputAnalysisResult中交给NotesFileProcessor类；
	5、通过Main类使用NotesFileProcessor类读取InputAnalysisResult中的每条输出结果并显示在屏幕上，到此即完成一个文件的整个处理流程。
