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

package automaton.main;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.UIManager;

import automaton.config.Conf;
import automaton.decoder.Decoder;
import automaton.grid.World;
import automaton.io.Console;
import automaton.io.Resource;
import automaton.ui.widget.Window;

/**
 * @author Adrien Belminksy
 * @author LHashDe
 * @author Mateo M.
 */
public class Automaton {

	protected boolean running = false;
	protected boolean playing = false;

	public World world = new World();
	protected Window window = new Window();

	/**
	 * The AWT toolkit, used to force display buffer
	 * to flush on Linux.
	 * 
	 * @see #render()
	 */
	protected Toolkit toolkit = Toolkit.getDefaultToolkit();


	/**
	 * <p>
	 * Initialization phase of the application life cycle.
	 * </p>
	 * 
	 * <p>
	 * The next phase is start.
	 * </p>
	 * 
	 * @see #start()
	 */
	public void init() {

		Console.write("Initializing", this);

		try {

			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		} catch (Exception e) {
			Console.err(e, this);
		}

		window.start();

		window.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				stop();
			}

		});

		window.getPlayButton().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				toggle();

				if (playing) {
					window.getPlayButton().setText("Pause");
				} else {
					window.getPlayButton().setText("Play");
				}

			}

		});

		window.getCanvas().setWorld(world);


		Decoder.decode(Resource.getRes("res/presets/guns/glider_gun.life"), world);

	}

	/**
	 * <p>
	 * Start phase of the application life cycle.
	 * </p>
	 * 
	 * <p>
	 * The next phase is loop.
	 * </p>
	 * 
	 * @see #loop()
	 */
	public void start() {

		Console.write("Started", this);

		running = true;
		playing = false;

	}

	/**
	 * <p>
	 * Play phase of the application life cycle.
	 * </p>
	 * 
	 * @see #pause()
	 * @see #toggle()
	 */
	public void play() {
		playing = true;
	}

	/**
	 * <p>
	 * Pause phase of the application life cycle.
	 * </p>
	 * 
	 * @see #play()
	 * @see #toggle()
	 */
	public void pause() {
		playing = false;
	}

	/**
	 * <p>
	 * Play the application if paused, or conversely. 
	 * </p>
	 * 
	 * @see #play()
	 * @see #pause()
	 */
	public void toggle() {
		playing = !playing;
	}

	/**
	 * <p>
	 * Stop phase of the application life cycle.
	 * </p>
	 * 
	 * <p>
	 * The next phase is exit
	 * </p>
	 * 
	 * @see #exit()
	 */
	public void stop() {
		running = false;
		playing = false;

		window.dispose();
	}

	/**
	 * Exit phase of the application life cycle.
	 */
	public void exit() {

		Console.write("Good bye", this);

	}


	/**
	 * <p>
	 * Loop phase of the application life cycle.
	 * </p>
	 * 
	 * <p>
	 * Calls at regular interval the update and the render
	 * method. The time between interval is defined in
	 * the configuration by ENGINE_TPS and ENGINE_FPS.
	 * </p>
	 */
	public void loop() {

		long lastTick = System.nanoTime();
		long lastFrame = System.nanoTime();

		double tickTime = 1000000000.0 / Conf.ENGINE_TPS;
		double frameTime = 1000000000.0 / Conf.ENGINE_FPS;

		while (running) {

			if (System.nanoTime() - lastTick > tickTime) {

				update();

				lastTick += tickTime;
				tickTime = 1000000000.0 / Conf.ENGINE_TPS;
			}

			else if (System.nanoTime() - lastFrame > frameTime) {

				render();

				lastFrame += frameTime;
			}

			else {
				try {
					Thread.sleep(1);
				} catch (Exception e) {
					Console.err(e, this);
				}
			}


		}

		exit();

	}


	/**
	 * <p>
	 * Update phase of the application life cycle.
	 * </p>
	 * 
	 * <p>
	 * Updates the localization of the cells.
	 * </p>
	 * 
	 * @see #loop()
	 * @see #render()
	 */
	public void update() {

		if (!playing) {
			return;
		}

		world.update();

	}

	/**
	 * <p>
	 * Render phase of the application life cycle.
	 * </p>
	 * 
	 * <p>
	 * Displays cells on a canvas.
	 * </p>
	 * 
	 * @see #loop()
	 * @see #update()
	 */
	public void render() {

		window.getCanvas().repaint();

		// Needed on Linux to force display buffer to flush.
		toolkit.sync();

	}

}
