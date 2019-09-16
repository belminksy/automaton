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

import automaton.decoder.Decoder;
import automaton.io.Console;
import automaton.io.Resource;
import automaton.main.Main;

/**
 * Import menu displayed in the menu bar.
 *
 * @author LHashDe
 */
public class ImportMenu extends JMenu {

	/**
	 * Import menu displayed in the menu bar.
	 */
	public ImportMenu() {

		this.setText("Import");


		JMenu stillLifesMenu = new JMenu("Still Lifes");
		JMenu oscillatorsMenu = new JMenu("Oscillators");
		JMenu spaceshipsMenu = new JMenu("Spaceships");
		JMenu gunsMenu = new JMenu("Guns");
		JMenu puffersMenu = new JMenu("Puffers");
		JMenu methuselahsMenu = new JMenu("Methuselahs");
		JMenu logicMenu = new JMenu("Logic Gates");


		// stable

		JMenuItem stillLifes_blockItem = new JMenuItem("Block");
		JMenuItem stillLifes_beehiveItem = new JMenuItem("Bee hive");
		JMenuItem stillLifes_loafItem = new JMenuItem("Loaf");
		JMenuItem stillLifes_boatItem = new JMenuItem("Boat");
		JMenuItem stillLifes_tubItem = new JMenuItem("Tub");
		JMenuItem stillLifes_cthulhuItem = new JMenuItem("Cthulhu");


		stillLifes_blockItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openPreset("res/presets/still_lifes/block.life");
			}
		});

		stillLifes_beehiveItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openPreset("res/presets/still_lifes/bee_hive.life");
			}
		});

		stillLifes_loafItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openPreset("res/presets/still_lifes/loaf.life");
			}
		});

		stillLifes_boatItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openPreset("res/presets/still_lifes/boat.life");
			}
		});

		stillLifes_tubItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openPreset("res/presets/still_lifes/tub.life");
			}
		});

		stillLifes_cthulhuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openPreset("res/presets/still_lifes/cthulhu.life");
			}
		});


		stillLifesMenu.add(stillLifes_blockItem);
		stillLifesMenu.add(stillLifes_beehiveItem);
		stillLifesMenu.add(stillLifes_loafItem);
		stillLifesMenu.add(stillLifes_boatItem);
		stillLifesMenu.add(stillLifes_tubItem);
		stillLifesMenu.add(stillLifes_cthulhuItem);


		// oscillator

		JMenuItem oscillators_blinkerItem = new JMenuItem("Blinker");
		JMenuItem oscillators_toadItem = new JMenuItem("Toad");
		JMenuItem oscillators_beaconItem = new JMenuItem("Beacon");
		JMenuItem oscillators_pulsarItem = new JMenuItem("Pulsar");
		JMenuItem oscillators_pentadecathlonItem = new JMenuItem("Penta decathlon");


		oscillators_blinkerItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openPreset("res/presets/oscillators/blinker.life");
			}
		});

		oscillators_toadItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openPreset("res/presets/oscillators/toad.life");
			}
		});

		oscillators_beaconItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openPreset("res/presets/oscillators/beacon.life");
			}
		});

		oscillators_pulsarItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openPreset("res/presets/oscillators/pulsar.life");
			}
		});

		oscillators_pentadecathlonItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openPreset("res/presets/oscillators/penta_decathlon.life");
			}
		});


		oscillatorsMenu.add(oscillators_blinkerItem);
		oscillatorsMenu.add(oscillators_toadItem);
		oscillatorsMenu.add(oscillators_beaconItem);
		oscillatorsMenu.add(oscillators_pulsarItem);
		oscillatorsMenu.add(oscillators_pentadecathlonItem);


		// spaceship

		JMenuItem spaceships_gliderItem = new JMenuItem("Glider");
		JMenuItem spaceships_lwssItem = new JMenuItem("Light Weight Spaceship");
		JMenuItem spaceships_mwssItem = new JMenuItem("Middle Weight Spaceship");
		JMenuItem spaceships_hwssItem = new JMenuItem("Heavy Weight Spaceship");
		JMenuItem spaceships_canadaGooseItem = new JMenuItem("Canada Goose");


		spaceships_gliderItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openPreset("res/presets/spaceships/glider.life");
			}
		});

		spaceships_lwssItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openPreset("res/presets/spaceships/lwss.life");
			}
		});

		spaceships_mwssItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openPreset("res/presets/spaceships/mwss.life");
			}
		});

		spaceships_hwssItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openPreset("res/presets/spaceships/hwss.life");
			}
		});

		spaceships_canadaGooseItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openPreset("res/presets/spaceships/canada_goose.life");
			}
		});


		spaceshipsMenu.add(spaceships_gliderItem);
		spaceshipsMenu.add(spaceships_lwssItem);
		spaceshipsMenu.add(spaceships_mwssItem);
		spaceshipsMenu.add(spaceships_hwssItem);
		spaceshipsMenu.add(spaceships_canadaGooseItem);


		// canon

		JMenuItem guns_gliderGunItem = new JMenuItem("Glider Gun");


		guns_gliderGunItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openPreset("res/presets/guns/glider_gun.life");
			}
		});


		gunsMenu.add(guns_gliderGunItem);


		// puffers

		JMenuItem puffers_puffer1Item = new JMenuItem("Puffer 1");


		puffers_puffer1Item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openPreset("res/presets/puffers/puffer_1.life");
			}
		});


		puffersMenu.add(puffers_puffer1Item);


		// methuselah

		JMenuItem methuselahs_rpentominoItem = new JMenuItem("R-pentomino");
		JMenuItem methuselahs_dieHardItem = new JMenuItem("Die Hard");
		JMenuItem methuselahs_bunniesItem = new JMenuItem("Bunnies");
		JMenuItem methuselahs_acornItem = new JMenuItem("Acorn");


		methuselahs_rpentominoItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openPreset("res/presets/methuselahs/r-pentomino.life");
			}
		});

		methuselahs_dieHardItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openPreset("res/presets/methuselahs/die_hard.life");
			}
		});

		methuselahs_bunniesItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openPreset("res/presets/methuselahs/bunnies.life");
			}
		});

		methuselahs_acornItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openPreset("res/presets/methuselahs/acorn.life");
			}
		});


		methuselahsMenu.add(methuselahs_rpentominoItem);
		methuselahsMenu.add(methuselahs_dieHardItem);
		methuselahsMenu.add(methuselahs_bunniesItem);
		methuselahsMenu.add(methuselahs_acornItem);


		// logic gates

		JMenuItem logic_not1 = new JMenuItem("NOT( 1 ) = 0");
		JMenuItem logic_not0 = new JMenuItem("NOT( 0 ) = 1");


		logic_not1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openPreset("res/presets/logic/not1.life");
			}
		});

		logic_not0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openPreset("res/presets/logic/not0.life");
			}
		});


		logicMenu.add(logic_not1);
		logicMenu.add(logic_not0);


		this.add(stillLifesMenu);
		this.add(oscillatorsMenu);
		this.add(spaceshipsMenu);
		this.add(gunsMenu);
		this.add(puffersMenu);
		this.add(methuselahsMenu);
		this.add(logicMenu);

	}


	/**
	 * Open a preset with his path.
	 */
	public void openPreset(String path) {

		Main.automaton.world.clear();

		try {

			Decoder.decode(Resource.getRes(path), Main.automaton.world);

		} catch (Exception e) {
			Console.err("Failed to decode the preset " + path, this);
		}

	}

}
