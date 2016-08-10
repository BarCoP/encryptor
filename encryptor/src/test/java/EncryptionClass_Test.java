import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class EncryptionClass_Test {
	Encryption enc =new Encryption();
	@Test
	public void testValidMethod() {
		// valid Input - 'e' ,'E','d','D'.
		
		char actual_output =enc.chooseMethod();
		assertEquals('e',actual_output);
		assertEquals('E',actual_output);
		assertEquals('d',actual_output);
		assertEquals('D',actual_output);
		
	}
	
	@Test
	public void testGetIndexOfLastDotInString()
	{
		String ans ="C:\\Users\\USER\\Desktop\\test.txt";
		int actual_index =enc.getIndexOfLastDotInString(ans);
		int expect_index =26;
		assertEquals(expect_index,actual_index);
	}

}
