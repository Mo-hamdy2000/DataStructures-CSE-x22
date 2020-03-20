package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Classes.singleLList;

class singleLListTest {

	singleLList list = new singleLList();
	@BeforeEach
	void setUp() throws Exception {
		
		list.add(new int[]{1, 1});
		list.add(new int[]{1, 2});
		list.add(new int[]{1, 3});
		list.add(new int[]{2, 1});
		list.add(new int[]{2, 2});
		list.add(new int[]{2, 3});
		list.add(new int[]{3, 1});
		list.add(new int[]{3, 2});
		list.add(new int[]{3, 3});
		list.add(new int[]{4, 1});
		list.add(new int[]{4, 2});
		list.add(new int[]{4, 3});
	}

	@Test
	void testAdd1() {
		int[] testingTerm = {2, 1};
		int[] listTerm = (int[])list.get(3);
		assertArrayEquals(testingTerm, listTerm);
	}
	
	@Test
	void testAdd2() {
		assertThrows(IndexOutOfBoundsException.class, ()->list.add(-3, new int[] {2,2}));
	}
	
	@Test
	void testAdd3() {
		int[] testingTerm = {23, 42};
		list.add(testingTerm);
		
		assertArrayEquals(testingTerm, (int[])list.get(list.size()-1));
	}
	
	@Test
	void testGet1() {
		assertNull(list.get(20));
		assertNull(list.get(-15));
		assertNull(list.get(15));
	}
	
	@Test
	void testGet2() {
		int[] testingTerm  = {86, 23};
		list.add(6, testingTerm);
		assertArrayEquals(testingTerm, (int[])list.get(6));
	}
	
	@Test
	void testSet1() {
		int[] testingTerm = {13, 7};
		list.set(9, testingTerm);
		assertArrayEquals(testingTerm, (int[])list.get(9));
	}
	
	@Test
	void testSet2() {
		assertThrows(IndexOutOfBoundsException.class, ()->list.set(20, new int[] {2,2}));
	}
	
	@Test
	void testSublist() {
		singleLList newList = (singleLList) list.sublist(2, 6);
		assertEquals(4, newList.size());
		assertEquals(list.get(2), newList.get(0));
		assertEquals(list.get(4), newList.get(2));
	}
	
	@Test
	void testRemove() {
		int tempSize = list.size();
		int[] testingTerm = (int[])list.get(4);
		list.remove(4);
		assertEquals(tempSize-1, list.size());
		assertNotEquals(testingTerm[0], (int[])list.get(4));
	}
	
	void testRemove2() {
		assertThrows(IndexOutOfBoundsException.class, ()->list.remove(33));
	}
	
	@Test
	void testContains() {
		int[] testingTerm = new int[] {25,93};
		list.add(testingTerm);
		assertTrue(list.contains(testingTerm));
		assertFalse(list.contains(new int[] {-3, -5}));
	}
	
	@Test
	void testClear() {
		list.clear();
		assertTrue(list.isEmpty());
	}
}
