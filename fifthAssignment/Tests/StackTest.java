package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Classes.Stack;

class StackTest {

	Stack stk = new Stack();
	
	@BeforeEach
	void setUp() throws Exception {
		stk.push(5);
		stk.push(9);
		stk.push(7);
	}

	@Test
	void testPop1() {
		assertEquals(7, (int)stk.pop());
	}
	
	@Test
	void testPop2() {
		stk.pop();
		stk.pop();
		stk.pop();
		assertThrows(IllegalStateException.class, ()->stk.pop());
	}

	@Test
	void testPeek() {
		assertEquals(7, (int)stk.peek());
	}

	@Test
	void testPush() {
		stk.push(85);
		assertEquals(85, (int)stk.peek());
	}

	@Test
	void testIsEmpty() {
		assertFalse(stk.isEmpty());
		stk.pop();
		stk.pop();
		stk.pop();
		assertTrue(stk.isEmpty());
	}

	@Test
	void testSize() {
		assertEquals(3, (int)stk.size());
	}

}
