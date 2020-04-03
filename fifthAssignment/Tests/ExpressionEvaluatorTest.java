package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Classes.ExpressionEvaluator;

class ExpressionEvaluatorTest {
	
	ExpressionEvaluator ee = new ExpressionEvaluator();
	
	@Test
	void testInfixToPostfix1() {
		String infix = "2 + 3 * 4";
		String postfix = "2 3 4 * +";
		assertEquals(postfix, ee.infixToPostfix(infix));
	}
	
	@Test
	void testInfixToPostfix2() {
		String infix = "a * b + 5";
		String postfix = "a b * 5 +";
		assertEquals(postfix, ee.infixToPostfix(infix));
	}
	
	@Test
	void testInfixToPostfix3() {
		String infix = "(1 + 2) * 7";
		String postfix = "1 2 + 7 *";
		assertEquals(postfix, ee.infixToPostfix(infix));
	}
	
	@Test
	void testInfixToPostfix4() {
		String infix = "a * b / c";
		String postfix = "a b * c /";
		assertEquals(postfix, ee.infixToPostfix(infix));
	}
	
	@Test
	void testInfixToPostfix5() {
		String infix = "(a / (b - c + d)) * (e - a) * c";
		String postfix = "a b c - d + / e a - * c *";
		assertEquals(postfix, ee.infixToPostfix(infix));
	}
	
	@Test
	void testInfixToPostfix6() {
		String infix = "a / b - c + d * e - a * c";
		String postfix = "a b / c - d e * + a c * -";
		assertEquals(postfix, ee.infixToPostfix(infix));
	}
	
	
	@Test
	void testInfixToPostfix7() {
		String infix = "2 ^ 3 + 8 * 9 - (3 - 2)";
		String postfix = "2 3 ^ 8 9 * + 3 2 - -";
		assertEquals(postfix, ee.infixToPostfix(infix));
	}
	
	@Test
	void testInfixToPostfix8() {
		String infix = "2 ^ 3 +# 8 * 9 - (3 - 2)";
		assertThrows(RuntimeException.class, ()->ee.infixToPostfix(infix));
	}
	
	
	@Test
	void testInfixToPostfix9() {
		String infix = "(2 ^ 3 + 8 * 9 - (3 - 2)";
		assertThrows(RuntimeException.class, ()->ee.infixToPostfix(infix));
	}
	
	@Test
	void testInfixToPostfix10() {
		String infix = "2 ^ 3 + 8 * 9 - (3 - 2))";
		assertThrows(RuntimeException.class, ()->ee.infixToPostfix(infix));
	}
	
	@Test
	void testInfixToPostfix11() {
		String infix = "-2 ^ 3 + 8 * 9 - (13 - 2)";
		String postfix = "0 2 - 3 ^ 8 9 * + 13 2 - -";
		assertEquals(postfix, ee.infixToPostfix(infix));
	}
	
	@Test
	void testInfixToPostfix12() {
		String infix = "18 * 93 + 3 * -5";
		String postfix = "18 93 * 3 0 5 - * +";
		assertEquals(postfix, ee.infixToPostfix(infix));
	}
	
	@Test
	void testInfixToPostfix13() {
		String infix = "15 / 5 - -5 * (3 - 2)";
		String postfix = "15 5 / 0 5 - 3 2 - * -";
		assertEquals(postfix, ee.infixToPostfix(infix));
	}
	
	@Test
	void testInfixToPostfix14() {
		String infix = "15 / 5 - (-5) * (3 - 2)";
		String postfix = "15 5 / 0 5 - 3 2 - * -";
		assertEquals(postfix, ee.infixToPostfix(infix));
	}
	
	@Test
	void testInfixToPostfix15() {
		String infix = "ab + 5";
		assertThrows(RuntimeException.class, ()->ee.infixToPostfix(infix));
	}
	
	@Test
	void testInfixToPostfix16() {
		String infix = "3 * / 5";
		assertThrows(RuntimeException.class, ()->ee.infixToPostfix(infix));
	}
	
	@Test
	void testEvaluate1() {
		String postfix = "6 2 / 3 - 4 2 * +";
		assertEquals(8, ee.evaluate(postfix));
	}
	
	@Test
	void testEvaluate2() {
		String infix = "2 ^ 3 + 8 * 9 - (3 - 2)";
		String s = ee.infixToPostfix(infix);
		assertEquals(79, ee.evaluate(s));
	}
	
	@Test
	void testEvaluate3() {
		String infix = "(1 + 2) * 7";
		assertEquals(21, ee.evaluate(ee.infixToPostfix(infix)));
	}
	
	@Test
	void testEvaluate4() {
		String postfix = "a 2 / 3 - 4 2 * +";
		assertThrows(RuntimeException.class, ()->ee.evaluate(postfix));
	}
	
	@Test
	void testEvaluate5() {
		String postfix = "6 2 / 3 - # 2 * +";
		assertThrows(RuntimeException.class, ()->ee.evaluate(postfix));
	}
	
	@Test
	void testEvaluate6() {
		String infix = "5 % (1 + 2) + 3 * 9";
		assertEquals(29, ee.evaluate(ee.infixToPostfix(infix)));
	}
	
	@Test
	void testEvaluate7() {
		String infix = "-3 * 10 +2";
		assertEquals(-28, ee.evaluate(ee.infixToPostfix(infix)));
	}
	
	@Test
	void testEvaluate8() {
		String infix = "-6 / 2 - 5 * 3 + 18 * 2";
		assertEquals(18, ee.evaluate(ee.infixToPostfix(infix)));
	}
	
	@Test
	void testEvaluate9() {
		String infix = "45 % (41 + 2) + 30 * 9";
		assertEquals(272, ee.evaluate(ee.infixToPostfix(infix)));
	}
	
	@Test
	void testEvaluate10() {
		String infix = "35 / (5 + 2) + 9 / -3";
		String f = ee.infixToPostfix(infix);
		assertEquals(2, ee.evaluate(f));
	}
	
	@Test
	void testEvaluate11() {
		String infix = "35 / (5 + 2) + 9 / (-3)";
		String f = ee.infixToPostfix(infix);
		assertEquals(2, ee.evaluate(f));
	}
	
	@Test
	void testEvaluate12() {
		String infix = "33 / (5 + (-2)) + 9 / (-3)";
		String f = ee.infixToPostfix(infix);
		assertEquals(8, ee.evaluate(f));
	}
	
	@Test
	void testEvaluate13() {
		String infix = "12+- 3";
		String f = ee.infixToPostfix(infix);
		assertEquals(9, ee.evaluate(f));
	}
	
}
