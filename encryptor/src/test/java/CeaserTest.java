import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class CeaserTest {
	Ceaser_Algo c = new Ceaser_Algo();
	
	@Test
	public void testPathNotNull() {
		String actualPath = c.getPath();
		String expectPath = "";
		assertNotEquals(expectPath, actualPath);
	}
	
	@Test
	public void testIsFileNull() {
		File f =c.getTheFile();
		assertNotNull(f);
	}
	
	@Test
	public void testDoActivateAlgo() {
		 boolean ans =c.getDoActivateAlgo();
		assertTrue(ans);
	}
	
	
	
	
	

}
