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

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import automaton.config.Conf;
import automaton.main.Main;

/**
 * Edit menu displayed in the menu bar.
 *
 * @author LHashDe
 */
public class EditMenu extends JMenu {

	/**
	 * Edit menu displayed in the menu bar.
	 */
	public EditMenu() {

		this.setText("Edit");


		JMenuItem playItem  = new JMenuItem("Play/Pause");
		JMenuItem clearItem = new JMenuItem("Clear");

		JCheckBoxMenuItem editingItem = new JCheckBoxMenuItem("Active Editing");
		JCheckBoxMenuItem coloredItem = new JCheckBoxMenuItem("Active Colored Cells");
		JCheckBoxMenuItem borderItem  = new JCheckBoxMenuItem("Show Chunk Borders");


		playItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Main.automaton.toggle();
			}

		});

		clearItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Main.automaton.world.clear();
				Main.automaton.pause();
			}

		});


		editingItem.setState(Conf.CANVAS_ACTIVE_EDITING);
		editingItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Conf.CANVAS_ACTIVE_EDITING = !Conf.CANVAS_ACTIVE_EDITING;
			}

		});

		coloredItem.setState(Conf.CANVAS_ACTIVE_COLORED_CELLS);
		coloredItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Conf.CANVAS_ACTIVE_COLORED_CELLS = !Conf.CANVAS_ACTIVE_COLORED_CELLS;
			}

		});

		borderItem.setState(Conf.CANVAS_ACTIVE_CHUNK_BORDERS);
		borderItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Conf.CANVAS_ACTIVE_CHUNK_BORDERS = !Conf.CANVAS_ACTIVE_CHUNK_BORDERS;
			}

		});


		this.add(playItem);
		this.add(clearItem);
		this.addSeparator();
		this.add(editingItem);
		this.add(coloredItem);
		this.add(borderItem);

	}

}
