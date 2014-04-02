package model;

import android.test.AndroidTestCase;

public class Test extends AndroidTestCase {

	myDate date;

	protected void setUp() throws Exception {
		date = new myDate();
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetMonth() {
		date.setMonth(4);
		assertEquals("Wrong month format", "April", date.getFormatMonth());
	}
	
	
}