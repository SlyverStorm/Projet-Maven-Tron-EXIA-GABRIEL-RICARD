package Exia2019.Tron.model;

import junit.framework.TestCase;

public class LightSquareTest extends TestCase {
	
	LightSquare lightSquare;

	protected void setUp() throws Exception {
		super.setUp();
		
		lightSquare = new LightSquare(2,2);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetX() {
		int expected = 2;
		
		assertEquals(expected, lightSquare.getX());
		
	}

	/*public void testSetX() {
		fail("Not yet implemented");
	}

	public void testGetY() {
		fail("Not yet implemented");
	}

	public void testSetY() {
		fail("Not yet implemented");
	}*/

}
