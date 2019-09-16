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

package automaton.io;

import java.io.PrintStream;

/**
 * @author Adrien Belminksy
 */
public class Console {

	/**
	 * <p>
	 * Display a message in the console, with the following syntax:
	 * <p>
	 * 
	 * <blockquote>
	 * [TAG] package.Class : message
	 * </blockquote>
	 * 
	 * @param stream The stream in which the message is displayed.
	 * @param tag The tag of the message.
	 * @param message The content of the message.
	 * @param cl The class from which this method is called.
	 */
	public static void log(PrintStream stream, String tag, String message, Class<?> cl) {
		stream.println("[" + tag.toUpperCase() + "] " + cl.getCanonicalName() + " : " + message);
	}


	/**
	 * <p>
	 * Display an informative message in the console, with
	 * the following syntax:
	 * </p>
	 * 
	 * <blockquote>
	 * [INFO] package.Class : message
	 * </blockquote>
	 * 
	 * @param message The content of the message.
	 * @param cl The class from which this method is called.
	 */
	public static void write(String message, Class<?> cl) {
		Console.log(System.out, "info", message, cl);
	}

	/**
	 * <p>
	 * Display an informative message in the console, with
	 * the following syntax:
	 * </p>
	 * 
	 * <blockquote>
	 * [INFO] package.Class : message
	 * </blockquote>
	 * 
	 * @param message The content of the message.
	 * @param obj The object from which this method is called.
	 */
	public static void write(String message, Object obj) {
		Console.write(message, obj.getClass());
	}


	/**
	 * <p>
	 * Display an error message in the console, with
	 * the following syntax:
	 * </p>
	 * 
	 * <blockquote>
	 * [ERR] package.Class : message
	 * </blockquote>
	 * 
	 * @param message The content of the message.
	 * @param cl The class from which this method is called.
	 */
	public static void err(String message, Class<?> cl) {
		Console.log(System.err, "err", message, cl);
	}

	/**
	 * <p>
	 * Display an error message in the console, with
	 * the following syntax:
	 * </p>
	 * 
	 * <blockquote>
	 * [ERR] package.Class : message
	 * </blockquote>
	 * 
	 * @param message The content of the message.
	 * @param obj The object from which this method is called.
	 */
	public static void err(String message, Object obj) {
		Console.err(message, obj.getClass());
	}

	/**
	 * <p>
	 * Display an error message in the console, with
	 * the following syntax:
	 * </p>
	 * 
	 * <blockquote>
	 * [ERR] package.Class : message
	 * </blockquote>
	 * 
	 * @param e The exception caught.
	 * @param cl The class from which this method is called.
	 */
	public static void err(Exception e, Class<?> cl) {
		Console.err(e.getMessage(), cl);
	}

	/**
	 * <p>
	 * Display an error message in the console, with
	 * the following syntax:
	 * </p>
	 * 
	 * <blockquote>
	 * [ERR] package.Class : message
	 * </blockquote>
	 * 
	 * @param e The exception caught.
	 * @param obj The class from which this method is called.
	 */
	public static void err(Exception e, Object obj) {
		Console.err(e.getMessage(), obj.getClass());
	}

}
