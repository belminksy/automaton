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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;

import automaton.grid.Cell;
import automaton.grid.Chunk;
import automaton.grid.World;
import automaton.maths.Coordinates;
import automaton.maths.Point;
import automaton.tests.TestCase;

/**
 * @author Adrien Belminksy
 */
public class EvolutionTest extends TestCase {

	public void testEvolution() {

		World world = new World();

		world.active(new Coordinates(1, 2));
		world.active(new Coordinates(0, 1));
		world.active(new Coordinates(0, 0));
		world.active(new Coordinates(1, 0));
		world.active(new Coordinates(2, 0));

		for (int i = 0; i < 5; i++) {
			world.update();
		}


		ArrayList<Point> expectedList = new ArrayList<>();

		expectedList.add(new Point(0, -1));
		expectedList.add(new Point(0, -2));
		expectedList.add(new Point(-1, 0));
		expectedList.add(new Point(-1, -1));
		expectedList.add(new Point(1, 0));


		int size = 0;
		Iterator<Entry<Point, Chunk>> chunks = world.getChunks();

		while (chunks.hasNext()) {

			Iterator<Entry<Point, Cell>> cells = chunks.next().getValue().getCells();

			while (cells.hasNext()) {

				Cell cell = cells.next().getValue();

				if (!cell.isAlive()) {
					continue;
				}

				size += 1;
				Point point = cell.getCoordinates().toAbsolutePoint();

				if (!expectedList.contains(point)) {
					fail("Failed asserting that the expected coordinates list contains (" + point.toString() + ").");
				}

			}

		}


		if (expectedList.size() != size) {
			fail("Failed asserting that the expected coordinates list contains the same number of generated cells.");
		}		

	}

}
