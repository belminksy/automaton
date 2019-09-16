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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JToolBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import automaton.config.Conf;
import automaton.io.Resource;

/**
 * Main window of the application.
 *
 * @author LHashDe
 */
public class Window extends JFrame {

	/**
	 * The play / pause button.
	 */
	protected JButton playButton = new JButton("Play");

	/**
	 * The canvas components for rendering.
	 */
	protected Canvas canvas = new Canvas();


	/**
	 * Initializes the window and his components.
	 */
	public void start() {

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

		JToolBar toolBar = new JToolBar();
		JSlider slider = new JSlider();
		JLabel speedLabel = new JLabel("Vitesse = " + Conf.ENGINE_TPS);
		MenuBar menuBar = new MenuBar();


		this.setTitle(Conf.WINDOW_TITLE);

		this.setIconImage(Resource.getImage("/res/texture/icon.png"));
		this.setSize(Conf.WINDOW_WIDTH, Conf.WINDOW_HEIGHT);

		/* Center the window on the screen. */
		this.setLocation(
			dimension.width / 2 - Conf.WINDOW_WIDTH / 2,
			dimension.height / 2 - Conf.WINDOW_HEIGHT / 2
		);


		slider.setValue((int) Conf.ENGINE_TPS * 10);
		slider.setMajorTickSpacing(10);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setMaximum(100);
		slider.setMinimum(10);

		slider.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent e) {

				double tps = ((double) slider.getValue()) / 10;

				Conf.ENGINE_TPS = tps;
				speedLabel.setText("Vitesse = " + tps);

			}

		});


		toolBar.setLayout(new BorderLayout());
		toolBar.add(playButton, BorderLayout.WEST);
		toolBar.add(slider, BorderLayout.CENTER);
		toolBar.add(speedLabel, BorderLayout.EAST);

		canvas.setBackground(Color.WHITE);

		this.setLayout(new BorderLayout());
		this.add(toolBar, BorderLayout.NORTH);
		this.add(canvas, BorderLayout.CENTER);

		this.setJMenuBar(menuBar);

		this.setVisible(true);

	}


	/**
	 * Returns the play / pause button.
	 * 
	 * @return The play / pause button.
	 */
	public JButton getPlayButton() {
		return playButton;
	}

	/**
	 * Returns the canvas component for rendering.
	 * 
	 * @return The canvas component for rendering.
	 * 
	 * @see Canvas
	 */
	public Canvas getCanvas() {
		return canvas;
	}

}
