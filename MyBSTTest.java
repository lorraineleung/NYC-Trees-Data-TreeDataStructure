package edu.nyu.cs.ll3094;

import static org.junit.Assert.*;

import org.junit.Test;

public class MyBSTTest {

	@Test
	public void testMyBST() {
		fail("Not yet implemented");
	}

	@Test
	public void testAdd() {
		try{
			MyBST<String> test = new MyBST<String>();
			test.add("hello");
			test.add("bye");
			test.add("zoo");
			String check1 = test.first();
			String check2 = test.last();
			assertEquals("Returned value is different from first value", "hello", check1);
			assertEquals("Returned value is different from last value", "hello", check2);
		}
		catch(Exception e){
			fail("Exception thrown" + e);
		}
	}

	@Test
	public void testRemove() {
		fail("Not yet implemented");
	}

	@Test
	public void testContains() {
		try{
			MyBST<Integer> test = new MyBST<Integer>();
			test.add(1);
			test.add(2);
			test.add(3);
			boolean check1 = test.contains(2);
			boolean check2 = test.contains(4);
			assertTrue("Returned not true", check1);
			assertFalse("Returned true", check2);
		}
		catch(Exception e){
			fail("Exception thrown" + e);
		}
	}

	@Test
	public void testFirst() {
		fail("Not yet implemented");
	}

	@Test
	public void testLast() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSize() {
		fail("Not yet implemented");
	}

}
