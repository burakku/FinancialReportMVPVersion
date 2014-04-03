package model;

import android.test.AndroidTestCase;

public class Test extends AndroidTestCase {

	MyDate date;

	protected void setUp() throws Exception {
		date = new MyDate();
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testNull(){
		assertEquals("Invalid month value", "null", date.getFormatMonth());
	}
	public void testGetMonth() {
		date.setMonth(4);
		assertEquals("Wrong month format", "April", date.getFormatMonth());
	}
	
	
}