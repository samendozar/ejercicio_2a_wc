/**
 * 
 */
package es.upm.grise.profundizacion.wc.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import es.upm.grise.profundizacion.wc.Counter;

class CounterTest {
	
	private static Counter counter;
	private static final int CHARCOUNT = 375;
	private static final int WORDCOUNT = 60;
	private static final int LINECOUNT = 8;
	private static final int LINECOUNT_2 = 820;
	private static final int CHARCOUNT_2 = 34129;
	private static final int WORDCOUNT_2 = 2280;
	
	@BeforeAll
	static public void initializeCounter() throws Exception {
		BufferedReader br = null;
    	String fileName = "sample.txt";

		try{
			br = new BufferedReader(new FileReader(fileName));
		} catch(Exception e) {
    		System.out.println("Cannot find file: " + fileName);
    		throw e;
		}
		
    	counter = null;
    	
		try {
			counter = new Counter(br);
		} catch (IOException e) {
    		System.out.println("Error reading file: " + fileName);
    		throw e;
		}
	}

	@Test
	void sample_text_char_count_returns_375() {
		assumingThat(counter != null, () -> assertEquals(CHARCOUNT, counter.getNumberCharacters()));
	}
	
	/**
	 * Este test falla con relación a resultado de wc, indicador de error en el código fuente
	 */
	@Test
	void sample_text_word_count_returns_60() {
		assumingThat(counter != null, () -> assertEquals(WORDCOUNT, counter.getNumberWords()));
	}
	
	/**
	 * Este test falla relación a resultado de wc, indicador de error en el código fuente
	 */
	@Test
	void sample_text_line_count_returns_8() {
		assumingThat(counter != null, () -> assertEquals(LINECOUNT, counter.getNumberLines()));
	}
	
	/**
	 * Ejemplo con archivo pasado por anotación
	 * @param filePath
	 * @throws IOException
	 */
	@ParameterizedTest
	@ValueSource(strings = "sample_2.csv")
	void sample_text_2_line_count_returns_820(String filePath) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		Counter cntr = new Counter(br);
		int lines = cntr.getNumberLines();
		assertEquals(LINECOUNT_2, cntr.getNumberLines());
	}
	
	@ParameterizedTest
	@ValueSource(strings = "sample_2.csv")
	void sample_text_2_word_count_returns_2280(String filePath) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		Counter cntr = new Counter(br);
		int lines = cntr.getNumberLines();
		assertEquals(WORDCOUNT_2, cntr.getNumberWords());
	}
	
	@ParameterizedTest
	@ValueSource(strings = "sample_2.csv")
	void sample_text_2_char_count_returns_34129(String filePath) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		Counter cntr = new Counter(br);
		int lines = cntr.getNumberLines();
		assertEquals(CHARCOUNT_2, cntr.getNumberCharacters());
	}

}
