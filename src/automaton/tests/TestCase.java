/*
 * MIT License
 *
 * Copyright (c) 2019 Adrien Belminksy and other contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package automaton.tests;

import java.util.Arrays;

/**
 * The test case represent an series of test methods
 * for a single class or a single concept.
 * 
 * @author Adrien Belminksy
 * @see TestSuite
 */
public abstract class TestCase {

	/**
	 * The printer which allows access to the output.
	 */
	protected Printer printer;

	/**
	 * The name of the current method launched.
	 */
	protected String currentMethod;


	/**
	 * Defines the printer.
	 * 
	 * @see #printer
	 */
	protected void setPrinter(Printer printer) {
		this.printer = printer;
	}

	/**
	 * Defines the current method launched.
	 * 
	 * @see #currentMethod
	 */
	protected void setCurrentMethod(String currentMethod) {
		this.currentMethod = currentMethod;
	}


	/**
	 * Triggers a failure.
	 * 
	 * @param message
	 */
	public void fail(String message) {

		printer.fail(new Failure(
			getClass(),
			currentMethod,
			message
		));

	}



	/**
	 * Asserts that the argument is true, an error
	 * message will be displayed in the output otherwise.
	 * 
	 * @see #assertTrue(boolean, String)
	 * @see #assertFalse(boolean)
	 */
	public void assertTrue(boolean bool) {
		assertTrue(bool, "Failed asserting that false is true.");
	}

	/**
	 * Asserts that the argument is true, an error
	 * message will be displayed in the output otherwise.
	 * 
	 * @see #assertTrue(boolean)
	 * @see #assertFalse(boolean, String)
	 */
	public void assertTrue(boolean bool, String message) {

		if (bool) {

			printer.succeed();
			return;

		}

		fail(message);

	}

	/**
	 * Asserts that the argument is true, an error
	 * message will be displayed in the output otherwise.
	 * 
	 * @see #assertTrue(boolean)
	 * @see #assertFalse(boolean, String)
	 */
	public void assertFalse(boolean bool) {
		assertFalse(bool, "Failed asserting that true is false.");
	}

	/**
	 * Asserts that the argument is true, an error
	 * message will be displayed in the output otherwise.
	 * 
	 * @see #assertTrue(boolean, String)
	 * @see #assertFalse(boolean)
	 */
	public void assertFalse(boolean bool, String message) {
		assertTrue(!bool, message);
	}



	/**
	 * Asserts that the argument is null, an error
	 * message will be displayed in the output otherwise.
	 * 
	 * @see #assertNull(Object, String)
	 * @see #assertNotNull(Object)
	 */
	public void assertNull(Object object) {
		assertNull(object, "Failed asserting that an object is null.");
	}

	/**
	 * Asserts that the argument is null, an error
	 * message will be displayed in the output otherwise.
	 * 
	 * @see #assertNull(Object)
	 * @see #assertNotNull(Object, String)
	 */
	public void assertNull(Object object, String message) {
		assertTrue(object == null, message);
	}

	/**
	 * Asserts that the argument is null, an error
	 * message will be displayed in the output otherwise.
	 * 
	 * @see #assertNull(Object)
	 * @see #assertNotNull(Object, String)
	 */
	public void assertNotNull(Object object) {
		assertNotNull(object, "Failed asserting that an object is not null.");
	}

	/**
	 * Asserts that the argument is null, an error
	 * message will be displayed in the output otherwise.
	 * 
	 * @see #assertNull(Object, String)
	 * @see #assertNotNull(Object)
	 */
	public void assertNotNull(Object object, String message) {
		assertFalse(object == null, message);
	}



	/**
	 * Asserts that the two arguments are identical, an error
	 * message will be displayed in the output otherwise.
	 * 
	 * @see #assertSame(Object, Object, String)
	 * @see #assertNotSame(Object, Object)
	 */
	public void assertSame(Object a, Object b) {
		assertSame(a, b, "Failed asserting that two objects are identical.");
	}

	/**
	 * Asserts that the two arguments are identical, an error
	 * message will be displayed in the output otherwise.
	 * 
	 * @see #assertSame(Object, Object)
	 * @see #assertNotSame(Object, Object, String)
	 */
	public void assertSame(Object a, Object b, String message) {
		assertTrue(a == b, message);
	}

	/**
	 * Asserts that the two arguments are identical, an error
	 * message will be displayed in the output otherwise.
	 * 
	 * @see #assertSame(Object, Object)
	 * @see #assertNotSame(Object, Object, String)
	 */
	public void assertNotSame(Object a, Object b) {
		assertNotSame(a, b , "Failed asserting that two objects are different.");
	}

	/**
	 * Asserts that the two arguments are identical, an error
	 * message will be displayed in the output otherwise.
	 * 
	 * @see #assertSame(Object, Object, String)
	 * @see #assertNotSame(Object, Object)
	 */
	public void assertNotSame(Object a, Object b, String message) {
		assertFalse(a == b, message);
	}



	/**
	 * Asserts that the two arguments are identical, an error
	 * message will be displayed in the output otherwise.
	 * 
	 * @see #assertSame(Object[], Object[], String)
	 * @see #assertNotSame(Object[], Object[])
	 */
	public void assertSame(Object[] a, Object[] b) {
		assertSame(a, b, "Failed asserting that two arrays are identical.");
	}

