package test.java.org.problem;

import static org.junit.Assert.*;
import main.java.org.problem.NotesFileProcessor;
import main.java.org.problem.inputanalyzer.InputAnalyzer;

import org.junit.Test;

/**
 * NotesFileProcessor的测试类
 *
 */

public class TestNotesFileProcessor {

	@Test
	public void testProcessor() {
		try {
			InputAnalyzer ia = NotesFileProcessor.processNoteFile(null);
			
			assertEquals("pish tegj glob glob is 42", ia.getAnalyseResult().pollAnswer());
			assertEquals("glob prok Silver is 68 Credits", ia.getAnalyseResult().pollAnswer());
			assertEquals("glob prok Gold is 57800 Credits", ia.getAnalyseResult().pollAnswer());
			assertEquals("glob prok Iron is 782 Credits", ia.getAnalyseResult().pollAnswer());
			assertEquals("I have no idea what you are talking about", ia.getAnalyseResult().pollAnswer());
		} catch (Exception ex) {
			assertTrue(ex == null);
		}
	}

}
