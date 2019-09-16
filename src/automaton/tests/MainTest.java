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

package automaton.tests;

import automaton.tests.grid.CellTest;
import automaton.tests.grid.ChunkTest;
import automaton.tests.grid.EvolutionTest;
import automaton.tests.grid.WorldTest;
import automaton.tests.maths.CoordinatesTest;
import automaton.tests.maths.PointTest;

/**
 * Unit tests of an implementation of the Conway's Game of Life.
 *
 * @author Adrien Belminksy
 */
public class MainTest {

	/**
	 * The second entry point of the application.
	 */
	public static void main(String[] args) {

		TestSuite suite = new TestSuite();

		suite.append(CoordinatesTest.class);
		suite.append(PointTest.class);

		suite.append(WorldTest.class);
		suite.append(ChunkTest.class);
		suite.append(CellTest.class);
		suite.append(EvolutionTest.class);

		suite.run();

	}

}
