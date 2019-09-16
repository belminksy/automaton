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

package automaton.decoder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;

import automaton.grid.Cell;
import automaton.grid.Chunk;
import automaton.grid.World;
import automaton.io.Console;
import automaton.maths.Coordinates;
import automaton.maths.Point;

/**
 * Decode and encode the world in life format.
 *
 * @author Adrien Belminksy
 * @author Mateo M.
 */
public class Decoder {

	/**
	 * Decode from a file.
	 */
	public static void decode(File file, World world) {

		try {

			/*
			 * Un scanner est crée à partir d'un fichier,
			 * puis on appelle la fonction decode appropriée.
			 */

			decode(new Scanner(file), world);

		} catch (FileNotFoundException e) {

			/*
			 * Si une exception se déclenche, elle est attrapée
			 * et un message est envoyé dans la console.
			 */
			Console.err(e, Decoder.class);
		}

	}

	/**
	 * Decode from an input stream.
	 */
	public static void decode(InputStream stream, World world) {

		/*
		 * Un scanner est crée à partir d'un InputStream,
		 * puis on appelle la fonction decode appropriée.
		 */

		decode(new Scanner(stream), world);
	}

	/**
	 * Decode from a scanner.
	 */
	public static void decode(Scanner scanner, World world) {

		/*
		 * Le scanner permet de lire le contenu
		 * du fichier du preset sélectionné.
		 */

		try {

			/*
			 * Cette boucle while s'exécute tant qu'il
			 * y a une ligne suivante dans le fichier.
			 */
			while (scanner.hasNextLine()) {

				/* Cette variable enregistre le contenu de la ligne suivante. */
				String line = scanner.nextLine();

				/*
				 * Cette variable est un tableau qui contient la ligne
				 * précédemment récupérée mais coupée en deux au
				 * niveau de la virgule.
				 * 
				 * Par exemple, si line vaut "5,6"
				 * alors split sera égale à {"5", "6"}
				 */
				String[] split = line.split(",");

				/*
				 * Les deux variables x et y vont prendre respectivement les valeurs
				 * avant et après la virgule, mais la chaine de caractère (string)
				 * est convertit en nombre entier (int).
				 * 
				 * De plus la fonction trim permet de supprimer les espaces
				 * autour des valeurs de sorte à ne garder que les valeurs elles mêmes.
				 */
				int x = Integer.parseInt(split[0].trim());
				int y = Integer.parseInt(split[1].trim());

				/*
				 * Finalement une nouvelle cellule est créee aux
				 * coordonnées récupérées avant.
				 */
				world.active(new Coordinates(x, y));

			}

			/* Une fois le Scanner utilisé, il est fermé. */
			scanner.close();

		} catch (NumberFormatException e) {

			/*
			 * Si une exception se déclenche, elle est attrapée
			 * et un message est envoyé dans la console.
			 */
			Console.err(e, Decoder.class);
		}

	}


	/**
	 * Encode the world to a file.
	 */
	public static void encode(World world, File file) {

		try {

			/*
			 * Cette variable contient tous les chunks existants
			 * dans le monde.
			 */
			Iterator<Entry<Point, Chunk>> chunks = world.getChunks();

			/* L'objet FileWriter permet d'écrire dans un fichier. */
			FileWriter writer = new FileWriter(file.getAbsolutePath());

			/*
			 * Cette boucle s'exécute autant de fois
			 * qu'il y a de chunk.
			 */
			while (chunks.hasNext()) {

				/*
				 * Cette variable contient le chunk sur
				 * lequel on travail dans ce tour de boucle.
				 */
				Chunk chunk = chunks.next().getValue();

				/*
				 * Cette variable contient toutes les cellules existantes
				 * dans le chunk sur lequel on travail.
				 */
				Iterator<Entry<Point, Cell>> cells = chunk.getCells();

				/*
				 * Cette boucle s'exécute autant de fois qu'il y
				 * a de cellule dans le chunk sur lequel on travail.
				 */
				while (cells.hasNext()) {

					/*
					 * Cette variable contient la cellule sur laquelle
					 * on travail dans ce tour de boucle.
					 */
					Cell cell = cells.next().getValue();

					/*
					 * Les coordonnées de la cellule ne sont écrites
					 * dans le fichier que si la cellule est vivante.
					 */
					if (cell.isAlive()) {

						/*
						 * Les coordonnées de la cellule sont écrites
						 * dans le fichier et on rajoute un saut de
						 * ligne avec \n.
						 */
						writer.write(cell.getCoordinates().toAbsolutePoint().toString() + "\n");

					}

				}

			}

			/* Une fois le FileWriter utilisé, il est fermé. */
			writer.close();

		} catch (IOException e) {

			/*
			 * Si une exception se déclenche, elle est attrappée
			 * et un message est envoyé dans la console.
			 */
			Console.err(e, Decoder.class);
		}

	}

}
