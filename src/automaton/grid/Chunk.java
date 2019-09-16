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

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import automaton.config.Conf;
import automaton.maths.Coordinates;
import automaton.maths.Point;
import automaton.render.RenderingContext;

/**
 * <p>
 * The chunk class represents a fragment of the grid
 * containing some cells. A chunk is a square of 16x16 cells.
 * </p>
 * 
 * <p>
 * In the grid system, a world contains chunks, a chunk
 * contains cells.
 * </p>
 * 
 * @author Adrien Belminksy
 * @see World
 * @see Cell
 */
public class Chunk {

	public static final int SIZE = 16;


	/**
	 * The map where all cells contained by this chunk are stored with their relative coordinates.
	 */
	protected Map<Point, Cell> cells = new ConcurrentHashMap<>();

	/**
	 * The world containing this chunk.
	 */
	protected World world;

	/**
	 * The coordinates of this chunk.
	 */
	protected Coordinates coordinates;


	/**
	 * <p>
	 * The chunk class represent a fragment of the grid
	 * containing some cells. A chunk is a square of 16x16 cells.
	 * </p>
	 * 
	 * <p>
	 * In the grid system, a world contains chunks, a chunk
	 * contains cells.
	 * </p>
	 * 
	 * @param world The world containing this chunk.
	 * @param coordinates The coordinates of this chunk.
	 * 
	 * @see World
	 * @see Cell
	 */
	public Chunk(World world, Coordinates coordinates) {
		this.world = world;
		this.coordinates = coordinates;
	}


	/**
	 * Updates the next state of all contained cells.
	 */
	public void updateNextState() {

		Iterator<Entry<Point, Cell>> iterator = getCells();

		while (iterator.hasNext()) {

			iterator.next().getValue().update();

		}

	}

	/**
	 * Updates the state of all contained cells.
	 */
	public void updateState() {

		Iterator<Entry<Point, Cell>> iterator = getCells();

		while (iterator.hasNext()) {

			Cell cell = iterator.next().getValue();

			cell.state = cell.nextState;

			/* If the cell is alive, it appears. */
			if (cell.isAlive())
				cell.appear();

			/* If the cell is dead, it is deleted. */
			if (cell.isDead())
				remove(cell);

		}

	}

	/**
	 * Renders the chunk on a canvas through a rendering context.
	 * 
	 * @param context The rendering context.
	 */
	public void render(RenderingContext context) {

		Iterator<Entry<Point, Cell>> iterator = getCells();

		if (Conf.CANVAS_ACTIVE_CHUNK_BORDERS) {
			context.drawChunkBorder(this);
		}

		while (iterator.hasNext()) {

			Cell cell = iterator.next().getValue();

			if (!cell.isAlive()) {
				continue;
			}

			context.drawCell(cell);

		}

	}


	/**
	 * Registers a new cell on the chunk.
	 * 
	 * @param cell The cell to be registered.
	 * 
	 * @see #active(Coordinates)
	 */
	public void register(Cell cell) {
		active(cell.getCoordinates(), false);
	}

	/**
	 * Creates a new cell on the chunk from their coordinates.
	 * 
	 * @param coordinates The coordinates of the cell.
	 * 
	 * @see #register(Cell)
	 * @see #active(Coordinates, boolean)
	 */
	public void active(Coordinates coordinates) {
		active(coordinates, false);
	}

	/**
	 * <p>
	 * Creates a new cell on the chunk from their coordinates.
	 * </p>
	 * 
	 * <p>
	 * This method can make the cell alive with the appear
	 * argument.
	 * </p>
	 * 
	 * @param coordinates The coordinates of the cell.
	 * @param appear Make the cell alive if it is true.
	 * 
	 * @see #active(Coordinates)
	 */
	public void active(Coordinates coordinates, boolean appear) {

		if (!getCoordinates().isInTheSameChunkOf(coordinates)) {

			world.getChunkAt(coordinates, true).active(coordinates, appear);
			return;

		}

		Cell cell = new Cell(this, coordinates);

		if (appear) {
			cell.appear();
		}

		cells.put(coordinates.toRelativePoint(), cell);
	}


	/**
	 * Removes an existing cell of the chunk.
	 * 
	 * @param cell The cell to remove.
	 */
	public void remove(Cell cell) {
		cells.remove(cell.getCoordinates().toRelativePoint());
	}


	/**
	 * Indicates if a cell is present at the specified location.
	 * 
	 * @param coordinates The cell coordinates.
	 * 
	 * @return true if there is a cell; false otherwise.
	 */
	public boolean hasCellAt(Coordinates coordinates) {

		if (!world.hasChunkAt(coordinates)) {
			return false;
		}

		if (!getCoordinates().isInTheSameChunkOf(coordinates)) {
			return world.getChunkAt(coordinates).hasCellAt(coordinates);
		}

		return cells.containsKey(coordinates.toRelativePoint());


		/*if (!getCoordinates().isInTheSameChunkOf(coordinates)) {

			if (!world.hasChunkAt(coordinates)) {
				return false;
			}

			return world.getChunkAt(coordinates).hasCellAt(coordinates);
		}

		return cells.containsKey(coordinates.toRelativePoint());/**/
	}


	/**
	 * Returns the cell present at the specified location or null
	 * if it does not exist.
	 * 
	 * @param coordinates The cell coordinates.
	 * 
	 * @return A cell or null
	 * 
	 * @see Cell
	 */
	public Cell getCellAt(Coordinates coordinates) {

		if (!hasCellAt(coordinates)) {
			return null;
		}

		if (!getCoordinates().isInTheSameChunkOf(coordinates)) {
			return world.getChunkAt(coordinates).getCellAt(coordinates);
		}

		return cells.get(coordinates.toRelativePoint());
	}


	/**
	 * Indicates if the chunk contains cells or not.
	 * 
	 * @return true if the chunk is empty; false otherwise.
	 */
	public boolean isEmpty() {
		return cells.isEmpty();
	}


	/**
	 * Returns an iterator of all contained cells coupled with
	 * their relative coordinates.
	 * 
	 * @return An iterator of all contained cells.
	 * 
	 * @see Cell
	 * @see Point
	 */
	public Iterator<Entry<Point, Cell>> getCells() {
		return cells.entrySet().iterator();
	}

	/**
	 * Returns the coordinates of the chunk.
	 * 
	 * @return The coordinates of the chunk.
	 */
	public Coordinates getCoordinates() {
		return coordinates;
	}

}
