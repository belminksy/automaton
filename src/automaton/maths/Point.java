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

package automaton.maths;

/**
 * <p>
 * The point class is used as a key to store
 * a chunk or a cell in a map.
 * </p>
 * 
 * <p>
 * Should <b>not</b> be used to define a chunk or a cell
 * location, use a coordinates for that.
 * </p>
 * 
 * @author Adrien Belminksy
 * @see Coordinates
 * @see Vector
 */
public class Point {

	protected int x;
	protected int y;


	/**
	 * <p>
	 * The point class is used as a key to store
	 * a chunk or a cell in a map.
	 * </p>
	 * 
	 * <p>
	 * Should <b>not</b> be used to define a chunk or a cell
	 * location, use a coordinates for that.
	 * </p>
	 * 
	 * @see Coordinates
	 * @see Vector
	 */
	public Point() {
		this(0, 0);
	}

	/**
	 * <p>
	 * The point class is used as a key to store
	 * a chunk or a cell in a map.
	 * </p>
	 * 
	 * <p>
	 * Should <b>not</b> be used to define a chunk or a cell
	 * location, use a coordinates for that.
	 * </p>
	 * 
	 * @param x The default abscissa value.
	 * @param y The default ordinate value.
	 * 
	 * @see Coordinates
	 * @see Vector
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}


	/**
	 * Defines a new abscissa value.
	 * 
	 * @param newx The new abscissa value.
	 */
	public void setX(int newx) {
		this.x = newx;
	}

	/**
	 * Defines a new ordinate value.
	 * 
	 * @param newy The new ordinate value.
	 */
	public void setY(int newy) {
		this.y = newy;
	}


	/**
	 * Returns the abscissa value.
	 * 
	 * @return The abscissa value.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Returns the ordinate value.
	 * 
	 * @return The ordinate value.
	 */
	public int getY() {
		return y;
	}


	/**
	 * <p>
	 * Returns a string representation of the point.
	 * This method returns a string equal to
	 * the value of:
	 * </p>
	 * 
	 * <blockquote>
	 * x + ", " + y
	 * </blockquote>
	 * 
	 * @return A string representation of the point.
	 */
	public String toString() {
		return x + ", " + y;
	}

	/**
	 * <p>
	 * Returns a hash code value for the point.
	 * The hash code is generated from the string
	 * representation of the point.
	 * </p>
	 * 
	 * <p>
	 * This method is used internally by the
	 * ConcurrentHashMap object.
	 * </p>
	 * 
	 * @return A hash code value for the point.
	 * 
	 * @see #toString()
	 */
	public int hashCode() {
		return toString().hashCode();
	}


	/**
	 * <p>
	 * Indicates whether some object is equal to
	 * this point.
	 * </p>
	 * 
	 * <p>
	 * This method is used internally by the
	 * ConcurrentHashMap object.
	 * </p>
	 * 
	 * @return true if this point is the same as the object argument; false otherwise.
	 */
	public boolean equals(Object object) {

		if (object == this) {
			return true;
		}

		if (object == null || object.getClass() != getClass()) {
			return false;
		}

		Point point = (Point) object;

		return point.getX() == x && point.getY() == y;
	}

}
