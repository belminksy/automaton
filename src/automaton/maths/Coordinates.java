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

import automaton.grid.Chunk;

/**
 * <p>
 * The coordinates class is used to define a chunk
 * or a cell location.
 * </p>
 * 
 * <p>
 * Should <b>not</b> be used as a key to store a chunk or
 * a cell in a map, use a point for that.
 * </p>
 * 
 * @author Adrien Belminksy
 * @see Point
 * @see Vector
 */
public class Coordinates {

	protected int x;
	protected int y;


	/**
	 * <p>
	 * The coordinates class is used to define a chunk
	 * or a cell location.
	 * </p>
	 * 
	 * <p>
	 * Should <b>not</b> be used as a key to store a chunk or
	 * a cell in a map, use a point for that.
	 * </p>
	 * 
	 * @param x The absolute abscissa value.
	 * @param y The absolute ordinate value.
	 * 
	 * @see Point
	 * @see Vector
	 */
	public Coordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}


	/**
	 * Returns the absolute abscissa value.
	 * 
	 * @return The absolute abscissa value.
	 * 
	 * @see #getAbsoluteY()
	 * @see #toAbsolutePoint()
	 */
	public int getAbsoluteX() {
		return x;
	}

	/**
	 * Returns the absolute ordinate value.
	 * 
	 * @return The absolute ordinate value.
	 * 
	 * @see #getAbsoluteX()
	 * @see #toAbsolutePoint()
	 */
	public int getAbsoluteY() {
		return y;
	}


	/**
	 * Returns the relative abscissa value, defined
	 * from the origin of the chunk.
	 * 
	 * @return The relative abscissa value.
	 * 
	 * @see #getRelativeY()
	 * @see #toRelativePoint()
	 */
	public int getRelativeX() {
		return Math.floorMod(x, Chunk.SIZE);
	}

	/**
	 * Returns the relative ordinate value, defined
	 * from the origin of the chunk.
	 * 
	 * @return The relative ordinate value.
	 * 
	 * @see #getRelativeX()
	 * @see #toRelativePoint()
	 */
	public int getRelativeY() {
		return Math.floorMod(y, Chunk.SIZE);
	}


	/**
	 * Returns the abscissa of the chunk origin.
	 * 
	 * @return The abscissa of the chunk origin.
	 * 
	 * @see #getChunkY()
	 * @see #toChunkPoint()
	 */
	public int getChunkX() {
		return (x - getRelativeX()) / Chunk.SIZE;
	}

	/**
	 * Returns the ordinate of the chunk origin.
	 * 
	 * @return The ordinate of the chunk origin.
	 * 
	 * @see #getChunkX()
	 * @see #toChunkPoint()
	 */
	public int getChunkY() {
		return (y - getRelativeY()) / Chunk.SIZE;
	}


	/**
	 * Returns the absolute abscissa and absolute ordinate
	 * as a point.
	 * 
	 * @return The absolute abscissa and ordinate as a point.
	 * 
	 * @see #getAbsoluteX()
	 * @see #getAbsoluteY()
	 */
	public Point toAbsolutePoint() {
		return new Point(x, y);
	}

	/**
	 * Returns the relative abscissa and the relative ordinate
	 * as a point, defined from the origin of the chunk.
	 * 
	 * @return The relative abscissa and ordinate as a point.
	 * 
	 * @see #getRelativeX()
	 * @see #getRelativeY()
	 */
	public Point toRelativePoint() {
		return new Point(getRelativeX(), getRelativeY());
	}

	/**
	 * Returns the abscissa and the ordinate of the
	 * chunk origin.
	 * 
	 * @return The abscissa and the ordinate of the chunk origin.
	 * 
	 * @see #getChunkX()
	 * @see #getChunkY()
	 */
	public Point toChunkPoint() {
		return new Point(getChunkX(), getChunkY());
	}


	/**
	 * <p>
	 * Indicates whether some coordinates is contained
	 * in the same chunk of the coordinates arguments.
	 * </p>
	 * 
	 * <p>
	 * This method returns true if the chunk point of
	 * this coordinates and the chunk point of the
	 * coordinates argument are equal.
	 * </p>
	 * 
	 * @return true if the same chunk contains this coordinates and the coordinates argument; false otherwise.
	 *  
	 * @see #toChunkPoint()
	 * @see Point#equals(Object)
	 */
	public boolean isInTheSameChunkOf(Coordinates coordinates) {
		return toChunkPoint().equals(coordinates.toChunkPoint());
	}

	/**
	 * <p>
	 * Replaces the abscissa and the ordinate value
	 * by the chunk origin, in absolute value.
	 * </p>
	 * 
	 * <p>
	 * This method does <b>not</b> returns a new instance of
	 * coordinates, if you need to preserve the value,
	 * use the clone method to create a copy.
	 * </p>
	 * 
	 * @return Self with normalized abscissa and ordinate.
	 * 
	 * @see #clone()
	 */
	public Coordinates normalizeForChunk() {
		this.x = getChunkX() * Chunk.SIZE;
		this.y = getChunkY() * Chunk.SIZE;

		return this;
	}


	/**
	 * <p>
	 * Changes the abscissa and the ordinate value
	 * by addition.
	 * </p>
	 * 
	 * <p>
	 * This method does <b>not</b> returns a new instance of
	 * coordinates, if you need to preserve the value,
	 * use the clone method to create a copy.
	 * </p>
	 * 
	 * @param x Value to add to the abscissa.
	 * @param y Value to add to the ordinate.
	 * 
	 * @return Self with new abscissa and ordinate.
	 * 
	 * @see #clone()
	 */
	public Coordinates add(int x, int y) {
		this.x += x;
		this.y += y;

		return this;
	}


	/**
	 * <p>
	 * Returns a new instance of this coordinates
	 * with the same values and properties.
	 * </p>
	 * 
	 * <p>
	 * Can be useful with the normalizeForChunk or
	 * the add methods.
	 * </p>
	 * 
	 * @return A new instance of this coordinates.
	 * 
	 * @see #normalizeForChunk()
	 * @see #add(int, int)
	 */
	public Coordinates clone() {
		return new Coordinates(x, y);
	}


	/**
	 * Indicates whether some object is equal to
	 * this coordinates.
	 * 
	 * @return true if this coordinates is the same as the object argument; false otherwise.
	 */
	public boolean equals(Object object) {

		if (object == this) {
			return true;
		}

		if (object == null || object.getClass() != getClass()) {
			return false;
		}

		Coordinates coordinates = (Coordinates) object;

		return coordinates.x == x && coordinates.y == y;

	}

}
