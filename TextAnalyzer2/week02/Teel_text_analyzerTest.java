package week02;
//Author Name: Charles Teel

//Date: 7/3/2020
//Program Name: Teel_module8_unit_test
//Program Purpose: Update your "word occurrences" application. Add unit testing to the word occurrences class.

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import java.io.File;

class Teel_text_analyzerTest {

	@Test
	void test() throws FileNotFoundException {
		Teel_text_analyzer test = new Teel_text_analyzer();

		String output = test.getWordUsageFromText("Test.txt");

		StringBuilder expected = new StringBuilder();
		expected.append("love = 3 uses \n" + "i    = 1 uses \n" + "you  = 1 uses \n");

		//expected.toString();

		System.out.println("expected : " + expected.length());
		System.out.println("output : " + output.length());
		System.out.println(expected.equals(output));

		System.out.print(output);

		assertEquals("love = 4 uses \n" + "i    = 1 uses \n" + "you  = 1 uses \n", output);

	}
}
