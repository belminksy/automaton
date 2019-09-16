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

package automaton.tests.grid;

import automaton.grid.Cell;
import automaton.grid.Chunk;
import automaton.grid.World;
import automaton.maths.Coordinates;
import automaton.tests.TestCase;

/**
 * @author Adrien Belminksy
 */
public class CellTest extends TestCase {

	public void testIsIsolated() {

		World world = new World();
		Chunk chunk = new Chunk(world, new Coordinates(0, 0));
		Cell cell = new Cell(chunk, new Coordinates(5, 5));

		world.register(chunk);
		chunk.register(cell);

		chunk.active(new Coordinates(4, 4), true);

		assertTrue(cell.isIsolated());

		chunk.active(new Coordinates(6, 6), true);

		assertFalse(cell.isIsolated());

	}

	public void testIsOverpopulated() {

		World world = new World();
		Chunk chunk = new Chunk(world, new Coordinates(0, 0));
		Cell cell = new Cell(chunk, new Coordinates(5, 5));

		world.register(chunk);
		chunk.register(cell);

		chunk.active(new Coordinates(4, 4), true);
		chunk.active(new Coordinates(4, 5), true);
		chunk.active(new Coordinates(4, 6), true);

		assertFalse(cell.isOverpopulated());

		chunk.active(new Coordinates(6, 6), true);

		assertTrue(cell.isOverpopulated());

	}

	public void testGetNumberOfNeighbors() {

		World world = new World();
		Chunk chunk = new Chunk(world, new Coordinates(0, 0));
		Cell cell = new Cell(chunk, new Coordinates(5, 5));

		world.register(chunk);
		chunk.register(cell);

		assertSame(cell.getNumberOfNeighbors(), 0);

		chunk.active(new Coordinates(4, 4), true);
		assertSame(cell.getNumberOfNeighbors(), 1);

		chunk.active(new Coordinates(4, 5), true);
		chunk.active(new Coordinates(3, 3), true);
		assertSame(cell.getNumberOfNeighbors(), 2);

		chunk.active(new Coordinates(4, 6), true);
		chunk.active(new Coordinates(5, 6));
		assertSame(cell.getNumberOfNeighbors(), 3);

	}

	public void testGetInfluenceArea() {

		Chunk chunk = new Chunk(new World(), new Coordinates(0, 0));
		Cell cell = new Cell(chunk, new Coordinates(5, 5));

		chunk.register(cell);

		Coordinates[] influence = new Coordinates[] {
			new Coordinates(4, 6),
			new Coordinates(5, 6),
			new Coordinates(6, 6),
			new Coordinates(4, 5),
			new Coordinates(6, 5),
			new Coordinates(4, 4),
			new Coordinates(5, 4),
			new Coordinates(6, 4)
		};

		assertSame(cell.getInfluenceArea(), influence);

	}


	public void testEquals() {

		Chunk chunk = new Chunk(new World(), new Coordinates(0, 0));
		Chunk anotherChunk = new Chunk(new World(), new Coordinates(0, 0));

		Cell cell1 = new Cell(chunk, new Coordinates(5, 5));
		Cell cell2 = new Cell(chunk, new Coordinates(5, 5));
		Cell cell3 = new Cell(anotherChunk, new Coordinates(5, 5));
		Cell cell4 = new Cell(chunk, new Coordinates(6, 6));

		assertEquals(cell1, cell2);
		assertEquals(cell1, cell3);
		assertNotEquals(cell1, cell4);

	}

}
