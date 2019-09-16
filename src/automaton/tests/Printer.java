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

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import automaton.config.Conf;

/**
 * The printer allows access to the output
 * for test suites and test cases.
 *
 * @author Adrien Belminksy
 */
public class Printer {

	/**
	 * The list where all failures is stored.
	 */
	protected List<Failure> failures = new ArrayList<>();

	protected int testsCounter = 0;
	protected int failuresCounter = 0;
	protected int assertionsCounter = 0;


	/**
	 * Start phase of the test suite.
	 * 
	 * @see #finish()
	 */
	public void begin() {

		System.out.println(Conf.APP_NAME + " " + Conf.APP_VERSION + " unit tests");

	}

	/**
	 * End phase of the test suite
	 * 
	 * @see #begin()
	 */
	public void finish() {

		System.out.println();
		System.out.println();

		if (failuresCounter == 0) {

			System.out.print("OK ");
			System.out.print("(");
			System.out.print(testsCounter + " Tests, ");
			System.out.print(assertionsCounter + " Assertions");
			System.out.print(") ");
			System.out.println("100%");

			return;

		}

		ListIterator<Failure> iterator = failures.listIterator();

		while (iterator.hasNext()) {

			Failure failure = iterator.next();

			System.out.print(failure.getSourceName() + " ");
			System.out.print("(" + failure.getMethod() + "): ");
			System.out.println(failure.getMessage());

		}

		int percentage = Math.round( ( (float) assertionsCounter - (float) failuresCounter ) / (float) assertionsCounter * 100 );

		System.out.println();
		System.out.print("FAILURES ");
		System.out.print("(");
		System.out.print(testsCounter + " Tests, ");
		System.out.print(failuresCounter + " Failures, ");
		System.out.print(assertionsCounter + " Assertions");
		System.out.print(")");

		if (percentage >= 0) {
			System.out.print(" " + percentage + "%");
		}

		System.out.println();

	}


	/**
	 * Informs that the test suite go to the next test method.
	 */
	public void next() {
		testsCounter++;
	}

	/**
	 * Informs that an assertion finished with success.
	 * 
	 * @see #fail(Failure)
	 */
	public void succeed() {

		if (assertionsCounter % 30 == 0) {
			System.out.println();
		}

		System.out.print('·');
		assertionsCounter++;

	}

	/**
	 * Informs that an assertion finished with a failure.
	 * 
	 * @see #succeed()
	 * @see #externalFail(Failure)
	 */
	public void fail(Failure failure) {

		if (assertionsCounter % 30 == 0) {
			System.out.println();
		}

		System.out.print('F');

		failures.add(failure);

		assertionsCounter++;
		failuresCounter++;

	}

	/**
	 * Informs that an external exception occurs.
	 * 
	 * @see #fail(Failure)
	 */
	public void externalFail(Failure failure) {

		if (assertionsCounter % 30 == 0) {
			System.out.println();
		}

		System.out.print('F');

		failures.add(failure);
		failuresCounter++;

	}

}
