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

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import automaton.io.Console;

/**
 * The test suite contains all test cases to be launched.
 * 
 * @author Adrien Belminksy
 * @see TestCase
 */
public class TestSuite {

	/**
	 * The printer for each test cases which allows access to the output.
	 */
	protected Printer printer = new Printer();

	/**
	 * The list where all test cases is stored.
	 */
	protected List<TestCase> tests = new ArrayList<>();


	/**
	 * Appends a new test case to the list.
	 * 
	 * @param testcase The test case to add.
	 * 
	 * @see #append(Object)
	 * @see #append(Class)
	 */
	public void append(TestCase testcase) {
		tests.add(testcase);
	}

	/**
	 * Appends a new test case to the list.
	 * 
	 * @param testcase The test case to add.
	 * 
	 * @see #append(TestCase)
	 * @see #append(Class)
	 */
	public void append(Object testcase) {

		if (!(testcase instanceof TestCase)) {
			return;
		}

		append((TestCase) testcase);

	}

	/**
	 * Appends a new test case to the list.
	 * 
	 * @param testcase The test case to add.
	 * 
	 * @see #append(TestCase)
	 * @see #append(Object)
	 */
	public void append(Class<?> testcase) {

		try {

			append(testcase.getConstructor().newInstance());

		} catch (Exception e) {
			Console.err(e, this);
		}

	}


	/**
	 * Runs each test cases.
	 */
	public void run() {

		printer.begin();

		ListIterator<TestCase> iterator = tests.listIterator();

		while (iterator.hasNext()) {

			TestCase testcase = iterator.next();
			testcase.setPrinter(printer);

			run(testcase);

		}

		printer.finish();

	}

	/**
	 * Runs a specific test case.
	 */
	public void run(TestCase testcase) {

		Method[] methods = testcase.getClass().getMethods();

		for (int i = 0; i < methods.length; i++) {

			if (!methods[i].getName().startsWith("test")) {
				continue;
			}

			printer.next();
			testcase.setCurrentMethod(methods[i].getName());

			try {

				methods[i].invoke(testcase, new Object[] { });

			} catch (Exception e) {

				printer.externalFail(new Failure(
					testcase.getClass(),
					methods[i].getName(),
					"Unable to call the '" + methods[i].getName() + "' method."
				));

			}

		}

	}

}
