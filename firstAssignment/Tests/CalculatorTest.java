package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CalculatorTest {

	@Test
	void testAdd() {
		Classes.Calculator calc = new Classes.Calculator();
		assertEquals(calc.add(2, 3), 5);
		assertEquals(calc.add(2, 0), 2);
		assertEquals(calc.add(0, 5), 5);
		assertEquals(calc.add(-6, 5), -1);
		assertEquals(calc.add(3, -5), -2);
	}

	@Test
	void testDivide() {
		Classes.Calculator calc = new Classes.Calculator();
		assertEquals(calc.divide(2, 3), ((float)2/3));
		assertEquals(calc.divide(0, 3), 0);
		assertEquals(calc.divide(0, -50), -0.0);
		assertEquals(calc.divide(-5, 3), ((float)-5/3));
		assertEquals(calc.divide(5, -3), ((float)5/-3));
		assertThrows(RuntimeException.class, ()->calc.divide(8, 0));
	}

}
