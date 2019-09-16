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

package automaton.tests.maths;

import automaton.maths.Coordinates;
import automaton.maths.Point;
import automaton.tests.TestCase;

/**
 * @author Adrien Belminksy
 */
public class CoordinatesTest extends TestCase {

	public void testAbsolute() {

		Coordinates coordinates = new Coordinates(50, -25);

		assertSame(coordinates.getAbsoluteX(), 50);
		assertSame(coordinates.getAbsoluteY(), -25);

		assertEquals(coordinates.toAbsolutePoint(), new Point(50, -25));

	}

	public void testRelative() {

		Coordinates coordinates = new Coordinates(50, -25);

		assertSame(coordinates.getRelativeX(), 2);
		assertSame(coordinates.getRelativeY(), 7);

		assertEquals(coordinates.toRelativePoint(), new Point(2, 7));

	}

	public void testChunk() {

		Coordinates coordinates = new Coordinates(50, -25);

		assertSame(coordinates.getChunkX(), 3);
		assertSame(coordinates.getChunkY(), -2);

		assertEquals(coordinates.toChunkPoint(), new Point(3, -2));

	}


	public void testInTheSameChunkOf() {

		Coordinates coordinates1 = new Coordinates(50, -25);
		Coordinates coordinates2 = new Coordinates(55, -30);
		Coordinates coordinates3 = new Coordinates(-25, 50);

		assertTrue(coordinates1.isInTheSameChunkOf(coordinates2));
		assertFalse(coordinates1.isInTheSameChunkOf(coordinates3));

	}

	public void testNormalize() {

		Coordinates coordinates = new Coordinates(50, -25);

		coordinates.normalizeForChunk();

		assertSame(coordinates.getAbsoluteX(), 48);
		assertSame(coordinates.getAbsoluteY(), -32);

	}

	public void testAdd() {

		Coordinates coordinates1 = new Coordinates(50, -25);
		Coordinates coordinates2 = coordinates1.add(10, 20);

		assertEquals(coordinates1.toAbsolutePoint(), new Point(60, -5));
		assertEquals(coordinates2.toAbsolutePoint(), new Point(60, -5));

	}


	public void testClone() {

		Coordinates coordinates1 = new Coordinates(50, -25);
		Coordinates coordinates2 = coordinates1.clone().add(10, 20);

		assertEquals(coordinates1.toAbsolutePoint(), new Point(50, -25));
		assertEquals(coordinates2.toAbsolutePoint(), new Point(60, -5));

	}

	public void testEquals() {

		Coordinates coordinates1 = new Coordinates(50, -25);
		Coordinates coordinates2 = new Coordinates(50, -25);
		Coordinates coordinates3 = new Coordinates(-25, 50);

		assertEquals(coordinates1, coordinates2);
		assertNotEquals(coordinates1, coordinates3);

	}

}
