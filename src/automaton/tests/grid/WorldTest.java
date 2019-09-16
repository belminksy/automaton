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

import automaton.grid.Chunk;
import automaton.grid.World;
import automaton.maths.Coordinates;
import automaton.tests.TestCase;

/**
 * @author Adrien Belminksy
 */
public class WorldTest extends TestCase {

	public void testHasChunkAt() {

		World world = new World();

		world.register(new Chunk(world, new Coordinates(50, -25)));

		assertTrue(world.hasChunkAt(new Coordinates(50, -25)));
		assertFalse(world.hasChunkAt(new Coordinates(-25, 50)));

	}

	public void testGetChunkAt() {

		World world = new World();
		Chunk chunk = new Chunk(world, new Coordinates(50, -25));

		world.register(chunk);

		assertSame(chunk, world.getChunkAt(new Coordinates(50, -25)));
		assertSame(chunk, world.getChunkAt(new Coordinates(55, -30)));

		assertNull(world.getChunkAt(new Coordinates(-25, 50)));

		assertNotNull(world.getChunkAt(new Coordinates(-25, 50), true));

	}


	public void testActive() {

		World world = new World();

		world.active(new Coordinates(50, -25));

		assertTrue(world.hasChunkAt(new Coordinates(50, -25)));
		assertTrue(world.getChunkAt(new Coordinates(50, -25)).hasCellAt(new Coordinates(50, -25)));

	}

	public void testToogle() {

		World world = new World();

		world.active(new Coordinates(5, -5));
		world.toogle(new Coordinates(5, -5));
		world.toogle(new Coordinates(-25, 25));

		assertTrue(world.hasChunkAt(new Coordinates(5, -5)));
		assertTrue(world.hasChunkAt(new Coordinates(-25, 25)));

		assertTrue(world.getChunkAt(new Coordinates(-25, 25)).hasCellAt(new Coordinates(-25, 25)));

	}


	public void testRemove() {

		World world = new World();

		world.register(new Chunk(world, new Coordinates(50, -25)));
		world.register(new Chunk(world, new Coordinates(-25, 50)));

		world.remove(world.getChunkAt(new Coordinates(50, -25)));

		assertFalse(world.hasChunkAt(new Coordinates(50, -25)));
		assertTrue(world.hasChunkAt(new Coordinates(-25, 50)));

	}

	public void testClear() {

		World world = new World();

		world.register(new Chunk(world, new Coordinates(50, -25)));
		world.register(new Chunk(world, new Coordinates(-25, 50)));

		world.clear();

		assertTrue(world.isEmpty());

	}

	public void testIsEmpty() {

		World world = new World();

		assertTrue(world.isEmpty());

		world.active(new Coordinates(50, -25));

		assertFalse(world.isEmpty());

	}

}
