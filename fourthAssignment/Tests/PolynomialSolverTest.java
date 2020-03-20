package Tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Classes.PolynomialSolver;

class PolynomialSolverTest {

	PolynomialSolver ps = new PolynomialSolver();

	@Test
	void testSetPolynomial() {
		int[][] terms = {new int[]{1,3}, new int[] {2,2}, new int[] {3, 1}};
		ps.setPolynomial('A', terms);
		assertArrayEquals(ps.linkedListToArray(ps.A), terms);
	}

	@Test
	void testClearPolynomial() {
		int[][] terms = {new int[]{1,3}, new int[] {2,2}, new int[] {3, 1}};
		ps.setPolynomial('B', terms);
		ps.clearPolynomial('B');
		assertTrue(ps.B.isEmpty());
	}
	
	@Test
	void testEvaluatePolynomial1() {
		int[][] terms = {new int[]{1,3}, new int[] {2,2}, new int[] {3, 1}};
		ps.setPolynomial('A', terms);
		// On evaluation at 5 answer is 190
		float ans = 190;
		assertEquals(ans, ps.evaluatePolynomial('A', 5));
	}
	
	@Test
	void testEvaluatePolynomial2() {
		int[][] terms = {new int[]{1,-3}, new int[] {2,5}, new int[] {3, 7}};
		ps.setPolynomial('C', terms);
		// On evaluation at 2 answer is 448.125
		float ans = 448.125f;
		assertEquals(ans, ps.evaluatePolynomial('C', 2));
	}
	
	@Test
	void testEvaluatePolynomial3() {
		int[][] terms = {new int[]{1,-5}, new int[] {7,3}, new int[] {3, 0}};
		ps.setPolynomial('C', terms);
		// On evaluation at 0 answer is 3
		float ans = 3;
		assertEquals(ans, ps.evaluatePolynomial('C', 0));
	}
	
	@Test
	void testAdd1() {
		int[][] terms = {new int[]{1,3}, new int[] {2,2}, new int[] {3, 1}};
		ps.setPolynomial('A', terms);
		ps.setPolynomial('B', terms);
		int[][] soln = {new int[]{2,3}, new int[] {4,2}, new int[] {6, 1}};
		assertArrayEquals(ps.add('A', 'B'), soln);
	}
	
	@Test
	void testAdd2() {
		int[][] terms1 = {new int[]{1,3}, new int[] {7,2}, new int[] {6, 1}, new int[] {9, 0}};
		ps.setPolynomial('A', terms1);
		int[][] terms2 = {new int[] {12, 7}, new int[] {9,2}, new int[]{25, 0}, new int[] {96, -3}, new int[]{1,-6}};
		ps.setPolynomial('B', terms2);
		int[][] soln = {new int[]{12, 7}, new int[] {1, 3}, new int[] {16, 2}, new int[] {6, 1}, new int[] {34, 0}, new int[] {96, -3}, new int[]{1,-6}};
		assertArrayEquals(ps.add('A', 'B'), soln);
	}
	
	@Test
	void testSubtract1() {
		int[][] terms = {new int[]{1,3}, new int[] {2,2}, new int[] {3, 1}};
		ps.setPolynomial('A', terms);
		ps.setPolynomial('B', terms);
		int[][] soln = new int[0][2];
		assertArrayEquals(ps.subtract('A', 'B'), soln);
	}
	
	@Test
	void testSubtract2() {
		int[][] terms1 = {new int[]{1,3}, new int[] {7,2}, new int[] {6, 1}, new int[] {9, 0}};
		ps.setPolynomial('A', terms1);
		int[][] terms2 = {new int[] {12, 7}, new int[] {9,2}, new int[]{25, 0}, new int[] {96, -3}, new int[]{1,-6}};
		ps.setPolynomial('B', terms2);
		int[][] soln = {new int[]{-12, 7}, new int[] {1, 3}, new int[] {-2, 2}, new int[] {6, 1}, new int[] {-16, 0}, new int[] {-96, -3}, new int[]{-1,-6}};
		assertArrayEquals(ps.subtract('A', 'B'), soln);
	}
	
	@Test
	void testMultiply1() {
		int[][] terms = {new int[]{1,3}, new int[] {2,2}, new int[] {3, 1}};
		ps.setPolynomial('A', terms);
		ps.setPolynomial('B', terms);
		int[][] soln = {new int[]{1,6}, new int[] {4,5}, new int[] {10, 4}, new int[] {12, 3}, new int[] {9, 2}};
		assertArrayEquals(ps.multiply('A', 'B'), soln);
	}
	
	void testMultiply2() {
		int[][] terms1 = {new int[]{25,3}};
		ps.setPolynomial('A', terms1);
		int[][] terms2 = {new int[]{2, -3}};
		ps.setPolynomial('B', terms2);
		int[][] soln = {new int[]{50,0}};
		assertArrayEquals(ps.multiply('A', 'B'), soln);
	}
}
