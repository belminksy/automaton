Automaton
===

Automaton is an open source implementation of the Conway's Game of Life, a cellular automaton Turing completeness.

This project was originally released in May 2019 as the result of the computer science exam (Informatique et Science du Numérique) of the Baccalauréat Français (equivalent to high school diploma) Série scientifique (scientific stream). I received the grade of 19/20.

/!\ This project is not longer maintained!

![Automaton animation](data/animation.gif?raw=true)


Building
---

You'll need the following dependencies:

- Java
- meson
- python3

Run `meson build` to configure the build environment. Change to the build directory and run `ninja install` to build.

```bash
meson build
cd build
ninja install
```

To execute, run the following command in the project directory:

```bash
java -jar ./build/src/Automaton.jar
```

### Testing

To prepare automated tests, in the build directory, set the _unittest_ option to _true_ and then run `ninja install`.

```bash
meson configure -Dunittest=true
ninja install
```

To execute automated tests, run the following command in the project directory:

```bash
java -jar ./build/src/Automaton-test.jar
```


Documentation
---

### Application lifecycle

The application lifeycle is composed of a set of methods, each representing a state of the program execution. All of theses methods are defined in the Automaton class.

![Application lifecycle](/data/lifecycle.png?raw=true)

> Visual representation of the lifecycle.


### Grid hierarchy

The grid system consists of three different components designed as a hierarchy, the World, the Chunk and the Cell.

![Grid hierarchy](/data/tree.png?raw=true)

> Visual representation of the grid hierarchy.

The World element represents the entire grid of the grid system. There is only one instance of the World in the application, it must be seen as the root element of the grid hierarchy.

The Chunk element represents a fragment of the grid containing some cells. A Chunk is a square of 16x16 cells.

The Cell element represents the smallest unity in the grid system (a cell itself obviously).


Contributors
---

- Adrien Belminksy
- [LHashDe](https://github.com/LHashDe)
- Mateo M.