	/**
	 * Asserts that the two arguments are identical, an error
	 * message will be displayed in the output otherwise.
	 * 
	 * @see #assertSame(Object[], Object[])
	 * @see #assertNotSame(Object[], Object[], String)
	 */
	public void assertSame(Object[] a, Object[] b, String message) {
		assertTrue(Arrays.equals(a, b), message);
	}

	/**
	 * Asserts that the two arguments are identical, an error
	 * message will be displayed in the output otherwise.
	 * 
	 * @see #assertSame(Object[], Object[])
	 * @see #assertNotSame(Object[], Object[], String)
	 */
	public void assertNotSame(Object[] a, Object[] b) {
		assertNotSame(a, b, "Failed asserting that two arrays are different.");
	}

	/**
	 * Asserts that the two arguments are identical, an error
	 * message will be displayed in the output otherwise.
	 * 
	 * @see #assertSame(Object[], Object[], String)
	 * @see #assertNotSame(Object[], Object[])
	 */
	public void assertNotSame(Object[] a, Object[] b, String message) {
		assertFalse(Arrays.equals(a, b), message);
	}



	/**
	 * Asserts that the two arguments are identical, an error
	 * message will be displayed in the output otherwise.
	 * 
	 * @see #assertSame(int, int, String)
	 * @see #assertNotSame(int, int)
	 */
	public void assertSame(int a, int b) {
		assertSame(a, b, "Failed asserting that " + a + " is identical to " + b + ".");
	}

	/**
	 * Asserts that the two arguments are identical, an error
	 * message will be displayed in the output otherwise.
	 * 
	 * @see #assertSame(int, int)
	 * @see #assertNotSame(int, int, String)
	 */
	public void assertSame(int a, int b, String message) {
		assertTrue(a == b, message);
	}

	/**
	 * Asserts that the two arguments are identical, an error
	 * message will be displayed in the output otherwise.
	 * 
	 * @see #assertSame(int, int)
	 * @see #assertNotSame(int, int, String)
	 */
	public void assertNotSame(int a, int b) {
		assertNotSame(a, b, "Failed asserting that " + a + " is different to " + b + ".");
	}

	/**
	 * Asserts that the two arguments are identical, an error
	 * message will be displayed in the output otherwise.
	 * 
	 * @see #assertSame(int, int, String)
	 * @see #assertNotSame(int, int)
	 */
	public void assertNotSame(int a, int b, String message) {
		assertFalse(a == b, message);
	}



	/**
	 * Asserts that the two arguments possesses the same hash, an error
	 * message will be displayed in the output otherwise.
	 * 
	 * @see #assertSameHash(Object, Object, String)
	 * @see #assertNotSameHash(Object, Object)
	 */
	public void assertSameHash(Object a, Object b) {
		assertSameHash(a, b, "Failed asserting that two hash codes are identical.");
	}

	/**
	 * Asserts that the two arguments possesses the same hash, an error
	 * message will be displayed in the output otherwise.
	 * 
	 * @see #assertSameHash(Object, Object)
	 * @see #assertNotSameHash(Object, Object, String)
	 */
	public void assertSameHash(Object a, Object b, String message) {
		assertSame(a.hashCode(), b.hashCode(), message);
	}

	/**
	 * Asserts that the two arguments possesses the same hash, an error
	 * message will be displayed in the output otherwise.
	 * 
	 * @see #assertSameHash(Object, Object)
	 * @see #assertNotSameHash(Object, Object, String)
	 */
	public void assertNotSameHash(Object a, Object b) {
		assertNotSameHash(a, b, "Failed asserting that two hash codes are different.");
	}

	/**
	 * Asserts that the two arguments possesses the same hash, an error
	 * message will be displayed in the output otherwise.
	 * 
	 * @see #assertSameHash(Object, Object, String)
	 * @see #assertNotSameHash(Object, Object)
	 */
	public void assertNotSameHash(Object a, Object b, String message) {
		assertNotSame(a.hashCode(), b.hashCode(), message);
	}



	/**
	 * Asserts that the two arguments are equal, an error
	 * message will be displayed in the output otherwise.
	 * 
	 * @see #assertEquals(Object, Object, String)
	 * @see #assertNotEquals(Object, Object)
	 */
	public void assertEquals(Object a, Object b) {
		assertEquals(a, b, "Failed asserting that two objects are equal.");
	}

	/**
	 * Asserts that the two arguments are equal, an error
	 * message will be displayed in the output otherwise.
	 * 
	 * @see #assertEquals(Object, Object)
	 * @see #assertNotEquals(Object, Object, String)
	 */
	public void assertEquals(Object a, Object b, String message) {
		assertTrue(a.equals(b), message);
	}

	/**
	 * Asserts that the two arguments are equal, an error
	 * message will be displayed in the output otherwise.
	 * 
	 * @see #assertEquals(Object, Object)
	 * @see #assertNotEquals(Object, Object, String)
	 */
	public void assertNotEquals(Object a, Object b) {
		assertNotEquals(a, b, "Failed asserting that two objects are different.");
	}

	/**
	 * Asserts that the two arguments are equal, an error
	 * message will be displayed in the output otherwise.
	 * 
	 * @see #assertEquals(Object, Object, String)
	 * @see #assertNotEquals(Object, Object)
	 */
	public void assertNotEquals(Object a, Object b, String message) {
		assertFalse(a.equals(b), message);
	}

}
