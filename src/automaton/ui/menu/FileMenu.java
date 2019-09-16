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

package automaton.ui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import automaton.decoder.Decoder;
import automaton.io.Console;
import automaton.main.Main;

/**
 * File menu displayed in the menu bar.
 *
 * @author LHashDe
 */
public class FileMenu extends JMenu {

	/**
	 * File menu displayed in the menu bar.
	 */
	public FileMenu() {

		this.setText("File");


		JMenuItem openItem = new JMenuItem("Open");
		JMenuItem saveItem = new JMenuItem("Save as");
		JMenuItem exitItem = new JMenuItem("Exit");


		openItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				JFileChooser fileChooser = new JFileChooser(System.getProperty("user.home"));

				fileChooser.setFileFilter(new FileNameExtensionFilter("life", "life"));

				if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

					File file = fileChooser.getSelectedFile();

					Main.automaton.world.clear();
					Decoder.decode(file, Main.automaton.world);

				}

			}

		});

		saveItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				JFileChooser fileChooser = new JFileChooser(System.getProperty("user.home"));

				fileChooser.setFileFilter(new FileNameExtensionFilter("life", "life"));

				if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {

					File file = fileChooser.getSelectedFile();

					if (!file.getName().toLowerCase().endsWith(".life")) {
						file = new File(file.getAbsolutePath() + ".life");
					}

					if (!file.exists()) {

						try {

							file.createNewFile();

						} catch (Exception exception) {
							Console.err(exception, this);
							return;
						}

					}

					Decoder.encode(Main.automaton.world, file);

				}

			}

		});

		exitItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				Main.automaton.stop();

			}

		});


		this.add(openItem);
		this.add(saveItem);
		this.add(exitItem);

	}

}
