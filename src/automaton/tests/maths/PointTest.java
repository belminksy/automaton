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

import automaton.maths.Point;
import automaton.tests.TestCase;

/**
 * @author Adrien Belminksy
 */
public class PointTest extends TestCase {

	public void testToString() {

		Point point1 = new Point(2, 3);
		Point point2 = new Point(4, 5);
		Point point3 = new Point(6, 7);

		point3.setX(8);
		point3.setY(9);

		assertEquals(point1.toString(), "2, 3");
		assertEquals(point2.toString(), "4, 5");
		assertEquals(point3.toString(), "8, 9");

	}

	public void testHashCode() {

		Point point1 = new Point(5, 6);
		Point point2 = new Point(5, 6);
		Point point3 = new Point(5, 6);

		point3.setX(7);
		point3.setY(8);

		assertSameHash(point1, point2);
		assertNotSameHash(point1, point3);

	}

	public void testEquals() {

		Point point1 = new Point(5, 6);
		Point point2 = new Point(5, 6);
		Point point3 = new Point(5, 6);

		point3.setX(7);
		point3.setY(8);

		assertEquals(point1, point2);
		assertNotEquals(point1, point3);

	}

}
