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

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import automaton.config.Conf;
import automaton.decoder.Decoder;
import automaton.io.Resource;
import automaton.main.Main;

/**
 * About menu displayed in the menu bar.
 *
 * @author LHashDe
 */
public class AboutMenu extends JMenu {

	/**
	 * About menu displayed in the menu bar.
	 */
	public AboutMenu() {

		this.setText("About");


		JMenuItem aboutItem = new JMenuItem("About " + Conf.APP_NAME);


		aboutItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				Main.automaton.world.clear();
				Decoder.decode(Resource.getRes("res/presets/about/about.life"), Main.automaton.world);

			}

		});


		this.add(aboutItem);

	}

}
