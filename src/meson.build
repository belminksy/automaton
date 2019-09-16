
src = [
	'automaton/config/Conf.java',
	'automaton/decoder/Decoder.java',
	'automaton/grid/Cell.java',
	'automaton/grid/Chunk.java',
	'automaton/grid/World.java',
	'automaton/io/Console.java',
	'automaton/io/Resource.java',
	'automaton/main/Automaton.java',
	'automaton/main/Main.java',
	'automaton/maths/Coordinates.java',
	'automaton/maths/Point.java',
	'automaton/maths/Vector.java',
	'automaton/render/RenderingContext.java',
	'automaton/ui/menu/AboutMenu.java',
	'automaton/ui/menu/EditMenu.java',
	'automaton/ui/menu/FileMenu.java',
	'automaton/ui/menu/ImportMenu.java',
	'automaton/ui/widget/Canvas.java',
	'automaton/ui/widget/MenuBar.java',
	'automaton/ui/widget/Window.java'
]

tests_src = [
	'automaton/tests/Failure.java',
	'automaton/tests/MainTest.java',
	'automaton/tests/Printer.java',
	'automaton/tests/TestCase.java',
	'automaton/tests/TestSuite.java',
	'automaton/tests/grid/CellTest.java',
	'automaton/tests/grid/ChunkTest.java',
	'automaton/tests/grid/EvolutionTest.java',
	'automaton/tests/grid/WorldTest.java',
	'automaton/tests/maths/CoordinatesTest.java',
	'automaton/tests/maths/PointTest.java'
]


if not get_option('unittest')

	jar(
		meson.project_name(),
		src,
		main_class: 'automaton.main.Main'
	)

else

	jar(
		meson.project_name() + '-test',
		src + tests_src,
		main_class: 'automaton.tests.MainTest'
	)

endif
