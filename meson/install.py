#!/usr/bin/env python3

import sys, os
import zipfile


## Merge resources in the executable JAR

jar = zipfile.ZipFile('../build/src/Automaton.jar', 'a')

namelist = jar.namelist()

print('Merging...')


for directory, subdirs, files in os.walk('../res'):

	# name of the current directory, without the relative ../ link
	dirname = directory[2:]


	# prevent for duplication warning
	if dirname[1:] + os.sep in namelist:
		continue


	## Create a new empty directory in the executable JAR
	jar.write(directory, dirname)


	for file in files:

		# name of the current file
		file = os.path.join(directory, file)

		# name of the current file, without the relative ../ link
		filename = file[2:]


		## Append a resources file in the executable JAR
		jar.write(file, filename)


jar.close()

sys.stdout.write('\033[F\033[K')
print('Success')
