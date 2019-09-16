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

package automaton.config;

import java.awt.Color;

/**
 * The configuration class.
 *
 * @author Adrien Belminksy
 */
public class Conf {

	public static final String APP_NAME = "Automaton";
	public static final String APP_VERSION = "1.0";


	public static final String WINDOW_TITLE = Conf.APP_NAME + " " + Conf.APP_VERSION;

	public static final int WINDOW_WIDTH = 975;
	public static final int WINDOW_HEIGHT = 650;


	public static double ENGINE_TPS = 6;
	public static final double ENGINE_FPS = 12;


	public static boolean CANVAS_ACTIVE_EDITING = true;
	public static boolean CANVAS_ACTIVE_COLORED_CELLS = false;
	public static boolean CANVAS_ACTIVE_CHUNK_BORDERS = false;

	public static final Color CANVAS_DEFAULT_CELL_COLOR = Color.BLACK;
	public static final Color CANVAS_CHUNK_BORDERS_COLOR = Color.GRAY;

	public static final Color CANVAS_LIVING_CELL_COLOR = Color.BLUE;
	public static final Color CANVAS_DYING_CELL_COLOR  = Color.RED;

	public static final int CANVAS_DEFAULT_ZOOM = 15;
	public static final int CANVAS_MAXIMUM_ZOOM = 20;
	public static final int CANVAS_MINIMUM_ZOOM = 1;

}
