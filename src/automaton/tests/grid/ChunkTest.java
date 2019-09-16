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
public class ChunkTest extends TestCase {

	public void testHasCellAt() {

		World world = new World();
		Chunk chunk = new Chunk(world, new Coordinates(0, 0));

		world.register(chunk);

		chunk.register(new Cell(chunk, new Coordinates(5, 5)));
		chunk.register(new Cell(chunk, new Coordinates(-5, -5)));

		assertTrue(chunk.hasCellAt(new Coordinates(5, 5)));
		assertTrue(chunk.hasCellAt(new Coordinates(-5, -5)));

		assertFalse(chunk.hasCellAt(new Coordinates(3, -3)));

	}

	public void testGetCellAt() {

		World world = new World();
		Chunk chunk = new Chunk(world, new Coordinates(0, 0));

		world.register(chunk);

		chunk.register(new Cell(chunk, new Coordinates(5, 5)));

		assertEquals(chunk.getCellAt(new Coordinates(5, 5)), new Cell(chunk, new Coordinates(5, 5)));
		assertNull(chunk.getCellAt(new Coordinates(-5, -5)));

	}


	public void testActive() {

		World world = new World();
		Chunk chunk = new Chunk(world, new Coordinates(0, 0));

		world.register(chunk);

		chunk.active(new Coordinates(5, 5));
		chunk.active(new Coordinates(-5, -5));

		assertTrue(chunk.hasCellAt(new Coordinates(5, 5)));
		assertTrue(chunk.hasCellAt(new Coordinates(-5, -5)));

		assertSame(chunk.getCellAt(new Coordinates(5, 5)).getChunk(), chunk);
		assertNotSame(chunk.getCellAt(new Coordinates(-5, -5)).getChunk(), chunk);

	}

	public void testRemove() {

		World world = new World();
		Chunk chunk = new Chunk(world, new Coordinates(0, 0));

		world.register(chunk);

		chunk.register(new Cell(chunk, new Coordinates(5, 5)));
		chunk.remove(chunk.getCellAt(new Coordinates(5, 5)));

		assertFalse(chunk.hasCellAt(new Coordinates(5, 5)));

	}


	public void testIsEmpty() {

		Chunk chunk = new Chunk(new World(), new Coordinates(0, 0));

		assertTrue(chunk.isEmpty());

		chunk.register(new Cell(chunk, new Coordinates(5, 5)));

		assertFalse(chunk.isEmpty());

	}

}
