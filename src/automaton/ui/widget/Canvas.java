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

package automaton.ui.widget;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;

import automaton.config.Conf;
import automaton.grid.World;
import automaton.maths.Coordinates;
import automaton.maths.Point;
import automaton.maths.Vector;
import automaton.render.RenderingContext;

/**
 * Canvas component displayed in the window.
 * 
 * @author Adrien Belminksy
 * @author LHashDe
 * @see Window
 */
public class Canvas extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener {

	/**
	 * The world containing all chunks and all cells.
	 */
	protected World world;

	/**
	 * The rendering context for displaying.
	 */
	protected RenderingContext context = new RenderingContext();


	/**
	 * Value of the previous translation on the canvas.
	 * 
	 * @see #mouseDragged(MouseEvent)
	 */
	protected Vector translation = new Vector();

	/**
	 * Value of the source position of the previous
	 * translation on the canvas.
	 * 
	 * @see #mousePressed(MouseEvent)
	 */
	protected Point source = new Point();

	/**
	 * Value of the saved final position of the
	 * previous on the canvas.
	 * 
	 * @see #mouseReleased(MouseEvent)
	 */
	protected Point saved  = new Point();


	/**
	 * Canvas component displayed in the window.
	 * 
	 * @see Window
	 */
	public Canvas() {

		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.addMouseWheelListener(this);

	}


	/**
	 * Update the rendering.
	 */
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		g.translate(translation.getX(), translation.getY());

		
		context.setGraphics(g);
		world.render(context);

	}


	/**
	 * Creates or removes a cell at the location clicked.
	 */
	public void mouseClicked(MouseEvent e) {

		if (!Conf.CANVAS_ACTIVE_EDITING) {
			return;
		}

		/*
		 * On rapporte les coordonnées du clic au centre du repère.
		 * Pour cela on doit annuler la translation en la soustrayant.
		 * 
		 * L'axe des ordonnées est renversé car la grille est renversée
		 * par rapport à la position des pixels sur l'écran.
		 */
		int x = e.getX() - translation.getX();
		int y = -e.getY() + translation.getY();

		/*
		 * On enlève les marges du clic pour se rammener aux coordonnées exactes
		 * de la cellule
		 * 
		 * Par exemple, si le niveau de zoom est égal à 15, cela signifie
		 * que les cellules ont une taille de 15x15 pixels.
		 * Donc si on clic à x=20, la cellule concernée est donc la seconde
		 * sur l'axe des abscisses.
		 * 
		 * Pour cela, si x=20 et zoomLevel=15, alors:
		 * 
		 *   (x - (x % zoomLevel)) / zoomLevel
		 * = (20 - (20 % 15)) / 15
		 * = 1, soit la seconde cellule sur l'axe des abscisses car on commence
		 *      à compter à partir de zéro.
		 */
		Coordinates coordinates = new Coordinates(
			(x - Math.floorMod(x, context.getZoomLevel())) / context.getZoomLevel(),
			(y - Math.floorMod(y, context.getZoomLevel())) / context.getZoomLevel()
		);

		/*
		 * La cellule est créée si il n'en n'existe pas déjà à cette position.
		 * Sinon elle est supprimée.
		 */
		world.toogle(coordinates);

	}

	/**
	 * Saves the source position of the current translation.
	 */
	public void mousePressed(MouseEvent e) {
		source.setX(e.getX());
		source.setY(e.getY());
	}

	/**
	 * Saves the final position of the current translation.
	 */
	public void mouseReleased(MouseEvent e) {

		saved.setX(translation.getX());
		saved.setY(translation.getY());

		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

	}

	/**
	 * Changes the value of the current translation when
	 * the mouse dragged.
	 */
	public void mouseDragged(MouseEvent e) {

		translation.setX(e.getX() - source.getX() + saved.getX());
		translation.setY(e.getY() - source.getY() + saved.getY());
		
		this.setCursor(new Cursor(Cursor.MOVE_CURSOR));
		
	}

	/**
	 * Changes the zoom level when the mouse wheel moved.
	 */
	public void mouseWheelMoved(MouseWheelEvent e) {

		if (e.getWheelRotation() < 0) {
			context.zoom();
		}

		else {
			context.unzoom();
		}

	}


	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseMoved(MouseEvent e) {
	}


	/**
	 * Defines the world.
	 */
	public void setWorld(World world) {
		this.world = world;
	}

}
