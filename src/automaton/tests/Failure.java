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

/**
 * A failure regroup useful informations about
 * an error during an assertion, like the location
 * and the expectation.
 *
 * @author Adrien Belminksy
 */
public class Failure {

	/**
	 * The class where from results the failure.
	 */
	protected Class<?> source;

	/**
	 * The method name where from results the failure.
	 */
	protected String method;

	/**
	 * A descriptive message of the failure.
	 */
	protected String message;


	/**
	 * A failure regroup useful informations about
	 * an error during an assertion, like the location
	 * and the expectation.
	 * 
	 * @param source The class where from results the failure.
	 * @param method The method name where from results the failure.
	 * @param message A descriptive message of the failure.
	 */
	public Failure(Class<?> source, String method, String message) {
		this.source = source;
		this.method = method;
		this.message = message;
	}


	/**
	 * Returns the class where from results the failure.
	 * 
	 * @return The class where from results the failure.
	 */
	public String getSourceName() {
		return source.getSimpleName();
	}

	/**
	 * Returns the method name where from results the failure.
	 * 
	 * @return The method name where from results the failure.
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * Returns a descriptive message of the failure.
	 * 
	 * @return A descriptive message of the failure.
	 */
	public String getMessage() {
		return message;
	}

}
