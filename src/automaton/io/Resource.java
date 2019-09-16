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

import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;

/**
 * @author Adrien Belminksy
 */
public class Resource {

	/**
	 * <p>
	 * Returns an input stream of the specified resource.
	 * </p>
	 * 
	 * <p>
	 * Example:
	 * </p>
	 * 
	 * <blockquote>
	 * <code>
	 * Resource.getRes("res/presets/spaceships/glider.life");
	 * </code>
	 * </blockquote>
	 * 
	 * @param path The path of the resource.
	 * 
	 * @return An input stream of the specified resource.
	 * 
	 * @see #getImage(String)
	 */
	public static InputStream getRes(String path) {

		if (path.startsWith("/")) {
			path = path.substring(1);
		}

		return ClassLoader.getSystemClassLoader().getResourceAsStream(path);

	}

	/**
	 * <p>
	 * Returns an image of the specified resource, or null.
	 * The path <b>must</b> start with a slash.
	 * </p>
	 * 
	 * <p>
	 * Example:
	 * </p>
	 * 
	 * <blockquote>
	 * <code>
	 * Resource.getImage("res/texture/icon.png");
	 * </code>
	 * </blockquote>
	 * 
	 * @param path The path of the resource.
	 * 
	 * @return An image of the specified resource, or null.
	 * 
	 * @see #getRes(String)
	 */
	public static Image getImage(String path) {

		try {

			return ImageIO.read(new BufferedInputStream(getRes(path)));

		} catch (Exception e) {
			Console.err(e, Resource.class);
		}

		return null;
	}

}
