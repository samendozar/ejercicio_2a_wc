package es.upm.grise.profundizacion.wc.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.*;

import es.upm.grise.profundizacion.wc.App;

public class MainTest {
    static ByteArrayOutputStream outputStream;
    static PrintStream line;
    static PrintStream old;
    String pathFile ="sample.txt";
    /*
     * Creates a PrintStream that captures the output
     */
    @BeforeAll
    static public void stringCapture(){
        outputStream = new ByteArrayOutputStream();
        line = new PrintStream(outputStream);
        old = System.out;
        System.setOut(line);
    }
    /*
     * No input test
     */
    @Test
    public void no_input_test(){
        String[] Args = new String[0];
        App.main(Args);
        assertEquals("Usage: wc [-clw file]\r\n",outputStream.toString());
    }
    /*
     * Too many imput test
     */
    @Test
    public void too_many_inputs_test(){
        String[] Args = {"a","b","c"};
        App.main(Args);
        assertEquals("Wrong arguments!\r\n",outputStream.toString());
    }
    /*
     * File not found test
     */
    @Test
    public void not_a_file_test(){
        String[] Args = {"a","b"};
        App.main(Args);
        assertEquals("Cannot find file: b\r\n",outputStream.toString());
    }
    /*
     * Wrong parameter test
     */
    @Test
    public void wrong_command_test(){
        String[] Args = {"wrong",pathFile};
        App.main(Args);
        assertEquals("The commands do not start with -\r\n",outputStream.toString());
    }
    /*
     * Get characters from sample test
     */
    @Test
    public void get_characters_test(){
        String[] Args = {"-c",pathFile};
        App.main(Args);
        assertEquals("\t373\tsample.txt\r\n",outputStream.toString());
    }
    /*
     * Get words from sample test
     */
    @Test
    public void get_words_test(){
        String[] Args = {"-w",pathFile};
        App.main(Args);
        assertEquals("\t74\tsample.txt\r\n",outputStream.toString());
    }
    /*
     * Get lines from sample test
     */
    @Test
    public void get_lines_test(){
        String[] Args = {"-l",pathFile};
        App.main(Args);
        assertEquals("\t8\tsample.txt\r\n",outputStream.toString());
    }
    /*
     * Get lines and characters from sample test
     */
    @Test
    public void get_lines_and_characters_test(){
        String[] Args = {"-lc",pathFile};
        App.main(Args);
        assertEquals("\t8\t373\tsample.txt\r\n",outputStream.toString());
    }
    /*
     * Get words and lines from sample test
     */
    @Test
    public void get_words_and_lines_test(){
        String[] Args = {"-wl",pathFile};
        App.main(Args);
        assertEquals("\t74\t8\tsample.txt\r\n",outputStream.toString());
    }
    /*
     * Get characters and words from sample test
     */
    @Test
    public void get_characters_and_words_test(){
        String[] Args = {"-cw",pathFile};
        App.main(Args);
        assertEquals("\t373\t74\tsample.txt\r\n",outputStream.toString());
    }
    /*
     * Output everything
     */
    @Test
    public void get_everything(){
        String[] Args = {"-cwl",pathFile};
        App.main(Args);
        assertEquals("\t373\t74\t8\tsample.txt\r\n",outputStream.toString());
    }
    /*
     * Clears outputStream
     */
    @AfterEach
    public void dumpString(){
        outputStream.reset();
        System.out.flush();
    }
    /*
     * Recovers original output
     */
    @AfterAll
    static public void reset(){
        System.setOut(old);
    }

}

