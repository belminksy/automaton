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

package automaton.grid;

import automaton.maths.Coordinates;

/**
 * <p>
 * The cell class represents the smallest unity
 * of the grid system.
 * <p>
 * 
 * <p>
 * In the grid system, a world contains chunks, a chunk
 * contains cells.
 * </p>
 * 
 * @author Adrien Belminksy
 * @see World
 * @see Chunk
 */
public class Cell {

	/**
	 * The cell is invisible with this state.
	 */
	public static final int NOTHING = 0;

	/**
	 * The cell is visible and alive with this state.
	 */
	public static final int ALIVE = 1;

	/**
	 * The cell is visible but she will die to the
	 * next generation with this state.
	 */
	public static final int DEAD = 2;


	/**
	 * The chunk containing this cell.
	 */
	protected Chunk chunk;

	/**
	 * The coordinates of this cell.
	 */
	protected Coordinates coordinates;


	/**
	 * The current state of the cell.
	 */
	protected int state = NOTHING;

	/**
	 * The current next planned sate of the cell.
	 */
	protected int nextState = NOTHING;


	/**
	 * <p>
	 * The cell class represent the smallest unity
	 * of the grid system.
	 * </p>
	 * 
	 * <p>
	 * In the grid system, a world contains chunks, a chunk
	 * contains cells.
	 * </p>
	 * 
	 * @param chunk The chunk containing this cell.
	 * @param coordinates The coordinates of this cell.
	 * 
	 * @see World
	 * @see Chunk
	 */
	public Cell(Chunk chunk, Coordinates coordinates) {
		this.chunk = chunk;
		this.coordinates = coordinates;
	}


	/**
	 * Updates the next state of the cell.
	 */
	public void update() {

		/* If the cell is alive and isolated, it disappears. */
		if (isAlive() && isIsolated())
			nextState = NOTHING;

		/* If the cell is alive and overpopulated, it disappears. */
		else if (isAlive() && isOverpopulated())
			nextState = NOTHING;

		/* If the cell possesses 3 alive neighbors, it becomes alive. */
		else if (getNumberOfNeighbors() == 3)
			nextState = ALIVE;

		/* If the cell possesses 2 alive neighbors, it keeps its current state. */
		else if (getNumberOfNeighbors() == 2)
			nextState = state;

		/* If the cell is not alive and is alone, it dies. */
		else if (!isAlive() && getNumberOfNeighbors() == 0)
			nextState = DEAD;

	}


	/**
	 * Makes the cell alive.
	 */
	public void appear() {

		state = ALIVE;

		Coordinates[] influence = getInfluenceArea();

		for (int i = 0; i < influence.length; i++) {

			if (chunk.hasCellAt(influence[i]) && chunk.getCellAt(influence[i]).nextState != DEAD) {
				continue;
			}

			chunk.active(influence[i]);

		}

	}


	/**
	 * Indicates if the cell is alive.
	 * 
	 * @return true if the cell is alive; false otherwise.
	 */
	public boolean isAlive() {
		return state == ALIVE;
	}

	/**
	 * Indicates if the cell is dead. The cell can
	 * be not alive <b>and</b> be not dead if his
	 * state is nothing.
	 * 
	 * @return true if the cell is dead; false otherwise.
	 */
	public boolean isDead() {
		return state == DEAD;
	}


	/**
	 * Indicates if the cell is isolated according
	 * to the rules of the Game of Life.
	 * 
	 * @return true if the cell is isolated; false otherwise.
	 */
	public boolean isIsolated() {
		return getNumberOfNeighbors() < 2;
	}

	/**
	 * Indicates if the cell is overpopulated according
	 * to the rules of the Game of Life.
	 * 
	 * @return true if the cell is overpopulated; false otherwise.
	 */
	public boolean isOverpopulated() {
		return getNumberOfNeighbors() > 3;
	}


	/**
	 * Returns the number of alive neighboring cells in the
	 * influence area of this cell.
	 * 
	 * @return The number of living cells neighbors.
	 */
	public int getNumberOfNeighbors() {

		Coordinates[] influence = getInfluenceArea();
		Cell cell;

		int number = 0;

		for (int i = 0; i < influence.length; i++) {

			cell = chunk.getCellAt(influence[i]);

			if (cell == null) {
				continue;
			}

			if (cell.isAlive()) {
				number++;
			}

		}

		return number;
	}


	/**
	 * Returns an array of coordinates of the influence area
	 * of this cell.
	 * 
	 * @return An array of coordinates of the influence area.
	 * 
	 * @see Coordinates
	 */
	public Coordinates[] getInfluenceArea() {

		return new Coordinates[] {
			getCoordinates().clone().add(-1, 1),
			getCoordinates().clone().add( 0, 1),
			getCoordinates().clone().add( 1, 1),
			getCoordinates().clone().add(-1, 0),
			getCoordinates().clone().add( 1, 0),
			getCoordinates().clone().add(-1,-1),
			getCoordinates().clone().add( 0,-1),
			getCoordinates().clone().add( 1,-1)
		};

	}


	/**
	 * Returns the chunk containing the cell.
	 * 
	 * @return The chunk containing the cell.
	 */
	public Chunk getChunk() {
		return chunk;
	}

	/**
	 * Returns the coordinates of the cell.
	 * 
	 * @return The coordinates of the cell.
	 */
	public Coordinates getCoordinates() {
		return coordinates;
	}


	/**
	 * <p>
	 * Indicates whether some object is equal to
	 * this cell.
	 * </p>
	 * 
	 * <p>
	 * This method is used for the unit tests.
	 * </p>
	 * 
	 * @return true if this cell is the same as the object argument; false otherwise.
	 */
	public boolean equals(Object object) {

		if (object == this) {
			return true;
		}

		if (object == null || object.getClass() != getClass()) {
			return false;
		}

		Cell cell = (Cell) object;

		return cell.getCoordinates().equals(getCoordinates());

	}

}
